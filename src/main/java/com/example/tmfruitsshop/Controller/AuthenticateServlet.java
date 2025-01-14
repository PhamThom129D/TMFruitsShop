package com.example.tmfruitsshop.Controller;

import com.example.tmfruitsshop.Model.User;
import com.example.tmfruitsshop.Service.Admin.AdminService;
import com.example.tmfruitsshop.Service.Admin.InAdminService;
import com.example.tmfruitsshop.Service.User.InUserService;
import com.example.tmfruitsshop.Service.User.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class AuthenticateServlet extends HttpServlet {
    private static final InUserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        String action = req.getParameter("action");
        if (action == null) action = "";

        switch (action) {
            case "redirectRegister":
                resp.sendRedirect("View/authenticate/register.jsp");
                break;
            case "redirectLogin":
                resp.sendRedirect("View/authenticate/login.jsp");
                break;
            case "logout":
                HttpSession session = req.getSession();
                session.setMaxInactiveInterval(60 * 60);
                resp.sendRedirect("View/authenticate/login.jsp");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        String action = req.getParameter("action");
        if (action == null) action = "";

        switch (action) {
            case "login":
                loginAction(req, resp);
                break;
            case "register":
                registerAction(req, resp);
                break;
            default:
                resp.sendRedirect("View/authenticate/login.jsp");
                break;
        }
    }

    private void registerAction(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String username = req.getParameter("username");
        String phonenumber = req.getParameter("phonenumber");
        String address = req.getParameter("address");
        String urlAvatar = req.getParameter("urlAvatar");

        User user = new User(username, password, email, phonenumber, address, urlAvatar);
        userService.register(user);
        resp.sendRedirect("View/authenticate/login.jsp");
    }

    private void loginAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = userService.login(req, email, password);
        HttpSession session = req.getSession();
        HttpSession session1 = req.getSession();
        session1.setAttribute("loggedInUserID",user);
        if (user == null) {
            session.setAttribute("errorMessage", "Sai mật khẩu hoặc tài khoản không tồn tại.");
            resp.sendRedirect("View/authenticate/login.jsp");
        } else if (user.getStatus()) {
            session.setAttribute("user", user);
            switch (user.getRole()) {
                case "admin":
                    resp.sendRedirect("/ContentServlet");
                    break;
                case "user":
                    resp.sendRedirect("/user");
                    break;
            }
        } else {
            session.setAttribute("errorMessage", "Tài khoản của bạn đã bị khóa.");
            resp.sendRedirect("View/authenticate/login.jsp");
        }
    }


}
