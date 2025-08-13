package com.book.servlet;

import com.book.dto.CustomerDTO;
import com.book.service.CustomerService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/customers")
public class CustomerServlet extends HttpServlet {

    private CustomerService customerService = new CustomerService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String accountNumber = request.getParameter("accountNumber");

        customerService.addCustomer(name, email, phone, address, accountNumber);


        request.getRequestDispatcher("customer-list.jsp").forward(request, response);
    }
}
