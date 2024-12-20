package com.example.tmfruitsshop.Controller;

import com.example.tmfruitsshop.Model.CartItem;
import com.example.tmfruitsshop.Model.Product;
import com.example.tmfruitsshop.Model.User;
import com.example.tmfruitsshop.Service.Admin.AdminService;
import com.example.tmfruitsshop.Service.Admin.InAdminService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

    @WebServlet("/cart")
    public class CartServlet extends HttpServlet {
        private static InAdminService adminService = new AdminService();

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            req.setCharacterEncoding("UTF-8");
            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html; charset=UTF-8");
            String action = req.getParameter("action");
            int productID = Integer.parseInt(req.getParameter("id"));
            if (action == null) {
                action = "";
            }
            switch (action) {
                case "showProductDetail":
                    req.setAttribute("product", adminService.getProductById(productID));
                    req.getRequestDispatcher("/View/user/productDetail.jsp").forward(req, resp);
                    break;
                case "showOrderDetail" :
                    break;
                default:
                    req.getRequestDispatcher("/View/user/homeUser.jsp").forward(req, resp);
                    break;
            }
        }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            req.setCharacterEncoding("UTF-8");
            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html; charset=UTF-8");

            String action = req.getParameter("action");
            String productIdParam = req.getParameter("id");  // Lấy tham số id từ request
            int productID = 0;  // Đảm bảo khởi tạo giá trị mặc định

            // Kiểm tra nếu id không null và có thể chuyển đổi thành số
            if (productIdParam != null && !productIdParam.isEmpty()) {
                try {
                    productID = Integer.parseInt(productIdParam);
                } catch (NumberFormatException e) {
                    // Log lỗi hoặc xử lý khi không thể ép kiểu
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid product ID");
                    return;
                }
            }

            // Tiến hành xử lý tiếp theo
            if (action == null || action.isEmpty()) {
                action = "";
            }

            List<CartItem> cart = (List<CartItem>) req.getSession().getAttribute("cart");
            if (cart == null) {
                cart = new ArrayList<>();
            }

            switch (action) {
                case "addToCart":
                    int quantityBuy = Integer.parseInt(req.getParameter("quantityBuy"));
                    addProductToCart(req, resp, cart, productID, quantityBuy);
                    break;

                case "removeProduct":
                    int finalProductID = productID;
                    cart.removeIf(item -> item.getProductID() == finalProductID);
                    updateCartItemCount(req, cart);
                    req.getSession().setAttribute("cart", cart);
                    resp.sendRedirect("/user?action=showCart");
                    break;

                case "updateCheckBox":
                    updateCheckedItems(req,cart);
                    break;

                default:
                    req.getRequestDispatcher("/user").forward(req, resp);
                    break;
            }
        }

        private void updateCheckedItems(HttpServletRequest req, List<CartItem> cart) {
            for (CartItem item : cart) {
                String checkboxName = "selected_" + item.getProductID();
                String checkboxValue = req.getParameter(checkboxName);

                if (checkboxValue != null) {
                    item.setChecked(true);
                } else {
                    item.setChecked(false);
                }
            }
            req.getSession().setAttribute("cart", cart);
        }


        private static void updateCartItemCount(HttpServletRequest req, List<CartItem> cart) {
            int cartItemCount = cart.size();
            req.getSession().setAttribute("cartItemCount", cartItemCount);
        }

        private static void addProductToCart(HttpServletRequest req, HttpServletResponse resp, List<CartItem> cart, int productID, int quantityBuy) throws ServletException, IOException {
            Product product = adminService.getProductById(productID);
            boolean productExists = false;

            for (CartItem item : cart) {
                if (item.getProductID() == productID) {
                    item.setQuantity(item.getQuantity() + quantityBuy);
                    productExists = true;
                    break;
                }
            }
            if (!productExists) {
                CartItem newItem = new CartItem(productID, product.getUrlImage(), product.getProductName(), product.getPrice(), quantityBuy,false);
                cart.add(newItem);
            }
            req.getSession().setAttribute("cart", cart);
            updateCartItemCount(req, cart);
            resp.sendRedirect("/user");

        }
    }

