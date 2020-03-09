package com.example.onlinemedicineservice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CustomerLoginPage extends AppCompatActivity {

    TextView emailText,passwordText,clickText;
    private String email,password,text;
    Button loginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customerloginpage);

        emailText = findViewById(R.id.emailText);
        passwordText = findViewById(R.id.passwordText);
        loginButton = findViewById(R.id.loginButton);
        clickText = findViewById(R.id.clickHere);
        text = clickText.getText().toString();

        SpannableString spannableString = new SpannableString(text);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {

                Intent intent  = new Intent(textView.getContext(),SignupforCustomer.class);
                startActivity(intent);

            }
            public void updateDrawState(TextPaint paint){

                super.updateDrawState(paint);
                paint.setUnderlineText(false);
            }
        };
        spannableString.setSpan(clickableSpan,16,26, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        clickText.setText(spannableString);
        clickText.setMovementMethod(LinkMovementMethod.getInstance());
        clickText.setHighlightColor(Color.TRANSPARENT);

    }

    public void login(View view){

        if(emailText.getText().toString().isEmpty() || passwordText.getText().toString().isEmpty()){

            Toast.makeText(this, "Fields are empty!", Toast.LENGTH_SHORT).show();



        }else{

            email = (String) emailText.getText();
            password = (String) passwordText.getText();

            //check in the database;     *if authentication succed then login is successful. else wrong username and password.

        }

    }



}
