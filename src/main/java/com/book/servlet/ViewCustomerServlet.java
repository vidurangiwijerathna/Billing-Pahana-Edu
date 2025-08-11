package com.book.servlet;

import com.book.model.Customer;
import com.book.service.CustomerService;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/viewCustomer")
public class ViewCustomerServlet extends HttpServlet {

    private CustomerService customerService = new CustomerService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Customer> customers = customerService.viewAllCustomers();

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        // Using Gson to serialize list to JSON
        String json = new com.google.gson.Gson().toJson(customers);
        resp.getWriter().write(json);
    }
}

