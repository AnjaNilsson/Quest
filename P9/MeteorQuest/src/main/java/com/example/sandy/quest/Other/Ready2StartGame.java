package com.example.sandy.quest.Other;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sandy.quest.Minigames.ChargeUp;
import com.example.sandy.quest.Minigames.SoundBite;
import com.example.sandy.quest.Minigames.MazeMe;
import com.example.sandy.quest.Minigames.TiltGameStart;
import com.example.sandy.quest.NavigationMethod.Navigation;
import com.example.sandy.quest.NavigationMethod.NavigationActivity;
import com.example.sandy.quest.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Ready2StartGame extends AppCompatActivity {

    TextView nameTop;
    TextView nameBottom;
    Button startGame;
    public FirebaseDatabase database;
    public DatabaseReference rootReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        setContentView(R.layout.activity_ready2_start_game);

        nameTop = (TextView) findViewById(R.id.nameTop);
        nameBottom = (TextView) findViewById(R.id.introduction);
        startGame = (Button) findViewById(R.id.startGame);

        rootReference = Database.getDatabaseRootReference();
        DatabaseReference gamesReference = rootReference.child("skipintroduction");
        gamesReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String value = ds.getValue().toString();
                    if(value.equals("true")) {
                        String key = ds.getKey().toString();
                        if (key.equals("minigame1")) {
                            Navigation.gameRunning = true;
                            Intent intent = new Intent(Ready2StartGame.this, SoundBite.class);
                            startActivity(intent);
                        }
                        if (key.equals("minigame2")) {
                            Navigation.gameRunning = true;
                            Intent intent = new Intent(Ready2StartGame.this, ChargeUp.class);
                            startActivity(intent);
                        }
                        if (key.equals("minigame3")) {
                            Navigation.gameRunning = true;
                            Intent intent = new Intent(Ready2StartGame.this, MazeMe.class);
                            startActivity(intent);
                        }
                        if (key.equals("minigame4")) {
                            Navigation.gameRunning = true;
                            Intent intent = new Intent(Ready2StartGame.this, TiltGameStart.class);
                            startActivity(intent);
                      /*  }
                        if (key.equals("minigame5")) {
                            Navigation.gameRunning = true;
                            Intent intent = new Intent(Ready2StartGame.this, BalanceGame.class);
                            startActivity(intent);*/
                        }
                        if (key.equals("navigation")) {
                            Intent intent = new Intent(Ready2StartGame.this, NavigationActivity.class);
                            startActivity(intent);
                        }
                        break;
                    }



                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });


    }


    public void startIntroduction(View v){
        Intent intent = new Intent(Ready2StartGame.this,Introduction.class);
        startActivity(intent);
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
}
