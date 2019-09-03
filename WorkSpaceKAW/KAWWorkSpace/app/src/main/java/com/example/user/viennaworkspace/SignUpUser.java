package com.example.user.viennaworkspace;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import info.hoang8f.widget.FButton;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SignUpUser extends AppCompatActivity {

    // Object [DBHelper] DataBase.
    DBHelper db;

    // EditText.
    EditText Name, EditNumCust;

    // Spinner.
    Spinner Activity;

    // ArrayList For Spinner.
    ArrayList<String> arrayList;

    // TextViews.
    TextView Place, StartTime;

    // Buttons.
    FButton Start;

    // LayOut For Animations.
    RelativeLayout Button1LayoutSignUp;

    // Place Of Animation.
    Animation LeftToRight;

    // To Store Last ID.
    static long ID;

    // To Store Number Of Customers In Hall From MSA Group.
    public static String strNumCust;

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
        setContentView(R.layout.activity_sign_up_user);

        // Constructor Take This Activity.
        db = new DBHelper(this);

        // Call GetLastestID() Method From DB To Get Last ID & Make +1  & Store It Into ID
        ID = db.GetLastestID() + 1;

        // Get LayOut By ID.
        Button1LayoutSignUp = (RelativeLayout) findViewById(R.id.btn_r1_sign_up);

        // Set Animation.
        LeftToRight = AnimationUtils.loadAnimation(this, R.anim.lefttoright);
        Button1LayoutSignUp.setAnimation(LeftToRight);

        // Get EditText By ID.
        Name = (EditText) findViewById(R.id.name);
        EditNumCust = (EditText) findViewById(R.id.num_of_cust);

        // Get Spinner By ID.
        Activity = (Spinner) findViewById(R.id.spnr_activity);

        // Create New ArrayList.
        arrayList = new ArrayList<String>();

        // Get TextViews By IDs.
        Place = (TextView) findViewById(R.id.place);
        StartTime = (TextView) findViewById(R.id.start_time);

        // Get Button By ID.
        Start = (FButton) findViewById(R.id.start);

        // Get Extras From Intent.
        Bundle extras = getIntent().getExtras();

        // Check If extras Not Null.
        assert extras != null;
        // Check If extras Contains Key Like ("...").
        if (extras.containsKey("Shared Space"))
            // Set Text For Place.
            Place.setText(extras.getString("Shared Space"));

        else if (extras.containsKey("Room1"))
            Place.setText(extras.getString("Room1"));

        else if (extras.containsKey("Room2"))
            Place.setText(extras.getString("Room2"));

        else if (extras.containsKey("Room3"))
            Place.setText(extras.getString("Room3"));

        else if (extras.containsKey("Room4"))
            Place.setText(extras.getString("Room4"));

        else if (extras.containsKey("Room5"))
            Place.setText(extras.getString("Room5"));

        else if (extras.containsKey("Room6"))
            Place.setText(extras.getString("Room6"));

        else if (extras.containsKey("Hall"))
            Place.setText(extras.getString("Hall"));

        // Call Method To Add Items Into Spinner.
        addItemIntoArray();

        // Click On Button.
        Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Store Name [Written From User] Into name String.
                String name = Name.getText().toString();

                // To Check It This Field Empty Or Not.
                String edtNumCust =  EditNumCust.getText().toString();

                // Store SelectedItem Into selectedActivity String.
                String selectedActivity = Activity.getSelectedItem().toString();

                // Shorted If Statement [Question ? If (True) : Else (False)]
                strNumCust = ((!edtNumCust.equals("") && Integer.parseInt(edtNumCust) >= 0) ?
                        edtNumCust : "0");

                // Convert String To Int To Store It Into DB.
                int intNumCust = Integer.parseInt(strNumCust);

                // Store Place From Extra Of Intent Into place String.
                String place = Place.getText().toString();

                // Store CurrentTime From Method getCurrentTime().
                String startTime = getCurrentTime(); // Get Current Time.

                    if (!name.equals("") &&
                            db.insertData(ID, name, selectedActivity, intNumCust, 0, 0, 0,
                                    0, 0, 0, place, startTime, "End Time","", 0)) {

                        Toast.makeText(SignUpUser.this, "Data Added", Toast.LENGTH_SHORT).show();

                        // Reset Name.
                        Name.setText("");

                        // Reset Edit Text To 1 As A Default.
                        EditNumCust.setText("0");

                        // Increase ID + 1.
                        ID++;

                        // GoTo Home Activity.
                        Intent intent = new Intent(SignUpUser.this, Home.class);
                        startActivity(intent);
                    }
                    // OtherWise.
                    else
                        Toast.makeText(SignUpUser.this, "Fill all fields", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Method for get current time.
    public static String getCurrentTime() {
        Calendar now = Calendar.getInstance();
        String currentDate = now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE);

        return currentDate;
    }

    // Method To Fill Data Into Spinner.
    private void addItemIntoArray() {
        // Store Place From Extra Of Intent Into place String.
        String place = Place.getText().toString();

        // Check Of All Of This Rules Before Adding Items Into Menu.
        if (place.equals("Shared Space")){
            arrayList.add("Standard Price");
            arrayList.add("Student Activity");
            arrayList.add("College Offer");
        }
        else if (place.equals("Room1")){
            arrayList.add("Standard Price");
            arrayList.add("Almun");
            arrayList.add("Career Zoom");
            arrayList.add("TEDx Cu");
            arrayList.add("TEDx MSA");
            arrayList.add("Special Offer");
        }
        else if (place.equals("Room2")){
            arrayList.add("Standard Price");
            arrayList.add("Almun");
            arrayList.add("Career Zoom");
            arrayList.add("TEDx Cu");
            arrayList.add("TEDx MSA");
            arrayList.add("Special Offer");
        }
        else if (place.equals("Room3")){
            arrayList.add("Standard Price");
            arrayList.add("Almun");
            arrayList.add("Career Zoom");
            arrayList.add("TEDx Cu");
            arrayList.add("TEDx MSA");
            arrayList.add("Special Offer");
        }
        else if (place.equals("Room4")){
            arrayList.add("Standard Price");
            arrayList.add("Almun");
            arrayList.add("Career Zoom");
            arrayList.add("TEDx Cu");
            arrayList.add("TEDx MSA");
            arrayList.add("Special Offer");
        }
        else if (place.equals("Room5")){
            arrayList.add("Standard Price");
            arrayList.add("Almun");
            arrayList.add("Career Zoom");
            arrayList.add("TEDx Cu");
            arrayList.add("Special Offer");
        }
        else if (place.equals("Room6")){
            arrayList.add("Standard Price");
            arrayList.add("Almun");
            arrayList.add("Career Zoom");
            arrayList.add("TEDx Cu");
            arrayList.add("TEDx MSA");
            arrayList.add("Special Offer");
        }
        else if (place.equals("Hall")){
            arrayList.add("Standard Price");
            arrayList.add("Career Zoom");
            arrayList.add("TEDx MSA");
            arrayList.add("Special Offer");
        }

        Activity.setPrompt("Select an item");
        Activity.setAdapter(new ArrayAdapter<String>(this,
                R.layout.spinner_item, arrayList));
    }
}
