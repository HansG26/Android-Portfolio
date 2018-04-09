package android.example.com.inventory.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by hansgoos on 26/03/18.
 */

public final class InventoryContract {
    /** Private constructor - no need to create instance of this class */
    private InventoryContract() {

    }

    /** Constant for content authority (unique address of the ContentProvider) */
    public static final String CONTENT_AUTHORITY = "android.example.com.inventory";
    /** Content Uri without appended path*/
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    /** Path to access inventory table in our database*/
    public static final String PATH_INVENTORY = "inventory";


    public static final class InventoryEntry implements BaseColumns {

        /** Content Uri for accessing inventory table in our database*/
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_INVENTORY);

        /** MIME type general URI */
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + CONTENT_AUTHORITY;

        /** MIME type specific URI */
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + CONTENT_AUTHORITY;

        /** Constant for table name */
        public static final String TABLE_NAME = "inventory";

        /** Constant for ID column */
        public static final String _ID = BaseColumns._ID;
        /** Constant for productname column */
        public static final String COLUMN_PRODUCT_NAME = "product_name";
        /** Constant for price column */
        public static final String COLUMN_PRICE = "price";
        /** Constant for quantity column */
        public static final String COLUMN_QUANTITY = "quantity";
        /** Constant for supplier name column */
        public static final String COLUMN_SUPPLIER_NAME = "supplier_name";
        /** Constant for image column */
        public static final String COLUMN_IMAGE = "image";

    }
}
