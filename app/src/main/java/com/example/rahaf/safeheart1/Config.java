package com.example.rahaf.safeheart1;

public class Config {

    public static final String URL = "https://safeheart996.000webhostapp.com/safeheart/";
    public static final String LOGIN_URL = URL+"family_login.php";
    public static final String PATIENT_STATUS = URL+"patient_status.php";


    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";


    //If server response is equal to this that means login is successful
    public static final String LOGIN_SUCCESS = "success";

    //Keys for Sharedpreferences
    //This would be the name of our shared preferences
    public static final String SHARED_PREF_NAME = "myloginapp";

    //This would be used to store the username of current logged in user
    public static final String USER_SHARED_PREF = "user";

    //We will use this to store the boolean in sharedpreference to track user is loggedin or not
    public static final String LOGGEDIN_SHARED_PREF = "loggedin";
}
