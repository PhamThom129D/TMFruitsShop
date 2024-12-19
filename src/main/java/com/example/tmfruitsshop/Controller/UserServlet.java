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
        System.out.println(action);
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "showCart":
                req.getRequestDispatcher("/View/user/cart.jsp").forward(req, resp);
                break;
            default:
                loadHomeUser(req, resp);
                break;
        }
    }

    private void loadHomeUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = adminService.getAllProduct();
        req.setAttribute("products", products);
        req.getRequestDispatcher("/View/user/homeUser.jsp").forward(req, resp);
    }
}
