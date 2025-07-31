package com.book.servlet;

import com.book.entity.Users;
import com.book.service.UsersService;
import com.book.service.impl.UsersServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {

    private UsersService usersService = new UsersServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "list";

        try {
            switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deleteUser(request, response);
                    break;
                case "list":
                default:
                    listUsers(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void listUsers(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Users> usersList = usersService.getAllUsers();
        request.setAttribute("usersList", usersList);
        request.getRequestDispatcher("/WEB-INF/views/users-list.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/users-form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Users existingUser = usersService.getUserById(id);
        request.setAttribute("user", existingUser);
        request.getRequestDispatcher("/WEB-INF/views/users-form.jsp").forward(request, response);
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        usersService.deleteUser(id);
        response.sendRedirect("users?action=list");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Collect user data from form
        int id = 0;
        String idStr = request.getParameter("id");
        if (idStr != null && !idStr.isEmpty()) {
            id = Integer.parseInt(idStr);
        }
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        Users user = new Users(id, username, email, password, role);

        try {
            if (id == 0) {
                usersService.registerUser(user);
            } else {
                usersService.updateUser(user);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }

        response.sendRedirect("users?action=list");
    }
}
