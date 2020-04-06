package com.example.onlinemedicineserviceOldVedrsion.drawermenuitems.store;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinemedicineserviceOldVedrsion.Model.Products;
import com.example.onlinemedicineserviceOldVedrsion.R;
import com.example.onlinemedicineserviceOldVedrsion.viewholder.ProductListAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class StoreFragment extends Fragment {

    private StoreViewModel homeViewModel;
    private SearchView searchView;
    private RecyclerView recyclerView;
    private String searchViewText;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(StoreViewModel.class);
        View root = inflater.inflate(R.layout.fragment_store, container, false);

        Toast.makeText(getContext(), "Welcome!", Toast.LENGTH_SHORT).show();


        recyclerView = root.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        searchView = (SearchView) root.findViewById(R.id.searchviewId);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchTheQuery(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                return false;
            }
        });

        return root;
    }

    private void searchTheQuery(String query){

        final List<Products> productList = new ArrayList<>();



        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference referenceOfProduct = database.getReference("Products");

        referenceOfProduct.orderByChild("product-Name").equalTo(query).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                String productName = (String) dataSnapshot.child("product-Name").getValue();
                String companyName = (String) dataSnapshot.child("company-id").getValue();
                String dosageForm = (String) dataSnapshot.child("dosage-form").getValue();
                String strength = (String) dataSnapshot.child("strength").getValue();
                String chemicalFormula = (String) dataSnapshot.child("chemical-formula").getValue();
                String price = (String) dataSnapshot.child("price").getValue();

                Products product = new Products(chemicalFormula,companyName,dosageForm,price,productName,strength);
                productList.add(product);

                ProductListAdapter adapter = new ProductListAdapter(productList,getContext());
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

}
