package com.example.onlinemedicineservice.drawermenuitems.cart;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.onlinemedicineservice.Model.Products;

import java.util.List;

public class CartViewModel extends ViewModel{

    private final MutableLiveData<List<Products>> productList = new MutableLiveData<>();

    public void setProducteList(List<Products> products){

        productList.setValue(products);

    }

    public LiveData<List<Products>> getProductList(){
        return productList;
    }
}
