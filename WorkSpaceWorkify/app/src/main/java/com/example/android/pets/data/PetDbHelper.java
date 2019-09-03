package com.example.android.pets.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.pets.data.PetContract.PetEntry;

/**
 * Database helper for Pets app. Manages database creation and version management.
 */
public class PetDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = PetDbHelper.class.getSimpleName();

    /** Name of the database file */
    private static final String DATABASE_NAME = "shelter.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Constructs a new instance of {@link PetDbHelper}.
     * @param context of the app
     */
    public PetDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is created for the first time.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_PETS_TABLE =  "CREATE TABLE " + PetEntry.TABLE_NAME + " ("
                + PetEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PetEntry.COLUMN_NUM_TABLE_ROOM + " INTEGER NOT NULL, "
                + PetEntry.COLUMN_HOURS + " INTEGER NOT NULL,"
                + PetEntry.COLUMN_TABLE_ROOM + " INTEGER NOT NULL, "
                + PetEntry.COLUMN_TOTAL + " INTEGER NOT NULL DEFAULT 0);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_PETS_TABLE);
    }

    // Store All Cash Into TotalOfCash.
    public int getTotal(){
        int TotalOfCash = 0;

        SQLiteDatabase db = getReadableDatabase();
        try{
        Cursor cur = db.rawQuery("SELECT SUM(" + PetEntry.COLUMN_TOTAL + ") as Total FROM " + PetEntry.TABLE_NAME, null);
        if (cur.moveToFirst()) {
            TotalOfCash = cur.getInt(cur.getColumnIndex("Total"));// get final total
        }
        return TotalOfCash;}
        catch (Exception e){
            return 0;
        }

    }

    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }
}