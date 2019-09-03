package com.example.user.viennaworkspace;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.HashMap;

import info.hoang8f.widget.FButton;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {

    // Buttons.
    FButton KAWNow, ShearingAreaBtn, RoomsBtn, MarketingBtn;

    // LayOut For Animations.
    LinearLayout Button0Layout, Button1Layout, Button2Layout, Button3Layout;

    // Type Of Animation.
    Animation LeftToRight;

    // To Make All App Like As The Same Font Make Sure To Put This Code Before onCreate Method
    // In Every Activity Press Ctrl + O.
//    @Override
//    protected void attachBaseContext(Context newBase) {
//        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // To Make All App Like As The Same Font Make Sure To Put This Code Before setContentView Method
        // In Every Activity.
//        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
//                .setDefaultFontPath("fonts/Capture_it.ttf")
//                .setFontAttrId(R.attr.fontPath)
//                .build());
        setContentView(R.layout.activity_main);

        // Get LayOuts By IDs.
        Button0Layout = (LinearLayout) findViewById(R.id.btn_l0_main);
        Button1Layout = (LinearLayout) findViewById(R.id.btn_l1_main);
        Button2Layout = (LinearLayout) findViewById(R.id.btn_l2_main);
        Button3Layout = (LinearLayout) findViewById(R.id.btn_l3_main);

        // Set Animations And Delay Time.
        LeftToRight = AnimationUtils.loadAnimation(this, R.anim.lefttoright);
        Button0Layout.setAnimation(LeftToRight);

        LeftToRight = AnimationUtils.loadAnimation(this, R.anim.lefttoright);
        LeftToRight.setDuration(1200);
        Button1Layout.setAnimation(LeftToRight);

        LeftToRight = AnimationUtils.loadAnimation(this, R.anim.lefttoright);
        LeftToRight.setDuration(1400);
        Button2Layout.setAnimation(LeftToRight);

        LeftToRight = AnimationUtils.loadAnimation(this, R.anim.lefttoright);
        LeftToRight.setDuration(1600);
        Button3Layout.setAnimation(LeftToRight);

        // Get Buttons By IDs.
        KAWNow = (FButton) findViewById(R.id.show_infos_main);
        ShearingAreaBtn = (FButton) findViewById(R.id.shearing_area);
        RoomsBtn = (FButton) findViewById(R.id.rooms);
        MarketingBtn = (FButton) findViewById(R.id.marketing);

        // Click On Buttons.
        KAWNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, Home.class);
                startActivity(intent);
            }
        });

        ShearingAreaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, ShearingArea.class);
                startActivity(intent);
            }
        });

        RoomsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, AllRooms.class);
                startActivity(intent);
            }
        });

        MarketingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, Market.class);
                startActivity(intent);
            }
        });
    }
}
