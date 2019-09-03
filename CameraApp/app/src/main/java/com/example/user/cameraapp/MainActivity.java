package com.example.user.cameraapp;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;


public class MainActivity extends AppCompatActivity {

    // Declare Variables.
    Button btn;

    // Create image_file & folder as a File To Store Image in Folder.
    File image_file, folder;

    // Counter To Change Name Of Images And Order Them From 0 To Infinity Number.
    int inc_num_name = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Call Views By ID.
        btn = (Button) findViewById(R.id.Button);

        // When Click On The Button.
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                * MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA Allow the user to use any app he
                * installed in his Device.
                * Or Use MediaStore.ACTION_IMAGE_CAPTURE To Use The DEFAULT Cam.
                */
                Intent cam_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                // Create file To Call the getFile() Method.
                File file = getFile();

                // Pass the Image into file by putExtra(KEY, Uri.fromFile(FILE Name)).
                cam_intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));

                // Call This Method Override onActivityResult.
                startActivity(cam_intent);

                // Tell The User To Check Image In Folder.
                Toast.makeText(MainActivity.this, "Image will save in "
                        + String.valueOf(folder), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Make new file.
    private File getFile() {
        // Create New Folder.
        folder = new File("sdcard/Camera_app");

        // If the Folder NOT exists.. Make new one.
        if (!folder.exists())
            folder.mkdir();

        // Create image_file to Save the Image into the folder for first time only.
        image_file = new File(folder, inc_num_name + "_cam_image.png");

        // Check if the file's name is exists increase 1 and make new file by new name.
        while (image_file.exists()){
            // Increase Counter To Change Name Of Images By 1.
            inc_num_name++;

            // Create New image_file to Save the Image into the folder by new name.
            image_file = new File(folder, inc_num_name + "_cam_image.png");
        }

        return image_file;
    }
}
