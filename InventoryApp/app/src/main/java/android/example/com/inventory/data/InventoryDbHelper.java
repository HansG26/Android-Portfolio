package android.example.com.inventory.data;

import android.example.com.inventory.data.InventoryContract.InventoryEntry;

/**
 * Created by hansgoos on 26/03/18.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/** Class that manages database creation of inventory.db */
public final class InventoryDbHelper extends SQLiteOpenHelper {
    /** Database name that stores products in inventory */
    private static final String DATABASE_NAME = "inventory.db";
    /** Current database version */
    private static final int DATABASE_VERSION = 1;

    /** Constructor InventoryDbHelper */
    public InventoryDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /** Method that is only called when database has to be created first time */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // creates table, using raw SQL, when connection to database is requested for first time
        String CREATE_TABLE_STATEMENT = "CREATE TABLE " + InventoryEntry.TABLE_NAME + " ("
                + InventoryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + InventoryEntry.COLUMN_PRODUCT_NAME + " TEXT NOT NULL, "
                + InventoryEntry.COLUMN_PRICE + " REAL NOT NULL, "
                + InventoryEntry.COLUMN_QUANTITY + " INTEGER NOT NULL, "
                + InventoryEntry.COLUMN_SUPPLIER_NAME + " TEXT NOT NULL, "
                + InventoryContract.InventoryEntry.COLUMN_IMAGE + " TEXT NOT NULL)";
        db.execSQL(CREATE_TABLE_STATEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}