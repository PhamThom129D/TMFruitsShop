package com.example.tmfruitsshop.Service.Admin;

import com.example.tmfruitsshop.Model.Product;

import java.util.List;

public interface InAdminService {

    List<Product> getAllProduct();

    Product getProductById(int id);

    void updateProduct(Product product);

    void addProduct(Product product);

    List<Product> searchProductWithName(String keyword);
}
