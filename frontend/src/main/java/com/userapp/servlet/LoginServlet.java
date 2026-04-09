package com.userapp.servlet;

import com.userapp.util.ApiClient;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        
        // Create JSON request
        Gson gson = new Gson();
        JsonObject loginRequest = new JsonObject();
        loginRequest.addProperty("email", email);
        loginRequest.addProperty("password", password);
        
        // Call backend API
        String response = ApiClient.post("/login", loginRequest.toString());
        
        // Parse response
        try {
            JsonObject jsonResponse = gson.fromJson(response, JsonObject.class);
            if (jsonResponse.get("success").getAsBoolean()) {
                // Store user info in session
                req.getSession().setAttribute("user", jsonResponse.get("user").toString());
                req.getSession().setAttribute("email", email);
                resp.sendRedirect("/users");
            } else {
                req.setAttribute("error", jsonResponse.get("message").getAsString());
                req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            req.setAttribute("error", "Error logging in: " + e.getMessage());
            req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
        }
    }
}
