package com.example.tmfruitsshop.Controller;

import com.example.tmfruitsshop.Model.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.tmfruitsshop.Controller.AdminServlet.adminService;

@WebServlet(value = "/productAdmin")
public class ProductAdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

    private void showUpdateProductForm(HttpServletRequest req, HttpServletResponse resp) {
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
        }
    }

    private void updateProductAction(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
        resp.sendRedirect("/View/admin/homeAdmin.jsp");
    }
}
