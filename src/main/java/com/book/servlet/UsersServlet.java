package com.book.servlet;

import com.book.entity.Users;
import com.book.service.UsersService;
import com.book.service.impl.UsersServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {

    private UsersService usersService = new UsersServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error processing request: " + e.getMessage());
        }
    }

    private void listUsers(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            List<Users> usersList = usersService.getAllUsers();
            request.setAttribute("usersList", usersList);
            request.getRequestDispatcher("/WEB-INF/views/users-list.jsp").forward(request, response);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Failed to list users: " + e.getMessage());
        }
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            request.getRequestDispatcher("/WEB-INF/views/users-form.jsp").forward(request, response);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Failed to show new form: " + e.getMessage());
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Users existingUser = usersService.getUserById(id);
            request.setAttribute("user", existingUser);
            request.getRequestDispatcher("/WEB-INF/views/users-form.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid user ID format.");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Failed to load user for edit: " + e.getMessage());
        }
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            usersService.deleteUser(id);
            response.sendRedirect("users?action=list");
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid user ID format.");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Failed to delete user: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int id = 0;
            String idStr = request.getParameter("id");
            if (idStr != null && !idStr.isEmpty()) {
                id = Integer.parseInt(idStr);
            }

            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String role = request.getParameter("role");

            // Construct Users without createdAt (DB handles it)
            Users user = new Users(id, username, email, password, role);

            if (id == 0) {
                usersService.registerUser(user);
            } else {
                usersService.updateUser(user);
            }

            response.sendRedirect("users?action=list");
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid input: " + e.getMessage());
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Failed to save user: " + e.getMessage());
        }
    }
}
