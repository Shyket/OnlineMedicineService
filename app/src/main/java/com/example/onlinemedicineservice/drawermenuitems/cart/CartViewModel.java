package com.example.onlinemedicineservice.drawermenuitems.cart;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.onlinemedicineservice.Model.FirebaseProductModel;

import java.util.List;

public class CartViewModel extends ViewModel{

    private final MutableLiveData<List<FirebaseProductModel>> productList = new MutableLiveData<>();

    public void setProducteList(List<FirebaseProductModel> products){

        productList.setValue(products);

    }

    public LiveData<List<FirebaseProductModel>> getProductList(){
        return productList;
    }
}
