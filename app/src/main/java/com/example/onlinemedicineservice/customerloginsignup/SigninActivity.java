package com.example.onlinemedicineservice.customerloginsignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlinemedicineservice.HomeActivity;
import com.example.onlinemedicineservice.R;
import com.example.onlinemedicineservice.sqlDatabase.ProductViewModel;
import com.example.onlinemedicineservice.sqlDatabase.SuggestionModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class SigninActivity extends AppCompatActivity {

    public static List<String> SUGGETION_FOR_SEARCH; //suggetion list
    ProductViewModel productViewModel;
    private String text;

    //UI components
    private EditText  passwordText;
    private EditText emailText;
    private TextView clickText;
    private Button loginButton;

    interface DatabaseCallback {
        void onCallBack(Boolean update);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customerloginpage);

        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        SUGGETION_FOR_SEARCH = productViewModel.getAllSuggestion();
        searchForUpdate(update -> {
            if(update) {
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference ref = firebaseDatabase.getReference("Products");
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            String suggestion = ds.child("productname").getValue(String.class);
                            if (suggestion != null) {
                                productViewModel.insertSuggestion(new SuggestionModel(suggestion));
                                if(!SUGGETION_FOR_SEARCH.contains(suggestion)){
                                    SUGGETION_FOR_SEARCH.add(suggestion);
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.e("database",databaseError.getMessage());
                    }
                });
            }

        });

        checkForCurrentUser();

    }

    private void checkForCurrentUser() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser != null) {
            if (currentUser.isEmailVerified()) {
                gotoHomeActivity(currentUser.getUid());
            }
        }
    }

    private void searchForUpdate(final DatabaseCallback databaseCallback) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference refToUpdate = firebaseDatabase.getReference("Update");
        refToUpdate.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                databaseCallback.onCallBack(dataSnapshot.getValue(Boolean.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



    @Override
    protected void onResume() {
        super.onResume();

        //initializing UI elements
        emailText = findViewById(R.id.emailText);
        passwordText = findViewById(R.id.passwordText);
        loginButton = findViewById(R.id.loginButton);
        clickText = findViewById(R.id.clickHere);
        text = clickText.getText().toString();

        actionOnClickText(clickText.getText().toString(),clickText);

        loginButton.setOnClickListener(view -> validateUser());

    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    private void validateUser() {

        FirebaseAuth auth = FirebaseAuth.getInstance();

        if (fieldscheck()) {
            auth.signInWithEmailAndPassword(
                    emailText.getText().toString(),
                    passwordText.getText().toString())
                    .addOnCompleteListener(this, task -> {

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
                    });

        }

    }

    private void gotoHomeActivity(String userid) {
        Intent intent  = new Intent(SigninActivity.this, HomeActivity.class);
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
}