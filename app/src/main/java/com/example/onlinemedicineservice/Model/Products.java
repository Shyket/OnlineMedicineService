package com.example.onlinemedicineservice.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Products implements Parcelable {

    private String chemicalFormula;
    private String companyId;
    private String dosageForm;
    private String price;
    private String productName;
    private String strength;
    private String quantity;
    private String productId;
    private int selectedQuantity;

    public Products() {
    }

    public Products(String chemicalFormula, String companyId, String dosageForm, String price, String productName, String quantity,
                    String strength) {
        setChemicalFormula(chemicalFormula);
        setCompanyId(companyId);
        setDosageForm(dosageForm);
        setPrice(price);
        setProductName(productName);
        setStrength(strength);
        setQuantity(quantity);
    }

    protected Products(Parcel in) {
        chemicalFormula = in.readString();
        companyId = in.readString();
        dosageForm = in.readString();
        price = in.readString();
        productName = in.readString();
        strength = in.readString();
        quantity = in.readString();
        productId = in.readString();
        selectedQuantity = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(chemicalFormula);
        dest.writeString(companyId);
        dest.writeString(dosageForm);
        dest.writeString(price);
        dest.writeString(productName);
        dest.writeString(strength);
        dest.writeString(quantity);
        dest.writeString(productId);
        dest.writeInt(selectedQuantity);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Products> CREATOR = new Creator<Products>() {
        @Override
        public Products createFromParcel(Parcel in) {
            return new Products(in);
        }

        @Override
        public Products[] newArray(int size) {
            return new Products[size];
        }
    };

    public int getSelectedQuantity() {
        return selectedQuantity;
    }

    public void setSelectedQuantity(int selectedQuantity) {
        this.selectedQuantity = selectedQuantity;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }


    public String getChemicalFormula() {
        return chemicalFormula;
    }

    public void setChemicalFormula(String chemicalFormula) {
        this.chemicalFormula = chemicalFormula;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getDosageForm() {
        return dosageForm;
    }

    public void setDosageForm(String dosageForm) {
        this.dosageForm = dosageForm;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
