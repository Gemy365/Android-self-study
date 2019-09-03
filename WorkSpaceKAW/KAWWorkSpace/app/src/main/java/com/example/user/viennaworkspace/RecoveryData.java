package com.example.user.viennaworkspace;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import info.hoang8f.widget.FButton;

public class RecoveryData extends AppCompatActivity {

    // Object From DBHelper.
    DBHelper db;

    // ArrayList For ListView.
    ArrayList<String> listItem;

    // ArrayAdapter For ListView.
    ArrayAdapter adapter;

    // ListView.
    ListView UserList;

    // Button.
    FButton StoreDataIntoFile;

    // LayOut For Animation.
    RelativeLayout LayoutRecoverData;

    // Type Of Animation.
    Animation LeftToRight;

    // Get Extra From Intent.
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
        setContentView(R.layout.activity_recovery_data);

        // Get Extra From Intent.
        extras = getIntent().getExtras();

        // Get LayOut By ID.
        LayoutRecoverData = (RelativeLayout) findViewById(R.id.r1_recover_data);

        // Set Animation.
        LeftToRight = AnimationUtils.loadAnimation(this, R.anim.lefttoright);
        LayoutRecoverData.setAnimation(LeftToRight);

        // Get Button By ID.
        StoreDataIntoFile = (FButton) findViewById(R.id.store_into_file);

        // Get ListView By ID.
        UserList = (ListView) findViewById(R.id.list_user_recovery_view);


        // Create Constructor Take This Activity As A Parameter.
        db = new DBHelper(this);

        // Create New ArrayList.
        listItem = new ArrayList<>();

        // Call Method.
        showListItem();

        // Store Items Of Listview Into Text File When Click On This Button.
        StoreDataIntoFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    // Store Items Of Listview Into Text File.
                    File locationFile = new File("/sdcard/KAWDataRecovery.txt");

                    // Make New File.
                    locationFile.createNewFile();

                    // Open File.
                    FileOutputStream fOut = new FileOutputStream(locationFile);

                    // Start To Write Into File.
                    OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);

                    // Write Lines Of Words Into File.
                    // Items of Listview have [,] Between Each One. In This Case I Don't Need [,] So Replace with 2 New Lines.
                    myOutWriter.append(listItem.toString().replace(",", "\n\n"));

                    // Close File.
                    myOutWriter.close();
                    fOut.close();

                    // Message.
                    Toast.makeText(getBaseContext(), "Data saved", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(getBaseContext(), e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Method To Show List Of Data Into ListView.
    private void showListItem() {
        // Get viewRecoveryData() Method From DBHelper Class.
        Cursor cursor = db.viewRecoveryData();

        // If Count Of Rows = 0.
        if (cursor.getCount() == 0)
            Toast.makeText(this, "No Data To Show", Toast.LENGTH_SHORT).show();

        // OtherWise.
        else {
            // Move Next If Available.
            while (cursor.moveToNext()) {
                // If Place Not Equal Null & Number Of Customer Hall From MSA = 1 [Meaning Equal Default].
                if (!cursor.getString(10).equals("") && cursor.getInt(3) == 0) {
                    listItem.add("Name: " + cursor.getString(1) + "\n" + // 1 For Name
                            "Activity: " + cursor.getString(2) + "\n" + // 2 For Group, 3 For No. Extra Customers No Need To Appear It.
                            "Place: " + cursor.getString(10) + "\n" + // 10 For Place We Need It After Group For Arrangement.
                            "NOODLES: " + cursor.getString(4) + "\n" + // 4 For NOODLES
                            "GreenTEA: " + cursor.getString(5) + "\n" + // 5 For GreenTEA
                            "BlackTEA: " + cursor.getString(6) + "\n" + // 6 For BlackTEA
                            "NescafeClassic: " + cursor.getString(7) + "\n" + // 7 For NescafeClassic
                            "Nescafe3IN1: " + cursor.getString(8) + "\n" + // 8 For Nescafe3IN1
                            "Domte: " + cursor.getString(9) + "\n" + // 9 For Domte
                            "Start Time: " + cursor.getString(11) + "\n" + // 11 For Start Time
                            "End Time: " + cursor.getString(12) + "\n" + // 12 For End Time
                            "Note: " + cursor.getString(13) + "\n" + // 13 For Note
                            "Total: " + cursor.getString(14));  // 14 For Start Total
                }
                // If Place Not Equal Null & Number Of Customer Hall From MSA != 1 [Meaning Not Equal Default].
                else if (!cursor.getString(10).equals("") && cursor.getInt(3) != 0) {
                    listItem.add("Name: " + cursor.getString(1) + "\n" + // 1 For Name
                            "Activity: " + cursor.getString(2) + "\n" + // 2 For Activity.
                            "No. Extra Customers : " + cursor.getInt(3) + "\n" + // 3 For Number Of Customer.
                            "Place: " + cursor.getString(10) + "\n" + // 10 For Place
                            "NOODLES: " + cursor.getString(4) + "\n" + // 4 For NOODLES
                            "Green TEA: " + cursor.getString(5) + "\n" + // 5 For GreenTEA
                            "Black TEA: " + cursor.getString(6) + "\n" + // 6 For BlackTEA
                            "Nescafe Classic: " + cursor.getString(7) + "\n" + // 7 For NescafeClassic
                            "Nescafe 3 IN 1: " + cursor.getString(8) + "\n" + // 8 For Nescafe3IN1
                            "Domte: " + cursor.getString(9) + "\n" + // 9 For Domte
                            "Start Time: " + cursor.getString(11) + "\n" + // 11 For Start Time
                            "End Time: " + cursor.getString(12) + "\n" + // 12 For End Time
                            "Note: " + cursor.getString(13) + "\n" + // 13 For Note
                            "Total: " + cursor.getString(14));  // 14 For Start Total
                }
                // If Place Equal Null.
                else if (cursor.getString(10).equals("")){
                    listItem.add("\n\n\n" +"Total of day: " + cursor.getString(15) + "\n\n\n");  // When Day is Done.. Store Total Of Day Only.
                }
            }
        }
        // Make Adapter For ListView.
        adapter = new ArrayAdapter(this, R.layout.list_form, R.id.rowTextView, listItem);
        UserList.setAdapter(adapter);
    }
}
