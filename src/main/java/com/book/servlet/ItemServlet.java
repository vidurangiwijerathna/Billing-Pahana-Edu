package com.book.servlet;

import com.book.dto.ItemDTO;
import com.book.service.ItemService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/items")
public class ItemServlet extends HttpServlet {

    private ItemService itemService = new ItemService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        if ("edit".equals(action)) {
            Long id = Long.parseLong(req.getParameter("id"));
            req.setAttribute("item", itemService.getItem(id));
            req.getRequestDispatcher("item-form.jsp").forward(req, resp);
        } else if ("delete".equals(action)) {
            Long id = Long.parseLong(req.getParameter("id"));
            itemService.deleteItem(id);
            resp.sendRedirect("items");
        } else {
            req.setAttribute("items", itemService.getAllItems());
            req.getRequestDispatcher("item-list.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String idStr = req.getParameter("id");
        String name = req.getParameter("name");
        String category = req.getParameter("category");
        double price = Double.parseDouble(req.getParameter("price"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));

        ItemDTO dto = new ItemDTO();
        if (idStr != null && !idStr.isEmpty()) {
            dto.setId(Long.parseLong(idStr));
        }
        dto.setName(name);
        dto.setCategory(category);
        dto.setPrice(price);
        dto.setQuantity(quantity);

        if (dto.getId() == null) {
            itemService.createItem(dto);
        } else {
            itemService.updateItem(dto);
        }

        resp.sendRedirect("items");
    }
}
