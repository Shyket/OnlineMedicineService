package com.example.onlinemedicineservice.drawermenuitems.store;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.onlinemedicineservice.Model.Products;

import java.util.List;

public class StoreViewModel extends ViewModel {

    private final MutableLiveData<List<Products>> productList = new MutableLiveData<>();

    public void setProductList(List<Products> products){

        productList.setValue(products);

    }

    public LiveData<List<Products>> getProductList(){
        return productList;
    }
}


