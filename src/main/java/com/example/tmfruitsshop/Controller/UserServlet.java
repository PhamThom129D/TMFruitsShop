package com.example.tmfruitsshop.Controller;

import com.example.tmfruitsshop.Model.Product;
import com.example.tmfruitsshop.Service.Admin.AdminService;
import com.example.tmfruitsshop.Service.Admin.InAdminService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
            default:
                loadHomeUser(req, resp);
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
        switch (action) {
            case "searchProduct":
                searchProduct(req, resp);
                break;
        }
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
        }else {
            List<Product> products = adminService.searchProductWithName(keyword);
            if (products.isEmpty()) {
                req.setAttribute("message", "Khong tim thay san pham nao voi tu khoa: " + keyword);
            } else {
                req.setAttribute("products", products);
                req.getRequestDispatcher("/View/user/homeUser.jsp").forward(req, resp);
            }
        }
    }

    private void loadHomeUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = adminService.getAllProduct();
        req.setAttribute("products", products);
        req.getRequestDispatcher("/View/user/homeUser.jsp").forward(req, resp);
    }
}
