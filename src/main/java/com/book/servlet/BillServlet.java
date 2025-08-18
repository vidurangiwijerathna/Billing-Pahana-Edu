// com/book/servlet/BillServlet.java
package com.book.servlet;

import com.book.dto.BillDTO;
import com.book.dto.BillItemDTO;
import com.book.model.Customer;
import com.book.model.Item;
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null || action.isEmpty()) {
            List<BillDTO> bills = billService.getAllBills();
            request.setAttribute("bills", bills);
            request.getRequestDispatcher("bills-list.jsp").forward(request, response);
            return;
        }

        switch (action.toLowerCase()) {
            case "create":
                // Load customers & items for dropdowns
                List<Customer> customers = billService.getAllCustomers();
                List<Item> items = billService.getAllItems();
                request.setAttribute("customers", customers);
                request.setAttribute("items", items);
                request.getRequestDispatcher("bill-create.jsp").forward(request, response);
                break;

            case "details":
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
                break;

            default:
                response.sendRedirect("bills");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String customerIdStr = request.getParameter("customerId");
        String createdByStr = request.getParameter("createdBy"); // you decide how to supply this

        String[] itemIds = request.getParameterValues("itemId[]");
        String[] quantities = request.getParameterValues("quantity[]");

        if (customerIdStr == null || createdByStr == null || itemIds == null || quantities == null) {
            // reload create page data
            request.setAttribute("error", "Missing fields");
            request.setAttribute("customers", billService.getAllCustomers());
            request.setAttribute("items", billService.getAllItems());
            request.getRequestDispatcher("bill-create.jsp").forward(request, response);
            return;
        }

        try {
            BillDTO billDTO = new BillDTO();
            billDTO.setCustomerId(Long.parseLong(customerIdStr));
            billDTO.setCreatedBy(Integer.parseInt(createdByStr));

            List<BillItemDTO> billItems = new ArrayList<>();
            for (int i = 0; i < itemIds.length; i++) {
                if (itemIds[i] == null || itemIds[i].isBlank()) continue;
                BillItemDTO it = new BillItemDTO();
                it.setItemId(Long.parseLong(itemIds[i]));
                it.setQuantity(Integer.parseInt(quantities[i]));
                billItems.add(it);
            }
            billDTO.setBillItems(billItems);

            boolean ok = billService.addBill(billDTO);
            if (ok) {
                response.sendRedirect("bills");
            } else {
                request.setAttribute("error", "Failed to create bill");
                request.setAttribute("customers", billService.getAllCustomers());
                request.setAttribute("items", billService.getAllItems());
                request.getRequestDispatcher("bill-create.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid input");
            request.setAttribute("customers", billService.getAllCustomers());
            request.setAttribute("items", billService.getAllItems());
            request.getRequestDispatcher("bill-create.jsp").forward(request, response);
        }
    }
}
