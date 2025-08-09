package com.book.servlet;

import com.book.dto.BillItemDTO;
import com.book.service.BillItemService;
import com.book.service.impl.BillItemServiceImpl;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class BillItemServlet extends HttpServlet {
    private BillItemService billItemService = new BillItemServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int billId = Integer.parseInt(request.getParameter("billId"));
        try {
            List<BillItemDTO> items = billItemService.getBillItemsByBillId(billId);
            request.setAttribute("items", items);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/bill-item-list.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
