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

    private ItemCategoryService service = new ItemCategoryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("edit".equals(action)) {
            String idStr = request.getParameter("id");
            Long id = idStr != null ? Long.parseLong(idStr) : null;
            if (id != null) {
                ItemCategoryDTO dto = service.getById(id);
                request.setAttribute("category", dto);
            }
            request.getRequestDispatcher("category-form.jsp").forward(request, response);
        } else if ("delete".equals(action)) {
            String idStr = request.getParameter("id");
            Long id = idStr != null ? Long.parseLong(idStr) : null;
            if (id != null) {
                service.deleteCategory(id);
            }
            response.sendRedirect("categories");
        } else {
            List<ItemCategoryDTO> categories = service.getAllCategories();
            request.setAttribute("categories", categories);
            request.getRequestDispatcher("category-list.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        String categoryName = request.getParameter("categoryName");

        ItemCategoryDTO dto = new ItemCategoryDTO();
        dto.setCategoryName(categoryName);

        if (idStr == null || idStr.isEmpty()) {
            // Add new category
            service.addCategory(dto);
        } else {
            // Update existing
            dto.setId(Long.parseLong(idStr));
            service.updateCategory(dto);
        }
        response.sendRedirect("categories");
    }
}
