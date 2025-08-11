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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String accountNumber = request.getParameter("accountNumber");

        CustomerDTO dto = new CustomerDTO();
        dto.setName(name);
        dto.setEmail(email);
        dto.setPhone(phone);
        dto.setAddress(address);
        dto.setAccountNumber(accountNumber);

        boolean added = customerService.addCustomer(dto);  // Make sure this returns boolean

        // Set success or failure message
        if (added) {
            request.setAttribute("message", "Customer added successfully!");
            request.setAttribute("messageType", "success");
        } else {
            request.setAttribute("message", "Failed to add customer.");
            request.setAttribute("messageType", "error");
        }

        // Refresh list and forward to customer list page with message
        List<CustomerDTO> customers = customerService.getAllCustomers();
        request.setAttribute("customers", customers);

        request.getRequestDispatcher("customer-list.jsp").forward(request, response);
    }

}
