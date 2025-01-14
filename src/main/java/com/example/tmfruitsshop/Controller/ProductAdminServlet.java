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
import java.util.List;

import static com.example.tmfruitsshop.Controller.AdminServlet.adminService;

@WebServlet(value = {"/productAdmin"})
public class ProductAdminServlet extends HttpServlet {
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
            case "update":
                showUpdateProductForm(req, resp);
                break;
            case "showAddProduct":
                resp.sendRedirect("/View/admin/addProduct.jsp");
                break;
            case "search":
                searchProductForm(req, resp);
                break;
            default:
                loadAllProducts(req, resp);
                break;
        }
    }

    private void loadAllProducts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = adminService.getAllProduct();
        req.setAttribute("products", products);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/View/admin/manageProduct.jsp");
        dispatcher.forward(req, resp);
    }

    private void searchProductForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        if (keyword == null || keyword.trim().isEmpty()) {
            req.setAttribute("error", "Keyword không được để trống!");
        } else {
            List<Product> products = adminService.searchProductWithName(keyword);
            if (products.isEmpty()) {
                req.setAttribute("message", "Không tìm thấy sản phẩm nào với từ khóa: " + keyword);
            } else {
                req.setAttribute("products", products);
            }
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/View/admin/manageProduct.jsp");
        dispatcher.forward(req, resp);
    }

    private void showUpdateProductForm(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException, ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = adminService.getProductById(id);
        req.setAttribute("product", product);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/View/admin/updateProduct.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "update":
                updateProductAction(req, resp);
                break;
            case "add":
                addProductAction(req, resp);
                break;
        }
    }

    private void addProductAction(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try {
            String productName = req.getParameter("name");
            int price = Integer.parseInt(req.getParameter("price"));
            String urlImage = req.getParameter("image");
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            String description = req.getParameter("description");
            String type = req.getParameter("type");
            Product product = new Product(productName, quantity, price, urlImage, type, description);
            adminService.addProduct(product);
            loadAllProducts(req, resp);
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Dữ liệu không hợp lệ!");
            loadAllProducts(req, resp);
        }
    }

    private void updateProductAction(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String productName = req.getParameter("name");
            int price = Integer.parseInt(req.getParameter("price"));
            String urlImage = req.getParameter("image");
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            String description = req.getParameter("description");
            String type = req.getParameter("type");
            Product product = new Product(id, productName, quantity, price, urlImage, type, description);
            adminService.updateProduct(product);
            List<Product> products = adminService.getAllProduct();
            req.setAttribute("products", products);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/View/admin/manageProduct.jsp");
            dispatcher.forward(req, resp);
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Dữ liệu không hợp lệ!");
            loadAllProducts(req, resp);
        }
    }
}
