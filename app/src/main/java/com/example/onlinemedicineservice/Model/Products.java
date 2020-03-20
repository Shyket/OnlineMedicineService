package com.example.onlinemedicineservice.Model;

public class Products {

    private String productName;
    private String productDesc;

    public Products(String productName,String productDesc) {
        this.productName = productName;
        this.productDesc = productDesc;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public String getProductName() {
        return productName;
    }
}
