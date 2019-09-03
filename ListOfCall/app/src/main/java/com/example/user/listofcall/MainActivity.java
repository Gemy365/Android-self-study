package com.example.user.listofcall;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Global variable from numbers class.
    numbers item;
   final int REQUEST_PRODUCTIONS_CODE = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * In this case.. ArrayList Allows the numbers class.
         * Use [x.add] to add items by adding new constructor of numbers every time.
         * Use final To use num into OnItemClickListener.
         */
        final ArrayList<numbers> num = new ArrayList<numbers>();
        num.add(new numbers("Nemo", "0112 488 8474"));
        num.add(new numbers("Mam", "0111 099 2708"));
        num.add(new numbers("Dad", "0114 772 3445"));
        num.add(new numbers("Sh3LaN", "0112 231 5519"));
        num.add(new numbers("BahNaSy", "0111 489 3703"));
        num.add(new numbers("Salma", "0112 199 2333"));
        num.add(new numbers("M7med 7asan", "0112 155 0600"));
        num.add(new numbers("BaDr", "0112 700 5833"));

        /**
         * Call the numbersAdapter class that extends from ArrayAdapter to allows you to use getView method.
         * numbersAdapter Construtor Has Two Parameters.
         * @param this As This Activity.
         * @param num Pass the ArrayList.
         */

        numbersAdapter adapter = new numbersAdapter(this, num);

        // Get the ListView by Id.
        ListView listView = (ListView) findViewById(R.id.list);

        // Use setAdapter to send the list of items to the ListView to display it.
        listView.setAdapter(adapter);

        //Check if NOT accept Permissions from device call the requestPermission to make a request.
        if (!checkPermissionFromDevice())
            requestPermission();

        /**
         * When click on items in the list.. will start the call.
         * When You write [new AdapterView.OnItemClickListener()] the Override method will created Auto.
         */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                item = num.get(position);

                //Check if accept Permissions from device make a call.
                if (checkPermissionFromDevice()) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + item.getmNums()));
                // If it has red line.. it's not error it's warning.
                startActivity(intent);
                Toast.makeText(MainActivity.this, "Call " + item.getmNames(), Toast.LENGTH_SHORT).show();
                }
                // Else try to make a request.
                else
                    requestPermission();
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
