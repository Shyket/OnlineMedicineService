package com.example.onlinemedicineservice.Model;

import android.os.LocaleList;
import android.os.Parcelable;

import com.example.onlinemedicineservice.ProductDetails;
import com.example.onlinemedicineservice.sqlDatabase.SQLProductModel;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class FirebaseOrderModel {

     private LocalDateTime orderTime;
     private String userId;
     private String userName;
     private String userPhoneNumber;
     private List<String> ProductList;
     private double lat;
     private double lon;
     private String totalPayment;
     private String paymentMethod;

    public FirebaseOrderModel(LocalDateTime orderTime,
                              String userId,
                              String userName,
                              String userPhoneNumber,
                              List<String> ProductList,
                              double lat,
                              double lon,
                              String totalPayment,
                              String paymentMethod) {

        this.orderTime = orderTime;
        this.userId = userId;
        this.userName = userName;
        this.userPhoneNumber = userPhoneNumber;
        this.ProductList = ProductList;
        this.lat = lat;
        this.lon = lon;
        this.totalPayment = totalPayment;
        this.paymentMethod = paymentMethod;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public List<String> getProductList() {
        return ProductList;
    }

    public void setProductList(List<String> productList) {
        this.ProductList = productList;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(String totalPayment) {
        this.totalPayment = totalPayment;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
