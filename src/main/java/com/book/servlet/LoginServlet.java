package com.book.servlet;

import com.book.model.User;
import com.book.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = userService.authenticateUser(email, password);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("userName", user.getName());
            session.setAttribute("userRole", user.getRole());

            if ("Admin".equalsIgnoreCase(user.getRole())) {
                response.sendRedirect("admin-db.jsp");
            } else if ("User".equalsIgnoreCase(user.getRole())) {
                response.sendRedirect("user-db.jsp");
            } else {
                response.sendRedirect("login.jsp?error=UnknownRole");
            }
        } else {
            response.sendRedirect("login.jsp?error=InvalidCredentials");
        }
    }
}
