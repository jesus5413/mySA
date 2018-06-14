package com.group.mysa.Database;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

/**
 * @author jesusnieto
 */
public class Database {
    private static DatabaseReference mDatabase;

    /**
     * Stores user information when signing up into node in the database.
     * @param email
     * @param password
     * @param confirmPassword
     * @param firstName
     * @param lastName
     * @param uID
     */
    public static void storeUserInfoInDatabase(String email, String password, String confirmPassword, String firstName, String lastName, String uID){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        HashMap<String, String> userInfo = new HashMap<>();
        userInfo.put("Email", email);
        userInfo.put("Password", password);
        userInfo.put("ConfirmPassword", confirmPassword);
        userInfo.put("FirstName", firstName);
        userInfo.put("LastName", lastName);
        userInfo.put("UniqueID", uID);
        mDatabase.child("users").child(uID).setValue(userInfo);
    }


}
