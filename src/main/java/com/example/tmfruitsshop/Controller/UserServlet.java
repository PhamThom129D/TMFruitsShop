package com.example.tmfruitsshop.Controller;

import com.example.tmfruitsshop.Model.User;
import com.example.tmfruitsshop.Service.InUserService;
import com.example.tmfruitsshop.Service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class UserServlet extends HttpServlet {
    private static final InUserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        String action = req.getParameter("action");
        if (action == null) action = "";

        switch (action) {
            case "redirectRegister" :
                resp.sendRedirect("View/authenticate/register.jsp");
                break;
            case "redirectLogin":
                resp.sendRedirect("View/authenticate/login.jsp");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        String action = req.getParameter("action");
        if (action == null) action = "";

        switch (action) {
            case "login":
                loginAction(req, resp);
                break;
            default:
                resp.sendRedirect("View/authenticate/login.jsp");
                break;
        }
    }

    private void loginAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = userService.login(req, email, password);
        if (user == null) {
            System.out.println("lỗi");
        } else if(user.getStatus()){
            switch (user.getRole()) {
                case "admin":
                    req.getRequestDispatcher("View/admin/home.jsp").forward(req, resp);
                    break;
                case "user":
                    req.getRequestDispatcher("View/user/home.jsp").forward(req, resp);
                    break;
            }
        }else {
            resp.sendRedirect("error.jsp");
        }
    }


    private void forgotAction(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.getWriter().println("<h1>Password Reset Page</h1>");
    }

}
