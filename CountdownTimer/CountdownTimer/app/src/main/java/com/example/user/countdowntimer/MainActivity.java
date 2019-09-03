package com.example.user.countdowntimer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final long START_TIME_IN_MILLIS = 10000; // Equals 10 Seconds.

    // Init Views.
    private TextView mTextViewCountDown;
    private Button mButtonStartPause;
    private Button mButtonReset;

    // Init View Called CountDownTimer.
    private CountDownTimer mCountDownTimer;

    // False.
    private boolean mTimerRunning;

    // Equal The Current Time For First Time Only.
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get Views By ID.
        mTextViewCountDown = findViewById(R.id.textForCountDown);

        mButtonStartPause = findViewById(R.id.BtnStartPause);

        mButtonReset = findViewById(R.id.BtnReset);

        // When Click on Start Button.
        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check If It True Called PauseTimer Method.
                if (mTimerRunning)
                    PauseTimer();
                    // If It False Called StartTimer Method.
                else
                    StartTimer();
            }
        });

        // When Click on Reset Button.
        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Called ResetTimer Method.
                ResetTimer();
            }
        });
        // Called updateCountDownText Method To Update The Timer.
        updateCountDownText();
    }

    // When Call StartTimer Method.
    private void StartTimer() {
        // CountDownTimer Take Two Parameters (Current Time, CountDown Timer).
        // CountDownTimer Work As a Loop, Can't Stop Before All Is Done.
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000 /* 1 Sec */) {
            // This Method called Auto.
            @Override
            public void onTick(long millisUntilFinished) {
                // Update The mTimeLeftInMillis By millisUntilFinished.
                // millisUntilFinished Equal the Current Time.
                mTimeLeftInMillis = millisUntilFinished;
                //Called updateCountDownText Method.
                updateCountDownText();
            }

            // This Method called Auto.
            @Override
            // When The Time Is Done.
            public void onFinish() {
                mTimerRunning = false;
                // Make It Cause The Text Still On (00:01) Not (00:00)
                //  mTextViewCountDown.setText("00:00");
                updateButtons();
            }
        }.start(); // Start The mCountDownTimer.
        mTimerRunning = true;
        updateButtons();
    }

    private void PauseTimer() {
        mCountDownTimer.cancel(); // Stop The mCountDownTimer.
        mTimerRunning = false;
        updateButtons();
    }

    private void ResetTimer() {
        mTimeLeftInMillis = START_TIME_IN_MILLIS; // Reset The mTimeLeftInMillis By The Original Time.
        updateCountDownText();
        updateButtons();
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60; // Convert from Millis To Minutes.
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60; // Convert from Millis To Seconds.

        // The %02d means: "Format as a decimal number using at least 2 digits.
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        mTextViewCountDown.setText(timeLeftFormatted); // Set The TextView.
    }

    private void updateButtons() {
        // Check If It True.
        if (mTimerRunning) {
            mButtonReset.setVisibility(View.INVISIBLE);
            mButtonStartPause.setText("Pause");
        } else {

            mButtonStartPause.setText("Start");

            // If mTimeLeftInMillis Less Than 2 Sec. Make This Button INVISIBLE.
            if (mTimeLeftInMillis < 2000)
                mButtonStartPause.setVisibility(View.INVISIBLE);
            else
                mButtonStartPause.setVisibility(View.VISIBLE);

            // If mTimeLeftInMillis Less That The Original Time Make This Button VISIBLE.
            if (mTimeLeftInMillis < START_TIME_IN_MILLIS)
                mButtonReset.setVisibility(View.VISIBLE);
            else
                // Make it INVISIBLE When Click On Reset Button In The Beginning Before Start The Time.
                mButtonReset.setVisibility(View.INVISIBLE);
        }
    }

    // When You Make Rotate For Your Device Don't Reset The Time, But Continue The Time.
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // outState.putX Take (Key, Value)
        outState.putLong("millisLeft", mTimeLeftInMillis);
        outState.putBoolean("timerRunning", mTimerRunning);
    }

    // Get The Current Values From onSaveInstanceState Method By Keys.
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Get The Current Values By Keys To Store It in These Variables.
        mTimeLeftInMillis = savedInstanceState.getLong("millisLeft");
        mTimerRunning = savedInstanceState.getBoolean("timerRunning");
        // Call Update's Methods To Send The New Variables.
        updateCountDownText();
        updateButtons();
        // If It True Call StartTimer Method.
        if (mTimerRunning)
            StartTimer();
    }
}
