package com.example.tmfruitsshop.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/ContentServlet")
public class AdminServlet extends HttpServlet {
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
            case "product":
                jspPath = "/View/admin/manageProduct.jsp";
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

