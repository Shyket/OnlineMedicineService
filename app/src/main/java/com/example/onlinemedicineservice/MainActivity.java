package com.example.onlinemedicineservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.onlinemedicineservice.customerloginsignup.SigninActivity;

public class MainActivity extends AppCompatActivity {

    private Button adminButton,customerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adminButton = findViewById(R.id.adminButton);
        customerButton = findViewById(R.id.customerButton);

        customerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent  = new Intent(MainActivity.this, SigninActivity.class);
                startActivity(intent);
            }
        });


    }
}
