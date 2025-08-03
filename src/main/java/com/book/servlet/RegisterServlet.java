package com.book.servlet;

import com.book.dao.UsersDAO;
import com.book.entity.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");  // Make sure your form uses "username"
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role"); // ADMIN or CASHIER

        try {
            Users user = new Users();
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(password);
            user.setRole(role);

            UsersDAO userDAO = new UsersDAO();
            boolean created = userDAO.save(user);

            if (created) {
                response.sendRedirect("login.jsp");  // Success: redirect to login page
            } else {
                request.setAttribute("error", "Failed to register user.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
