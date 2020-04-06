package com.example.onlinemedicineserviceOldVedrsion.customerloginsignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
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

import com.example.onlinemedicineserviceOldVedrsion.HomeActivity;
import com.example.onlinemedicineserviceOldVedrsion.Model.Users;
import com.example.onlinemedicineserviceOldVedrsion.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CustomerLoginPage extends AppCompatActivity {

    EditText phonenumberText, passwordText;
    TextView clickText;
    private String text;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customerloginpage);

        phonenumberText = findViewById(R.id.phonenumberText);
        passwordText = findViewById(R.id.passwordText);
        loginButton = findViewById(R.id.loginButton);
        clickText = findViewById(R.id.clickHere);
        text = clickText.getText().toString();

        SpannableString spannableString = new SpannableString(text);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {

                Intent intent = new Intent(textView.getContext(), SignupforCustomer.class);
                startActivity(intent);

            }

            public void updateDrawState(TextPaint paint) {

                super.updateDrawState(paint);
                paint.setUnderlineText(false);
            }
        };
        spannableString.setSpan(clickableSpan, 16, 26, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        clickText.setText(spannableString);
        clickText.setMovementMethod(LinkMovementMethod.getInstance());
        clickText.setHighlightColor(Color.TRANSPARENT);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validateUser();
            }
        });

    }

    private void validateUser() {

        if (phonenumberText.getText().toString().isEmpty() || passwordText.getText().toString().isEmpty()) {

            Toast.makeText(CustomerLoginPage.this, "Fields are empty!", Toast.LENGTH_SHORT).show();

        } else {

            //check in the database;
            // *if authentication succed then login is successful. else wrong username and password.

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference users = database.getReference("Users");

            users.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    if (dataSnapshot.child(phonenumberText.getText().toString()).exists()) {

                        Users user = dataSnapshot.child(phonenumberText.getText().toString()).getValue(Users.class);

                        if (user.getPassword().equals(passwordText.getText().toString())) {

                             Intent intent  = new Intent(CustomerLoginPage.this, HomeActivity.class);
                             startActivity(intent);

                        } else {

                            Toast.makeText(CustomerLoginPage.this, "Login Failed", Toast.LENGTH_LONG).show();

                        }
                    } else {

                        Toast.makeText(CustomerLoginPage.this, "User doesn't exist! Create new account.",
                                Toast.LENGTH_LONG).show();

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {


                }
            });

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        if (requestCode == 1) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                // trackUserLocation();

            } else {

            }

        }

    }


}