package com.example.user.viennaworkspace;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import info.hoang8f.widget.FButton;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class Update extends AppCompatActivity {
    // Object Of DBHelper Class.
    DBHelper db;

    // Create Views.
    EditText Name, EditNumCust;
    TextView Activity;
    EditText MarketNoodles, MarketGreenTea, MarketBlackTea, MarketNescafeClassic, MarketNescafe3In1,
            MarketDomte, EditNote;
    TextView Place, StartTime;

    // Buttons.
    FButton btnEndTime, UpdateInfo;

    // Create Views.
    TextView txtShowEndTime, Total;

    // LayOut For Animations.
    RelativeLayout Button1LayoutUpdate;

    // Type Of Animation.
    Animation LeftToRight;

    // Strings.
    String OriginalInfo, strEndTime;

    // Get Number Of Customer In Hall From MSA Group.
    int numCust;

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
        setContentView(R.layout.activity_update);

        // Make Constructor From DBHelper Class Take This Activity As A Parameter.
        db = new DBHelper(this);

        // Get LayOuts By IDs.
        Button1LayoutUpdate = (RelativeLayout) findViewById(R.id.btn_r1_update);

        // Set Animation.
        LeftToRight = AnimationUtils.loadAnimation(this, R.anim.lefttoright);
        Button1LayoutUpdate.setAnimation(LeftToRight);

        // Get Views By IDs.
        Name = (EditText) findViewById(R.id.name);

        Activity = (TextView) findViewById(R.id.vip_customer_type);

        MarketNoodles = (EditText) findViewById(R.id.quantity_noodles);
        MarketGreenTea = (EditText) findViewById(R.id.quantity_green_tea);
        MarketBlackTea = (EditText) findViewById(R.id.quantity_black_tea);
        MarketNescafeClassic = (EditText) findViewById(R.id.quantity_nescafe_classic);
        MarketNescafe3In1 = (EditText) findViewById(R.id.quantity_nescafe_3_in_1);
        MarketDomte = (EditText) findViewById(R.id.quantity_domte);

        Place = (TextView) findViewById(R.id.place);

        EditNumCust = (EditText) findViewById(R.id.num_of_cust);

        StartTime = (TextView) findViewById(R.id.start_time);

        // Get Button By ID.
        btnEndTime = (FButton) findViewById(R.id.end_time);

        // Get View By ID.
        EditNote = (EditText) findViewById(R.id.note);

        // Get View By ID.
        txtShowEndTime = (TextView) findViewById(R.id.show_end_time);

        Total = (TextView) findViewById(R.id.total);

        UpdateInfo = (FButton) findViewById(R.id.update_info);

        Bundle extras = getIntent().getExtras();

        // Store All Data Come From Previous Activity Into The Key [AllInfo] In OriginalInfo.
        OriginalInfo = extras.getString("AllInfo");

        // Strip Info After New Line [\n] & Store In separated Array Of String.
        final String[] separated = OriginalInfo.split("\n");

        Name.setText(separated[1]);   // For Name
        Activity.setText(separated[2]);   // For Type Of Customer [VIP Or NOT].
        Place.setText(separated[3]); // For Place
        StartTime.setText(separated[4]);    // For StartTime

        // Call Method Take separated[0] [ID] As A Parameter.
        viewDataForID(separated[0]);   // Method To Check Total For ID.

        // Set Text For Num Of Customers By Get Last Number Of Customer By Specific ID From DB.
        EditNumCust.setText("" + db.GetLastNumCust(separated[0]));

        // Click On Button.
        btnEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Show Current Time From getCurrentTime() Method [As End Time].
                txtShowEndTime.setText(getCurrentTime());

                // VISIBLE The txtShowEndTime.
                txtShowEndTime.setVisibility(VISIBLE);

                // Convert txtShowEndTime To String To Store It Into strEndTime.
                strEndTime = txtShowEndTime.getText().toString(); // Get String Of End Time

                // Gone The btnEndTime [ Make It INVISIBLE].
                btnEndTime.setVisibility(GONE);
            }
        });

        // Click On Button.
        UpdateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = Name.getText().toString();
                String strNumCust = EditNumCust.getText().toString();
                String marketNoodles = MarketNoodles.getText().toString();
                String marketGreenTea = MarketGreenTea.getText().toString();
                String marketBlackTea = MarketBlackTea.getText().toString();
                String marketNescafeClassic = MarketNescafeClassic.getText().toString();
                String marketNescafe3In1 = MarketNescafe3In1.getText().toString();
                String marketDomte = MarketDomte.getText().toString();

                // Check If Fields Not Empty To Avoid Error.
                if (!name.equals("") && !strNumCust.equals("") && !marketNoodles.equals("") &&
                        !marketGreenTea.equals("") && !marketBlackTea.equals("") && !marketNescafeClassic.equals("")
                        && !marketNescafe3In1.equals("") && !marketDomte.equals("")) {

                    // Convert All Info To String To Store It Into DB.
                    String id = separated[0];

                    String activity = Activity.getText().toString();

                    String place = Place.getText().toString();

                    String note = EditNote.getText().toString();

                    String startTime = StartTime.getText().toString();

                    // Init Value From Total Price 0.
                    int totalPriceOfTime = 0;

                    // Get Updated Number Of Customers.
                    numCust = Integer.parseInt(EditNumCust.getText().toString());

                    // When Press On Btn End Time.
                    if (btnEndTime.getVisibility() == GONE) {
                        // Separate Start Time To Hours & Minutes.
                        final String[] separatedStartTime = startTime.split(":");
                        String HourStartTime = separatedStartTime[0];
                        String MinuteStartTime = separatedStartTime[1];

                        // Separate End Time To Hours & Minutes.
                        final String[] separatedEndTime = strEndTime.split(":");
                        String HourEndTime = separatedEndTime[0];
                        String MinuteEndTime = separatedEndTime[1];

                        // Get Total Of Hours.
                        int numberOfHours = 0;

                        // Convert Current Hours To Minutes + Current Minutes To Get Total Of Minutes..
                        int convertHoursToMinutes = (Integer.parseInt(HourEndTime) * 60 + Integer.parseInt(MinuteEndTime))
                                - (Integer.parseInt(HourStartTime) * 60 + Integer.parseInt(MinuteStartTime));

                        // Loop To Calculate Time Of Hours
                        // Check If Total Of Time - 60Min >= 0.
                        // 2 For Test.
                        while ((convertHoursToMinutes - 2) >= 0) {
                            // Increase 1 On The Total Of Hours.
                            numberOfHours += 1;

                            // Minus 60Min From Total Of Time.
                            // 2 For Test.
                            convertHoursToMinutes -= 2;
                        }

                        // Check If User Take Time Grater Than 30 Min.
                        // 1 For Test.
                        if ((convertHoursToMinutes - 1) >= 0) {
                            // Increase 1 On The Total Of Hours.
                            numberOfHours += 1;
                        }

                        // Check Place Is Shared Space &
                        // Hours Greater Than 1 [Start From 30 To 60 min (Check convertHoursToMinutes Var)].
                        if (place.equals("Shared Space")) {

                            // Check When User Become Alone.
                            if (activity.equals("Standard Price")) {
                                if (numberOfHours == 1)
                                    totalPriceOfTime = 15;
                                else if (numberOfHours >= 2 && numberOfHours <= 4)
                                    // [numberOfHours - 1] Cause We Need The First Hour Equal [15] As Constant.
                                    totalPriceOfTime = 15 + (10 * (numberOfHours - 1));
                                else if (numberOfHours > 4)
                                    // At 5th Hours Make Constant Price = 50.
                                    totalPriceOfTime = 50;
                            } else if (activity.equals("Student Activity")) {
                                if (numberOfHours >= 1 && numberOfHours <= 6)
                                    totalPriceOfTime = (5 * numberOfHours);
                                else if (numberOfHours > 6)
                                    // At 5th Hours Make Constant Price = 30.
                                    totalPriceOfTime = 25;
                            } else if (activity.equals("College Offer")) {
                                if (numberOfHours >= 1 && numberOfHours <= 2)
                                    totalPriceOfTime = 10;
                                else if (numberOfHours >= 3 && numberOfHours <= 5)
                                    totalPriceOfTime = (5 * numberOfHours);
                                else if (numberOfHours > 5)
                                    // At 5th Hours Make Constant Price = 25.
                                    totalPriceOfTime = 30;
                            }

                            // Get Total Of All Shared Space Price Before Adding Price Of Drinks.
                            db.insertTotalPlaces(totalPriceOfTime, 0);

                            // TO Avoid Get Price Without EndTime >= 30 Min Or
                            // Customer Leave Before 30Min But Toke Hot Drink.
                            if (totalPriceOfTime != 0 || note.toLowerCase().equals("gone")) {
                                // If Alone's Customers Take Hot Drinks.
                                if (!marketNoodles.equals("0")) {
                                    totalPriceOfTime += Integer.parseInt(marketNoodles) * 10;
                                }

                                if (!marketGreenTea.equals("0")) {
                                    totalPriceOfTime += Integer.parseInt(marketGreenTea) * 10;
                                }
                                if (!marketBlackTea.equals("0")) {
                                    totalPriceOfTime += Integer.parseInt(marketBlackTea) * 5;
                                }

                                if (!marketNescafeClassic.equals("0")) {
                                    totalPriceOfTime += Integer.parseInt(marketNescafeClassic) * 5;
                                }
                                if (!marketNescafe3In1.equals("0")) {
                                    totalPriceOfTime += Integer.parseInt(marketNescafe3In1) * 10;
                                }

                                if (!marketDomte.equals("0")) {
                                    totalPriceOfTime += Integer.parseInt(marketDomte) * 6;
                                }
                            }
                        }
                        // Check Place NOT Shared Space &
                        // Hours Greater Than 1 [Start From 30 To 60 min (Check convertHoursToMinutes Var)].
                        if (!place.equals("Shared Space") && numberOfHours >= 1) {

                            // Check When User Become In Room1.
                            // (10 * (numCust + 1) * numberOfHours) [numCust = 0] For Extra Customers 10L.e / person.
                            if (place.equals("Room1") && activity.equals("Standard Price"))
                                totalPriceOfTime += 110 * numberOfHours + (10 * (numCust + 1) * numberOfHours);
                            else if (place.equals("Room1") && activity.equals("Almun"))
                                totalPriceOfTime += 50 * numberOfHours + (10 * (numCust + 1) * numberOfHours);
                            else if (place.equals("Room1") && activity.equals("Career Zoom"))
                                totalPriceOfTime += 55 * numberOfHours + (10 * (numCust + 1) * numberOfHours);
                            else if (place.equals("Room1") && activity.equals("TEDx Cu"))
                                totalPriceOfTime += 90 * numberOfHours + (10 * (numCust + 1) * numberOfHours);
                            else if (place.equals("Room1") && activity.equals("TEDx MSA"))
                                totalPriceOfTime += 90 * numberOfHours + (10 * (numCust + 1) * numberOfHours);
                            else if (place.equals("Room1") && activity.equals("Special Offer"))
                                totalPriceOfTime += 5 * numCust * numberOfHours;

                                // Check When User Become In Room2.
                                // (10 * (numCust + 1) * numberOfHours) [numCust = 0] For Extra Customers 10L.e / person.
                            else if (place.equals("Room2") && activity.equals("Standard Price"))
                                totalPriceOfTime += 90 * numberOfHours + (10 * (numCust + 1) * numberOfHours);
                            else if (place.equals("Room2") && activity.equals("Almun"))
                                totalPriceOfTime += 45 * numberOfHours + (10 * (numCust + 1) * numberOfHours);
                            else if (place.equals("Room2") && activity.equals("Career Zoom"))
                                totalPriceOfTime += 50 * numberOfHours + (10 * (numCust + 1) * numberOfHours);
                            else if (place.equals("Room2") && activity.equals("TEDx Cu"))
                                totalPriceOfTime += 60 * numberOfHours + (10 * (numCust + 1) * numberOfHours);
                            else if (place.equals("Room2") && activity.equals("TEDx MSA"))
                                totalPriceOfTime += 60 * numberOfHours + (10 * (numCust + 1) * numberOfHours);
                            else if (place.equals("Room2") && activity.equals("Special Offer"))
                                totalPriceOfTime += 5 * numCust * numberOfHours;

                                // Check When User Become In Room3
                                // (10 * (numCust + 1) * numberOfHours) [numCust = 0] For Extra Customers 10L.e / person.
                            else if (place.equals("Room3") && activity.equals("Standard Price"))
                                totalPriceOfTime += 140 * numberOfHours + (10 * (numCust + 1) * numberOfHours);
                            else if (place.equals("Room3") && activity.equals("Almun"))
                                totalPriceOfTime += 65 * numberOfHours + (10 * (numCust + 1) * numberOfHours);
                            else if (place.equals("Room3") && activity.equals("Career Zoom"))
                                totalPriceOfTime += 140 * numberOfHours + (10 * (numCust + 1) * numberOfHours);
                            else if (place.equals("Room3") && activity.equals("TEDx Cu"))
                                totalPriceOfTime += 140 * numberOfHours + (10 * (numCust + 1) * numberOfHours);
                            else if (place.equals("Room3") && activity.equals("TEDx MSA"))
                                totalPriceOfTime += 140 * numberOfHours + (10 * (numCust + 1) * numberOfHours);
                            else if (place.equals("Room3") && activity.equals("Special Offer"))
                                totalPriceOfTime += 5 * numCust * numberOfHours;

                                // Check When User Become In Room4.
                                // (10 * (numCust + 1)) [numCust = 0] For Extra Customers 10L.e / person.
                            else if (place.equals("Room4") && activity.equals("Standard Price"))
                                totalPriceOfTime += 140 * numberOfHours + (10 * (numCust + 1) * numberOfHours);
                            else if (place.equals("Room4") && activity.equals("Almun"))
                                totalPriceOfTime += 65 * numberOfHours + (10 * (numCust + 1) * numberOfHours);
                            else if (place.equals("Room4") && activity.equals("Career Zoom"))
                                totalPriceOfTime += 140 * numberOfHours + (10 * (numCust + 1) * numberOfHours);
                            else if (place.equals("Room4") && activity.equals("TEDx Cu"))
                                totalPriceOfTime += 140 * numberOfHours + (10 * (numCust + 1) * numberOfHours);
                            else if (place.equals("Room4") && activity.equals("TEDx MSA"))
                                totalPriceOfTime += 140 * numberOfHours + (10 * (numCust + 1) * numberOfHours);
                            else if (place.equals("Room4") && activity.equals("Special Offer"))
                                totalPriceOfTime += 5 * numCust * numberOfHours;

                                // Check When User Become In Room5.
                                // (10 * (numCust + 1)) [numCust = 0] For Extra Customers 10L.e / person.
                            else if (place.equals("Room5") && activity.equals("Standard Price"))
                                totalPriceOfTime += 40 * numberOfHours + (10 * (numCust + 1) * numberOfHours);
                            else if (place.equals("Room5") && activity.equals("Almun"))
                                totalPriceOfTime += 40 * numberOfHours + (10 * (numCust + 1) * numberOfHours);
                            else if (place.equals("Room5") && activity.equals("Career Zoom"))
                                totalPriceOfTime += 40 * numberOfHours + (10 * (numCust + 1) * numberOfHours);
                            else if (place.equals("Room5") && activity.equals("TEDx Cu"))
                                totalPriceOfTime += 40 * numberOfHours + (10 * (numCust + 1) * numberOfHours);
                            else if (place.equals("Room5") && activity.equals("Special Offer"))
                                totalPriceOfTime += 5 * numCust * numberOfHours;

                                // Check When User Become In Room6.
                                // (10 * (numCust + 1)) [numCust = 0] For Extra Customers 10L.e / person.
                            else if (place.equals("Room6") && activity.equals("Standard Price"))
                                totalPriceOfTime += 90 * numberOfHours + (10 * (numCust + 1) * numberOfHours);
                            else if (place.equals("Room6") && activity.equals("Almun"))
                                totalPriceOfTime += 45 * numberOfHours + (10 * (numCust + 1) * numberOfHours);
                            else if (place.equals("Room6") && activity.equals("Career Zoom"))
                                totalPriceOfTime += 50 * numberOfHours + (10 * (numCust + 1) * numberOfHours);
                            else if (place.equals("Room6") && activity.equals("TEDx Cu"))
                                totalPriceOfTime += 60 * numberOfHours + (10 * (numCust + 1) * numberOfHours);
                            else if (place.equals("Room6") && activity.equals("TEDx MSA"))
                                totalPriceOfTime += 60 * numberOfHours + (10 * (numCust + 1) * numberOfHours);
                            else if (place.equals("Room6") && activity.equals("Special Offer"))
                                totalPriceOfTime += 5 * numCust * numberOfHours;

                                // Check When User Become In Hall.
                                // (10 * (numCust + 1)) [numCust = 0] For Extra Customers 10L.e / person.
                            else if (place.equals("Hall") && activity.equals("Standard Price"))
                                totalPriceOfTime += 440 * numberOfHours + (10 * (numCust + 1) * numberOfHours);
                            else if (place.equals("Hall") && activity.equals("Career Zoom"))
                                totalPriceOfTime += 390 * numberOfHours + (10 * (numCust + 1) * numberOfHours);
                            else if (place.equals("Hall") && activity.equals("TEDx MSA"))
                                totalPriceOfTime += 5 * numCust * numberOfHours;
                            else if (place.equals("Hall") && activity.equals("Special Offer"))
                                totalPriceOfTime += 5 * numCust * numberOfHours;

                            // Get Total Of All Rooms Price Before Adding Price Of Drinks.
                            db.insertTotalPlaces(0, totalPriceOfTime);

                            // TO Avoid Get Price Without EndTime >= 30 Min Or
                            // Customer Leave Before 30Min But Toke Hot Drink.
                            if (totalPriceOfTime != 0 || note.toLowerCase().equals("gone")) {
                                // If Room's Customers Take Hot Drinks.
                                if (!marketNoodles.equals("0")) {
                                    totalPriceOfTime += Integer.parseInt(marketNoodles) * 10;
                                }

                                if (!marketGreenTea.equals("0")) {
                                    totalPriceOfTime += Integer.parseInt(marketGreenTea) * 10;
                                }
                                if (!marketBlackTea.equals("0")) {
                                    totalPriceOfTime += Integer.parseInt(marketBlackTea) * 5;
                                }

                                if (!marketNescafeClassic.equals("0")) {
                                    totalPriceOfTime += Integer.parseInt(marketNescafeClassic) * 5;
                                }
                                if (!marketNescafe3In1.equals("0")) {
                                    totalPriceOfTime += Integer.parseInt(marketNescafe3In1) * 10;
                                }

                                if (!marketDomte.equals("0")) {
                                    totalPriceOfTime += Integer.parseInt(marketDomte) * 6;
                                }
                            }
                        }
                        // Set Text Of Total By Total Of Price.
                        Total.setText(String.valueOf(totalPriceOfTime));

                        // Get All Values From Fields & Convert It To Int Values.
                        int intTotal = Integer.parseInt(Total.getText().toString());

                        int intNoodles = Integer.parseInt(marketNoodles);
                        int intGreenTea = Integer.parseInt(marketGreenTea);
                        int intBlackTea = Integer.parseInt(marketBlackTea);
                        int intNescafeC = Integer.parseInt(marketNescafeClassic);
                        int intNescafe3In1 = Integer.parseInt(marketNescafe3In1);
                        int intDomte = Integer.parseInt(marketDomte);

                        // Check To Update Data & Store This Data Into Recovery Data.
                        if (intTotal > 0 &&

                                db.UpdateData(id, name, activity, numCust, intNoodles, intGreenTea, intBlackTea,
                                        intNescafeC, intNescafe3In1, intDomte, place, startTime, strEndTime, note, intTotal) &&

                                db.insertRecoveryData(name, activity, numCust, intNoodles, intGreenTea, intBlackTea,
                                        intNescafeC, intNescafe3In1, intDomte, place, startTime, strEndTime, note, intTotal,
                                        0)) {

                            Toast.makeText(Update.this, "Data Updated", Toast.LENGTH_SHORT).show();
                            UpdateInfo.setVisibility(GONE);

                        }

                        // OtherWise note = ["gone"]
                        // Need For Customer Didn't Take Less Than 30 Min And Must Leave The WorkSpace.
                        else if (note.toLowerCase().equals("gone") &&

                                db.UpdateData(id, name, activity, numCust, intNoodles, intGreenTea, intBlackTea,
                                        intNescafeC, intNescafe3In1, intDomte, place, startTime, strEndTime, note, intTotal) &&

                                db.insertRecoveryData(name, activity, numCust, intNoodles, intGreenTea, intBlackTea,
                                        intNescafeC, intNescafe3In1, intDomte, place, startTime, strEndTime, note, intTotal,
                                        0)) {

                            Toast.makeText(Update.this, "Data Updated", Toast.LENGTH_SHORT).show();
                            UpdateInfo.setVisibility(GONE);
                        } else
                            Toast.makeText(Update.this, "Total still 0", Toast.LENGTH_SHORT).show();
                    }
                    // OtherWise If Btn End Time VISIBLE.
                    else
                        Toast.makeText(Update.this, "Press on End Time button", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(Update.this, "Fields Cannot be Empty", Toast.LENGTH_SHORT).show();
            }

        });
    }

    // Method For Prevent Update Price After Finish Work.
    private void viewDataForID(String id) {
        // Invoke Method From DBHelper.
        Cursor cursor = db.viewDataForSingleID(id);

        // Move Through All Information Start From Index [0] ID.
        cursor.moveToFirst();

        // Check If Total Not Equal Or [Greater than 0] Or Note Equal gone.
        if (cursor.getInt(14) != 0 || cursor.getString(13).toLowerCase().equals("gone")) {
            // Get Data From DB & Show In Fields.
            MarketNoodles.setText(cursor.getString(4));
            MarketGreenTea.setText(cursor.getString(5));
            MarketBlackTea.setText(cursor.getString(6));
            MarketNescafeClassic.setText(cursor.getString(7));
            MarketNescafe3In1.setText(cursor.getString(8));
            MarketDomte.setText(cursor.getString(9));
            txtShowEndTime.setText(cursor.getString(12));
            EditNote.setText(String.valueOf(cursor.getString(13)));
            Total.setText(String.valueOf(cursor.getString(14)));

            // Change Visibility Of Each View To Prevent From Making New Update On Stored Data.
            txtShowEndTime.setVisibility(VISIBLE);
            btnEndTime.setVisibility(GONE);
            UpdateInfo.setVisibility(GONE);
        }
    }

    // Method for get current time.
    public static String getCurrentTime() {
        Calendar now = Calendar.getInstance();
        String currentDate = now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE);

        return currentDate;
    }

    // When Press On Back Button.. Just make Refresh For Data Into Home Activity.
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Update.this, Home.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}

