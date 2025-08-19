package com.book.servlet;

import com.book.model.Customer;
import com.book.service.CustomerService;
import com.google.gson.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet("/api/viewCustomers")
public class ViewCustomerServlet extends HttpServlet {

    private final CustomerService customerService = new CustomerService();
    private final Gson gson;

    public ViewCustomerServlet() {
        // Serialize LocalDateTime as ISO-8601 strings like "2025-08-13T12:34:56"
        gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class,
                        (JsonSerializer<LocalDateTime>) (src, typeOfSrc, context) ->
                                new JsonPrimitive(src.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)))
                .create();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Customer> customers = customerService.getAllCustomers();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(gson.toJson(customers));
    }
}
