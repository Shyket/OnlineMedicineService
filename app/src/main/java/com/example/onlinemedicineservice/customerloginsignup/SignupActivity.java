package com.example.onlinemedicineservice.customerloginsignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.onlinemedicineservice.Model.Users;
import com.example.onlinemedicineservice.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.onlinemedicineservice.PasswordFactory;

public class SignupActivity extends AppCompatActivity {

    private Button signupButton;
    private TextInputEditText firstname;
    private TextInputEditText lastname;
    private TextInputEditText email;
    private TextInputEditText phonenumber;
    private TextInputEditText password;
    private TextInputEditText repassword;
    private PasswordFactory passwordFactory;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupfor_customer);

        assignID();
        passwordFactory = new PasswordFactory();
        auth = FirebaseAuth.getInstance();

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccount();
            }
        });

    }

    private void createAccount() {

        if(fieldscheck()) {
            auth.createUserWithEmailAndPassword(
                    email.getText().toString(),
                    password.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {

                                sendVerificationEmail();

                                if(assignUserInDatabase(auth.getCurrentUser().getUid())) {
                                    Toast.makeText(getApplicationContext(),
                                            "Account Created! Please Check your email for verification",
                                            Toast.LENGTH_LONG).show();
                                    gotoSignInActivity();
                                    }

                            } else {
                                if(!task.getException().getMessage().isEmpty()) {
                                    Toast.makeText(SignupActivity.this, task.getException().getMessage(),
                                            Toast.LENGTH_SHORT).show();
                                }
                            }

                        }
                    });
        }

    }

    private void sendVerificationEmail() {

        if(auth.getCurrentUser() != null) {

            auth.getCurrentUser().sendEmailVerification()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (!task.isSuccessful()) {

                                if(!task.getException().getMessage().isEmpty()) {
                                    Toast.makeText(getApplicationContext(), task.getException().getMessage(),
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
        }

    }

    private void gotoSignInActivity() {
        Intent intent = new Intent(getApplicationContext(), SigninActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        SignupActivity.this.finish();
    }

    private boolean assignUserInDatabase(String Uid) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference userReference = database.getReference("Users");

        Users user = new Users(
                email.getText().toString(),
                firstname.getText().toString(),
                lastname.getText().toString(),
                passwordFactory.makePassword(password.getText().toString()),
                phonenumber.getText().toString()
                );

        if(!Uid.isEmpty()){
            userReference.child(Uid).setValue(user);
            return true;
        }else{
            return false;
        }

    }
    private void assignID() {
        signupButton = findViewById(R.id.signupbutton);
        firstname = findViewById(R.id.name1Edittext);
        lastname = findViewById(R.id.name2Edittext);
        email = findViewById(R.id.emailEdittext);
        phonenumber = findViewById(R.id.phonenumberEdittext);
        password = findViewById(R.id.passwordEdittext);
        repassword = findViewById(R.id.repasswordEdittext);
    }
    private boolean fieldscheck(){

        if(email.getText().toString().isEmpty()){
            email.setError("This Field can't be empty");
            return false;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
            email.setError("Give a valid email Address");
            return false;
        }
        if(phonenumber.getText().toString().isEmpty()){
            phonenumber.setError("This Field can't be empty");
            return false;
        }
        if(phonenumber.getText().toString().length() < 11){
            phonenumber.setError("Invalid Phone number");
            return false;
        }
        if(password.getText().toString().isEmpty()){
            password.setError("This Field can't be empty");
            return false;
        }
        if(password.getText().toString().length() < 8){
            password.setError("Password should be at least 8 charecters");
            return false;
        }
        if(repassword.getText().toString().isEmpty()){
            repassword.setError("This Field can't be empty");
            return false;
        }
        if(!password.getText().toString().equals(repassword.getText().toString())){
            repassword.setError("This field should match with Password field");
            return false;
        }

        return true;
    }


}
