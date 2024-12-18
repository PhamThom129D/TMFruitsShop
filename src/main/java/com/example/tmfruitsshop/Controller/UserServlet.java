package com.example.tmfruitsshop.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/user")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "showCart":
                req.getRequestDispatcher("/View/user/cart.jsp").forward(req, resp);
                break;
            default:
                req.getRequestDispatcher("/View/user/homeUser.jsp").forward(req, resp);
                break;
        }
    }
}
