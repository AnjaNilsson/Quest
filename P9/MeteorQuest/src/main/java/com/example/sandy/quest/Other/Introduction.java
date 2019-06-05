package com.example.sandy.quest.Other;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.sandy.quest.NavigationMethod.NavigationActivity;
import com.example.sandy.quest.R;

import java.io.IOException;

public class Introduction extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    String file = "one";
    Button playAgain;
    Button proceedToNav;
    ImageButton callButton;
    boolean calling = false;
    boolean playerRoleReady = false;
    TextView txtView;
    String playerRole;
    android.support.constraint.ConstraintLayout background;
    int counterShowButton = 0;
    Button startGame;
    int counter = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);
        mediaPlayer = new MediaPlayer();

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        SharedPreferences shared = getSharedPreferences("your_file_name", MODE_PRIVATE);
        playerRole = (shared.getString("PLAYERROLE", ""));

        playAgain = (Button) findViewById(R.id.playAgain);
        proceedToNav = (Button) findViewById(R.id.proceedToNav);
        callButton = (ImageButton) findViewById(R.id.callButton);
        txtView = (TextView)findViewById(R.id.txtView);
        background = (android.support.constraint.ConstraintLayout) findViewById(R.id.background);
        startGame = (Button) findViewById(R.id.startGame);

        playAgain.setVisibility(View.GONE);
        proceedToNav.setVisibility(View.GONE);
        startGame.setVisibility(View.INVISIBLE);


        callPlayer();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    public void showButton(){
        counterShowButton++;
        if(counterShowButton == 1){
            startGame.setVisibility(View.VISIBLE);
        }

        if(counterShowButton == 2){
            counterShowButton = 0;
            startGame.setVisibility(View.INVISIBLE);
        }
    }

   public void mediaplayerListen(){
       mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

           @Override
           public void onCompletion(MediaPlayer mp) {
               counter ++;
               if(counter >= 2 && playerRoleReady == true){
                   txtView.setText("Touch the screen to start the game");
                   proceedToNav.setVisibility(View.GONE);
                   background.setOnClickListener(new View.OnClickListener()
                   {
                       @Override
                       public void onClick(View v)
                       {
                           showButton();
                       }
                   });

               }
               else{
                   proceedToNav.setVisibility(View.VISIBLE);
               }
               background.setBackgroundColor(Color.WHITE);
               playAgain.setClickable(true);
               playAgain.setVisibility(View.VISIBLE);
               if(playerRole .equals("1") || playerRole .equals("2") || playerRole .equals("3") || playerRole .equals("4")){
                   navigatorRole();
               }
           }

       });
   };

    public void startNavigation(View v){
        Intent intent = new Intent(Introduction.this, NavigationActivity.class);
        startActivity(intent);
    }

    public void navigatorRole(){
        txtView.setText("touch screen to start game");
        proceedToNav.setVisibility(View.GONE);
        background.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                showButton();
            }
        });
    }


    public void introFile(View v){

       playAgain.setVisibility(View.VISIBLE);
       proceedToNav.setVisibility(View.VISIBLE);
       callButton.setVisibility(View.GONE);

       mediaPlayer.stop();
       mediaPlayer.reset();
       mediaPlayer.release();

       mediaPlayer = new MediaPlayer();

       mediaPlayer.setAudioStreamType(AudioManager.STREAM_VOICE_CALL);
       if(playerRoleReady == false) {
           mediaplayerListen();
           if(playerRole .equals("1") || playerRole .equals("2") || playerRole .equals("3") || playerRole .equals("4")){
               file = "intro_navigation";
           }
            background.setBackgroundColor(Color.BLACK);
            playAgain.setClickable(false);
            playAgain.setVisibility(View.GONE);
            proceedToNav.setVisibility(View.GONE);
            txtView.setText("Click to hear the message again or start game");
       }
       whichSoundFile();
       mediaPlayer.start();
   }


    public void whichSoundFile(){
        try {
            //set datasource to MediaPlayer
            mediaPlayer.setDataSource(this, Uri.parse("android.resource://" + this.getPackageName() + "/raw/" + file));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void playAgain(View v){
        introFile(null);
    }

    public void proceedToNav(View v){
        if(playerRoleReady == true){
            Intent intent = new Intent(Introduction.this, NavigationActivity.class);
        startActivity(intent);
        }
        playerRoleReady = true;
        callPlayer();
    }

    public void playSoundfile(View v){
        mediaPlayer.stop();
        mediaPlayer.reset();
        mediaPlayer.release();
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_VOICE_CALL);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        try {
            //set datasource to MediaPlayer
            mediaPlayer.setDataSource(this, Uri.parse("android.resource://" + this.getPackageName() + "/raw/" + file));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.start();

    }

    public void callPlayer(){
        if(playerRoleReady == true){
            callButton.setVisibility(View.VISIBLE);
        }

        if(calling != true) {
            txtView.setText("Accept the call");
            mediaPlayer = MediaPlayer.create(this, R.raw.ringtone);
            mediaPlayer.start();
            calling = true;

        }
    }

    public void callPlayer2(View v){
        calling = false;
        playerRoleReady = true;

        if(calling != true && playerRole .equals("1") || playerRole .equals("2") || playerRole .equals("3") || playerRole .equals("4")) {
            callButton.setVisibility(View.VISIBLE);
            playAgain.setVisibility(View.GONE);
            proceedToNav.setVisibility(View.GONE);
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.release();
            mediaPlayer = new MediaPlayer();
            txtView.setText("Accept the call");
            mediaPlayer = MediaPlayer.create(this, R.raw.ringtone);
            mediaPlayer.start();
            calling = true;
        }
    }

}


