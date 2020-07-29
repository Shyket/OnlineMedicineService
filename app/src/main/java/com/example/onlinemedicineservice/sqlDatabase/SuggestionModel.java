package com.example.onlinemedicineservice.sqlDatabase;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "suggestion_table")
public class SuggestionModel {

    @PrimaryKey

    @NonNull
    private String suggetion;

    public SuggestionModel(String suggetion) {
        this.suggetion = suggetion;
    }


    @NonNull
    public String getSuggetion() {
        return suggetion;
    }

    public void setSuggetion(@NonNull String suggetion) {
        this.suggetion = suggetion;
    }
}
