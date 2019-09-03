package com.example.user.viennaworkspace;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import info.hoang8f.widget.FButton;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SelectedRoom extends AppCompatActivity {

    // Buttons.
    FButton CreateNewSelectedRoom, KAWNow;

    // LayOut For Animation.
    LinearLayout Button1LayoutSelectedRoom, Button2LayoutSelectedRoom;

    // Type Of Animation.
    Animation LeftToRight;

    // Strings To Check Extra From Intent.
    String Room1 = "Room1";
    String Room2 = "Room2";
    String Room3 = "Room3";
    String Room4 = "Room4";
    String Room5 = "Room5";
    String Room6 = "Room6";
    String Hall = "Hall";

    Bundle extras;

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
        setContentView(R.layout.activity_selected_room);

        // Get Extra From Intent.
        extras =  getIntent().getExtras();

        // Get LayOuts By IDs.
        Button1LayoutSelectedRoom = (LinearLayout) findViewById(R.id.btn_l1_selected_room);
        Button2LayoutSelectedRoom = (LinearLayout) findViewById(R.id.btn_l2_selected_room);

        // Set Animations.
        LeftToRight = AnimationUtils.loadAnimation(this, R.anim.lefttoright);
        Button1LayoutSelectedRoom.setAnimation(LeftToRight);

        LeftToRight = AnimationUtils.loadAnimation(this, R.anim.lefttoright);
        LeftToRight.setDuration(1200);
        Button2LayoutSelectedRoom.setAnimation(LeftToRight);


        // Get Buttons By IDs.
        CreateNewSelectedRoom = (FButton) findViewById(R.id.create_new_selected_room);
        KAWNow = (FButton) findViewById(R.id.show_infos_selected_room);

        // Wheck CLick On Button.
        CreateNewSelectedRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // GoTo SignUpUser Activity With Put Extra.
                Intent intent = new Intent(SelectedRoom.this, SignUpUser.class);

                // Check If Extra Contains Key Like ("...").
                if(extras.containsKey("Room1"))
                    intent.putExtra("Room1", Room1);

                else if(extras.containsKey("Room2"))
                    intent.putExtra("Room2", Room2);

                else if(extras.containsKey("Room3"))
                    intent.putExtra("Room3", Room3);

                else if(extras.containsKey("Room4"))
                    intent.putExtra("Room4", Room4);

                else if(extras.containsKey("Room5"))
                    intent.putExtra("Room5", Room5);

                else if(extras.containsKey("Room6"))
                    intent.putExtra("Room6", Room6);

                else if(extras.containsKey("Hall"))
                    intent.putExtra("Hall", Hall);

                startActivity(intent);
            }
        });

        // Click On Button.
        KAWNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // GoTo Home Activity.
                Intent intent = new Intent(SelectedRoom.this, Home.class);
                startActivity(intent);
            }
        });
    }
}
