package com.example.onlinemedicineserviceOldVedrsion.customerloginsignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.onlinemedicineserviceOldVedrsion.Model.Users;
import com.example.onlinemedicineserviceOldVedrsion.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignupforCustomer extends AppCompatActivity {

    private Button signupButton;
    private TextInputEditText firstname,lastname,email,phonenumber,dob,password,repassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupfor_customer);

        signupButton = findViewById(R.id.signupbutton);

        firstname = findViewById(R.id.name1Edittext);
        lastname = findViewById(R.id.name2Edittext);
        email = findViewById(R.id.emailEdittext);
        phonenumber = findViewById(R.id.phonenumberEdittext);
        dob = findViewById(R.id.dobEdittext);
        password = findViewById(R.id.passwordEdittext);
        repassword = findViewById(R.id.repasswordEdittext);


        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                createAccount();

            }
        });

    }


    private boolean passwordCheck(String pass,String repass){

        if(pass.equals(repass)){
            return true;
        }

        return false;
    }

    private boolean fieldscheck(){

        if(dob.getText().toString().equals(null) || email.getText().toString().equals(null) ||
                firstname.getText().toString().equals(null) ||lastname.getText().toString().equals(null) || password.getText().toString().equals(null)
               ){

            return false;

        }else{

            if(phonenumber.getText().toString().length() != 11){

                phonenumber.setError("Invalid Phone Numnber");

            }


        }

        return true;

    }

    private void createAccount(){

        if(!fieldscheck()){

            Toast.makeText(SignupforCustomer.this, "Fields are empty", Toast.LENGTH_SHORT).show();

        }else {

            if (!passwordCheck(password.getText().toString(), repassword.getText().toString())) {

                Toast.makeText(SignupforCustomer.this, "Passwords didnt match", Toast.LENGTH_SHORT).show();

            } else {

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference users = database.getReference("Users");

                users.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.child(phonenumber.getText().toString()).exists()) {

                            Toast.makeText(SignupforCustomer.this, "User Already Exist!!", Toast.LENGTH_LONG).show();

                        } else {


                            Users user = new Users(dob.getText().toString(), email.getText().toString(), firstname.getText().toString(),
                                                   lastname.getText().toString(), password.getText().toString());

                            users.child(phonenumber.getText().toString()).setValue(user);

                            Toast.makeText(SignupforCustomer.this, "Account Created!!", Toast.LENGTH_LONG).show();


                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        }


    }

}
