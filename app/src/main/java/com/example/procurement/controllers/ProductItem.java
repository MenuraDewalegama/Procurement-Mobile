package com.example.procurement.controllers;

import java.io.Serializable;

public class ProductItem implements Serializable {

    private int pid;
    private String productName;
    private int qty;
    private double productPrice;
    private double productTotalPrice;

    public ProductItem(String productName, int qty, double productPrice, double productTotalPrice) {
        this.productName = productName;
        this.qty = qty;
        this.productPrice = productPrice;
        this.productTotalPrice = productTotalPrice;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public double getProductTotalPrice() {
        return productTotalPrice;
    }

    public void setProductTotalPrice(double productTotalPrice) {
        this.productTotalPrice = productTotalPrice;
    }
}
