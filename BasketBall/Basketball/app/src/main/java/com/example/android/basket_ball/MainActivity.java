package com.example.android.basket_ball;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int resultTeamA = 0;

    public void add3ForTeamA(View view) {
        displayForTeamA(resultTeamA += 3);
    }

    public void add2ForTeamA(View view) {
        displayForTeamA(resultTeamA += 2);
    }

    public void add1ForTeamA(View view) {
        displayForTeamA(resultTeamA += 1);
    }

    int resultTeamB = 0;

    public void add3ForTeamB(View view) {
        displayForTeamB(resultTeamB += 3);
    }

    public void add2ForTeamB(View view) {
        displayForTeamB(resultTeamB += 2);
    }

    public void add1ForTeamB(View view) {
        displayForTeamB(resultTeamB += 1);
    }

    /**
     * Displays the given score for Team A.
     */
    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.scoreA);
        scoreView.setText(score + "");
        if (Integer.parseInt(scoreView.getText().toString()) >= 20){
            Toast.makeText(this, "Team A is winner", Toast.LENGTH_LONG).show();
            scoreView.setText("0");
            resultTeamA = 0;
            resultTeamB = 0;
            displayForTeamB(resultTeamB);
        }
    }

    public void displayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.scoreB);
        scoreView.setText(score + "");
        if (Integer.parseInt(scoreView.getText().toString()) >= 20){
            Toast.makeText(this, "Team B is winner", Toast.LENGTH_LONG).show();
            scoreView.setText("0");
            resultTeamA = 0;
            resultTeamB = 0;
            displayForTeamA(resultTeamA);
        }
    }

    public void reset(View v) {
        resultTeamA = 0;
        resultTeamB = 0;
        displayForTeamA(resultTeamA);
        displayForTeamB(resultTeamB);
    }
}