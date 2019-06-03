package com.example.sandy.quest.Minigames;

import android.app.Activity;
import android.content.Intent;


public class Minigame {
    /*
    Set static boolean gameRunning to false and minigameDone 1,2 and 3 to true after each game!!!
     */


    public void startGame(String game, Activity activity){

        if(game.equals("1")) {
            Intent intent = new Intent(activity, SoundBite.class);
            activity.startActivity(intent);
        }

        if(game.equals("2")) {
            Intent intent = new Intent(activity, ChargeUp.class);
            activity.startActivity(intent);
        }

        if(game.equals("3")) {
            Intent intent = new Intent(activity, MazeMe.class);
            activity.startActivity(intent);
        }

        if(game.equals("4")) {
            Intent intent = new Intent(activity, TiltGameStart.class);
            activity.startActivity(intent);
        }

      /*  if(game.equals("5")) {
            Intent intent = new Intent(activity, BalanceGame.class);
            activity.startActivity(intent);
        }*/



    }


}
