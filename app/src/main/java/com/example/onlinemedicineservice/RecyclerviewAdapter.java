package com.example.onlinemedicineservice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinemedicineservice.Model.Products;

import java.util.List;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.ViewHolder> {

    private List<Products> product;
    private Context context;

    public RecyclerviewAdapter(List<Products> product) {
        this.product = product;
        //this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.products,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String productNameText = product.get(position).getProductName();
        String productDescText = product.get(position).getProductDesc();
        holder.setData(productNameText,productDescText);


    }

    @Override
    public int getItemCount() {
        return product.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView productName,productDesc;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productName =(TextView) itemView.findViewById(R.id.productNameText);
            productDesc =(TextView) itemView.findViewById(R.id.productDescText);


        }

        public void setData(String productNameText,String productDescText){

            productName.setText(productNameText);
            productDesc.setText(productDescText);

        }
    }

}
