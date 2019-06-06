package com.example.sandy.quest.Other;

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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sandy.quest.Minigames.Minigame;
import com.example.sandy.quest.R;

import java.io.IOException;

public class GameIntro extends AppCompatActivity {

    public TextView txtView;
    int counter = 10;
    public boolean run = false;
    String playerRole;
    MediaPlayer mediaPlayer;
    String game = "";
    String file = "one";
    public ImageButton acceptCall;
    public RelativeLayout background;
    public Button startGame;
    int counterShowButton = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        setContentView(R.layout.activity_game_intro);

        txtView = (TextView) findViewById(R.id.txtView);
        acceptCall = (ImageButton) findViewById(R.id.callButton);
        background = (RelativeLayout) findViewById(R.id.background);
        startGame = (Button) findViewById(R.id.moveOn);

        startGame.setVisibility(View.INVISIBLE);

        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            game = extras.getString("GAME");
        }

        SharedPreferences shared = getSharedPreferences("your_file_name", MODE_PRIVATE);
        playerRole = (shared.getString("PLAYERROLE", ""));
        mediaPlayer = new MediaPlayer();
        if(playerRole.equals("1")||playerRole.equals("2") || playerRole.equals("3")||playerRole.equals("4")){
            background.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    showButton();
                }
            });
        }

        callNavigators();

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

        if(counterShowButton == 4){
            counterShowButton = 0;
            startGame.setVisibility(View.INVISIBLE);
        }
    }

    public void callNavigators() {
        if (playerRole.equals("1") || playerRole.equals("2") || playerRole.equals("3") || playerRole.equals("4")) {
            txtView.setText("Accept the call");
            mediaPlayer = MediaPlayer.create(this, R.raw.ringtone);
            mediaPlayer.start();
        }
    }

    public void playSoundfile(View v){
        background.setBackgroundColor(Color.BLACK);
        txtView.setVisibility(View.INVISIBLE);
        acceptCall.setVisibility(View.INVISIBLE);
        mediaPlayer.stop();
        mediaPlayer.reset();
        mediaPlayer.release();
        mediaPlayer = new MediaPlayer();
        try {
            //mediaPlayer.setAudioStreamType(AudioManager.STREAM_VOICE_CALL);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        if(playerRole .equals("1") || playerRole .equals("2") || playerRole .equals("3") || playerRole .equals("4")){
            if(game.equals("1")){
                file = "sound_puzzle_intro"; // SoundBite
                whichSoundFile();
            }
            if(game.equals("2")){
                mediaPlayer.reset();
                file = "balance_intro";  // BalanceGame
                whichSoundFile();
            }
            if(game.equals("3")){
                mediaPlayer.reset();
                file = "maze_intro"; // MazeGame
                whichSoundFile();
            }
            if(game.equals("4")){
                mediaPlayer.reset();
                file = "headtiltintro"; // HeadTilt
                whichSoundFile();
            }
            if(game.equals("5")){
                mediaPlayer.reset();
                file = "chargebattery"; // ChargeUp
                whichSoundFile();
            }
        }
    };


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
        //detect when soundfile is done playing
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                background.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        showButton();
                    }
                });

                background.setBackgroundColor(Color.WHITE);
                acceptCall.setVisibility(View.VISIBLE);
                txtView.setVisibility(View.VISIBLE);
                txtView.setText("Play again");
                acceptCall.setBackgroundResource(R.drawable.play);
            }

        });

        mediaPlayer.start();
    }

    public void startGame(View v){
        if(game.equals("1")){
            Minigame minigame1 = new Minigame();
            minigame1.startGame("1",this);
        }
        if(game.equals("2")){
            Minigame minigame1 = new Minigame();
            minigame1.startGame("2",this);
        }
        if(game.equals("3")){
            Minigame minigame1 = new Minigame();
            minigame1.startGame("3",this);
        }
        if(game.equals("4")){
            Minigame minigame1 = new Minigame();
            minigame1.startGame("4",this);
        }
        if (game.equals("5")) {
            Minigame minigame1 = new Minigame();
            minigame1.startGame("5", this);
        }
    }
}
