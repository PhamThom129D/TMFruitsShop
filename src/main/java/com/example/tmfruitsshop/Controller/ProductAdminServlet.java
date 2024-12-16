package com.example.tmfruitsshop.Controller;

import com.example.tmfruitsshop.Model.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static com.example.tmfruitsshop.Controller.AdminServlet.adminService;

@WebServlet(value = "/productAdmin")
public class ProductAdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        String action = req.getParameter("action");
        if(action==null){
            action ="";
        }
        switch (action){
            case "update" :
                showUpdateProductForm(req,resp);
                break;
        }
    }

    private void showUpdateProductForm(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = adminService.getProductById(id);
        req.setAttribute("product",product);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/View/admin/updateProduct.jsp");
        try {
            dispatcher.forward(req,resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action==null){
            action ="";
        }
        switch (action){
            case "update" :
                updateProductAction(req,resp);
                break;
            case "add" :
                addProductAction(req,resp);
                break;
        }
    }

    private void addProductAction(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String productName = req.getParameter("name");
        int price = Integer.parseInt(req.getParameter("price"));
        String urlImage = req.getParameter("image");
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        String description = req.getParameter("description");
        String type = req.getParameter("type");
        Product product = new Product(productName,quantity,price,urlImage,type,description);
        adminService.addProduct(product);
        resp.sendRedirect(req.getContextPath() + "/ContentServlet?page=product");
    }

    private void updateProductAction(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String productName = req.getParameter("name");
        int price = Integer.parseInt(req.getParameter("price"));
        String urlImage = req.getParameter("image");
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        String description = req.getParameter("description");
        String type = req.getParameter("type");
        int id = Integer.parseInt(req.getParameter("id"));
        System.out.println(type);
        Product product = new Product(id,productName,quantity,price,urlImage,type,description);
        adminService.updateProduct(product);
        resp.sendRedirect(req.getContextPath() + "/ContentServlet?page=product");
    }
}
