package com.book.servlet;

import com.book.dto.ItemDTO;
import com.book.service.ItemService;
import com.book.service.ItemCategoryService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/items")
public class ItemServlet extends HttpServlet {

    private final ItemService itemService = new ItemService();
    private final ItemCategoryService categoryService = new ItemCategoryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "new":
                request.setAttribute("categories", categoryService.getAllCategories());
                request.getRequestDispatcher("item-form.jsp").forward(request, response);
                break;

            case "edit":
                Long editId = Long.valueOf(request.getParameter("id"));
                request.setAttribute("item", itemService.getItemById(editId));
                request.setAttribute("categories", categoryService.getAllCategories());
                request.getRequestDispatcher("item-form.jsp").forward(request, response);
                break;

            case "delete":
                Long deleteId = Long.valueOf(request.getParameter("id"));
                itemService.deleteItem(deleteId);
                response.sendRedirect("items");
                break;

            default: // list
                request.setAttribute("items", itemService.getAllItems());
                request.getRequestDispatcher("items-list.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String idStr = request.getParameter("id");
        String name = request.getParameter("name");
        String author = request.getParameter("author");
        double price = Double.parseDouble(request.getParameter("price"));
        int stock = Integer.parseInt(request.getParameter("stock"));
        Long categoryId = Long.valueOf(request.getParameter("categoryId"));

        ItemDTO dto = new ItemDTO();
        if (idStr != null && !idStr.isEmpty()) {
            dto.setId(Long.valueOf(idStr));
        }
        dto.setName(name);
        dto.setAuthor(author);
        dto.setPrice(price);
        dto.setStock(stock);
        dto.setCategoryId(categoryId);

        if (dto.getId() == null) {
            itemService.addItem(dto);
        } else {
            itemService.updateItem(dto);
        }

        response.sendRedirect("items");
    }
}
