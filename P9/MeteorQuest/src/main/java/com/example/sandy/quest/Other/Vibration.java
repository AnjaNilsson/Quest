package com.example.sandy.quest.Other;

import android.content.Context;
import android.os.Vibrator;



public class Vibration {

    Context context;

    //constructor that takes the context as a parameter
    public Vibration(Context context){
        this.context = context;
    }

    //method that vibrates phone. The duration 500 is in milliseconds
    public void vibrate(){
        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
            v.vibrate(500);

    }

}
