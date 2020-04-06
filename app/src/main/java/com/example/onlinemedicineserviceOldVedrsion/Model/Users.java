package com.example.onlinemedicineserviceOldVedrsion.Model;


public class Users {

    private String name;
    private String password;
    private String dob;
    private String email;
    private String firstName;
    private String lastName;



    public Users() {
    }


    public Users(String dob, String email, String firtsName, String lastName, String password) {

        setFirstName(firtsName);
        setLastName(lastName);
        setDob(dob);
        setEmail(email);
        setName(name);
        setPassword(password);

    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
