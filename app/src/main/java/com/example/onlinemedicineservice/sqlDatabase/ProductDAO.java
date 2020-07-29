package com.example.onlinemedicineservice.sqlDatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProductDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProduct(SQLProductModel SQLProductModel);

    @Query("SELECT * FROM product_table")
    List<SQLProductModel> getAllProduct();

    @Delete
    void deleteProduct(SQLProductModel SQLProductModel);

    @Query("DELETE FROM product_table")
    void deleteAllProduct();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSuggestion(SuggestionModel suggestionModel);

    @Query("SELECT * FROM suggestion_table")
    List<String> getAllSuggestion();



}
