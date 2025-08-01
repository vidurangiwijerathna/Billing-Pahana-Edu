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
        try {
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
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error retrieving items: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
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
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid number format: " + e.getMessage());
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error adding item: " + e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            itemService.deleteItem(id);

            response.setContentType("text/plain");
            response.getWriter().write("Item deleted");
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid item ID format.");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error deleting item: " + e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
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
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid input: " + e.getMessage());
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error updating item: " + e.getMessage());
        }
    }
}
