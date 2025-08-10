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
        // action: list (default), add form, edit form, delete
        String action = request.getParameter("action");
        if (action == null || action.isEmpty() || action.equals("list")) {
            List<ItemCategoryDTO> categories = service.getAllCategories();
            request.setAttribute("categories", categories);
            request.getRequestDispatcher("category-list.jsp").forward(request, response);
            return;
        }

        if (action.equals("add")) {
            // show empty form (itemId will be generated on submit)
            request.getRequestDispatcher("category-form.jsp").forward(request, response);
            return;
        }

        if (action.equals("edit")) {
            String itemId = request.getParameter("itemId");
            if (itemId != null) {
                ItemCategoryDTO dto = service.getByItemId(itemId);
                request.setAttribute("category", dto);
            }
            request.getRequestDispatcher("category-form.jsp").forward(request, response);
            return;
        }

        if (action.equals("delete")) {
            String itemId = request.getParameter("itemId");
            boolean deleted = false;
            if (itemId != null) deleted = service.deleteCategory(itemId);
            // set message and reload list
            if (deleted) {
                request.setAttribute("message", "Category deleted successfully.");
                request.setAttribute("messageType", "success");
            } else {
                request.setAttribute("message", "Failed to delete category.");
                request.setAttribute("messageType", "error");
            }
            List<ItemCategoryDTO> categories = service.getAllCategories();
            request.setAttribute("categories", categories);
            request.getRequestDispatcher("category-list.jsp").forward(request, response);
            return;
        }

        // fallback list
        List<ItemCategoryDTO> categories = service.getAllCategories();
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("category-list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Determine if this is Add or Update by presence of itemId hidden field
        String itemId = request.getParameter("itemId");
        String categoryName = request.getParameter("categoryName");

        ItemCategoryDTO dto = new ItemCategoryDTO();
        dto.setCategoryName(categoryName);

        boolean ok;
        if (itemId == null || itemId.trim().isEmpty()) {
            // Add new
            ok = service.addCategory(dto);
            if (ok) {
                request.setAttribute("message", "Category added successfully.");
                request.setAttribute("messageType", "success");
            } else {
                request.setAttribute("message", "Failed to add category.");
                request.setAttribute("messageType", "error");
            }
        } else {
            // Update existing
            dto.setItemId(itemId);
            ok = service.updateCategory(dto);
            if (ok) {
                request.setAttribute("message", "Category updated successfully.");
                request.setAttribute("messageType", "success");
            } else {
                request.setAttribute("message", "Failed to update category.");
                request.setAttribute("messageType", "error");
            }
        }

        // reload list and forward
        List<ItemCategoryDTO> categories = service.getAllCategories();
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("category-list.jsp").forward(request, response);
    }
}
