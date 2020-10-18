package com.example.procurement.controllers;

import com.google.firebase.firestore.Exclude;

import java.util.Map;

public class Order {

    private String documentId;
    private String orderId;
    private String orderDate;
    private String manager;
    private String designation;
    private int contact;
    private String projectName;
    private String address;
    private double orderTotal;
    Map<String, Object> products;

    //default constructor required when using firestore
    public Order() {
    }

    public Order(String orderId, String orderDate, String manager, String designation, int contact, String projectName, String address, Map<String, Object> products, double orderTotal) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.manager = manager;
        this.designation = designation;
        this.contact = contact;
        this.projectName = projectName;
        this.address = address;
        this.products = products;
        this.orderTotal = orderTotal;
    }

    //all getters and setters used by firestore
    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Map<String, Object> getProducts() {
        return products;
    }

    public void setProducts(Map<String, Object> products) {
        this.products = products;
    }

    @Exclude
    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }
}
