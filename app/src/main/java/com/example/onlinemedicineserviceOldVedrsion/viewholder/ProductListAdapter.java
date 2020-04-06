package com.example.onlinemedicineserviceOldVedrsion.viewholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinemedicineserviceOldVedrsion.Model.Products;
import com.example.onlinemedicineserviceOldVedrsion.R;

import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {

    private List<Products> product;
    private Context context;

    public ProductListAdapter(List<Products> product,Context context) {
        this.product = product;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.products,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String productName = product.get(position).getProductName();
        String productDosageForm = product.get(position).getDosageForm();
        String productCompanyName = product.get(position).getCompanyId();
        String productChemicalFormula = product.get(position).getChemicalFormula();
        String productStrength = product.get(position).getStrength();
        String productPrice = product.get(position).getPrice();

        holder.setData(productName,productCompanyName,productDosageForm,productStrength,productChemicalFormula,productPrice);

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

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            productName = itemView.findViewById(R.id.productNameText);
            productChemicalFormula = itemView.findViewById(R.id.chemicalText);
            productCompanyName = itemView.findViewById(R.id.companyNameText);
            productDosageForm = itemView.findViewById(R.id.dosageFormText);
            productStrength = itemView.findViewById(R.id.strengthText);
            productPrice = itemView.findViewById(R.id.priceText);


        }

        void setData(String productNameText, String productCompanyNameText, String productDosageFormText,
                     String productStrengthText, String productChemicalFormulatext, String productPriceText){

            productName.setText(productNameText);
            productCompanyName.setText(productCompanyNameText);
            productDosageForm.setText(productDosageFormText);
            productStrength.setText(productStrengthText);
            productChemicalFormula.setText(productChemicalFormulatext);
            productPrice.setText(productPriceText);


        }
    }

}
