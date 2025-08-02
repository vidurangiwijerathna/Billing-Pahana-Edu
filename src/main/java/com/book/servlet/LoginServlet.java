package com.book.servlet;

import com.book.dao.UserDAO;
import com.book.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            UserDAO userDAO = new UserDAO();
            User user = userDAO.findByEmail(email);

            if (user != null && user.getPassword().equals(password)) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                session.setAttribute("role", user.getRole());

                if ("ADMIN".equals(user.getRole())) {
                    response.sendRedirect("dashboard-admin.jsp");
                } else {
                    response.sendRedirect("dashboard-cashier.jsp");
                }
            } else {
                request.setAttribute("error", "Invalid email or password");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
