package com.example.tmfruitsshop.Service.Admin;

import com.example.tmfruitsshop.Model.Product;
import com.example.tmfruitsshop.Service.ConnectDatabase;

import javax.servlet.http.HttpSession;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalTime.now;

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
    public int addOrder(int userID) {
        String query = "INSERT INTO `order`(orderDate, userID, statusOrder) VALUES (?, ?, 'Pending');";
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);
        try (Connection conn = ConnectDatabase.getConnection();
             PreparedStatement prep = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            prep.setTimestamp(1, timestamp);
            prep.setInt(2, userID);
            prep.executeUpdate();

            try (ResultSet generatedKeys = prep.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating order failed, no ID obtained.");
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addOrderDetail(int orderID, int productID, int quantity) {
        String query = "INSERT INTO `products_order` (orderID, productID, quantity) VALUES (?,?,?)";
        try (Connection conn = ConnectDatabase.getConnection();
             PreparedStatement prep = conn.prepareStatement(query)) {
            prep.setInt(1, orderID);
            prep.setInt(2, productID);
            prep.setInt(3, quantity);
            prep.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
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
