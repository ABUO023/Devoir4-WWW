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

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/register.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        
        // Create JSON request
        Gson gson = new Gson();
        JsonObject registerRequest = new JsonObject();
        registerRequest.addProperty("name", name);
        registerRequest.addProperty("email", email);
        registerRequest.addProperty("password", password);
        
        // Call backend API
        String response = ApiClient.post("/register", registerRequest.toString());
        
        // Parse response
        try {
            JsonObject jsonResponse = gson.fromJson(response, JsonObject.class);
            if (jsonResponse.get("success").getAsBoolean()) {
                req.setAttribute("message", "Registration successful! Please log in.");
                req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
            } else {
                req.setAttribute("error", jsonResponse.get("message").getAsString());
                req.getRequestDispatcher("/WEB-INF/register.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            req.setAttribute("error", "Error registering user: " + e.getMessage());
            req.getRequestDispatcher("/WEB-INF/register.jsp").forward(req, resp);
        }
    }
}
