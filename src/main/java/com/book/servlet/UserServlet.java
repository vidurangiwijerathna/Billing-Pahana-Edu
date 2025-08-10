package com.book.servlet;

import com.book.model.User;
import com.book.service.UserService;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/users")
public class UserServlet extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<User> users = userService.viewAllUsers();

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        // Using Gson to serialize list to JSON
        String json = new com.google.gson.Gson().toJson(users);
        resp.getWriter().write(json);
    }
}

