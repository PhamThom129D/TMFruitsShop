package com.example.tmfruitsshop.Model;

import java.sql.Timestamp;
import java.util.List;

public class Order {
    private int orderID;
    private Timestamp orderDate;
    private String statusOrder;
    private String username;
    private String paymentMethod;
    private String address;
    private List<Product> productList;
    private int quantityBuy;
    public Order() {

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public int getQuantityBuy() {
        return quantityBuy;
    }

    public void setQuantityBuy(int quantityBuy) {
        this.quantityBuy = quantityBuy;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Order(int orderID, Timestamp orderDate, String statusOrder) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.statusOrder = statusOrder;
    }

    public Order(Timestamp orderDate, String statusOrder) {
        this.orderDate = orderDate;
        this.statusOrder = statusOrder;
    }

    public Order(String address, Timestamp orderDate, int orderID, String paymentMethod, List<Product> productList, int quantityBuy, String statusOrder, String username) {
        this.address = address;
        this.orderDate = orderDate;
        this.orderID = orderID;
        this.paymentMethod = paymentMethod;
        this.productList = productList;
        this.quantityBuy = quantityBuy;
        this.statusOrder = statusOrder;
        this.username = username;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(String statusOrder) {
        this.statusOrder = statusOrder;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderDate=" + orderDate +
                ", orderID=" + orderID +
                ", statusOrder='" + statusOrder + '\'' +
                '}';
    }
}