//    // Get Total Of Hours.
//    // [Integer.parseInt(HourEndTime) - Integer.parseInt(HourStartTime)] For Test.
////                        int convertHoursToMinutes = 0;
//
//    // Convert Current Hours To Minutes + Current Minutes To Get Total Of Minutes.
//    // (Integer.parseInt(HourEndTime) * 60 + Integer.parseInt(MinuteEndTime))- (Integer.parseInt(HourStartTime) * 60 + Integer.parseInt(MinuteStartTime)).
//    int convertHoursToMinutes = (Integer.parseInt(HourEndTime) * 60 + Integer.parseInt(MinuteEndTime))
//            - (Integer.parseInt(HourStartTime) * 60 + Integer.parseInt(MinuteStartTime));
//
//// Check If Customer Take Time Grater Than Or Equal 30 Min.
//// >= 1 For Test.
//                        if (convertHoursToMinutes - 30 >= 0) {
//                                // Minus 30Min From Total Of Time.
//                                convertHoursToMinutes -= 30;
//                                // If User Take Minutes > 30 Minutes Then Increase 1 On The Total Of Time.
////                            convertHoursToMinutes += 1;
//                                }
//
//                                // Check Place Is Shared Space &
//                                // Hours Greater Than 1 [Start From 30 To 60 min (Check convertHoursToMinutes Var)].
//                                if (place.equals("Shared Space")) {
//
//                                // Check When User Become Alone.
//                                if (activity.equals("Standard Price")) {
//                                // Check If Customer Take Time Equal 60Min [One Hour] Or Equal 30 Min.
//                                if (convertHoursToMinutes == 1 * 60 || convertHoursToMinutes == 0)
//                                totalPriceOfTime = 15;