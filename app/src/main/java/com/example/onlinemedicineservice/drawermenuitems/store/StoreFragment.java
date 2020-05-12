package com.example.onlinemedicineservice.drawermenuitems.store;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinemedicineservice.Model.Products;
import com.example.onlinemedicineservice.ProductDetails;
import com.example.onlinemedicineservice.R;
import com.example.onlinemedicineservice.viewholder.CartAdapter;
import com.example.onlinemedicineservice.viewholder.StoreAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class StoreFragment extends Fragment implements StoreAdapter.onProductClick{

    private SearchView searchView;
    private RecyclerView recyclerView;
    private String searchViewText;
    private ArrayList<Products> productList = new ArrayList<Products>();
    private static ArrayList<Products> savedProductList = new ArrayList<>();

    public StoreFragment(){

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView  =  inflater.inflate(R.layout.fragment_store, container, false);
        assignId(rootView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        if(savedProductList != null) {
            productList = savedProductList;
            setAdapterForRecycler(productList);
        }


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


        return rootView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        savedProductList = productList;
    }


    private void assignId(View view) {

        recyclerView = view.findViewById(R.id.recyclerview);
        searchView =  view.findViewById(R.id.searchviewId);

    }

    private void searchTheQuery(String query){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference referenceOfProduct = database.getReference("Products");
        productList = new ArrayList<>();

        referenceOfProduct.orderByChild("product-Name").equalTo(query).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                Products product = getProductsOnSearch(dataSnapshot);
                product.setProductId(dataSnapshot.getKey());
                productList.add(product);
                setAdapterForRecycler(productList);

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

    private void setAdapterForRecycler(List<Products> productListForAdapter) {
        StoreAdapter adapter = new StoreAdapter(productListForAdapter, getContext(),this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private Products getProductsOnSearch(@NonNull DataSnapshot dataSnapshot) {
        String productName = (String) dataSnapshot.child("product-Name").getValue();
        String companyName = (String) dataSnapshot.child("company-id").getValue();
        String dosageForm = (String) dataSnapshot.child("dosage-form").getValue();
        String strength = (String) dataSnapshot.child("strength").getValue();
        String chemicalFormula = (String) dataSnapshot.child("chemical-formula").getValue();
        String price = (String) dataSnapshot.child("price").getValue();
        String quantity = (String) dataSnapshot.child("quantity").getValue();

        Products product = new Products(chemicalFormula,companyName,dosageForm,price,productName,quantity,strength);
        product.setProductId(dataSnapshot.getKey());
        return product;
    }



    @Override
    public void onClickListener(int position, Products product) {
        Intent intent = new Intent(getContext(), ProductDetails.class);
        Bundle args = new Bundle();
        args.putParcelable("currentProduct",product);
        intent.putExtras(args);
        startActivity(intent);
    }
}
