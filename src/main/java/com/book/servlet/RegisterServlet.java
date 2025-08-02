package com.book.servlet;

import com.book.dao.UserDAO;
import com.book.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role"); // ADMIN or CASHIER

        try {
            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
            user.setRole(role);

            UserDAO userDAO = new UserDAO();
            userDAO.save(user);

            response.sendRedirect("login.jsp");

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
