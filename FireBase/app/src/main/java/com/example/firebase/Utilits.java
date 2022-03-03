package com.example.firebase;

import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilits {

    public static boolean checkEmailForValidity(String email, String password, String passwordRepeat, String phone){

        if (password.equals(passwordRepeat)){
            Matcher mathcher =  VALID_EMAIL_ADDRESS_REGEX.matcher(email);
//            Log.println(Log.ASSERT, "1", String.valueOf(mathcher.find()));
            Matcher matcher2 = VALID_EMAIL_PASSWORD_REGEX.matcher(password);
//            Log.println(Log.ASSERT, "1", String.valueOf(matcher2.find()));
            Matcher matcher3 = VALID_EMAIL_PHONE_REGEX.matcher(phone);
//            Log.println(Log.ASSERT, "1", String.valueOf(matcher3.find()));

//            Log.println(Log.ASSERT, "1", String.valueOf((mathcher.find() && matcher2.find() && matcher3.find())));
            return (mathcher.find() && matcher2.find() && matcher3.find());
        }

        return false;
    }

    private  static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private  static final Pattern VALID_EMAIL_PASSWORD_REGEX = Pattern.compile("^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{8,18}$");
    private  static final Pattern VALID_EMAIL_PHONE_REGEX = Pattern.compile("^(\\s*)?(\\+)?([- _():=+]?\\d[- _():=+]?){10,14}(\\s*)?$");
}
