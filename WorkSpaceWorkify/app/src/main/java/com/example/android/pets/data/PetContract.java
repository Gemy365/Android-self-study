package com.example.android.pets.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * API Contract for the Pets app.
 */
public final class PetContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private PetContract() {
    }

    /**
     * The "Content authority" is a name for the entire content provider, similar to the
     * relationship between a domain name and its website.  A convenient string to use for the
     * content authority is the package name for the app, which is guaranteed to be unique on the
     * device.
     */
    public static final String CONTENT_AUTHORITY = "com.example.android.pets";

    /**
     * Use CONTENT_AUTHORITY to create the base of all URI's which apps will use to contact
     * the content provider.
     */
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    /**
     * Possible path (appended to base content URI for possible URI's)
     * For instance, content://com.example.android.pets/pets/ is a valid path for
     * looking at pet data. content://com.example.android.pets/staff/ will fail,
     * as the ContentProvider hasn't been given any information on what to do with "staff".
     */
    public static final String PATH_PETS = "pets";

    /**
     * Inner class that defines constant values for the pets database table.
     * Each entry in the table represents a single pet.
     */
    public static final class PetEntry implements BaseColumns {

        /**
         * The content URI to access the pet data in the provider
         */
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PETS);

        /**
         * The MIME type of the {@link #CONTENT_URI} for a list of pets.
         * CURSOR_DIR_BASE_TYPE (which maps to the constant "vnd.android.cursor.dir").
         */
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PETS;

        /**
         * The MIME type of the {@link #CONTENT_URI} for a single pet.
         * CURSOR_ITEM_BASE_TYPE (which maps to the constant “vnd.android.cursor.item”).
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PETS;

        /**
         * Possible values for the gender of the pet.
         */
        public static final int CHOOSE_UNKNOWN = 0;
        public static final int CHOOSE_TABLE = 1;
        public static final int CHOOSE_ROOM = 2;

        /**
         * Name of database table for pets
         */
        public final static String TABLE_NAME = "pets";

        /**
         * Unique ID number for the pet (only for use in the database table).
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         * NUMBER OF TABLE OR ROOM.
         * Type: INTEGER
         */
        public final static String COLUMN_NUM_TABLE_ROOM = "num_t_r";

        /**
         * Hours.
         * Type: INTEGER
         */
        public final static String COLUMN_HOURS = "hours";

        /**
         * Choose Table Or Room.
         * The only possible values are {@link #CHOOSE_UNKNOWN}, {@link #CHOOSE_TABLE},
         * or {@link #CHOOSE_ROOM}.
         * Type: INTEGER
         */
        public final static String COLUMN_TABLE_ROOM = "table_room";

        /**
         * Total.
         * Type: INTEGER
         */
        public final static String COLUMN_TOTAL = "total";

        /**
         * Returns whether or not the given gender is {@link #CHOOSE_UNKNOWN}, {@link #CHOOSE_TABLE},
         * or {@link #CHOOSE_ROOM}.
         */
        public static boolean isValidTableRoom(int gender) {
            if (gender == CHOOSE_UNKNOWN || gender == CHOOSE_TABLE || gender == CHOOSE_ROOM) {
                return true;
            }
            return false;
        }

    }
}

