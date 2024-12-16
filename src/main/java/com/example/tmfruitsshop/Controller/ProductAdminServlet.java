package com.example.tmfruitsshop.Controller;

import com.example.tmfruitsshop.Model.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
            case "delete" :
                deleteProductAction(req,resp);
                break;
            default:
                showAllProduct(req,resp);
                break;
        }
    }

    private void showAllProduct(HttpServletRequest req, HttpServletResponse resp) {
        List<Product> products = adminService.getAllProduct();
        req.setAttribute("products",products);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/View/admin/manageProduct.jsp");
        try {
            dispatcher.forward(req,resp);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteProductAction(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int productID = Integer.parseInt(req.getParameter("id"));
        adminService.deleteProduct(productID);
    }
}
