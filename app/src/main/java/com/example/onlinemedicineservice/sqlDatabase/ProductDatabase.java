package com.example.onlinemedicineservice.sqlDatabase;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {SQLProductModel.class, SuggestionModel.class} , version = 8, exportSchema = false)
public abstract class ProductDatabase extends RoomDatabase{

    private static volatile ProductDatabase productDatabaseInstance;

    public abstract ProductDAO productDAO();

    private static final int NUMBER_OF_THREADS = 8;

    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static ProductDatabase getInstance(Context context){

        if(productDatabaseInstance == null){

            productDatabaseInstance = Room.databaseBuilder(context.getApplicationContext(), ProductDatabase.class,
                    "product_database").allowMainThreadQueries().fallbackToDestructiveMigration().build();

        }
        return productDatabaseInstance;
    }

}
