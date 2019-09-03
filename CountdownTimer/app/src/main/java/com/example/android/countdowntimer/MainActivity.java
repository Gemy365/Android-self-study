package com.example.android.countdowntimer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    // Hint: Press [Shift + F6] If U Want To Change Variable Name,
    // In Every Line Use It In Our Code At Once Time.

    // Init Views.
    private EditText mEditTextInput;
    private TextView mTextViewCountDown;
    private Button mButtonSet;
    private Button mButtonStartPause;
    private Button mButtonReset;

    // Init View Called CountDownTimer.
    private CountDownTimer mCountDownTimer;

    // Equal False.
    private boolean mTimerRunning;

    // Store The Start Time From The User.
    private long mStartTimeInMillis;

    // Store The Left Time.
    private long mTimeLeftInMillis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Get Views By ID.
        mEditTextInput = findViewById(R.id.editTextInput);

        mTextViewCountDown = findViewById(R.id.textForCountDown);

        mButtonSet = findViewById(R.id.BtnSet);

        mButtonStartPause = findViewById(R.id.BtnStartPause);

        mButtonReset = findViewById(R.id.BtnReset);

        // When Click on Set Button.
        mButtonSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Input From User & Convert It From Integer To String.
                String input = mEditTextInput.getText().toString();
                // Check If User Didn't Enter Any Number.
                if(input.length() == 0) {
                    Toast.makeText(MainActivity.this, "Field Can't Be Empty!", Toast.LENGTH_SHORT).show();
                    return; // Back To Method WithOut Crash The Program.
                }

                // Convert The Input String To Long & [* 60000] To Change In Minutes.
                long millisInput = Long.parseLong(input) * 60000;

                // Check If User Enter 0.
                if(millisInput == 0){
                    Toast.makeText(MainActivity.this, "Please Enter Positive Number!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Call setTime Method With Parameter millisInput (User's Input).
                setTime(millisInput);
                // Make Field Is Empty.
                mEditTextInput.setText("");
            }
        });

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

    // When Call setTime Method.
    private void setTime(long milliseconds){
        // Store The User's Input In mStartTimeInMillis Variable.
        mStartTimeInMillis = milliseconds;
        //Call ResetTimer Method.
        ResetTimer();
        //Call closeKeyboard Method.
        closeKeyboard();
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
            // When The Time Is Done.
            @Override
            public void onFinish() {
                mTimerRunning = false;
                // Make It Cause The Text Still On (00:01) Not (00:00), Avoid Bug.
                mTextViewCountDown.setText("00:00");
                updateInterFace();
            }
        }.start(); // Start The mCountDownTimer.
        mTimerRunning = true;
        updateInterFace();
    }

    // When Call PauseTimer Method.
    private void PauseTimer() {
        mCountDownTimer.cancel(); // Stop The mCountDownTimer.
        mTimerRunning = false;
        updateInterFace();
    }

    // When Call ResetTimer Method.
    private void ResetTimer() {
        // Store The Start Time (User's Input) In mTimeLeftInMillis.
        mTimeLeftInMillis = mStartTimeInMillis;
        // When Call updateCountDownText Method.
        updateCountDownText();
        // When Call updateInterFace Method.
        updateInterFace();
    }

    // When Call updateCountDownText Method.
    private void updateCountDownText() {
        int hours   = (int) (mTimeLeftInMillis / 1000) / 3600; // Convert from Millis To Hours.
        int minutes = (int) ((mTimeLeftInMillis / 1000) % 3600) / 60; // Convert from Millis To Minutes.
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60; // Convert from Millis To Seconds.

        // Global Variable.
        String timeLeftFormatted;
        // Check If Hours > 0.
        if(hours > 0){
            // The %02d means: "Format as a decimal number using At Least 2 digits.
            timeLeftFormatted = String.format(Locale.getDefault(),
                    "%02d:%02d:%02d", hours, minutes, seconds);
        }else{
            timeLeftFormatted = String.format(Locale.getDefault(),
                    "%02d:%02d", minutes, seconds);
        }
        mTextViewCountDown.setText(timeLeftFormatted); // Set The TextView.
    }

    // When Call closeKeyboard Method.
    private void closeKeyboard(){
        View view = this.getCurrentFocus();
        if(view != null){
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void updateInterFace() {
        // Check If It True.
        if (mTimerRunning) {
            mEditTextInput.setVisibility(View.INVISIBLE);
            mButtonSet.setVisibility(View.INVISIBLE);
            mButtonReset.setVisibility(View.INVISIBLE);
            mButtonStartPause.setVisibility(View.VISIBLE);
            mButtonStartPause.setText("Pause");
        } else {
            mEditTextInput.setVisibility(View.VISIBLE);
            mButtonSet.setVisibility(View.VISIBLE);
            mButtonStartPause.setText("Start");

            // If mTimeLeftInMillis Less Than 2 Sec. Make This Button INVISIBLE.
            if (mTimeLeftInMillis < 2000)
                mButtonStartPause.setVisibility(View.INVISIBLE);
            else
                mButtonStartPause.setVisibility(View.VISIBLE);

            // If mTimeLeftInMillis Less That The Original Time Make This Button VISIBLE.
            if (mTimeLeftInMillis < mStartTimeInMillis)
                mButtonReset.setVisibility(View.VISIBLE);
            else
                // Make it INVISIBLE When Click On Reset Button To Back To Start The Time.
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
        updateInterFace();
        // If It True Call StartTimer Method.
        if (mTimerRunning)
            StartTimer();
    }
}
