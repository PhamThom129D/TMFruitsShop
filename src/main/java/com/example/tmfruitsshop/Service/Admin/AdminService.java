package com.example.tmfruitsshop.Service.Admin;

import com.example.tmfruitsshop.Model.Product;
import com.example.tmfruitsshop.Service.ConnectDatabase;
import com.example.tmfruitsshop.Service.User.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AdminService implements InAdminService {
    @Override
    public List<Product> getAllProduct() {
        List<Product> productList = new ArrayList<>();
        String query = "SELECT * FROM products";
        try (Connection conn = ConnectDatabase.getConnection();
             Statement stm = conn.createStatement()) {
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                Product product = mapResultSetToProduct(rs);
                productList.add(product);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return productList;
    }

    @Override
    public Product getProductById(int id) {
        String query = "SELECT * FROM products WHERE productID = ?";
        try (Connection conn = ConnectDatabase.getConnection();
             PreparedStatement prep = conn.prepareStatement(query)) {
            prep.setInt(1, id);
            ResultSet rs = prep.executeQuery();
            if (rs.next()) {
                return mapResultSetToProduct(rs);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void updateProduct(Product product) {
        String query = "UPDATE products SET productName = ?, stock = ?, price = ?, urlImage = ?, type = ?, description = ? WHERE productID = ?";
        try (Connection conn = ConnectDatabase.getConnection();
             PreparedStatement prep = conn.prepareStatement(query)) {
            prep.setString(1, product.getProductName());
            prep.setInt(2, product.getQuantity());
            prep.setInt(3, product.getPrice());
            prep.setString(4, product.getUrlImage());
            prep.setString(5, product.getType());
            prep.setString(6, product.getDescription());
            prep.setInt(7, product.getProductID());
            prep.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addProduct(Product product) {
        String query = "INSERT INTO products(productName, stock, price, urlImage, type, description) VALUES (?,?,?,?,?,?)";
        try (Connection conn = ConnectDatabase.getConnection();
             PreparedStatement prep = conn.prepareStatement(query)) {
            prep.setString(1, product.getProductName());
            prep.setInt(2, product.getQuantity());
            prep.setInt(3, product.getPrice());
            prep.setString(4, product.getUrlImage());
            prep.setString(5, product.getType());
            prep.setString(6, product.getDescription());
            prep.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> searchProductWithName(String keyword) {
        List<Product> productList = new ArrayList<>();
        String query = "SELECT * FROM products WHERE productName LIKE ?";
        try (Connection conn = ConnectDatabase.getConnection();
             PreparedStatement prep = conn.prepareStatement(query)) {
            prep.setString(1, "%" + keyword + "%");
            ResultSet rs = prep.executeQuery();
            while (rs.next()) {
                Product product = mapResultSetToProduct(rs);
                productList.add(product);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return productList;
    }

    @Override
    public List<Product> searchProductByType(String type) {
        List<Product> productList = new ArrayList<>();
        String category = "";

        if ("fruits".equalsIgnoreCase(type)) {
            category = "Trái cây";
        } else if ("vegetables".equalsIgnoreCase(type)) {
            category = "Rau củ";
        } else if ("combo".equalsIgnoreCase(type)) {
            category = "Combo";
        }

        String query = "SELECT * FROM products WHERE type = ?";
        try (Connection conn = ConnectDatabase.getConnection();
             PreparedStatement prep = conn.prepareStatement(query)) {
            prep.setString(1, category);
            ResultSet rs = prep.executeQuery();
            while (rs.next()) {
                Product product = mapResultSetToProduct(rs);
                productList.add(product);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return productList;
    }


    @Override
    public List<Product> searchProductByPrice(int min, int max) {
        List<Product> productList = new ArrayList<>();
        String query = "SELECT * FROM products WHERE price BETWEEN ? AND ?";
        try (Connection conn = ConnectDatabase.getConnection();
             PreparedStatement prep = conn.prepareStatement(query)) {
            prep.setInt(1, min);
            prep.setInt(2, max);
            ResultSet rs = prep.executeQuery();
            while (rs.next()) {
                Product product = mapResultSetToProduct(rs);
                productList.add(product);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return productList;
    }

    @Override
    public List<Product> searchProductByName(String name) {
        return searchProductWithName(name);
    }

    @Override
    public void addOrderAndDetails(int userID, int productID, int quantity) {
        String query = "{CALL AddOrder(?, ?, ?)}";

        try (Connection conn = ConnectDatabase.getConnection();
             CallableStatement stmt = conn.prepareCall(query)) {

            stmt.setInt(1, userID);
            stmt.setInt(2, productID);
            stmt.setInt(3, quantity);
            stmt.execute();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private Product mapResultSetToProduct(ResultSet rs) throws SQLException {
        int productID = rs.getInt("productID");
        String productName = rs.getString("productName");
        int quantity = rs.getInt("stock");
        int price = rs.getInt("price");
        String urlImage = rs.getString("urlImage");
        String description = rs.getString("description");
        String type = rs.getString("type");
        return new Product(productID, productName, quantity, price, urlImage, type, description);
    }
}
