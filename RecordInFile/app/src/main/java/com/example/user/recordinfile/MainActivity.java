package com.example.user.recordinfile;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    // Declare Variables As TextViews.
    TextView record, stopRecord, play, stop;
    final int PRODUCTION_CODE = 1000;
    MediaRecorder mediaRecorder;
    MediaPlayer mediaPlayer;

    // Declare Variable record_file & folder To Store The Record in Folder.
    File record_file, folder;

    // Counter To Change Name Of Records And Order Them From 1 To Infinity Number.
    int inc_num_name = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Init Views
        record = (TextView) findViewById(R.id.Record);

        stopRecord = (TextView) findViewById(R.id.StopRecord);

        play = (TextView) findViewById(R.id.Play);

        stop = (TextView) findViewById(R.id.Stop);

        //Check if NOT accept Permissions from device.
        if (!checkPermissionFromDevice())
            requestPermission();

        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermissionFromDevice()) {
                    // Call the getFile() Method.
                    getFile();
                    // Call setUpRecord method to SetUp MIC.
                    setUpRecord();

                    try {
                        mediaRecorder.prepare();
                        mediaRecorder.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    // [.setEnabled] Make Buttons Enable or Disable To Click On It.
                    record.setEnabled(false);
                    stopRecord.setEnabled(true);
                    play.setEnabled(false);
                    stop.setEnabled(false);

                    Toast.makeText(MainActivity.this, "Recording...", Toast.LENGTH_SHORT).show();
                } else
                    requestPermission();
            }
        });

        stopRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaRecorder != null) {
                    mediaRecorder.release();

                    record.setEnabled(true);
                    stopRecord.setEnabled(false);
                    play.setEnabled(true);
                    stop.setEnabled(false);

                    Toast.makeText(MainActivity.this, "Stop Recording...", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(MainActivity.this, "Make Record First...", Toast.LENGTH_SHORT).show();
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Make New MediaPlayer to Avoid play the same record Every time.
                mediaPlayer = new MediaPlayer();

                try {
                    // Call the record by own path from folder.
                    mediaPlayer.setDataSource(String.valueOf(record_file));
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                record.setEnabled(false);
                stopRecord.setEnabled(false);
                play.setEnabled(false);
                stop.setEnabled(true);

                Toast.makeText(MainActivity.this, "Play Audio...", Toast.LENGTH_SHORT).show();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer != null) {
                    mediaPlayer.release();

                    record.setEnabled(true);
                    stopRecord.setEnabled(false);
                    play.setEnabled(true);
                    stop.setEnabled(false);

                    Toast.makeText(MainActivity.this, "Stop Audio...", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(MainActivity.this, "play Audio First...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Make new file.
    private File getFile() {
        // Create New Folder.
        folder = new File("sdcard/Record_app");

        // If the Folder not exists.. Make new one.
        if (!folder.exists())
            folder.mkdir();

        // Create record_file to Save the Record into the folder By Initial value of inc_num_name.
        record_file = new File(folder, inc_num_name + "_record.mp3");

        // Check if the file's name is exists increase 1 and make new file by new name,
        // False For First Time Only But Always True After That.
        while (record_file.exists()) {

            // Increase Counter To Change Name Of Images By 1.
            inc_num_name++;

            // Create record_file to Save the Record into the folder.
            record_file = new File(folder, inc_num_name + "_record.mp3");
        }

        return record_file;
    }


    // SetUp The Recorder To Allow the MIC & The Path To Save File.
    public void setUpRecord() {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setOutputFile(String.valueOf(record_file));
    }

    // Send these Permissions to Override onRequestPermissionsResult Method to ask the program,
    // to send the result as permission accepted or permission denied.
    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO
        }, PRODUCTION_CODE);
    }

    /**
     * Ctrl + O
     * Override Method Calling Automatic When we call requestPermission Method Cause,
     * requestPermission Method Has "ActivityCompat.requestPermissions" That's as Calling,
     * the Override onRequestPermissionsResult Method.
     * Check If The Permissions are in Manifest.xml or not.
     **/
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
    }

    /**
     * PackageManager.PERMISSION_GRANTED = 0 Means True
     * PackageManager.PERMISSION_DENIED = -1 Means False
     * This method return true (0) if every things is Ok and accepted the permissions from the user,
     * Else return false (-1) if every things is NOT Ok and NOT accept the permissions from the user
     * Ask the user to allow these permission manual.
     **/
    public boolean checkPermissionFromDevice() {
        int write_external_storage_result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int record_audio_result = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);
        return write_external_storage_result == PackageManager.PERMISSION_GRANTED &&
                record_audio_result == PackageManager.PERMISSION_GRANTED;
    }
}
