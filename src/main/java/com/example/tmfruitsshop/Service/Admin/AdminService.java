package com.example.tmfruitsshop.Service.Admin;

import com.example.tmfruitsshop.Model.Product;
import com.example.tmfruitsshop.Service.ConnectDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminService implements InAdminService{
    @Override
    public List<Product> getAllProduct() {
        List<Product> productList = new ArrayList<>();
        String query = "Select * from products";
        try(Connection conn = ConnectDatabase.getConnection();
            Statement stm = conn.createStatement()
        ){
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()){
                int productID = rs.getInt("productID");
                String productName = rs.getString("productName");
                int quantity = rs.getInt("stock");
                int price = rs.getInt("price");
                String urlImage = rs.getString("urlImage");
                String description = rs.getString("description");
                String type = rs.getString("type");
                Product product = new Product(productID,productName,quantity,price,urlImage,type,description);
                System.out.println(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return productList;
    }

    @Override
    public void deleteProduct(int productID) {
        String query = "Delete from product where productID = ?";
        try(Connection conn = ConnectDatabase.getConnection();
            PreparedStatement prep = conn.prepareStatement(query)
        ){
            prep.setInt(1,productID);
            prep.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
