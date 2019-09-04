package com.example.android.circlemenu;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;
import com.hitomi.cmlibrary.OnMenuStatusChangeListener;

public class CircleMenuActivity extends AppCompatActivity {

    RelativeLayout R1;
    CircleMenu circleMenu;
    final int REQUEST_PRODUCTIONS_CODE = 1000;

    String urls[] = {"https://www.facebook.com/Gemy365",
            "01114767290",
            "https://www.youtube.com/watch?v=34JQxtOgYmY"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_menu);

        R1 = (RelativeLayout) findViewById(R.id.r1);
        circleMenu = (CircleMenu) findViewById(R.id.circle_menu);

        //Color.parseColor("COLOR") COLOR MUST BE 6 BIT TO AVOID THE CRASHING.
        circleMenu.setMainMenu(Color.parseColor("#CDCDCD"), R.drawable.add_icon, R.drawable.close_icon)
                .addSubMenu(Color.parseColor("#0039E4"), R.drawable.facebook_icon)
                .addSubMenu(Color.parseColor("#FF25CE08"), R.drawable.call_icon)
                .addSubMenu(Color.parseColor("#FFFFFF"), R.drawable.youtube_icon)
                .addSubMenu(Color.parseColor("#FFF54100"), R.drawable.camera_icon);

        //Check if NOT accept Permissions from device call the requestPermission to make a request.
        if (!checkPermissionFromDevice())
            requestPermission();

        circleMenu.setOnMenuSelectedListener(new OnMenuSelectedListener() {
            @Override
            public void onMenuSelected(int index) {
                switch (index) {
                    case 0:
                    case 2: {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urls[index]));
                        startActivity(intent);
                        break;
                    }
                    case 1: {
                        //Check if accept Permissions from device make a call.
                        if (checkPermissionFromDevice()) {
                        Intent intent = new Intent(Intent.ACTION_CALL);
                        intent.setData(Uri.parse("tel:" + urls[index]));
                        startActivity(intent);
                            break;
                        }
                        // Else try to make a request.
                        else
                            requestPermission();
                        break;
                    }
                    case 3: {
                        Intent cam_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivity(cam_intent);
                        break;
                    }
                }
            }
        });
        circleMenu.setOnMenuStatusChangeListener(new OnMenuStatusChangeListener() {

            // For Main Menu.
            @Override
            public void onMenuOpened() {
                R1.setBackgroundResource(R.drawable.back_ground);
            }
            // For Main Menu.
            @Override
            public void onMenuClosed() {
                R1.setBackgroundResource(R.drawable.black);
            }
        });
}

    // Send these Permissions to Override onRequestPermissionsResult Method to ask the program,
    // to send the result as permission accepted or permission denied.
    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.CALL_PHONE
        }, REQUEST_PRODUCTIONS_CODE);
    }

    /**
     * Override Method Calling Automatic When we call requestPermission Method Cause,
     * requestPermission Method Has "ActivityCompat.requestPermissions" That's as Calling,
     * the Override onRequestPermissionsResult Method.
     * This Method Used To Check If The Permissions are in Manifest.xml or not.
     **/
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {

            case REQUEST_PRODUCTIONS_CODE: {

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();

                else
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
            break;
        }
    }
    // Ask the user to allow these permission manual.
    private boolean checkPermissionFromDevice() {
        int make_call_result = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE); // == 0 is True

        return make_call_result == PackageManager.PERMISSION_GRANTED;
    }
}
