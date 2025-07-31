package com.book.servlet;

import com.book.dto.ItemCategoryDTO;
import com.book.entity.ItemCategory;
import com.book.service.ItemCategoryService;
import com.book.service.impl.ItemCategoryServiceImpl;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ItemCategoryServlet", urlPatterns = {"/categories", "/categories/add"})
public class ItemCategoryServlet extends HttpServlet {

    private final ItemCategoryService categoryService = new ItemCategoryServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        ItemCategoryDTO dto = new ItemCategoryDTO();
        dto.setName(name);

        try {
            ItemCategory savedCategory = categoryService.addCategory(dto);
            request.setAttribute("category", savedCategory);
            request.getRequestDispatcher("/WEB-INF/views/category-success.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<ItemCategory> categories = categoryService.getAllCategories();
            request.setAttribute("categories", categories);
            request.getRequestDispatcher("/WEB-INF/views/category-list.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
