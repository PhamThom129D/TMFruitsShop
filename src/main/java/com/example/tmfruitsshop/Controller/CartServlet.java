package com.example.tmfruitsshop.Controller;

import com.example.tmfruitsshop.Model.CartItem;
import com.example.tmfruitsshop.Model.Product;
import com.example.tmfruitsshop.Service.Admin.AdminService;
import com.example.tmfruitsshop.Service.Admin.InAdminService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

    @WebServlet("/cart")
    public class CartServlet extends HttpServlet {
        private static final InAdminService adminService = new AdminService();

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            req.setCharacterEncoding("UTF-8");
            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html; charset=UTF-8");
            String action = req.getParameter("action");
            System.out.println(action);
            int productID = Integer.parseInt(req.getParameter("id"));
            if (action == null) {
                action = "";
            }
            switch (action) {
                case "showProductDetail":
                    req.setAttribute("product", adminService.getProductById(productID));
                    req.getRequestDispatcher("/View/user/productDetail.jsp").forward(req, resp);
                    break;
                default:
                    RequestDispatcher dispatcher = req.getRequestDispatcher("/View/user/order.jsp");
                    dispatcher.forward(req, resp);
                    break;
            }
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

            List<CartItem> cart = (List<CartItem>) req.getSession().getAttribute("cart");
            if (cart == null) {
                cart = new ArrayList<>();
            }
            int productID = Integer.parseInt(req.getParameter("id"));
            switch (action) {
                case "addToCart":
                    int quantityBuy = Integer.parseInt(req.getParameter("quantityBuy"));
                    addProductToCart(req, resp, cart, productID, quantityBuy);
                    break;

                case "removeProduct":
                    cart.removeIf(item -> item.getProductID() == productID);
                    updateCartItemCount(req, cart);
                    req.getSession().setAttribute("cart", cart);
                    resp.sendRedirect("/user?action=showCart");
                    break;
                default:
                    req.getRequestDispatcher("/user").forward(req , resp);
                    break;
            }
        }


        static void updateCartItemCount(HttpServletRequest req, List<CartItem> cart) {
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
                CartItem newItem = new CartItem(productID, product.getUrlImage(), product.getProductName(), product.getPrice(), quantityBuy);
                cart.add(newItem);
            }
            req.getSession().setAttribute("cart", cart);
            updateCartItemCount(req, cart);
            resp.sendRedirect("/user");

        }
    }

 