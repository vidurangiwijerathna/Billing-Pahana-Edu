package com.book.servlet;

import com.book.dto.ItemsDTO;
import com.book.entity.Items;
import com.book.service.ItemsService;
import com.book.service.impl.ItemsServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/items")
public class ItemServlet extends HttpServlet {

    private ItemsService itemService = new ItemsServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Items> itemsList = itemService.getAllItems();

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h2>Item List</h2>");
        for (Items item : itemsList) {
            out.println("<p>ID: " + item.getId() + " | Name: " + item.getName() +
                    " | Price: " + item.getPrice() + "</p>");
        }
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String author = request.getParameter("author");
        double price = Double.parseDouble(request.getParameter("price"));
        int stock = Integer.parseInt(request.getParameter("stock"));
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));

        ItemsDTO dto = new ItemsDTO();
        dto.setName(name);
        dto.setAuthor(author);
        dto.setPrice(price);
        dto.setStock(stock);
        dto.setCategoryId(categoryId);

        Items createdItem = itemService.addItem(dto);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<p>Item added: " + createdItem.getName() + "</p>");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        itemService.deleteItem(id);

        response.setContentType("text/plain");
        response.getWriter().write("Item deleted");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        String author = request.getParameter("author");
        double price = Double.parseDouble(request.getParameter("price"));
        int stock = Integer.parseInt(request.getParameter("stock"));
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));

        ItemsDTO dto = new ItemsDTO();
        dto.setName(name);
        dto.setAuthor(author);
        dto.setPrice(price);
        dto.setStock(stock);
        dto.setCategoryId(categoryId);

        Items updatedItem = itemService.updateItem(id, dto);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<p>Item updated: " + updatedItem.getName() + "</p>");
    }
}
