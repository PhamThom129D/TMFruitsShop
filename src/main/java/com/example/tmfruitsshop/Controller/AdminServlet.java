package com.example.tmfruitsshop.Controller;

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
import java.util.List;

@WebServlet(value = "/ContentServlet")
public class AdminServlet extends HttpServlet {
    public static final InAdminService adminService = new AdminService();

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
            case "redirectUser":
                resp.sendRedirect("View/admin/manageUser.jsp");
                break;
            case "redirectProduct":
                showProductForm(req, resp);
                break;
            case "redirectOrder":
                resp.sendRedirect("View/admin/manageOrder.jsp");
                break;
            default:
                showProductForm(req, resp);
                break;
        }
    }

    private void showProductForm(HttpServletRequest req, HttpServletResponse resp) {
        List<Product> products = adminService.getAllProduct();
        req.setAttribute("products", products);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/View/admin/manageProduct.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

}

