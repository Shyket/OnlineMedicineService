package com.example.onlinemedicineservice.sqlDatabase;

import android.app.Application;
import java.util.List;

public class ProductRepository {

    private ProductDAO productDAO;
    private List<SQLProductModel> SQLProductModelList;
    private List<String> suggetionList;

    ProductRepository(Application application){
        ProductDatabase productDatabase = ProductDatabase.getInstance(application);
        productDAO = productDatabase.productDAO();
        SQLProductModelList = productDAO.getAllProduct();
        suggetionList = productDAO.getAllSuggestion();
    }

    List<SQLProductModel> getAllProduct() {
         return SQLProductModelList;
    }
    List<String> getAllSuggestion() {
        return suggetionList;
    }

    void insetSuggetion(SuggestionModel suggestionModel){
        ProductDatabase.databaseWriteExecutor.execute(() -> {
            productDAO.insertSuggestion(suggestionModel);
        });
    }


    void insertProduct(final SQLProductModel SQLProductModel) {
        ProductDatabase.databaseWriteExecutor.execute(() -> {
            productDAO.insertProduct(SQLProductModel);
        });
    }

    void deleteProduct(final SQLProductModel SQLProductModel) {
        ProductDatabase.databaseWriteExecutor.execute(() -> {
            productDAO.deleteProduct(SQLProductModel);
        });
    }

    void deleteAllProduct() {
        ProductDatabase.databaseWriteExecutor.execute(() -> {
            productDAO.deleteAllProduct();
        });
    }




}




