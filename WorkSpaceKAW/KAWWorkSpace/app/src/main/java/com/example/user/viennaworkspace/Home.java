package com.example.user.viennaworkspace;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.text.InputType;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // Object Of DBHelper Class.
    DBHelper db;

    // Create Array List For List View To Show All Data.
    ArrayList<String> listItem;

    // Create Array Adapter For List View To Show All Data.
    ArrayAdapter adapter;

    // Create List View To Show All Data.
    ListView UserList;

    // Create TextView.
    TextView TotalOfDay, TotalOfDrinks, TotalOfDomte, TotalOfNoodles, TotalOfSharedSpace, TotalOfRooms;

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

        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Get ListView By ID.
        UserList = (ListView) findViewById(R.id.list_user_view);

        // Get View By ID For Total Of Money.
        TotalOfDay = (TextView) findViewById(R.id.total_of_cash);
        TotalOfDrinks = (TextView) findViewById(R.id.total_of_drinks);
        TotalOfDomte = (TextView) findViewById(R.id.total_of_domte);
        TotalOfNoodles = (TextView) findViewById(R.id.total_of_noodles);
        TotalOfSharedSpace = (TextView) findViewById(R.id.total_of_shared_space);
        TotalOfRooms = (TextView) findViewById(R.id.total_of_rooms);

        // Constructor Take This Activity.
        db = new DBHelper(this);

        // Create New Array List.
        listItem = new ArrayList<>();

        // Call Method To Show Data Into List View.
        showListItem();

        // When Click On Item Of ListView.
        UserList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Store All Data In Single Item Into AllInfo String.
                String AllInfo = UserList.getItemAtPosition(i).toString();

                // GoTo Update Activity.
                Intent intent = new Intent(Home.this, Update.class);
                // Put Extra As Key & Value.
                intent.putExtra("AllInfo", AllInfo);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    // Method To Show List Item On ListView To Show Data.
    private void showListItem() {
        // Make Cursor From viewData() Method Into DB.
        Cursor cursor = db.viewData();

        // Check If Count Of Cursor = 0.
        if (cursor.getCount() == 0)
            Toast.makeText(this, "No Data To Show", Toast.LENGTH_SHORT).show();

        // OtherWise.
        else {
            // While Loop For If Cursor Still Moving.
            while (cursor.moveToNext()) {
                // Add Item To Show Data Into List View.
                listItem.add(cursor.getInt(0) + "\n" + // 0 For ID.
                        cursor.getString(1) + "\n" + // 1 For Name.
                        cursor.getString(2) + "\n" + // 2 For VipType.
//                        3 For NumCustHallMSA, 4 For noodles, 5 For greenTea, 6 For blackTea, 7 For nescafeClassic, 8 For nescafe3IN1, 9 For Domte
//                          Don't Need To Appear them Into ListView.
                        cursor.getString(10) + "\n" + // 9 For Place
                        cursor.getString(11) + "\n" + // 10 For Start Time
                        cursor.getString(12) + "\n" + // 11 For End Time
                        cursor.getString(14));  // 12 For Start Total
            }

            // Set Adapter For List View.
            adapter = new ArrayAdapter(this, R.layout.list_form, R.id.rowTextView, listItem);
            UserList.setAdapter(adapter);
        }
    }

    // To Update When Add New Money.
    @Override
    protected void onStart() {
        super.onStart();
        // Get Method From DbHelper To Calculate All Of Cash Of This Day.
        TotalOfDrinks.setText(String.valueOf(db.getTotalCountOfDrinks()));
        TotalOfDomte.setText(String.valueOf(db.getTotalCountOfDomte()));
        TotalOfNoodles.setText(String.valueOf(db.getTotalCountOfNoodles()));
        TotalOfSharedSpace.setText(String.valueOf(db.getTotalCountOfSharedSpace()));
        TotalOfRooms.setText(String.valueOf(db.getTotalCountOfRooms()));
        TotalOfDay.setText(String.valueOf(db.getTotalOfDay()));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);

        // Make Search View For Filteration List View To Select What I Searched For.
        MenuItem searchItem = menu.findItem(R.id.action_search);

        // Create Search View To Search On Name.
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        // Make Text As Type Of Searching.
        searchView.setInputType(InputType.TYPE_CLASS_TEXT);
        // Make Hint For Search View.
        searchView.setQueryHint("Search by name...");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            // When Type Some Letters.
            @Override
            public boolean onQueryTextChange(String newText) {
                // Make ArrayList.
                ArrayList<String> selectedUsers = new ArrayList<>();

                // Make [For Each Loop] For Searching Into Content Of listItem.
                for (String user : listItem) {
                    // Check If Same Letters In LowerCase.
                    if (user.toLowerCase().contains(newText.toLowerCase()))
                        // Add It Into ArrayList.
                        selectedUsers.add(user);
                }
                // Make Adapter For This ArrayList To Appear Results.
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(Home.this,
                        R.layout.list_form, R.id.rowTextView, selectedUsers);
                UserList.setAdapter(adapter);

                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // Check If id = ID Of Menu's Item Like DELETE.
        if (id == R.id.action_delete) {
            // Store Value Of Total Of Previous Day Before Deleting Ole Data.
            int StoredTotalOfMoney = db.getTotalOfDay();

            // If StoredTotalOfMoney Not = 0.
            if(StoredTotalOfMoney != 0) {
                // Trick To Store Total Of Day Only Into DB & Appear It Alone Into ListView Of Recovery Data.
                db.insertRecoveryData("", "",1, 0,0, 0, 0,
                        0,0, "", "", "", "", 0,
                        StoredTotalOfMoney);

                // Remove All Stored Information from table Of DB.
                db.deleteMainTable();
                db.deleteSharedRoomsTable();

                // Remove All Stored Information from listView.
                UserList.setAdapter(null);

                // Reset Total Of Day When Day Is Done.
                TotalOfDrinks.setText("0");
                TotalOfDomte.setText("0");
                TotalOfNoodles.setText("0");
                TotalOfSharedSpace.setText("0");
                TotalOfRooms.setText("0");
                TotalOfDay.setText("0");


                // Reset ID To 1.
                SignUpUser.ID = db.GetLastestID() + 1;
            }
            // OtherWise.
            else
                Toast.makeText(this, "There's no data to delete it", Toast.LENGTH_SHORT).show();


            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_recover_data) {
            Intent intent = new Intent(Home.this, RecoveryData.class);
            startActivity(intent);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
