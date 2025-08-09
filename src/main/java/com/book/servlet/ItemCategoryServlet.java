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
        List<ItemCategoryDTO> categories = service.getAllCategories();
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("category-list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");

        ItemCategoryDTO dto = new ItemCategoryDTO();
        dto.setName(name);

        boolean added = service.addCategory(dto);

        if (added) {
            request.setAttribute("message", "Category added successfully!");
            request.setAttribute("messageType", "success");
        } else {
            request.setAttribute("message", "Failed to add category.");
            request.setAttribute("messageType", "error");
        }

        List<ItemCategoryDTO> categories = service.getAllCategories();
        request.setAttribute("categories", categories);

        request.getRequestDispatcher("category-list.jsp").forward(request, response);
    }
}
