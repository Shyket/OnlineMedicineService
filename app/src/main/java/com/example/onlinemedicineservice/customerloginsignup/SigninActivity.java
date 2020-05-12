package com.example.onlinemedicineservice.customerloginsignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlinemedicineservice.HomeActivity;
import com.example.onlinemedicineservice.Model.Users;
import com.example.onlinemedicineservice.PasswordFactory;
import com.example.onlinemedicineservice.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.onlinemedicineservice.common.common.currentUser;

public class SigninActivity extends AppCompatActivity {

    EditText  passwordText;
    EditText emailText;
    TextView clickText;
    private String text;
    Button loginButton;
    public Users user;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customerloginpage);

        auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser != null){ gotoHomeActivity(currentUser.getUid()); }
    }

    @Override
    protected void onResume() {
        super.onResume();

        emailText = findViewById(R.id.emailText);
        passwordText = findViewById(R.id.passwordText);
        loginButton = findViewById(R.id.loginButton);
        clickText = findViewById(R.id.clickHere);
        text = clickText.getText().toString();


        actionOnClickText(clickText.getText().toString(),clickText);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validateUser();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    private void validateUser() {

        if (fieldscheck()) {
            auth.signInWithEmailAndPassword(
                    emailText.getText().toString(),
                    passwordText.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {

                                if (auth.getCurrentUser().isEmailVerified()) {

                                    gotoHomeActivity(auth.getCurrentUser().getUid());

                                }
                                else {
                                    Toast.makeText(getApplicationContext(), "Verify Your Account",
                                            Toast.LENGTH_LONG).show();
                                }

                            }
                            else {

                                Toast.makeText(getApplicationContext(), task.getException().getMessage(),
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    });

        }

    }

    private void gotoHomeActivity(String userid) {
        Bundle args = new Bundle();
        args.putString("userid",userid);
        Intent intent  = new Intent(SigninActivity.this, HomeActivity.class);
        intent.putExtras(args);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private boolean fieldscheck() {

        if(emailText.getText().toString().isEmpty()){
            emailText.setError("Field can't be empty");
            return false;
        }
        if(passwordText.getText().toString().isEmpty()){
            emailText.setError("Field can't be empty");
            return false;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(emailText.getText().toString()).matches()){
            emailText.setError("invalid email address");
            return false;
        }
        return true;
    }

    private void actionOnClickText(String text, TextView clickText){

        SpannableString spannableString = new SpannableString(text);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {

                Intent intent = new Intent(textView.getContext(), SignupActivity.class);
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

    }

    private boolean validatePassword(String password){

        PasswordFactory passwordFactory = new PasswordFactory();

        return passwordFactory.retrievePassword(password).equals(passwordText.getText().toString());
    }



}