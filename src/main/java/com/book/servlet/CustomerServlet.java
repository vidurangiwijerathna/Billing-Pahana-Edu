package com.book.servlet;

import com.book.dto.CustomerDTO;
import com.book.service.CustomerService;
import com.book.service.impl.CustomerServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet(name = "CustomerServlet", urlPatterns = {"/api/v2/customers/*"})
public class CustomerServlet extends HttpServlet {

    private CustomerService customerService = new CustomerServiceImpl();
    private ObjectMapper mapper = new ObjectMapper();

    // Helper method to extract ID from URL
    private Long extractId(HttpServletRequest req) {
        String pathInfo = req.getPathInfo(); // e.g., /123
        if (pathInfo != null && pathInfo.length() > 1) {
            return Long.parseLong(pathInfo.substring(1));
        }
        return null;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        Long id = extractId(req);

        try {
            if (id == null) {
                List<CustomerDTO> customers = customerService.getAllCustomers();
                resp.getWriter().write(mapper.writeValueAsString(customers));
            } else {
                CustomerDTO customer = customerService.getCustomerById(id);
                if (customer == null) {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    resp.getWriter().write("{\"message\":\"Customer not found\"}");
                } else {
                    resp.getWriter().write(mapper.writeValueAsString(customer));
                }
            }
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"message\":\"" + e.getMessage() + "\"}");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        try {
            CustomerDTO customerDTO = mapper.readValue(req.getReader(), CustomerDTO.class);
            CustomerDTO created = customerService.saveCustomer(customerDTO);
            resp.setStatus(HttpServletResponse.SC_CREATED);
            resp.getWriter().write(mapper.writeValueAsString(created));
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"message\":\"" + e.getMessage() + "\"}");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        Long id = extractId(req);
        if (id == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"message\":\"Customer ID is required in URL\"}");
            return;
        }

        try {
            CustomerDTO customerDTO = mapper.readValue(req.getReader(), CustomerDTO.class);
            CustomerDTO updated = customerService.updateCustomer(id, customerDTO);
            resp.getWriter().write(mapper.writeValueAsString(updated));
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"message\":\"" + e.getMessage() + "\"}");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = extractId(req);
        if (id == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"message\":\"Customer ID is required in URL\"}");
            return;
        }

        try {
            customerService.deleteCustomer(id);
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"message\":\"" + e.getMessage() + "\"}");
        }
    }
}
