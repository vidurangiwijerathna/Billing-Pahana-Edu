package com.book.servlet;

import com.book.dto.ItemDTO;
import com.book.model.Item;
import com.book.service.ItemService;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/api/viewItems")
public class ViewItemServlet extends HttpServlet {
    private final ItemService itemService = new ItemService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<ItemDTO> dtoList = itemService.getAllItems().stream()
                .map(ItemDTO::new)
                .toList();

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String json = new Gson().toJson(dtoList);
        resp.getWriter().write(json);
    }
}
