package com.example.tmfruitsshop.Controller;

import com.example.tmfruitsshop.Model.CartItem;
import com.example.tmfruitsshop.Model.Product;
import com.example.tmfruitsshop.Model.User;
import com.example.tmfruitsshop.Service.Admin.AdminService;
import com.example.tmfruitsshop.Service.Admin.InAdminService;
import com.example.tmfruitsshop.Service.User.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.example.tmfruitsshop.Controller.CartServlet.updateCartItemCount;

@WebServlet(value = "/user")
public class UserServlet extends HttpServlet {
    private static InAdminService adminService = new AdminService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "showCart":
                req.getRequestDispatcher("/View/user/cart.jsp").forward(req, resp);
                break;
            case "searchWithType":
                searchWithType(req, resp);
                break;
            case "cancelOrder":
                cancelOrder(req, resp);
                break;
            case "showAccount":
                req.getRequestDispatcher("/View/user/account.jsp").forward(req, resp);
                break;
            case "showOrderHistory" :
                showOrderHistory(req, resp);
                break;
            default:
                loadHomeUser(req, resp);
                break;
        }
    }

    private void showOrderHistory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("loggedInUser");
        List<Order> orderList = adminService.getOrderByUserID(user.getUserID());
        List<Order> orderList1 = new ArrayList<>();
        List<Order> orderList2 = new ArrayList<>();
        List<Order> orderList3 = new ArrayList<>();

        for (Order order : orderList) {
            if (order.getStatusOrder().equals("Cancel")) {
                orderList1.add(order);
            }else if (order.getStatusOrder().equals("Pending")) {
                orderList2.add(order);
            } else if (order.getStatusOrder().equals("Paid")) {
                orderList3.add(order);
            }
        }
        req.setAttribute("pendingOrders", orderList2);
        req.setAttribute("approvedOrders", orderList3);
        req.setAttribute("cancelOrders", orderList1);
        req.getRequestDispatcher("/View/user/orderHistory.jsp").forward(req, resp);
    }

    private void cancelOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int orderID = Integer.parseInt(req.getParameter("orderID"));
        String paymentMethod = req.getParameter("paymentMethod");
        adminService.updateStatusOrder(orderID, "Cancel", paymentMethod);
        loadHomeUser(req, resp);
    }

    private void payOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int orderID = Integer.parseInt(req.getParameter("orderID"));
        String paymentMethod = req.getParameter("paymentMethod");
        adminService.updateStatusOrder(orderID, "Pending", paymentMethod);
        loadHomeUser(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "searchProduct":
                searchProduct(req, resp);
                break;
            case "checkout":
                checkoutCart(req, resp);
                break;
            case "payOrder":
                payOrder(req, resp);
                break;
        }
    }

    private void checkoutCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CartItem> cart = (List<CartItem>) req.getSession().getAttribute("cart");
        if (cart == null || cart.isEmpty()) {
            resp.sendRedirect("/user?action=showCart");
            return;
        }
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("loggedInUser");

        for (CartItem item : cart) {
            String quantityParam = req.getParameter("quantity_" + item.getProductID());
            if (quantityParam != null) {
                int newQuantity = Integer.parseInt(quantityParam);
                item.setQuantity(newQuantity);
            }
        }
        session.setAttribute("cart", cart);

        if (user == null) {
            resp.sendRedirect("/login");
            return;
        }

        List<CartItem> cartAfterCheckout = new ArrayList<>();
        List<Integer> cartItemSelectedID = new ArrayList<>();
        int orderID = adminService.addOrder(user.getUserID());
        double totalAmount = 0;
        String[] productSelected = req.getParameterValues("selectedProduct");

        if (productSelected != null) {
            for (String id : productSelected) {
                int productID = Integer.parseInt(id);
                cartItemSelectedID.add(productID);
            }
        } else {
            req.setAttribute("error", "Vui lòng chọn sản phẩm cần mua!");
            req.getRequestDispatcher("/user?action=showCart").forward(req, resp);
            return;
        }

        for (CartItem item : cart) {
            if (cartItemSelectedID.contains(item.getProductID())) {
                totalAmount += item.getQuantity() * item.getPrice();
                adminService.addOrderDetail(orderID, item.getProductID(), item.getQuantity());
            } else {
                cartAfterCheckout.add(item);
            }
        }
        updateCartItemCount(req, cart);
        List<CartItem> orderList = adminService.getOrderDetail(orderID);
        req.setAttribute("orderList", orderList);

        req.getSession().setAttribute("cart", cartAfterCheckout);
        req.setAttribute("totalAmount", totalAmount);
        req.setAttribute("orderID", orderID);

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedNow = now.format(formatter);
        req.setAttribute("orderDate",formattedNow);

        req.setAttribute("success", "Thanh toán thành công!");
        req.getRequestDispatcher("/View/user/order.jsp").forward(req, resp);
    }

    private void searchWithType(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        List<Product> products = adminService.searchProductByType(type);
        req.setAttribute("products", products);
        req.getRequestDispatcher("/View/user/homeUser.jsp").forward(req, resp);
    }

    private void searchProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        if (keyword == null || keyword.trim().isEmpty()) {
            req.setAttribute("error", "Keyword không được để trống!");
        } else {
            List<Product> products = adminService.searchProductWithName(keyword);
            if (products.isEmpty()) {
                req.setAttribute("message", "Khong tim thay san pham nao voi tu khoa: " + keyword);
            } else {
                req.setAttribute("products", products);
            }
        }
        req.getRequestDispatcher("/View/user/homeUser.jsp").forward(req, resp);
    }

    private void loadHomeUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = adminService.getAllProduct();
        req.setAttribute("products", products);
        req.getRequestDispatcher("/View/user/homeUser.jsp").forward(req, resp);
    }
}
