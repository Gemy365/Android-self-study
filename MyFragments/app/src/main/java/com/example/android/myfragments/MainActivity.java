package com.example.android.myfragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    // Init Views.
    BottomNavigationView bottomNavigationView;
    FrameLayout frameLayout;

    // Make Object Of Fragments.
    HomeFragment homeFragment;
    SearchFragment searchFragment;
    NotificationFragment notificationFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get Views By IDs.
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.main_bottom_nav);
        frameLayout = (FrameLayout) findViewById(R.id.main_frame);

        // Make New Constructors From Fragments.
        homeFragment = new HomeFragment();
        searchFragment = new SearchFragment();
        notificationFragment = new NotificationFragment();

        // Change Color Of Frame As Default.
        frameLayout.setBackgroundColor(Color.BLACK);
        // Make Home Fragment As Default.
        setFragment(homeFragment);

        // When Select Item Of Bottom Nav Bar.
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                // Switch By ID.
                switch (menuItem.getItemId()) {

                    // Check Item By ID.
                    case R.id.nav_home:

                        // Change Color Of Frame.
                        frameLayout.setBackgroundColor(Color.BLACK);

                        // Call Method & Send HomeFragment As Param.
                        setFragment(homeFragment);

                        // To Prevent switch To Inter Inside Next case.
                        return true;

                    // Check Item By ID.
                    case R.id.nav_search:

                        // Change Color Of Frame.
                        frameLayout.setBackgroundColor(Color.BLUE);

                        setFragment(searchFragment);

                        // To Prevent switch To Inter Inside Next case.
                        return true;

                    // Check Item By ID.
                    case R.id.nav_notification:

                        // Change Color Of Frame.
                        frameLayout.setBackgroundColor(Color.GRAY);

                        setFragment(notificationFragment);

                        // To Prevent switch To Inter Inside Next case.
                        return true;

                     // If Not Any OF These IDs.
                    default:
                        return false;
                }
            }
        });
    }

    // setFragment Method Take Type Of Fragment As Param.
    private void setFragment(Fragment fragment) {

        // This 3 Line To Change From Frame LayOut To Fragment.
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.main_frame, fragment);

        fragmentTransaction.commit();
    }
}
