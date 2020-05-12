package com.example.onlinemedicineservice.common;

import com.example.onlinemedicineservice.Model.Products;

import java.util.ArrayList;
import java.util.List;

public class CartIteam {

    List<Products> productList = new ArrayList<>();
    List<Integer> quantitylist = new ArrayList<>();

    public void addProduct(Products product){
        productList.add(product);
    }

}
