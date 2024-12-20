package com.example.tmfruitsshop.Model;

public class CartItem {
    private int productID;
    private String urlImage;
    private String productName;
    private double price;
    private int quantity;

    public CartItem(int productID, String urlImage, String productName, double price, int quantity) {
        this.productID = productID;
        this.urlImage = urlImage;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "productID=" + productID +
                ", urlImage='" + urlImage + '\'' +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
