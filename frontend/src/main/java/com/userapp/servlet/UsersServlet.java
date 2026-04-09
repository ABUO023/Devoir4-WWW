package com.userapp.servlet;

import com.userapp.util.ApiClient;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        // Check if user is logged in
        if (req.getSession().getAttribute("email") == null) {
            resp.sendRedirect("/login");
            return;
        }
        
        // Get users from backend API
        String response = ApiClient.get("/users");
        
        try {
            Gson gson = new Gson();
            JsonArray jsonArray = gson.fromJson(response, JsonArray.class);
            List<Map> users = new ArrayList<>();
            
            // Convert JsonArray to List of Maps for JSTL compatibility
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject userObj = jsonArray.get(i).getAsJsonObject();
                Map<String, Object> userMap = new java.util.HashMap<>();
                userMap.put("id", userObj.get("id").getAsInt());
                userMap.put("name", userObj.get("name").getAsString());
                userMap.put("email", userObj.get("email").getAsString());
                users.add(userMap);
            }
            
            req.setAttribute("users", users);
        } catch (Exception e) {
            req.setAttribute("error", "Error fetching users: " + e.getMessage());
        }
        
        req.getRequestDispatcher("/WEB-INF/users.jsp").forward(req, resp);
    }
}
