package com.example.user.viennaworkspace;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import info.hoang8f.widget.FButton;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class AllRooms extends AppCompatActivity {
    FButton BtnRoom1, BtnRoom2, BtnRoom3, BtnRoom4, BtnRoom5, BtnRoom6, BtnRoomHall;

    LinearLayout Button1LayoutRooms, Button2LayoutRooms, Button3LayoutRooms, Button4LayoutRooms,
            Button5LayoutRooms, Button6LayoutRooms, Button7LayoutRooms;

    Animation LeftToRight;

    String Room1 = "Room1";
    String Room2 = "Room2";
    String Room3 = "Room3";
    String Room4 = "Room4";
    String Room5 = "Room5";
    String Room6 = "Room6";
    String Hall = "Hall";

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
        setContentView(R.layout.activity_all_rooms);

        Button1LayoutRooms = (LinearLayout) findViewById(R.id.btn_l1_rooms);
        Button2LayoutRooms = (LinearLayout) findViewById(R.id.btn_l2_rooms);
        Button3LayoutRooms = (LinearLayout) findViewById(R.id.btn_l3_rooms);
        Button4LayoutRooms = (LinearLayout) findViewById(R.id.btn_l4_rooms);
        Button5LayoutRooms = (LinearLayout) findViewById(R.id.btn_l5_rooms);
        Button6LayoutRooms = (LinearLayout) findViewById(R.id.btn_l6_rooms);
        Button7LayoutRooms = (LinearLayout) findViewById(R.id.btn_l7_rooms);

        LeftToRight = AnimationUtils.loadAnimation(this, R.anim.lefttoright);
        Button1LayoutRooms.setAnimation(LeftToRight);

        LeftToRight = AnimationUtils.loadAnimation(this, R.anim.lefttoright);
        LeftToRight.setDuration(1200);
        Button2LayoutRooms.setAnimation(LeftToRight);

        LeftToRight = AnimationUtils.loadAnimation(this, R.anim.lefttoright);
        LeftToRight.setDuration(1400);
        Button3LayoutRooms.setAnimation(LeftToRight);

        LeftToRight = AnimationUtils.loadAnimation(this, R.anim.lefttoright);
        LeftToRight.setDuration(1600);
        Button4LayoutRooms.setAnimation(LeftToRight);

        LeftToRight = AnimationUtils.loadAnimation(this, R.anim.lefttoright);
        LeftToRight.setDuration(1800);
        Button5LayoutRooms.setAnimation(LeftToRight);

        LeftToRight = AnimationUtils.loadAnimation(this, R.anim.lefttoright);
        LeftToRight.setDuration(2000);
        Button6LayoutRooms.setAnimation(LeftToRight);

        LeftToRight = AnimationUtils.loadAnimation(this, R.anim.lefttoright);
        LeftToRight.setDuration(2200);
        Button7LayoutRooms.setAnimation(LeftToRight);


        BtnRoom1 = (FButton) findViewById(R.id.room1);
        BtnRoom2 = (FButton) findViewById(R.id.room2);
        BtnRoom3 = (FButton) findViewById(R.id.room3);
        BtnRoom4 = (FButton) findViewById(R.id.room4);
        BtnRoom5 = (FButton) findViewById(R.id.room5);
        BtnRoom6 = (FButton) findViewById(R.id.room6);
        BtnRoomHall = (FButton) findViewById(R.id.room_hall);


        BtnRoom1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AllRooms.this, SelectedRoom.class);
                intent.putExtra("Room1", Room1);
                startActivity(intent);
            }
        });

        BtnRoom2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AllRooms.this, SelectedRoom.class);
                intent.putExtra("Room2", Room2);
                startActivity(intent);
            }
        });

        BtnRoom3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AllRooms.this, SelectedRoom.class);
                intent.putExtra("Room3", Room3);
                startActivity(intent);
            }
        });

        BtnRoom4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AllRooms.this, SelectedRoom.class);
                intent.putExtra("Room4", Room4);
                startActivity(intent);
            }
        });

        BtnRoom5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AllRooms.this, SelectedRoom.class);
                intent.putExtra("Room5", Room5);
                startActivity(intent);
            }
        });

        BtnRoom6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AllRooms.this, SelectedRoom.class);
                intent.putExtra("Room6", Room6);
                startActivity(intent);
            }
        });

        BtnRoomHall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AllRooms.this, SelectedRoom.class);
                intent.putExtra("Hall", Hall);
                startActivity(intent);
            }
        });
    }
}
