package com.example.user.viennaworkspace;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import info.hoang8f.widget.FButton;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ShearingArea extends AppCompatActivity {
    // Buttons.
    FButton CreateNewIndividual, ViennaNow;

    // LayOut For Animations.
    LinearLayout Button1LayoutIndivid, Button2LayoutIndivid;

    // Place Of Animation.
    Animation LeftToRight;

    // String To Store Place Of Customer.
    String SharedSpace = "Shared Space";

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
        setContentView(R.layout.activity_shearing_area);

        // Get LayOuts By IDs.
        Button1LayoutIndivid = (LinearLayout) findViewById(R.id.btn_l1_individ);
        Button2LayoutIndivid = (LinearLayout) findViewById(R.id.btn_l2_individ);

        // Set Animations And Delay Time.
        LeftToRight = AnimationUtils.loadAnimation(this, R.anim.lefttoright);
        Button1LayoutIndivid.setAnimation(LeftToRight);

        LeftToRight = AnimationUtils.loadAnimation(this, R.anim.lefttoright);
        LeftToRight.setDuration(1200);
        Button2LayoutIndivid.setAnimation(LeftToRight);

        // Get Buttons By IDs.
        CreateNewIndividual = (FButton) findViewById(R.id.create_new_individual);
        ViennaNow = (FButton) findViewById(R.id.show_infos_individual);

        // Click On Buttons.
        CreateNewIndividual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShearingArea.this, SignUpUser.class);
                // PutExtra To Travel To Another Activity By Key & Value.
                intent.putExtra("Shared Space", SharedSpace);
                startActivity(intent);
            }
        });

        ViennaNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent(ShearingArea.this, Home.class);
                    startActivity(intent);

            }
        });
    }
}
