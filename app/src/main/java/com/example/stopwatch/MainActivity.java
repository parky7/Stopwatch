package com.example.stopwatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class MainActivity extends AppCompatActivity {

    //look up the log class for android
    //list all the levels of logging w=and when they're used
    //order form lowest prtiority to highest
    // Verbose log.v
    //debug log.d
    //info log.i
    //warning log.w
    //error log.e
    //assert log.a
    // what a terrible failure log.wtf
    // launced app --> onCreate, onStart, onResume
//rotate --> onPause, onStop, onDestroy, onCreate, onStart, onResume
//hit the square button --> onStop
//click back on the app from the square button --> onPause, onStop
//hit the circle button --> onPause, onStop
//relaunch the app from the phone navigation (not play button) --> onStart, onResume
//hit the back button --> onStop, onDestroy
    public static final String TAG = MainActivity.class.getSimpleName();
    public static final String KEY_CHRONOMETER_BASE = "chronometer base";
    public static final String KEY_CHRONOMETER_IS_CLICKED = "chronometer isClicked";
    public static final String KEY_CHRONOMETER_PAUSE_TIME = "Pause Time";
    private Button buttonStart;
    private Button buttonReset;
    private Chronometer stopWatch;
    private boolean isClicked;
    private long pauseTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: ");
        isClicked = false;
        pauseTime = 0;
        wirewidgets();
        setListeners();

        // if the savedInstanceState isn't null
            // pull value of base from bundle
            // set the chronometer's base to that value
            // start
        if(!(savedInstanceState == null)){
            isClicked = savedInstanceState.getBoolean(KEY_CHRONOMETER_IS_CLICKED);

            pauseTime = savedInstanceState.getLong(KEY_CHRONOMETER_PAUSE_TIME);

            if(isClicked){
                stopWatch.setBase(savedInstanceState.getLong(KEY_CHRONOMETER_BASE));
                stopWatch.start();

                buttonStart.setText(R.string.main_stop);
            }
            else {
                stopWatch.setBase(savedInstanceState.getLong(KEY_CHRONOMETER_BASE) +
                        SystemClock.elapsedRealtime() - pauseTime );
                pauseTime = SystemClock.elapsedRealtime();
            }



        }
    }

    private void wirewidgets() {
        buttonReset = findViewById(R.id.button_main_reset);
        buttonStart = findViewById(R.id.button_main_startStop);
        stopWatch = findViewById(R.id.chronometer_main_time);
    }

    private void setListeners() {
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isClicked = !isClicked;
                if (isClicked) {
                    if (pauseTime == 0) {
                        stopWatch.setBase(SystemClock.elapsedRealtime());
                        stopWatch.start();
                        buttonStart.setText(R.string.main_stop);
                    }
                    else {
                        stopWatch.setBase(stopWatch.getBase() + SystemClock.elapsedRealtime() -
                                pauseTime);
                        stopWatch.start();
                        buttonStart.setText(R.string.main_stop);
                    }
                } else {
                    stopWatch.stop();
                    buttonStart.setText(R.string.main_start);
                    pauseTime = SystemClock.elapsedRealtime();

                }


            }
        });
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopWatch.setBase(SystemClock.elapsedRealtime());
                stopWatch.stop();
                buttonStart.setText(R.string.main_start);
                isClicked = false;
                pauseTime = SystemClock.elapsedRealtime();


            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }
    // activity is running

    //another activity is covering this activity

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    //this activity is completely hidden
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    // activity is finished
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putLong(KEY_CHRONOMETER_BASE, stopWatch.getBase());
        outState.putBoolean(KEY_CHRONOMETER_IS_CLICKED, isClicked);
        outState.putLong(KEY_CHRONOMETER_PAUSE_TIME, pauseTime);
    }
}
