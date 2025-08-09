package com.book.servlet;

import com.book.dao.UsersDAO;
import com.book.entity.Users;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UsersDAO usersDAO = new UsersDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Show login form
        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            request.setAttribute("error", "Email and password are required.");
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
            return;
        }

        try {
            Users user = usersDAO.findByEmail(email);

            if (user != null) {
                // For plain text password check (replace with hashing for real app)
                if (password.equals(user.getPassword())) {
                    // Login success - create session
                    HttpSession session = request.getSession();
                    session.setAttribute("loggedUser", user);
                    response.sendRedirect("dashboard"); // redirect to dashboard or home page
                    return;
                }
            }

            // Login failed
            request.setAttribute("error", "Invalid email or password.");
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
        }
    }
}
