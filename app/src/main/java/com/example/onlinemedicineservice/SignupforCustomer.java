package com.example.onlinemedicineservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignupforCustomer extends AppCompatActivity {

    Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupfor_customer);

        signupButton = findViewById(R.id.signupbutton);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent  = new Intent(SignupforCustomer.this,CustomerLoginPage.class);
                startActivity(intent);

            }
        });



    }
}
