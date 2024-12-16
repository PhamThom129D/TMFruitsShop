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

@WebServlet(value = "/ContentServlet")
public class AdminServlet extends HttpServlet {
    public static final InAdminService adminService = new AdminService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        String page = req.getParameter("page");
        String jspPath = null;

        switch (page) {
            case "user":
                jspPath = "/View/admin/manageUser.jsp";
                break;
            case "addUser" :
                jspPath = "/View/admin/addUser.jsp";
                break;
            case "product":
                List<Product> products = adminService.getAllProduct();
                req.setAttribute("products", products);
                jspPath = "/View/admin/manageProduct.jsp";
                break;
                case "addProduct" :
                    jspPath = "/View/admin/addProduct.jsp";
                    break;
            case "order":
                jspPath = "/View/admin/manageOrder.jsp";
                break;
            default:
                jspPath = null;
                break;
        }

        if (jspPath != null) {
            req.getRequestDispatcher(jspPath).include(req, resp);
        } else {
            resp.getWriter().println("<p>Không tìm thấy nội dung</p>");
        }
    }

}

