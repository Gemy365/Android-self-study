package com.example.user.viennaworkspace;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import info.hoang8f.widget.FButton;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Market extends AppCompatActivity {
    // Object From DBHelper Class.
    DBHelper db;

    // Views.
    EditText MarketNoodles, MarketGreenTea, MarketBlackTea, MarketNescafeClassic, MarketNescafe3In1, MarketDomte;

    FButton Buy;

    // To Make All App Like As The Same Font Make Sure To Put This Code Before onCreate Method
    // In Every Activity Press Ctrl + O.
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // To Make All App Like As The Same Font Make Sure To Put This Code Before setContentView Method
        // In Every Activity.
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Capture_it.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
        setContentView(R.layout.activity_market);

        // Create Constructor Take This Activity As A Parameter.
        db = new DBHelper(this);

        // Get Views By IDs.
        MarketNoodles = (EditText) findViewById(R.id.quantity_noodles);
        MarketGreenTea = (EditText) findViewById(R.id.quantity_green_tea);
        MarketBlackTea = (EditText) findViewById(R.id.quantity_black_tea);
        MarketNescafeClassic = (EditText) findViewById(R.id.quantity_nescafe_classic);
        MarketNescafe3In1 = (EditText) findViewById(R.id.quantity_nescafe_3_in_1);
        MarketDomte = (EditText) findViewById(R.id.quantity_domte);

        // Get Button By ID.
        Buy = (FButton) findViewById(R.id.buy);

        // When Click On Button
        Buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Last ID & Increase 1.
                long ID = db.GetLastestID() + 1;

                // Check If All Fields Not Empty.
                if (!MarketNoodles.getText().toString().isEmpty() && !MarketGreenTea.getText().toString().isEmpty() &&
                        !MarketBlackTea.getText().toString().isEmpty() && !MarketNescafeClassic.getText().toString().isEmpty() &&
                        !MarketNescafe3In1.getText().toString().isEmpty() && !MarketDomte.getText().toString().isEmpty()) {

                    // Get All Values From Fields & Convert It To Int Values.
                    int intNoodles = Integer.parseInt(MarketNoodles.getText().toString());
                    int intGreenTea = Integer.parseInt(MarketGreenTea.getText().toString());
                    int intBlackTea = Integer.parseInt(MarketBlackTea.getText().toString());
                    int intNescafeC = Integer.parseInt(MarketNescafeClassic.getText().toString());
                    int intNescafe3In1 = Integer.parseInt(MarketNescafe3In1.getText().toString());
                    int intDomte = Integer.parseInt(MarketDomte.getText().toString());

                    // Init Total Price Of Items = 0.
                    int totalPriceOfItems = 0;

                    // Check If All Items = 0 Quantity.
                    if (intNoodles == 0 && intGreenTea == 0 && intBlackTea == 0 && intNescafeC == 0 &&
                            intNescafe3In1 == 0 && intDomte == 0) {
                        Toast.makeText(Market.this, "Can't buy with 0 Quantity!!", Toast.LENGTH_SHORT).show();
                    }

                    // If Any Item Not = 0.
                    if (intNoodles != 0) {
                        totalPriceOfItems += intNoodles * 10;
                    }

                    if (intGreenTea != 0) {
                        totalPriceOfItems += intGreenTea * 10;
                    }

                    if (intBlackTea != 0) {
                        totalPriceOfItems += intBlackTea * 5;
                    }

                    if (intNescafeC != 0) {
                        totalPriceOfItems += intNescafeC * 5;
                    }

                    if (intNescafe3In1 != 0) {
                        totalPriceOfItems += intNescafe3In1 * 10;
                    }

                    if (intDomte != 0) {
                        totalPriceOfItems += intDomte * 6;
                    }

                    // Check If User Buy Any Item..
                    if (totalPriceOfItems > 0 &&
                            db.insertData(ID, "", "", 1,intNoodles, intGreenTea, intBlackTea,
                                    intNescafeC, intNescafe3In1, intDomte, "Market", "", "",
                                    "", totalPriceOfItems) &&

                            db.insertRecoveryData("", "", 1,intNoodles, intGreenTea, intBlackTea,
                                    intNescafeC, intNescafe3In1, intDomte, "Market", "", "",
                                    "", totalPriceOfItems, 0)) {

                        Toast.makeText(Market.this, "Buy From Market", Toast.LENGTH_SHORT).show();

                        // GoTo Main Activity.
                        Intent intent = new Intent(Market.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                }
                // OtherWise If There's Empty Fields.
                else
                    Toast.makeText(Market.this, "Check Empty Fields!!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
