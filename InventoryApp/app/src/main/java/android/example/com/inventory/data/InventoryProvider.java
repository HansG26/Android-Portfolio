package android.example.com.inventory.data;

/**
 * Created by hansgoos on 26/03/18.
 */

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.example.com.inventory.data.InventoryContract.InventoryEntry;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

/**
 * Class used for directly interacting with database (layer between UI and database)
 */
public class InventoryProvider extends ContentProvider {

    /**
     * UriMatcher used for matching Uristo certain code
     */
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    private static final int CODE_INVENTORY = 100;
    private static final int CODE_INVENTORY_ID = 101;

    /** Matching possible Uris to code*/
    static {
        sUriMatcher.addURI(InventoryContract.CONTENT_AUTHORITY, InventoryContract.PATH_INVENTORY, CODE_INVENTORY);
        sUriMatcher.addURI(InventoryContract.CONTENT_AUTHORITY, InventoryContract.PATH_INVENTORY + "/#", CODE_INVENTORY_ID);
    }

    /**
     * InventoryDbHelper that manages creation of database connections
     */
    private InventoryDbHelper mDbHelper;

    @Override
    public boolean onCreate() {
        mDbHelper = new InventoryDbHelper(getContext());
        return true;
    }

    /**
     * Method for querying the database
     */
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase database = mDbHelper.getReadableDatabase();
        int match = sUriMatcher.match(uri);
        Cursor cursor;
        switch (match) {
            case CODE_INVENTORY:
                cursor = database.query(InventoryEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case CODE_INVENTORY_ID:
                selection = InventoryEntry._ID + " = ?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(InventoryEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("No such Uri");
        }
        // When changes occur at the specified uri, the cursor will get notified
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        int match = sUriMatcher.match(uri);
        switch (match) {
            case CODE_INVENTORY:
                return InventoryEntry.CONTENT_TYPE;
            case CODE_INVENTORY_ID:
                return InventoryEntry.CONTENT_ITEM_TYPE;
            default:
                return null;
        }
    }

    /**
     * Method for inserting a product into the database
     */
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int match = sUriMatcher.match(uri);
        switch (match) {
            case CODE_INVENTORY:
                return insertProduct(uri, values);
            default:
                throw new IllegalArgumentException("No such Uri");
        }
    }

    /**
     * Helper method for inserting a product into the database
     */
    private Uri insertProduct(Uri uri, ContentValues values) {
        String produchtName = values.getAsString(InventoryEntry.COLUMN_PRODUCT_NAME);
        if (TextUtils.isEmpty(produchtName)) {
            throw new IllegalArgumentException("Product name is required");
        }

        String price = values.getAsString(InventoryEntry.COLUMN_PRICE);
        if (TextUtils.isEmpty(price)) {
            throw new IllegalArgumentException("Price is required");
        }

        String quantity = values.getAsString(InventoryEntry.COLUMN_QUANTITY);
        if (TextUtils.isEmpty(quantity)) {
            throw new IllegalArgumentException("Quantity is required");
        }

        String image = values.getAsString(InventoryEntry.COLUMN_IMAGE);
        if (TextUtils.isEmpty(image)) {
            throw new IllegalArgumentException("Image is required");
        }

        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        long id = database.insert(InventoryEntry.TABLE_NAME, null, values);

        if(id == -1) {
            return null;
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return ContentUris.withAppendedId(uri, id);
    }

    /**
     * Method for updating products in the database
     */
    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
        int match = sUriMatcher.match(uri);
        int rowsUpdated = 0;
        switch (match) {
            case CODE_INVENTORY:
                rowsUpdated = updateProduct(uri, contentValues, selection, selectionArgs);
                break;
            case CODE_INVENTORY_ID:
                selection = InventoryEntry._ID + " = ?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsUpdated = updateProduct(uri, contentValues, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("No such Uri");
        }
        if(rowsUpdated != 0) {
            // Notify listeners that data was changed at the specified Uri
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsUpdated;
    }

    /**
     * Helper method for updating product in the database
     */
    private int updateProduct(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        if (values.containsKey(InventoryEntry.COLUMN_PRODUCT_NAME)) {
            String produchtName = values.getAsString(InventoryEntry.COLUMN_PRODUCT_NAME);
            if (TextUtils.isEmpty(produchtName)) {
                throw new IllegalArgumentException("Product name is required");
            }
        }

        if (values.containsKey(InventoryEntry.COLUMN_PRICE)) {
            String price = values.getAsString(InventoryEntry.COLUMN_PRICE);
            if (TextUtils.isEmpty(price)) {
                throw new IllegalArgumentException("Price is required");
            }
        }

        if (values.containsKey(InventoryEntry.COLUMN_QUANTITY)) {
            String quantity = values.getAsString(InventoryEntry.COLUMN_QUANTITY);
            if (TextUtils.isEmpty(quantity)) {
                throw new IllegalArgumentException("Quantity is required");
            }
        }

        if (values.containsKey(InventoryEntry.COLUMN_IMAGE)) {
            String image = values.getAsString(InventoryEntry.COLUMN_IMAGE);
            if (TextUtils.isEmpty(image)) {
                throw new IllegalArgumentException("Image is required");
            }
        }

        if (values.size() == 0) {
            return 0;
        }

        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        return database.update(InventoryEntry.TABLE_NAME, values, selection, selectionArgs);
    }

    /**
     * Method for deleting pets in the database
     */
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        int match = sUriMatcher.match(uri);
        int rowsDeleted = 0;
        switch (match) {
            case CODE_INVENTORY:
                rowsDeleted = database.delete(InventoryEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case CODE_INVENTORY_ID:
                selection = InventoryEntry._ID + " = ?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(InventoryEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("No such Uri");
        }
        if(rowsDeleted != 0) {
            // Notify listeners that data was changed at the specified Uri
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsDeleted;
    }


}
