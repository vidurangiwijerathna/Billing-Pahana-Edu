package com.book.servlet;

import com.book.dto.ItemCategoryDTO;
import com.book.entity.ItemCategory;
import com.book.service.ItemCategoryService;
import com.book.service.impl.ItemCategoryServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ItemCategoryServlet", urlPatterns = {"/api/v2/categories/*"})
public class ItemCategoryServlet extends HttpServlet {

    private final ItemCategoryService categoryService = new ItemCategoryServiceImpl();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo(); // e.g. "/itemcategory"
        if (path != null && path.equals("/itemcategory")) {
            BufferedReader reader = req.getReader();
            ItemCategoryDTO dto = objectMapper.readValue(reader, ItemCategoryDTO.class);

            ItemCategory created = categoryService.addCategory(dto);
            String json = objectMapper.writeValueAsString(created);

            resp.setContentType("application/json");
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write(json);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo(); // e.g. "/getitemcategory"
        if (path != null && path.equals("/getitemcategory")) {
            List<ItemCategory> categoryList = categoryService.getAllCategories();
            String json = objectMapper.writeValueAsString(categoryList);

            resp.setContentType("application/json");
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write(json);
        }
    }
}
