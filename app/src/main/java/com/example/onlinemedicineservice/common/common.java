package com.example.onlinemedicineservice.common;

import com.example.onlinemedicineservice.Model.FirebaseProductModel;
import com.example.onlinemedicineservice.Model.FirebaseUserModel;

import java.util.ArrayList;
import java.util.List;

public class common {

    public static FirebaseUserModel currentUser;
    public static ArrayList<FirebaseProductModel> currentSearchedProducts = new ArrayList<>();
    public static List<FirebaseProductModel> currentSelectedProducts = new ArrayList<>();

}
