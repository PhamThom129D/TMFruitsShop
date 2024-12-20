package com.example.tmfruitsshop.Service.User;

import java.sql.Timestamp;

public class Order {
    private int orderID;
    private Timestamp orderDate;
    private String statusOrder;

    public Order(int orderID, Timestamp orderDate, String statusOrder) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.statusOrder = statusOrder;
    }

    public Order(Timestamp orderDate, String statusOrder) {
        this.orderDate = orderDate;
        this.statusOrder = statusOrder;
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
