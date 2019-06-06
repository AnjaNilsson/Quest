package com.example.sandy.quest.Minigames;

import android.content.Intent;
import android.content.res.Configuration;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.sandy.quest.NavigationMethod.Navigation;
import com.example.sandy.quest.Other.Victory;
import com.example.sandy.quest.R;

public class TiltGameLeft extends AppCompatActivity implements SensorEventListener{
    private SensorManager smanager;
    private Sensor sensor;
    private ImageView image;
    Integer[] leftImage = new Integer[28];
    int counter = 0, currentState = 1, prevState = 0; //counter and state machine

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_left_tilt);
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
        leftImage[0] = R.drawable.left_background;
        leftImage[1] = R.drawable.leftside_q1;
        leftImage[2] = R.drawable.leftside_q2;
        leftImage[3] = R.drawable.leftside_q3;
        leftImage[4] = R.drawable.leftside_q4;
        leftImage[5] = R.drawable.leftside_q5;
        leftImage[6] = R.drawable.leftside_q6;
        leftImage[7] = R.drawable.leftside_q7;
        leftImage[8] = R.drawable.leftside_q8;
        leftImage[9] = R.drawable.leftside_q9;
        leftImage[10] = R.drawable.leftside_q10;
        leftImage[11] = R.drawable.leftside_q11;
        leftImage[12] = R.drawable.leftside_q12;
        leftImage[13] = R.drawable.leftside_q13;
        leftImage[14] = R.drawable.leftside_q14;
        leftImage[15] = R.drawable.leftside_q15;
        leftImage[16] = R.drawable.leftside_q16;
        leftImage[17] = R.drawable.leftside_q17;
        leftImage[18] = R.drawable.leftside_q18;
        leftImage[19] = R.drawable.leftside_q19;
        leftImage[20] = R.drawable.leftside_q20;
        leftImage[21] = R.drawable.leftside_q21;
        leftImage[22] = R.drawable.leftside_q22;
        leftImage[23] = R.drawable.leftside_q23;
        leftImage[24] = R.drawable.leftside_q24;
        leftImage[25] = R.drawable.leftside_q25;
        leftImage[26] = R.drawable.leftside_q26;
        leftImage[27] = R.drawable.leftside_done;


        //if back to portrait
        if(y>= 6)
        {
            final Intent i = new Intent(TiltGameLeft.this, TiltGameStart.class);
            startActivity(i);
        }

        if (z >= 7){
            image.setImageResource(R.drawable.android_nope);
            if (counter < 27 && (currentState != prevState))
            {
                counter++;
                prevState++;
            }
        } else if (z <= -7){
            image.setImageResource(R.drawable.android_yes);
            if (counter < 27 && (currentState != prevState))
            {
                counter++;
                prevState++;
            }
        }else{
            image.setImageResource(leftImage[counter]);
            if (currentState == prevState){
                currentState++;
            } else if(counter>=27) {
                Navigation.gameRunning = false;
                Intent intent = new Intent(TiltGameLeft.this, Victory.class);
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

        //if back to portrait
        if(y> 6)
        {
            final Intent i = new Intent(TiltGameLeft.this, TiltGameStart.class);
            startActivity(i);
        }

        if (z >= 8) {
            image.setImageResource(R.drawable.android_nope);
//            if(!played){
//                mp.start();
//                played = true;
//            }

            if (counter < 27 && (currentState != prevState))
            {
                counter++;
                prevState++;
            }

        } else if (z <= -8) {
            image.setImageResource(R.drawable.android_yes);
//            if(!played){
//                mp.start();
//               played = true;
//           }

            if (counter < 27 && (currentState != prevState))
            {
                counter++;
                prevState++;
            }
        } else if(x>6) {
            image.setImageResource(leftImage[counter]);
            played=false;
            if (currentState == prevState){
                currentState++;
            } else if(counter>=27) {
                //end game
                Navigation.gameRunning = false;
                Intent intent = new Intent(TiltGameLeft.this, Victory.class);
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
    public void onDestroy() //main thread stopped
    {
        super.onDestroy();
        android.os.Process.killProcess(android.os.Process.myPid());  //remove app from memory
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
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
*/


