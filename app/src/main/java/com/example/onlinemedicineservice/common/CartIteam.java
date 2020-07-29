package com.example.onlinemedicineservice.common;

import com.example.onlinemedicineservice.Model.FirebaseProductModel;

import java.util.ArrayList;
import java.util.List;

public class CartIteam {

    List<FirebaseProductModel> productList = new ArrayList<>();
    List<Integer> quantitylist = new ArrayList<>();

    public void addProduct(FirebaseProductModel product){
        productList.add(product);
    }

}
