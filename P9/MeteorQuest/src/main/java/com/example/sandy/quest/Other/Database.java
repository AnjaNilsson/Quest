package com.example.sandy.quest.Other;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class Database {

    public static FirebaseDatabase database;
    public static DatabaseReference rootReference;


    public Database(){
        database = FirebaseDatabase.getInstance();
        rootReference = database.getReference();
    }

    public static DatabaseReference getDatabaseRootReference(){
        return rootReference;
    }

}
