package com.example.user.welcomescreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    LinearLayout L1,L2;
    Button btnSub;
    Animation UpToDown, DownToTop;
    int i=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSub = (Button) findViewById(R.id.btn_sub) ;
        L1 = (LinearLayout) findViewById(R.id.L1);
        L2 = (LinearLayout) findViewById(R.id.L2);
        UpToDown = AnimationUtils.loadAnimation(this,R.anim.uptodown);
        DownToTop = AnimationUtils.loadAnimation(this,R.anim.downtotop);
        L1.setAnimation(UpToDown);
        L2.setAnimation(DownToTop);

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "No More.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
