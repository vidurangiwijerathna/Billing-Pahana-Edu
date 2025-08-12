package com.book.servlet;

import com.book.dto.BillDTO;
import com.book.dto.BillItemDTO;
import com.book.mapper.BillMapper;
import com.book.model.Bill;
import com.book.service.BillService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/bills")
public class BillServlet extends HttpServlet {

    private final BillService billService = new BillService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null || action.isEmpty()) {
            listBills(request, response);
        } else if ("create".equalsIgnoreCase(action)) {
            // Directly forward to create page (no DAO calls here)
            request.getRequestDispatcher("bill-create.jsp").forward(request, response);
        } else if ("details".equalsIgnoreCase(action)) {
            showBillDetails(request, response);
        } else {
            listBills(request, response);
        }
    }

    private void listBills(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<BillDTO> bills = billService.getAllBills();
        request.setAttribute("bills", bills);
        request.getRequestDispatcher("bills-list.jsp").forward(request, response);
    }

    private void showBillDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if (idStr == null) {
            response.sendRedirect("bills");
            return;
        }

        try {
            Integer id = Integer.parseInt(idStr);
            BillDTO bill = billService.getBillById(id);
            if (bill == null) {
                response.sendRedirect("bills");
                return;
            }

            request.setAttribute("bill", bill);
            request.getRequestDispatcher("bills-view.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.sendRedirect("bills");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String customerIdStr = request.getParameter("customerId");
        String createdByStr = request.getParameter("createdBy");

        String[] itemIds = request.getParameterValues("itemId");
        String[] quantities = request.getParameterValues("quantity");
        String[] prices = request.getParameterValues("price");

        if (customerIdStr == null || createdByStr == null || itemIds == null || quantities == null || prices == null) {
            response.sendRedirect("bills-create.jsp");
            return;
        }

        try {
            BillDTO billDTO = new BillDTO();
            billDTO.setCustomerId(Long.parseLong(customerIdStr));
            billDTO.setCreatedBy(Integer.parseInt(createdByStr));

            List<BillItemDTO> billItems = new ArrayList<>();
            double totalAmount = 0.0;

            for (int i = 0; i < itemIds.length; i++) {
                BillItemDTO itemDTO = new BillItemDTO();
                itemDTO.setItemId(Long.parseLong(itemIds[i]));
                itemDTO.setQuantity(Integer.parseInt(quantities[i]));
                double price = Double.parseDouble(prices[i]);
                itemDTO.setPrice(price);

                billItems.add(itemDTO);
                totalAmount += price * itemDTO.getQuantity();
            }

            billDTO.setBillItems(billItems);
            billDTO.setTotalAmount(totalAmount);

            boolean success = billService.addBill(billDTO);
            if (success) {
                response.sendRedirect("bills");
            } else {
                request.setAttribute("error", "Failed to create bill");
                request.getRequestDispatcher("bills-create.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid input");
            request.getRequestDispatcher("bills-list.jsp").forward(request, response);
        }
    }
}
