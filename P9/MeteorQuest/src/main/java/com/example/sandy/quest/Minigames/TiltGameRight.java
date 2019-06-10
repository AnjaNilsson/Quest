package com.example.sandy.quest.Minigames;

import android.content.Intent;
import android.content.res.Configuration;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.sandy.quest.NavigationMethod.Navigation;
import com.example.sandy.quest.Other.Victory;
import com.example.sandy.quest.R;


public class TiltGameRight extends AppCompatActivity implements SensorEventListener{
    private SensorManager smanager;
    private Sensor sensor;
    private ImageView image;
    Integer[] rightImage = new Integer[28];
    int counter = 0, currentState = 1, prevState = 0; //counter and state machine

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_right_tilt);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        this.smanager = (SensorManager)getSystemService(SENSOR_SERVICE);
        this.sensor = smanager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        image = findViewById(R.id.img);


    }

    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1)
    {
        //might make something happen here
    }

    @Override
    public void onSensorChanged(SensorEvent event)
    {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        rightImage[0] = R.drawable.right_background;
        rightImage[1] = R.drawable.rightside_q1;
        rightImage[2] = R.drawable.rightside_q2;
        rightImage[3] = R.drawable.rightside_q3;
        rightImage[4] = R.drawable.rightside_q4;
        rightImage[5] = R.drawable.rightside_q5;
        rightImage[6] = R.drawable.rightside_q6;
        rightImage[7] = R.drawable.rightside_q7;
        rightImage[8] = R.drawable.rightside_q8;
        rightImage[9] = R.drawable.rightside_q9;
        rightImage[10] = R.drawable.rightside_q10;
        rightImage[11] = R.drawable.rightside_q11;
        rightImage[12] = R.drawable.rightside_q12;
        rightImage[13] = R.drawable.rightside_q13;
        rightImage[14] = R.drawable.rightside_q14;
        rightImage[15] = R.drawable.rightside_q15;
        rightImage[16] = R.drawable.rightside_q16;
        rightImage[17] = R.drawable.rightside_q17;
        rightImage[18] = R.drawable.rightside_q18;
        rightImage[19] = R.drawable.rightside_q19;
        rightImage[20] = R.drawable.rightside_q20;
        rightImage[21] = R.drawable.rightside_q21;
        rightImage[22] = R.drawable.rightside_q22;
        rightImage[23] = R.drawable.rightside_q23;
        rightImage[24] = R.drawable.rightside_q24;
        rightImage[25] = R.drawable.rightside_q25;
        rightImage[26] = R.drawable.rightside_q26;
        rightImage[27] = R.drawable.right_done;


        //if back to portrait
        if(y>= 7)
        {
            final Intent i = new Intent(TiltGameRight.this, TiltGameStart.class);
            startActivity(i);
        }

        if (z >= 8){
            image.setImageResource(R.drawable.rightside_nope);
            if (counter < 27 && (currentState != prevState))
            {
                counter++;
                prevState++;
            }
        } else if (z <= -8){
            image.setImageResource(R.drawable.rightside_yes);
            if (counter < 27 && (currentState != prevState))
            {
                counter++;
                prevState++;
            }
        }else{
            image.setImageResource(rightImage[counter]);
            if (currentState == prevState){
                currentState++;
            } else if(counter>=27) {
                Navigation.gameRunning = false;
                Intent intent = new Intent(TiltGameRight.this, Victory.class);
                startActivity(intent);
            }
        }

    }


    @Override
    public void onResume()
    {
        super.onResume();
        smanager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause()
    {
        super.onPause();
        smanager.unregisterListener(this);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus){
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
/*
public class TiltGameRight extends AppCompatActivity implements SensorEventListener {
    private SensorManager smanager;
    private Sensor sensor;
    private ImageView image;
    Integer[] rightImage = new Integer[28];
    int counter = 0, currentState = 1, prevState = 0; //counter and state machine
    boolean played;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_right_tilt);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        this.smanager = (SensorManager) getSystemService(SENSOR_SERVICE);
        this.sensor = smanager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        image = findViewById(R.id.img);


    }

    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {
        //might make something happen here
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        rightImage[0] = R.drawable.right_background;
        rightImage[1] = R.drawable.rightside_q1;
        rightImage[2] = R.drawable.rightside_q2;
        rightImage[3] = R.drawable.rightside_q3;
        rightImage[4] = R.drawable.rightside_q4;
        rightImage[5] = R.drawable.rightside_q5;
        rightImage[6] = R.drawable.rightside_q6;
        rightImage[7] = R.drawable.rightside_q7;
        rightImage[8] = R.drawable.rightside_q8;
        rightImage[9] = R.drawable.rightside_q9;
        rightImage[10] = R.drawable.rightside_q10;
        rightImage[11] = R.drawable.rightside_q11;
        rightImage[12] = R.drawable.rightside_q12;
        rightImage[13] = R.drawable.rightside_q13;
        rightImage[14] = R.drawable.rightside_q14;
        rightImage[15] = R.drawable.rightside_q15;
        rightImage[16] = R.drawable.rightside_q16;
        rightImage[17] = R.drawable.rightside_q17;
        rightImage[18] = R.drawable.rightside_q18;
        rightImage[19] = R.drawable.rightside_q19;
        rightImage[20] = R.drawable.rightside_q20;
        rightImage[21] = R.drawable.rightside_q21;
        rightImage[22] = R.drawable.rightside_q22;
        rightImage[23] = R.drawable.rightside_q23;
        rightImage[24] = R.drawable.rightside_q24;
        rightImage[25] = R.drawable.rightside_q25;
        rightImage[26] = R.drawable.rightside_q26;
        rightImage[27] = R.drawable.rightside_done;

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.fart);
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }
        });

        //if back to portrait
        if (y >= 6) {
            final Intent i = new Intent(TiltGameRight.this, TiltGameStart.class);
            startActivity(i);
        }

        if (z >= 8) {
            image.setImageResource(R.drawable.rightside_nope);
//            if(!played){
//                mp.start();
//                played = true;
//            }

            if (counter < 27 && (currentState != prevState)) {

                counter++;
                prevState++;
            }
        } else if (z <= -8) {
            image.setImageResource(R.drawable.rightside_yes);
//            if(!played){
//                mp.start();
//                played = true;
//            }
            if (counter < 27 && (currentState != prevState)) {
                counter++;
                prevState++;
            }
        } else if (x < -6) {
            image.setImageResource(rightImage[counter]);
            played = false;
            if (currentState == prevState) {
                currentState++;
            } else if (counter >= 27) {
                Navigation.gameRunning = false;
                Intent intent = new Intent(TiltGameRight.this, Victory.class);
                startActivity(intent);
            }
        }

    }


    @Override
    public void onResume() {
        super.onResume();
        smanager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        super.onPause();
        smanager.unregisterListener(this);
    }

    @Override
    public void onDestroy() //main thread stopped
    {
        super.onDestroy();
        android.os.Process.killProcess(android.os.Process.myPid());  //remove app from memory
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
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
*/
