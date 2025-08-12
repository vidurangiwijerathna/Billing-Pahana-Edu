package com.book.servlet;

import com.book.dto.BillItemDTO;
import com.book.service.BillItemService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/bill-items")
public class BillItemServlet extends HttpServlet {

    private final BillItemService billItemService = new BillItemService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String billIdStr = request.getParameter("billId");

        if (billIdStr != null) {
            try {
                Integer billId = Integer.parseInt(billIdStr);
                List<BillItemDTO> items = billItemService.getItemsByBillId(billId);
                request.setAttribute("billItems", items);
                request.getRequestDispatcher("/WEB-INF/views/bill-items-list.jsp").forward(request, response);
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid bill ID");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing bill ID");
        }
    }
}
