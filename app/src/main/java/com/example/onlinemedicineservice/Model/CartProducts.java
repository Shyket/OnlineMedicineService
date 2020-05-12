package com.example.onlinemedicineservice.Model;

import com.example.onlinemedicineservice.common.common;

public class CartProducts extends Products {


    public void addItem(Products product){

        if(common.currentSelectedProducts.contains(product)){

            int count = product.getSelectedQuantity();
            product.setSelectedQuantity(count++);

        }
        else{
            common.currentSelectedProducts.add(product);
        }
    }

    public void removeItem(Products product){

        if(common.currentSelectedProducts.contains(product)){

            if(product.getSelectedQuantity() > 0){

                int count = product.getSelectedQuantity();
                product.setSelectedQuantity(--count);

            }

        }

    }

}
