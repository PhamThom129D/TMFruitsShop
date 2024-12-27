package com.example.tmfruitsshop.Service.Admin;

import com.example.tmfruitsshop.Model.CartItem;
import com.example.tmfruitsshop.Model.Product;
import com.example.tmfruitsshop.Model.Order;

import java.util.List;

public interface InAdminService {

    List<Product> getAllProduct();

    Product getProductById(int id);

    void updateProduct(Product product);

    void addProduct(Product product);

    List<Product> searchProductWithName(String keyword);

    List<Product> searchProductByType(String type);

    List<Product> searchProductByPrice(int min, int max);

    List<Product> searchProductByName(String name);
    int addOrder(int userID);
    void addOrderDetail(int orderID,int productID,int quantity);
    List<CartItem> getOrderDetail(int orderID);

    void updateStatusOrder(int orderID,String status,String paymentMethod);
    void addInvoice(int orderID,double total);
    List<Order> getOrderByUserID(int userID);
}
