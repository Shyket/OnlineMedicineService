package com.example.onlinemedicineservice.drawermenuitems.profile;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.example.onlinemedicineservice.Model.FirebaseUserModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileViewModel extends ViewModel {

    interface FetchUser{
        void onCallBack(FirebaseUserModel user);
    }

    private void getUserInstance(final ProfileFragment.FetchUser fetchUser, final String currentUserID) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref = database.getReference("Users");


        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                fetchUser.onCallBack(dataSnapshot.child(currentUserID).getValue(FirebaseUserModel.class));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

    }


}