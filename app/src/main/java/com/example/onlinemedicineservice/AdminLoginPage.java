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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlinemedicineservice.Model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminLoginPage extends AppCompatActivity {

    EditText phonenumberText,passwordText;
    TextView clickText;
    private String text;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login_page);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference users = database.getReference("Users");

        phonenumberText= findViewById(R.id.phonenumberText);
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


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(phonenumberText.getText().toString().isEmpty() || passwordText.getText().toString().isEmpty()){

                    Toast.makeText(AdminLoginPage.this, "Fields are empty!", Toast.LENGTH_SHORT).show();


                }else{

                    //check in the database;     *if authentication succed then login is successful. else wrong username and password.

                    users.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            if(dataSnapshot.child(phonenumberText.getText().toString()).exists()) {

                                Users user = dataSnapshot.child(phonenumberText.getText().toString()).getValue(Users.class);

                                if (user.getPassword().equals(passwordText.getText().toString())) {

                                    Toast.makeText(AdminLoginPage.this, "Welcome!", Toast.LENGTH_SHORT).show();

                                } else {

                                    Toast.makeText(AdminLoginPage.this, "Login Failed", Toast.LENGTH_LONG).show();
                                }
                            }else{

                                Toast.makeText(AdminLoginPage.this, "User doesn't exist! Create new account.", Toast.LENGTH_LONG).show();

                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {


                        }
                    });

                }

            }
        });

    }
