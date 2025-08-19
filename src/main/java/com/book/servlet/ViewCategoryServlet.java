package com.book.servlet;

import com.book.model.ItemCategory;
import com.book.service.ItemCategoryService;
import com.book.service.ItemCategoryService;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/api/viewCategories")
public class ViewCategoryServlet extends HttpServlet {

    private final ItemCategoryService categoryService = new ItemCategoryService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<ItemCategory> categories = categoryService.getAllCategories();

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String json = new Gson().toJson(categories);
        resp.getWriter().write(json);
    }
}
