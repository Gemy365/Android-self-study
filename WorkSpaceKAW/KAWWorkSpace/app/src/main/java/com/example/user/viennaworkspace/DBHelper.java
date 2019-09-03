package com.example.user.viennaworkspace;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// Class For SQLite DATABASE.
public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "Vienna.db";
    private static final String DB_TABLE = "Users";
    private static final String RECOVERY_DB_TABLE = "RecoveryUsers";
    private static final String SHARED_AND_ROOMS_TABLE = "TotalPlaces";
    private static final String ID = "ID";
    private static final String NAME = "NAME";
    private static final String ACTIVITY = "ACTIVITY";
    private static final String NUMCUST = "NUMCUST";
    private static final String NOODLES = "NOODLES";
    private static final String GREENTEA = "GREENTEA";
    private static final String BLACKTEA = "BLACKTEA";
    private static final String NESCAFECLASSIC = "NESCAFECCLASSIC";
    private static final String NESCAFE3IN1 = "NESCAFE3IN1";
    private static final String DOMTE = "DOMTE";
    private static final String PLACE = "PLACE";
    private static final String START_TIME = "START_TIME";
    private static final String END_TIME = "END_TIME";
    private static final String NOTE = "NOTE";
    private static final String TOTALOFCUSTOMER = "TOTALOFCUSTOMER";
    private static final String TOTALOFSHAREDSPACE = "TOTALOFSHAREDSPACE";
    private static final String TOTALOFROOMS = "TOTALOFROOMS";
    private static final String TOTALOFDAY = "TOTALOFDAY";

    private static final String CREATE_TABLE = "CREATE TABLE " + DB_TABLE + " (" +
            ID + " INTEGER PRIMARY KEY, " +
            NAME + " TEXT NOT NULL, " +
            ACTIVITY + " TEXT, " +
            NUMCUST + " INTEGER DEFAULT 0, " +
            NOODLES + " INTEGER DEFAULT 0, " +
            GREENTEA + " INTEGER DEFAULT 0, " +
            BLACKTEA + " INTEGER DEFAULT 0, " +
            NESCAFECLASSIC + " INTEGER DEFAULT 0, " +
            NESCAFE3IN1 + " INTEGER DEFAULT 0, " +
            DOMTE + " INTEGER DEFAULT 0, " +
            PLACE + " TEXT NOT NULL, " +
            START_TIME + " TEXT, " +
            END_TIME + " TEXT, " +
            NOTE + " TEXT, " +
            TOTALOFCUSTOMER + " INTEGER )";

    private static final String CREATE_RECOVERY_TABLE = "CREATE TABLE " + RECOVERY_DB_TABLE + " (" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NAME + " TEXT NOT NULL, " +
            ACTIVITY + " TEXT, " +
            NUMCUST + " INTEGER DEFAULT 0, " +
            NOODLES + " INTEGER DEFAULT 0, " +
            GREENTEA + " INTEGER DEFAULT 0, " +
            BLACKTEA + " INTEGER DEFAULT 0, " +
            NESCAFECLASSIC + " INTEGER DEFAULT 0, " +
            NESCAFE3IN1 + " INTEGER DEFAULT 0, " +
            DOMTE + " INTEGER DEFAULT 0, " +
            PLACE + " TEXT NOT NULL, " +
            START_TIME + " TEXT, " +
            END_TIME + " TEXT, " +
            NOTE + " TEXT, " +
            TOTALOFCUSTOMER + " INTEGER," +
            TOTALOFDAY + " INTEGER )";

    private static final String CREATE_SHARED_ROOMS_TABLE = "CREATE TABLE " + SHARED_AND_ROOMS_TABLE + " (" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            TOTALOFSHAREDSPACE + " INTEGER DEFAULT 0, " +
            TOTALOFROOMS + " INTEGER DEFAULT 0 )";


    public DBHelper(Context context) {
        super(context, DB_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
        sqLiteDatabase.execSQL(CREATE_RECOVERY_TABLE);
        sqLiteDatabase.execSQL(CREATE_SHARED_ROOMS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Drop older table if existed
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
        // Drop older table if existed
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + RECOVERY_DB_TABLE);
        // Drop older table if existed
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + SHARED_AND_ROOMS_TABLE);
        // Create tables again
        onCreate(sqLiteDatabase);
    }

    // Method To Insert Data.
    public boolean insertData(long id, String name, String activity, int num_cust, int noodles, int green_tea, int black_tea,
                              int nescafe_classic, int nescafe_3_in_1, int domte, String place,
                              String start_time, String end_time, String note, int total) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(ID, id);
        contentValues.put(NAME, name);
        contentValues.put(ACTIVITY, activity);
        contentValues.put(NUMCUST, num_cust);
        contentValues.put(NOODLES, noodles);
        contentValues.put(GREENTEA, green_tea);
        contentValues.put(BLACKTEA, black_tea);
        contentValues.put(NESCAFECLASSIC, nescafe_classic);
        contentValues.put(NESCAFE3IN1, nescafe_3_in_1);
        contentValues.put(DOMTE, domte);
        contentValues.put(PLACE, place);
        contentValues.put(START_TIME, start_time);
        contentValues.put(END_TIME, end_time);
        contentValues.put(NOTE, note);
        contentValues.put(TOTALOFCUSTOMER, total);

        long result = db.insert(DB_TABLE, null, contentValues);

        return result != -1; // if result = -1 data doesn't insert.
    }

    // Method To Insert Recovery Data.
    public boolean insertRecoveryData(String name, String activity, int num_cust, int noodles, int green_tea, int black_tea,
                                      int nescafe_classic, int nescafe_3_in_1, int domte, String place,
                                      String start_time, String end_time, String note, int total, int totalOfDay) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(NAME, name);
        contentValues.put(ACTIVITY, activity);
        contentValues.put(NUMCUST, num_cust);
        contentValues.put(NOODLES, noodles);
        contentValues.put(GREENTEA, green_tea);
        contentValues.put(BLACKTEA, black_tea);
        contentValues.put(NESCAFECLASSIC, nescafe_classic);
        contentValues.put(NESCAFE3IN1, nescafe_3_in_1);
        contentValues.put(DOMTE, domte);
        contentValues.put(PLACE, place);
        contentValues.put(START_TIME, start_time);
        contentValues.put(END_TIME, end_time);
        contentValues.put(NOTE, note);
        contentValues.put(TOTALOFCUSTOMER, total);
        contentValues.put(TOTALOFDAY, totalOfDay);

        long result = db.insert(RECOVERY_DB_TABLE, null, contentValues);

        return result != -1; // if result = -1 data doesn't insert.
    }

    // Method To Insert Data.
    public boolean insertTotalPlaces(int total_shaered_space, int total_rooms) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(TOTALOFSHAREDSPACE, total_shaered_space);
        contentValues.put(TOTALOFROOMS, total_rooms);

        long result = db.insert(SHARED_AND_ROOMS_TABLE, null, contentValues);

        return result != -1; // if result = -1 data doesn't insert.
    }



    // Method To Update Data.
    public boolean UpdateData(String id, String name, String activity, int num_cust, int noodles, int green_tea, int black_tea,
                              int nescafe_classic, int nescafe_3_in_1, int domte, String place,
                              String start_time, String end_time, String note, int total) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(ID, id);
        contentValues.put(NAME, name);
        contentValues.put(ACTIVITY, activity);
        contentValues.put(NUMCUST, num_cust);
        contentValues.put(NOODLES, noodles);
        contentValues.put(GREENTEA, green_tea);
        contentValues.put(BLACKTEA, black_tea);
        contentValues.put(NESCAFECLASSIC, nescafe_classic);
        contentValues.put(NESCAFE3IN1, nescafe_3_in_1);
        contentValues.put(DOMTE, domte);
        contentValues.put(PLACE, place);
        contentValues.put(START_TIME, start_time);
        contentValues.put(END_TIME, end_time);
        contentValues.put(NOTE, note);
        contentValues.put(TOTALOFCUSTOMER, total);

        long result = db.update(DB_TABLE, contentValues, ID + " = " + id, null);

        return result != -1; // if result = -1 data doesn't insert.
    }

    // Method To View Data.
    public Cursor viewData() {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM \"" + DB_TABLE + "\";";

        Cursor cursor = db.rawQuery(query, null);

        return cursor;
    }

    // Method To View Recovery Data.
    public Cursor viewRecoveryData() {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM \"" + RECOVERY_DB_TABLE + "\";";

        Cursor cursor = db.rawQuery(query, null);

        return cursor;
    }

    // Method To View Data For Single ID.
    public Cursor viewDataForSingleID(String id) {
        SQLiteDatabase db = getReadableDatabase();
        String Query = "SELECT " + "*" + " FROM " + DB_TABLE + " WHERE " + ID + " = " + id + ";";
        Cursor cursor = db.rawQuery(Query, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    // Method To Get Last ID.
    public long GetLastestID() {
        SQLiteDatabase sqldb = getReadableDatabase();
        String Query = "SELECT " + ID + " FROM " + DB_TABLE + " ORDER BY " + ID + " DESC LIMIT 1;";
        Cursor cursor = sqldb.rawQuery(Query, null);
        if (cursor != null && cursor.moveToFirst()) {
            long lastId = cursor.getLong(0); //The 0 is the column index, we only have 1 column, so the index is 0
            cursor.close();
            return lastId;
        }
        return 0;
    }

    // Method To Get Last ID.
    public int GetLastNumCust(String id) {
        SQLiteDatabase sqldb = getReadableDatabase();
        String Query = "SELECT " + NUMCUST + " FROM " + DB_TABLE + " WHERE " + ID + "=" + id + ";";
        Cursor cursor = sqldb.rawQuery(Query, null);
        if (cursor != null && cursor.moveToFirst()) {
            int lastCust = cursor.getInt(0); //The 0 is the column index, we only have 1 column, so the index is 0
            cursor.close();
            return lastCust;
        }
        return 0;
    }

    // Store All Cash Into TotalOfCash.
    public int getTotalOfDay(){
        int TotalOfCash = 0;

        SQLiteDatabase db = getReadableDatabase();
        try{
            Cursor cur = db.rawQuery("SELECT SUM(" + TOTALOFCUSTOMER + ") as Total FROM " + DB_TABLE, null);
            if (cur.moveToFirst()) {
                TotalOfCash = cur.getInt(cur.getColumnIndex("Total"));// get final total
            }
            return TotalOfCash;
        }
        catch (Exception e){
            return 0;
        }
    }

    // Store All Count Of Noodles.
    public int getTotalCountOfNoodles(){
        int TotalOfCountNoodles = 0;

        SQLiteDatabase db = getReadableDatabase();
        try{
            Cursor cur = db.rawQuery("SELECT SUM(" + NOODLES + " * 10) as Total FROM " + DB_TABLE, null);
            if (cur.moveToFirst()) {
                TotalOfCountNoodles = cur.getInt(cur.getColumnIndex("Total"));// get final total
            }
            return TotalOfCountNoodles;
        }
        catch (Exception e){
            return 0;
        }
    }

    // Store All Count Of Domte.
    public int getTotalCountOfDomte(){
        int TotalOfCountDomte = 0;

        SQLiteDatabase db = getReadableDatabase();
        try{
            Cursor cur = db.rawQuery("SELECT SUM(" + DOMTE + " * 6) as Total FROM " + DB_TABLE, null);
            if (cur.moveToFirst()) {
                TotalOfCountDomte = cur.getInt(cur.getColumnIndex("Total"));// get final total
            }
            return TotalOfCountDomte;
        }
        catch (Exception e){
            return 0;
        }
    }

    // Store All Count Of Drinks.
    public int getTotalCountOfDrinks() {
        int TotalOfCountDrinks = 0;

        SQLiteDatabase db = getReadableDatabase();
        try {
            Cursor cur = db.rawQuery("SELECT SUM(" + GREENTEA + " * 10 + " + BLACKTEA + "  * 5 + " +
                    NESCAFECLASSIC + "  * 5 + " + NESCAFE3IN1  + "  * 10) as Total FROM " + DB_TABLE, null);
            if (cur.moveToFirst()) {
                TotalOfCountDrinks = cur.getInt(cur.getColumnIndex("Total"));// get final total
            }
            return TotalOfCountDrinks;
        } catch (Exception e) {
            return 0;
        }
    }
    // Store All Count Of SharedSpace.
    public int getTotalCountOfSharedSpace() {
        int TotalOfCountSharedSpace = 0;

        SQLiteDatabase db = getReadableDatabase();
        try {
            String Query = "SELECT SUM(" + TOTALOFSHAREDSPACE + ") as Total FROM " + SHARED_AND_ROOMS_TABLE ;

            Cursor cur = db.rawQuery(Query ,null);
            if (cur.moveToFirst()) {
                TotalOfCountSharedSpace = cur.getInt(cur.getColumnIndex("Total"));// get final total
            }
            return TotalOfCountSharedSpace;
        } catch (Exception e) {
            return 0;
        }
    }

    // Store All Count Of Rooms.
    public int getTotalCountOfRooms() {
        int TotalOfCountRooms = 0;

        SQLiteDatabase db = getReadableDatabase();
        try {
            String Query = "SELECT SUM(" + TOTALOFROOMS + ") as Total FROM " + SHARED_AND_ROOMS_TABLE ;

            Cursor cur = db.rawQuery(Query ,null);
            if (cur.moveToFirst()) {
                TotalOfCountRooms = cur.getInt(cur.getColumnIndex("Total"));// get final total
            }
            return TotalOfCountRooms;
        } catch (Exception e) {
            return 0;
        }
    }

    public void deleteMainTable() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM \"" + DB_TABLE + "\";");
    }

    public void deleteSharedRoomsTable() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM \"" + SHARED_AND_ROOMS_TABLE + "\";");
    }
}
