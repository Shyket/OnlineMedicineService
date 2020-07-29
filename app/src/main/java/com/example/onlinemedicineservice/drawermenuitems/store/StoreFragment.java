package com.example.onlinemedicineservice.drawermenuitems.store;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinemedicineservice.Model.FirebaseProductModel;
import com.example.onlinemedicineservice.ProductDetails;
import com.example.onlinemedicineservice.R;
import com.example.onlinemedicineservice.viewholder.StoreAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import static com.example.onlinemedicineservice.HomeActivity.PRODUCT_DETAILS_TAG;
import static com.example.onlinemedicineservice.customerloginsignup.SigninActivity.SUGGETION_FOR_SEARCH;


public class StoreFragment extends Fragment implements StoreAdapter.onProductClick{

    private SearchView searchView;
    private RecyclerView recyclerView;
    private String searchViewText;
    private ArrayList<FirebaseProductModel> productList = new ArrayList<FirebaseProductModel>();
    private static ArrayList<FirebaseProductModel> savedProductList = new ArrayList<>();
    private List<String> searchSuggetions;
    private ArrayAdapter<String> adapter;
    private ListView suggestionList;

    public StoreFragment(){}

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView  =  inflater.inflate(R.layout.fragment_store, container, false);
        initializeUIComponent(rootView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        if(savedProductList != null) {
            productList = savedProductList;
            setAdapterForRecycler(productList);
        }

        searchSuggetions = new ArrayList<>();
        searchSuggetions = SUGGETION_FOR_SEARCH;
        if(searchSuggetions == null){
            Toast.makeText(getContext(), "Null", Toast.LENGTH_SHORT).show();
        }
        adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,searchSuggetions);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                suggestionList.setAdapter(null);
                searchTheQuery(query);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                if(s.isEmpty()){
                    suggestionList.setAdapter(null);
                }else {
                    adapter.getFilter().filter(s);
                    suggestionList.setAdapter(adapter);
                }
                return false;
            }
        });

        suggestionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                searchView.setQuery((String)suggestionList.getItemAtPosition(position),false);
                suggestionList.setAdapter(null);
            }
        });


        return rootView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        savedProductList = productList;
    }


    private void initializeUIComponent(View view) {

        recyclerView = view.findViewById(R.id.searchedproduct_recycler_view);
        searchView =  view.findViewById(R.id.searchviewId);
        suggestionList = view.findViewById(R.id.suggetion_list_view);

    }

    private void searchTheQuery(String query){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference referenceOfProduct = database.getReference("Products");
        productList = new ArrayList<>();

        referenceOfProduct.orderByChild("productname").equalTo(query).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                FirebaseProductModel product = getProductsOnSearch(dataSnapshot);
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

    private void setAdapterForRecycler(List<FirebaseProductModel> productListForAdapter) {
        StoreAdapter adapter = new StoreAdapter(productListForAdapter, getContext(),this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private FirebaseProductModel getProductsOnSearch(@NonNull DataSnapshot dataSnapshot) {
        String productName = (String) dataSnapshot.child("productname").getValue();
        String companyName = (String) dataSnapshot.child("companyid").getValue();
        String dosageForm = (String) dataSnapshot.child("dosageform").getValue();
        String strength = (String) dataSnapshot.child("strength").getValue();
        String chemicalFormula = (String) dataSnapshot.child("chemicalformula").getValue();
        String price = (String) dataSnapshot.child("price").getValue();
        String quantity = (String) dataSnapshot.child("quantity").getValue();

        FirebaseProductModel product = new FirebaseProductModel(chemicalFormula,companyName,dosageForm,price,productName,quantity,strength);
        product.setProductId(dataSnapshot.getKey());
        return product;
    }



    @Override
    public void onClickListener(int position, FirebaseProductModel product) {

        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.host,

                new ProductDetails(product),PRODUCT_DETAILS_TAG).commit();
    }

}
