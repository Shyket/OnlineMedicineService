package com.example.onlinemedicineservice;

import java.util.Random;

public class PasswordFactory {

    private String AlphaNumericString ;

    private StringBuilder stringBuilder;

    public PasswordFactory(){
        AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "abcdefghijklmnopqrstuvwxyz"
                + "1234567890"
                + "!@#$%^&*<>?()[]" ;
    }

    public String makePassword(String password){

        String subString1 = password.substring(0,3);
        String subString2 = password.substring(3);

        return generateString(3) + subString1 + generateString(5) + subString2 + generateString(7);

    }

    private String generateString(int n){

        Random random = new Random();

        stringBuilder = new StringBuilder(n);
        for(int i = 0; i < n; i++){

            stringBuilder.append(AlphaNumericString.charAt(random.nextInt(75)));

        }
        return stringBuilder.toString();

    }

    public String retrievePassword(String password){

        return password.substring(3,6) + password.substring(11,password.length()-7);


    }




}
