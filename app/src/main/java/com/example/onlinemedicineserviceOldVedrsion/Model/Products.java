package com.example.onlinemedicineserviceOldVedrsion.Model;

public class Products {

    private String chemicalFormula;
    private String companyId;
    private String dosageForm;
    private String price;
    private String productName;
    private String strength;

    public Products() {
    }

    public Products(String chemicalFormula, String companyId, String dosageForm, String price, String productName, String strength) {
        setChemicalFormula(chemicalFormula);
        setCompanyId(companyId);
        setDosageForm(dosageForm);
        setPrice(price);
        setProductName(productName);
        setStrength(strength);
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
}
