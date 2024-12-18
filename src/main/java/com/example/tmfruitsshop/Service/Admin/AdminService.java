package com.example.tmfruitsshop.Service.Admin;

import com.example.tmfruitsshop.Model.Product;
import com.example.tmfruitsshop.Service.ConnectDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminService implements InAdminService {
    @Override
    public List<Product> getAllProduct() {
        List<Product> productList = new ArrayList<>();
        String query = "Select * from products";
        try (Connection conn = ConnectDatabase.getConnection();
             Statement stm = conn.createStatement()
        ) {
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                int productID = rs.getInt("productID");
                String productName = rs.getString("productName");
                int quantity = rs.getInt("stock");
                int price = rs.getInt("price");
                String urlImage = rs.getString("urlImage");
                String description = rs.getString("description");
                String type = rs.getString("type");
                Product product = new Product(productID, productName, quantity, price, urlImage, type, description);
                productList.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return productList;
    }

    @Override
    public Product getProductById(int id) {
        String query = "Select * from products where productID = ?";
        try (Connection conn = ConnectDatabase.getConnection();
             PreparedStatement prep = conn.prepareStatement(query)) {
            prep.setInt(1, id);
            ResultSet rs = prep.executeQuery();
            while (rs.next()) {
                int productID = rs.getInt("productID");
                String productName = rs.getString("productName");
                int quantity = rs.getInt("stock");
                int price = rs.getInt("price");
                String urlImage = rs.getString("urlImage");
                String description = rs.getString("description");
                String type = rs.getString("type");
                Product product = new Product(productID, productName, quantity, price, urlImage, type, description);
                return product;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void updateProduct(Product product) {
        String query = "Update products set productName = ?, stock = ?, price = ?, urlImage = ?, type = ?, description = ? where productID = ?";
        try (Connection conn = ConnectDatabase.getConnection();
             PreparedStatement prep = conn.prepareStatement(query)
        ) {
            prep.setString(1, product.getProductName());
            prep.setInt(2, product.getQuantity());
            prep.setInt(3, product.getPrice());
            prep.setString(4, product.getUrlImage());
            prep.setString(5, product.getType());
            prep.setString(6, product.getDescription());
            prep.setInt(7, product.getProductID());
            prep.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addProduct(Product product) {
        String query = "Insert into products(productName, stock, price, urlImage, type, description) values (?,?,?,?,?,?)";
        try (Connection conn = ConnectDatabase.getConnection();
             PreparedStatement prep = conn.prepareStatement(query)
        ) {
            prep.setString(1, product.getProductName());
            prep.setInt(2, product.getQuantity());
            prep.setInt(3, product.getPrice());
            prep.setString(4, product.getUrlImage());
            prep.setString(5, product.getType());
            prep.setString(6, product.getDescription());
            prep.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
