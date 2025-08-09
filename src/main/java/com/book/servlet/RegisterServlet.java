package com.book.servlet;

import com.book.dao.UsersDAO;
import com.book.entity.Users;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        try {
            Users user = new Users();
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(password);
            user.setRole(role);

            UsersDAO usersDAO = new UsersDAO();
            boolean created = usersDAO.save(user);

            if (created) {
                request.setAttribute("success", "Registration successful! Please login.");
            } else {
                request.setAttribute("error", "Failed to register user.");
            }

            request.getRequestDispatcher("/register.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error: " + e.getMessage());
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
    }
}
