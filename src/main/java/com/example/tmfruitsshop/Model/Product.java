package com.example.tmfruitsshop.Model;

public class Product {
    private int productID;
    private String productName;
    private int quantity;
    private int price;
    private String urlImage;
    private String type;
    private String description;

    public Product(int productID, String productName, int quantity, int price, String urlImage, String type, String description) {
        this.productID = productID;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.urlImage = urlImage;
        this.type = type;
        this.description = description;
    }

    public Product(String productName, int quantity, int price, String urlImage, String type, String description) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.urlImage = urlImage;
        this.type = type;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productID=" + productID +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", urlImage='" + urlImage + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                '}';
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
