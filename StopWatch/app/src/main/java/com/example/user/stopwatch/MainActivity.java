package com.example.user.stopwatch;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private Chronometer chronometer;
    private long pauseOffset;
    private boolean running;

    public Button start, stop, reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = (Button) findViewById(R.id.startChronometer);
        stop  = (Button) findViewById(R.id.stopChronometer);
        reset = (Button) findViewById(R.id.resetChronometer);

        chronometer = (Chronometer) findViewById(R.id.chronometer);
        chronometer.setFormat("Time: %s");

        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if ((SystemClock.elapsedRealtime() - chronometer.getBase()) >= 10000 /** 10000 MilliSeconds = 10 Second*/) {
                    chronometer.setBase(SystemClock.elapsedRealtime()); // Set Time To 0.
                    Toast.makeText(MainActivity.this, "10 Seconds Later!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!running) {
                    //* To UnderStand Why We Need To This Two Commands:
                    // * In Start: chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
                    //* In Stop:  pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
                    //* Try To Think Of This Story.
                    //*
                    //* 5 Seconds Between each rounds & 5 Seconds Between Click On Start And Stop.
                    //*
                    //* set(real - pause) => (5s - 0s) (15s - 5s) (25s - 10s) (35s - 15s)
                    //* start Until get 5s: (5s) (10s) (15s)
                    //*
                    //* stop When get 5s: (5s) (10s) (15s)
                    //* pause = real - get (10s - 5s) (20s - 10s) (30s - 15s)
                    chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
                    chronometer.start();
                    running = true;
                }
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (running) {
                    // chronometer.stop() Stop Our chronometer But doesn't Stop The chronometer itself.
                    // itself means the sleeping time when we stop it, It doesn't stop the sleeping time.
                    chronometer.stop();
                    pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
                    running = false;
                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set Time To 0.
                chronometer.setBase(SystemClock.elapsedRealtime());
                pauseOffset = 0;
            }
        });

    }
}
