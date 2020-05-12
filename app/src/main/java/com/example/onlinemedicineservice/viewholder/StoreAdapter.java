package com.example.onlinemedicineservice.viewholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinemedicineservice.Model.Products;
import com.example.onlinemedicineservice.R;

import java.util.List;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.ViewHolder> {

    private List<Products> product;
    private Context context;
    private onProductClick onProductClick;

    public StoreAdapter(List<Products> product, Context context,onProductClick onProductClick ){
        this.product = product;
        this.context = context;
        this.onProductClick = onProductClick;
    }

    @NonNull
    @Override
    public StoreAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.products,parent,false);
        return new ViewHolder(view,onProductClick);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreAdapter.ViewHolder holder, int position) {
        holder.setData(product.get(position));
    }

    @Override
    public int getItemCount() {
        return product.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView productName;
        TextView productStrength;
        onProductClick onProductClick;
        Products product;

        ViewHolder(@NonNull View itemView,onProductClick onProductClick) {
            super(itemView);

            productName = itemView.findViewById(R.id.product_name);
            productStrength = itemView.findViewById(R.id.product_strength);
            this.onProductClick = onProductClick;
            itemView.setOnClickListener(this);

        }

        void setData(Products product){
            productName.setText(product.getProductName());
            productStrength.setText(product.getStrength());
            this.product = product;
        }

        @Override
        public void onClick(View v) {
            onProductClick.onClickListener(getAdapterPosition(),product);
        }
    }

    public interface onProductClick{
        void onClickListener(int position,Products product);
    }

}
