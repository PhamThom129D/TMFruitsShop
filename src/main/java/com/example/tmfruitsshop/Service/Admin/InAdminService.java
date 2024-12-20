package com.example.tmfruitsshop.Service.Admin;

import com.example.tmfruitsshop.Model.Product;
import com.example.tmfruitsshop.Service.User.Order;
import com.mysql.cj.x.protobuf.MysqlxCrud;

import javax.servlet.http.HttpServletRequest;
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

    void addOrderAndDetails(int userID, int productID, int quantity);
}
