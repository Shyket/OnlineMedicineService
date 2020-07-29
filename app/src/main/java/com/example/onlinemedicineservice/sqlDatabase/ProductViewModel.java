package com.example.onlinemedicineservice.sqlDatabase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {

    private ProductRepository productRepository;
    private List<SQLProductModel> SQLProductModelList;
    private List<String> suggetionList;

    public ProductViewModel(@NonNull Application application) {
        super(application);
        productRepository = new ProductRepository(application);
        SQLProductModelList = productRepository.getAllProduct();
        suggetionList = productRepository.getAllSuggestion();
    }

    public void insertProduct(SQLProductModel SQLProductModel){
        productRepository.insertProduct(SQLProductModel);
    }

    public void insertSuggestion(SuggestionModel suggestionModel){
        productRepository.insetSuggetion(suggestionModel);
    }

    public List<SQLProductModel> getAllProduct(){
       return SQLProductModelList;
    }

    public List<String> getAllSuggestion(){
        return suggetionList;
    }


    public void deleteProduct(SQLProductModel SQLProductModel){
        productRepository.deleteProduct(SQLProductModel);
    }

    public void deleteAllProduct(SQLProductModel SQLProductModel){
        productRepository.deleteAllProduct();
    }

}
