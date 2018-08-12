package com.group.mysa.Database;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.group.mysa.Model.FeedInfo;

import java.util.HashMap;
import java.util.Map;

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

    public static void newKeyAndValue(String uid, String postID, FeedInfo postInfo){
        mDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(uid).child("attend");
        Map<String, Object> attend = new HashMap<String, Object>();
        attend.put(postID, postInfo);
        mDatabase.updateChildren(attend);
    }

    public static void likeDBFeature(String postId, int counter){
        mDatabase = FirebaseDatabase.getInstance().getReference().child("feed").child(postId);
        counter = counter + 1;
        Map<String, Object> likes = new HashMap<>();
        likes.put("counter", counter);
        mDatabase.updateChildren(likes);
    }

    public static void storeLikedPosts(String uid, String postUid, FeedInfo postInfo){
        mDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(uid).child("liked");
        Map<String, Object> liked = new HashMap<>();
        liked.put(postUid, postInfo);
        mDatabase.updateChildren(liked);
    }

    public static void likeTwitterDBFeature(String postId, int counter){
        mDatabase = FirebaseDatabase.getInstance().getReference().child("twitter").child(postId);
        counter = counter + 1;
        Map<String, Object> likes = new HashMap<>();
        likes.put("counter", counter);
        mDatabase.updateChildren(likes);
    }

    public static void storeTwitterLikedPosts(String uid, String postUid, FeedInfo postInfo){
        mDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(uid).child("liked");
        Map<String, Object> liked = new HashMap<>();
        postInfo.setTitle(postInfo.getUser());
        liked.put(postUid, postInfo);
        mDatabase.updateChildren(liked);
    }




}
