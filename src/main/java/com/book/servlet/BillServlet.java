package com.book.servlet;

import com.book.dto.BillDTO;
import com.book.dto.BillItemDTO;
import com.book.service.BillService;
import com.book.service.impl.BillServiceImpl;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BillServlet extends HttpServlet {
    private BillService billService = new BillServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<BillDTO> bills = billService.getAllBills();
            request.setAttribute("bills", bills);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/bill-list.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int customerId = Integer.parseInt(request.getParameter("customerId"));
            int createdBy = Integer.parseInt(request.getParameter("createdBy"));
            double totalAmount = Double.parseDouble(request.getParameter("totalAmount"));

            String[] itemIds = request.getParameterValues("itemId");
            String[] quantities = request.getParameterValues("quantity");
            String[] prices = request.getParameterValues("unitPrice");

            List<BillItemDTO> items = new ArrayList<>();
            for (int i = 0; i < itemIds.length; i++) {
                BillItemDTO item = new BillItemDTO();
                item.setItemId(Integer.parseInt(itemIds[i]));
                item.setQuantity(Integer.parseInt(quantities[i]));
                item.setUnitPrice(Double.parseDouble(prices[i]));
                items.add(item);
            }

            BillDTO billDTO = new BillDTO(customerId, createdBy, totalAmount, items);
            billService.createBill(billDTO);

            response.sendRedirect("bill");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
