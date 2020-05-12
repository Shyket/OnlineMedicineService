package com.example.onlinemedicineservice;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.onlinemedicineservice.Model.Products;

public class ProductDetails extends AppCompatActivity {

    TextView productName;
    TextView productDosageForm;
    TextView productCompanyName;
    TextView productChemicalFormula;
    TextView productStrength;
    TextView productPrice;
    Button addButton;
    Products product;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_details);

        product = getIntent().getExtras().getParcelable("currentProduct");

    }

    @Override
    protected void onResume() {
        super.onResume();
        productName = findViewById(R.id.productNameText);
        productChemicalFormula = findViewById(R.id.chemicalText);
        productCompanyName = findViewById(R.id.companyNameText);
        productDosageForm = findViewById(R.id.dosageFormText);
        productStrength = findViewById(R.id.strengthText);
        productPrice = findViewById(R.id.priceText);
       // addButton = findViewById(R.id.addButton);


        productName.setText(product.getProductName());
        productCompanyName.setText(product.getCompanyId());
        productDosageForm.setText(product.getDosageForm());
        productStrength.setText(product.getStrength());
        productChemicalFormula.setText(product.getChemicalFormula());
        productPrice.setText(product.getPrice());

    }
}
