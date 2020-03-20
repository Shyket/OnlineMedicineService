package com.example.onlinemedicineservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignupforAdmin extends AppCompatActivity {

    Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupfor_admin);

        signupButton = findViewById(R.id.signupbutton);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent  = new Intent(SignupforAdmin.this,AdminLoginPage.class);
                startActivity(intent);

            }
        });



    }
}
