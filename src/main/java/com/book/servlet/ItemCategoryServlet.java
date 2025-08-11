package com.book.servlet;

import com.book.dto.ItemCategoryDTO;
import com.book.service.ItemCategoryService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/categories")
public class ItemCategoryServlet extends HttpServlet {

    private final ItemCategoryService categoryService = new ItemCategoryService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String categoryName = request.getParameter("categoryName");

        // Create DTO and pass it to the service
        ItemCategoryDTO dto = new ItemCategoryDTO();
        dto.setCategoryName(categoryName);

        categoryService.addCategory(dto);

        response.sendRedirect("admin-db.jsp?success=true");
    }
}
