package com.example.tmfruitsshop.Service.Admin;

import com.example.tmfruitsshop.Model.Product;

import java.util.List;

public interface InAdminService {

    List<Product> getAllProduct();
    void deleteProduct(int productID);
}
