package com.example.android.circlemenu;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

import com.hitomi.cmlibrary.CircleMenu;

public class MainActivity extends AppCompatActivity {
    private LinearLayout l1, l2;
    private Button button ;
    private Animation UpToDown, DownToUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        l1 = (LinearLayout) findViewById(R.id.L1);
        l2 = (LinearLayout) findViewById(R.id.L2);
        UpToDown = AnimationUtils.loadAnimation(this,R.anim.uptodown);
        DownToUp = AnimationUtils.loadAnimation(this,R.anim.downtotop);
        button = (Button) findViewById(R.id.btn_get_started);

        l1.setAnimation(UpToDown);
        l2.setAnimation(DownToUp);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CircleMenuActivity.class);
                startActivity(intent);
            }
        });
    }
}
