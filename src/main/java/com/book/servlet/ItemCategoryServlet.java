package com.book.servlet;

import com.book.dto.ItemCategoryDTO;
import com.book.service.ItemCategoryService;
import com.book.service.impl.ItemCategoryServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/item-categories/*")
public class ItemCategoryServlet extends HttpServlet {

    private ItemCategoryService categoryService;

    @Override
    public void init() throws ServletException {
        categoryService = new ItemCategoryServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            try {
                List<ItemCategoryDTO> categories = categoryService.getAllCategories();
                request.setAttribute("categories", categories);
                request.getRequestDispatcher("/views/item-category-list.jsp").forward(request, response);
            } catch (SQLException e) {
                throw new ServletException("Database error while fetching categories", e);
            }
        } else if (pathInfo.equals("/new")) {
            request.getRequestDispatcher("/views/item-category-form.jsp").forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        if (pathInfo != null && pathInfo.equals("/add")) {
            String name = request.getParameter("name");

            ItemCategoryDTO category = new ItemCategoryDTO();
            category.setName(name);

            try {
                categoryService.addCategory(category);
                response.sendRedirect(request.getContextPath() + "/item-categories");
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("error", "Failed to add category: " + e.getMessage());
                request.getRequestDispatcher("/views/item-category-form.jsp").forward(request, response);
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
