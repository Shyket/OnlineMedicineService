package com.example.onlinemedicineservice.drawermenuitems.cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinemedicineservice.Model.FirebaseOrderModel;
import com.example.onlinemedicineservice.R;
import com.example.onlinemedicineservice.sqlDatabase.SQLProductModel;
import com.example.onlinemedicineservice.sqlDatabase.ProductViewModel;
import com.example.onlinemedicineservice.viewholder.CartAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CartFragment extends Fragment{

    private RecyclerView recyclerView;
    private Button confirmButton;
    private ProductViewModel productViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_cart, container, false);

        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        confirmButton = root.findViewById(R.id.CONFIRM_BUTTON);
        recyclerView = root.findViewById(R.id.searchedproduct_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        CartAdapter adapter = new CartAdapter(getContext());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        List<SQLProductModel> SQLProductModelList = productViewModel.getAllProduct();

        if(SQLProductModelList != null){
            adapter.setProductList(SQLProductModelList);
        }else{
            Toast.makeText(getContext(), "Empty!", Toast.LENGTH_SHORT).show();
        }

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                SQLProductModelList.remove(viewHolder.getAdapterPosition());
                productViewModel.deleteProduct(adapter.getItemAt(viewHolder.getAdapterPosition()));

            }
        }).attachToRecyclerView(recyclerView);

        confirmButton.setOnClickListener(v -> {



            List<String> productIDs = new ArrayList<>();
            for(int i = 0; i<SQLProductModelList.size(); i++){
                productIDs.add(SQLProductModelList.get(i).getProductId());
            }

            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Orders");

            ref.child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
                    .setValue(new FirebaseOrderModel(java.time.LocalDateTime.now(),
                                                    FirebaseAuth.getInstance().getCurrentUser().getUid(),
                                                     "dd","01724156717",productIDs,
                                                     9.0,7.8,"1200.8",
                                                     "cashOn Delivary"));
        });


        return root;
    }

    private String generateOrderId() {

        return null;
    }


}
