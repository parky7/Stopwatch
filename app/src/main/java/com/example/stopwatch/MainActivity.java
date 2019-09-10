package com.example.stopwatch;

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
    public static final String TAG = MainActivity.class.getSimpleName();
    private Button buttonStart;
    private Button buttonReset;
    private Chronometer stopWatch;
    private boolean isClicked;
    private long thinkOfANameLater;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: ");
        isClicked = false;
        thinkOfANameLater = 0;
        wirewidgets();
        setListeners();
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
                    if (thinkOfANameLater == 0) {
                        thinkOfANameLater = SystemClock.elapsedRealtime();
                    }
                    stopWatch.setBase(stopWatch.getBase() + SystemClock.elapsedRealtime() -
                            thinkOfANameLater);
                    stopWatch.start();
                    buttonStart.setText("Stop");
                } else {
                    stopWatch.stop();
                    buttonStart.setText("Start");
                    thinkOfANameLater = SystemClock.elapsedRealtime();

                }


            }
        });
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopWatch.setBase(SystemClock.elapsedRealtime());
                stopWatch.stop();
                buttonStart.setText("Start");
                thinkOfANameLater = SystemClock.elapsedRealtime();
                isClicked = false;

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
}
