package com.example.tmfruitsshop.Controller;

import com.example.tmfruitsshop.Model.CartItem;
import com.example.tmfruitsshop.Model.Product;
import com.example.tmfruitsshop.Service.Admin.AdminService;
import com.example.tmfruitsshop.Service.Admin.InAdminService;

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
        int productID = Integer.parseInt(req.getParameter("id"));
        int quantityBuy = Integer.parseInt(req.getParameter("quantityBuy"));
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "addToCart":
                Product product = adminService.getProductById(productID);
                List<CartItem> cart = (List<CartItem>) req.getSession().getAttribute("cart");
                if (cart == null) {
                    cart = new ArrayList<>();
                }

                boolean productExists = false;
                for (CartItem item : cart) {
                    if (item.getProductID() == productID) {
                        item.setQuantity(item.getQuantity() + quantityBuy);
                        productExists = true;
                        break;
                    }
                }
                if (!productExists) {
                    CartItem newItem = new CartItem(productID,product.getUrlImage(), product.getProductName(), product.getPrice(), quantityBuy);
                    cart.add(newItem);
                }

                req.getSession().setAttribute("cart", cart);
                req.setAttribute("cart", cart);
                req.setAttribute("products", adminService.getAllProduct());
                req.getRequestDispatcher("/View/user/homeUser.jsp").forward(req, resp);
                break;

            default:
                req.getRequestDispatcher("/View/user/homeUser.jsp").forward(req, resp);
                break;
        }
    }

}