package com.example.onlinemedicineservice.ui.store;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinemedicineservice.Model.Products;
import com.example.onlinemedicineservice.R;
import com.example.onlinemedicineservice.RecyclerviewAdapter;

import java.util.ArrayList;
import java.util.List;

public class StoreFragment extends Fragment {

    private StoreViewModel homeViewModel;
    private SearchView searchView;
    private RecyclerView recyclerView;
    private List<Products> productList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(StoreViewModel.class);
        View root = inflater.inflate(R.layout.fragment_store, container, false);

        searchView = root.findViewById(R.id.searchviewId);
        recyclerView = root.findViewById(R.id.recyclerview);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        productList.add(new Products("kdjb","ldjnf"));
        productList.add(new Products("kdjb","ldjnf"));
        productList.add(new Products("ldsknc","gfgfg"));
        productList.add(new Products("ldsknc","gfgfg"));
        productList.add(new Products("ldsknc","gfgfg"));

        RecyclerviewAdapter adapter = new RecyclerviewAdapter(productList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        return root;
    }
}
