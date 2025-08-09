package com.book.servlet;

import com.book.dto.BillDTO;
import com.book.dto.BillItemDTO;
import com.book.service.BillService;
import com.book.service.impl.BillServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/bills")
public class BillServlet extends HttpServlet {

    private BillService billService;

    @Override
    public void init() throws ServletException {
        billService = new BillServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long customerId = Long.parseLong(req.getParameter("customerId"));
            double totalAmount = Double.parseDouble(req.getParameter("totalAmount"));
            Long createdBy = Long.parseLong(req.getParameter("createdBy")); // typically the user ID

            // Extract bill item details from request
            String[] itemIds = req.getParameterValues("itemId");
            String[] quantities = req.getParameterValues("quantity");
            String[] prices = req.getParameterValues("price");

            if (itemIds == null || itemIds.length == 0) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "No bill items provided.");
                return;
            }

            List<BillItemDTO> billItems = new ArrayList<>();
            for (int i = 0; i < itemIds.length; i++) {
                BillItemDTO item = new BillItemDTO();
                item.setItemId(Long.parseLong(itemIds[i]));
                item.setQuantity(Integer.parseInt(quantities[i]));
                item.setPrice(Double.parseDouble(prices[i]));
                billItems.add(item);
            }

            // Create BillDTO
            BillDTO billDTO = new BillDTO();
            billDTO.setCustomerId(customerId);
            billDTO.setTotalAmount(totalAmount);
            billDTO.setCreatedBy(createdBy); // ✅ correct method name
            billDTO.setItems(billItems);

            // Save bill
            billService.createBill(billDTO); // ✅ matches BillService method name

            resp.sendRedirect("views/bill-list.jsp"); // Adjust as per your JSP structure

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing bill: " + e.getMessage());
        }
    }
}
