package com.book.servlet;

import com.book.dto.UserDTO;
import com.book.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/users")
public class UserServlet extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Fetch all users as DTOs
        List<UserDTO> users = userService.getAllUsers();

        // Put users list into request scope
        request.setAttribute("users", users);

        // Forward to JSP page to display users
        request.getRequestDispatcher("users-list.jsp").forward(request, response);
    }
}
