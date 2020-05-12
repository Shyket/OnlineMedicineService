package com.example.onlinemedicineservice.viewholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinemedicineservice.Model.Products;
import com.example.onlinemedicineservice.R;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private List<Products> product;
    private Context context;
    public static List<Products> cartpProducts;


    public CartAdapter(List<Products> product, Context context) {
        this.product = product;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_details,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(product.get(position));
    }

    @Override
    public int getItemCount() {
        return product.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView productName;
        TextView productDosageForm;
        TextView productCompanyName;
        TextView productChemicalFormula;
        TextView productStrength;
        TextView productPrice;
        Button addButton;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            productName = itemView.findViewById(R.id.productNameText);
            productChemicalFormula = itemView.findViewById(R.id.chemicalText);
            productCompanyName = itemView.findViewById(R.id.companyNameText);
            productDosageForm = itemView.findViewById(R.id.dosageFormText);
            productStrength = itemView.findViewById(R.id.strengthText);
            productPrice = itemView.findViewById(R.id.priceText);
          //  addButton = itemView.findViewById(R.id.addButton);

        }

        void setData(final Products product){

            productName.setText(product.getProductName());
            productCompanyName.setText(product.getCompanyId());
            productDosageForm.setText(product.getDosageForm());
            productStrength.setText(product.getStrength());
            productChemicalFormula.setText(product.getChemicalFormula());
            productPrice.setText(product.getPrice());



        }

    }

}
