package android.example.com.inventory.data;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.example.com.inventory.R;
import android.example.com.inventory.data.InventoryContract.InventoryEntry;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by hansgoos on 27/03/18.
 */

public class InventoryAdapter extends CursorAdapter {

    public InventoryAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    /** Method to inflate layout list_item.xml*/
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false);
    }

    /** Method for populating list item views*/
    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        TextView productNameTextView = view.findViewById(R.id.product_name_textview);
        TextView priceTextView = view.findViewById(R.id.price_textview);
        final TextView quantityTextView = view.findViewById(R.id.quantity_textview);
        ImageButton imageButton = view.findViewById(R.id.image_button);

        // Set text of product TextView equal to product name in COLUMN_PRODUCT_NAME column
        productNameTextView.setText(cursor.getString(cursor.getColumnIndexOrThrow(InventoryEntry.COLUMN_PRODUCT_NAME)));

        // Set text of price TextView equal to price in COLUMN_PRICE column
        priceTextView.setText(cursor.getString(cursor.getColumnIndexOrThrow(InventoryEntry.COLUMN_PRICE)));

        // Set text of quantity TextView equal to quantity in QUANTITY column
        quantityTextView.setText(cursor.getString(cursor.getColumnIndexOrThrow(InventoryEntry.COLUMN_QUANTITY)));

        // ID of item in inventory table
        final Long id = cursor.getLong(cursor.getColumnIndexOrThrow(InventoryEntry._ID));

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Quantity inside quantity TextView
                int quantity = Integer.parseInt(quantityTextView.getText().toString());
                // Uri of item in inventory table
                Uri currentUri = ContentUris.withAppendedId(InventoryEntry.CONTENT_URI, id);

                // Update quantity (minus one)
                if(quantity > 0) {
                    ContentValues values = new ContentValues();
                    values.put(InventoryEntry.COLUMN_QUANTITY, quantity - 1);
                    context.getContentResolver().update(currentUri, values, null, null);
                }
            }
        });

    }
}
