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

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role"); // ADMIN or CASHIER

        try {
            Users user = new Users();
            user.setUsername(name);
            user.setEmail(email);
            user.setPassword(password);
            user.setRole(role);

            UsersDAO userDAO = new UsersDAO();
            userDAO.save(user); // <-- must exist!

            response.sendRedirect("login.jsp");

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
