package com.example.sandy.quest.Minigames;

import android.content.Intent;
import android.content.res.Configuration;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.OrientationEventListener;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sandy.quest.NavigationMethod.NavigationActivity;
import com.example.sandy.quest.R;

public class TiltGameStart extends AppCompatActivity implements SensorEventListener {

    private OrientationEventListener orient;
    private SensorManager smanager;
    private Sensor sensor;
    TextView txt;
    ImageView image;
    Button start;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tilt_start);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        txt = (TextView) findViewById(R.id.txt);

        this.smanager = (SensorManager) getSystemService(SENSOR_SERVICE);
        this.sensor = smanager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

    }

    @Override
    public void onSensorChanged (SensorEvent event)
    {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        //if it's landscape left way <<<
        if (x >= 7) {
            //TODO create a 4 second timer to give user time before activity starts
            final Intent i = new Intent(TiltGameStart.this, TiltGameLeft.class);
            startActivity(i);
        }

        //if it's landscape right way >>>
        if (x<=-8) {
            //TODO create a 4 second timer to give user time before activity starts
            final Intent q = new Intent(TiltGameStart.this, TiltGameRight.class);
            startActivity(q);
        }

    }

    @Override
    public void onAccuracyChanged (Sensor arg0,int arg1)
    {
        //might make something happen here
    }

    @Override
    public void onResume ()
    {
        super.onResume();
        smanager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause ()
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
    public void onWindowFocusChanged ( boolean hasFocus){
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

    @Override
    public void onBackPressed() {

        //smAccelerometer.unregisterListener(this);
        Intent intent = new Intent(TiltGameStart.this, NavigationActivity.class);
        startActivity(intent);
    }

}
