package android.example.com.inventory;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.example.com.inventory.data.InventoryContract.InventoryEntry;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DetailActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    /**
     * ID for loader we want to instantiate
     */
    private static final int LOADER_ID = 1;

    /**
     * Button for changing product picture
     */
    private Button mChangePictureButton;

    /**
     * EditText with product name
     */
    private EditText mProductEditText;

    /**
     * EditText with supplier name
     */
    private EditText mSupplierEditText;

    /**
     * EditText with price
     */
    private EditText mPriceEditText;

    /**
     * EditText with quantity
     */
    private EditText mQuantityEditText;

    /**
     * ImageView for picture of product
     */
    private ImageView mImageView;

    /**
     * Reference to possible Uri that was passed from OverviewActivity to this Activity
     */
    private Uri mUri;

    /**
     * Code that will be returned in onActivityResult if requested activity exists
     */
    private static final int REQUEST_IMAGE_GET = 0;

    /**
     * Reference to the Uri of the product image
     */
    private Uri mPictureUri;

    /**
     * FloatingActionButton to order more of the same product
     */
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Get different layout elements in activity_detail.xml
        mChangePictureButton = findViewById(R.id.change_picture_button);
        mProductEditText = findViewById(R.id.product_name_edittext);
        mSupplierEditText = findViewById(R.id.supplier_name_edittext);
        mPriceEditText = findViewById(R.id.price_edittext);
        mQuantityEditText = findViewById(R.id.quantity_edittext);
        mImageView = findViewById(R.id.image);
        floatingActionButton = findViewById(R.id.fab_detail);

        //Gets Uri of Intent that started this activity
        mUri = getIntent().getData();

        // Checks if Acitivity was started by clicking Add product button
        if (mUri == null) {
            // Changes title in the Action Bar when DetailActivity is launched for adding a new product
            getSupportActionBar().setTitle(getString(R.string.add_product));
            // No 'order more' button in 'Add product' mode
            floatingActionButton.setVisibility(View.GONE);
            // Set ImageView to no picture selected
            mImageView.setImageResource(R.drawable.no_iimage);
            // If not started by Add product button, was started by clicking ListView
        } else {
            // Changes title in the Action Bar when DetailActivity is launched for editing an existing product
            getSupportActionBar().setTitle(getString(R.string.edit_product));
            // Method that makes sure loader exists and otherwise makes sure that one is created
            // Only gets called if item is clicked inside ListView of OverviewActvity
            getLoaderManager().initLoader(LOADER_ID, null, this);
        }

        // Enable up button in DetailActivity
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // To request that system calls onPrepareOptionsMenu()
        invalidateOptionsMenu();

        // Setting up FloatingActionButton
        // When it is clicked, mail client is launched to order more of the same product
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Gets product name ou of EditText
                String productName = mProductEditText.getText().toString();
                // Body of the email
                String subject = getString(R.string.email_subject) + " " + productName;
                // Body of the email
                String body = "Dear Sir / Madam\n\n I would like to order __ items of the product \"" + productName + "\".\n\n"
                        + "Kind regards\nHans Goos";
                // Action of the Intent
                Intent intent = new Intent(Intent.ACTION_SEND);
                // MIME Type
                intent.setType("*/*");
                // Sets subject of the email
                intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                // Sets the body of the email
                intent.putExtra(Intent.EXTRA_TEXT, body);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        // Set up changing picture inside DetailActivity by hooking up 'change picture button'
        mChangePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.setType("image/*");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, REQUEST_IMAGE_GET);
                }
            }
        });
    }

    /**
     * Method that gets Uri from picture we choose and sets mImageView equal to this Uri
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_GET && resultCode == RESULT_OK) {
            mPictureUri = data.getData();
            try {
                mImageView.setImageBitmap(MediaStore.Images.Media.getBitmap(this.getContentResolver(), mPictureUri));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Method that inflates our detail_menu.xml inside activity_detail
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detail_menu, menu);
        return true;
    }

    /**
     * Method that is called right before the menu is shown
     * Used to hide 'delete product' option in Add Product version of DetailActivity
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        // Find and hide 'delete product' option in DetailActivity for 'Add product' version
        if (mUri == null) {
            MenuItem item = menu.findItem(R.id.delete_product);
            item.setVisible(false);
        }
        return true;
    }

    /**
     * Method for responding to actions on menu_detail.xml
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            // Respond to 'delete product' menu item
            case R.id.delete_product:
                showDialogDeleteProduct();
                return true;
            // Respoond to 'save product' menu item
            case R.id.save_product:
                saveProduct();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /** Method for popping dialog 'delete produxct"*/
    private void showDialogDeleteProduct() {
        AlertDialog.Builder builder = new AlertDialog.Builder(DetailActivity.this);
        builder.setMessage(R.string.delete_product_dialog_message)
                .setPositiveButton(R.string.delete_all_dialog_positive, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        getContentResolver().delete(InventoryEntry.CONTENT_URI, null, null);
                        finish();
                    }
                })
                .setNegativeButton(R.string.delete_all_dialog_negative, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        // Create the AlertDialog object and show it on screen
        builder.create();
        builder.show();
    }

    /**
     * Method for adding a new product or editing an existing one
     */
    private void saveProduct() {

        // Get input out of fields
        String productNameString = mProductEditText.getText().toString();
        String priceString = mPriceEditText.getText().toString();
        String quantityString = mQuantityEditText.getText().toString();
        String supplierString = "";
        if (!TextUtils.isEmpty(mSupplierEditText.getText())) {
            supplierString = mSupplierEditText.getText().toString();
        }

        // Make sure that picture is chosen
        if (mPictureUri == null) {
            showToastRequiredInformation();
            return;
        }

        // Make sure that product name is chosen
        if (TextUtils.isEmpty(productNameString)) {
            showToastRequiredInformation();
            return;
        }

        // Make sure that price is chosen
        if (TextUtils.isEmpty(priceString)) {
            showToastRequiredInformation();
            return;
        }

        // Make sure that quantity is chosen
        if (TextUtils.isEmpty(quantityString)) {
            showToastRequiredInformation();
            return;
        }

        // Create ContentValues with values inside input fields
        ContentValues values = new ContentValues();
        values.put(InventoryEntry.COLUMN_IMAGE, mPictureUri.toString());
        values.put(InventoryEntry.COLUMN_PRODUCT_NAME, productNameString);
        values.put(InventoryEntry.COLUMN_PRICE, priceString);
        values.put(InventoryEntry.COLUMN_QUANTITY, quantityString);
        values.put(InventoryEntry.COLUMN_SUPPLIER_NAME, supplierString);

        // If we are in 'Add product' mode, insert product in inventory table
        if (mUri == null) {
            getContentResolver().insert(InventoryEntry.CONTENT_URI, values);
        } else {
            // If we are in 'Edit product' mode, update product in inventory table
            getContentResolver().update(mUri, values, null, null);
        }

        // End DetailActivity, go back to OverviewActivity
        finish();
    }

    /**
     * Toast that is shown when not all required information is filled in
     */
    private void showToastRequiredInformation() {
        Toast toast = Toast.makeText(this, getString(R.string.required_information), Toast.LENGTH_LONG);
        toast.show();
    }

    /**
     * Instantiates a new Loader for a given ID
     */
    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] projection = {InventoryEntry._ID,
                InventoryEntry.COLUMN_PRODUCT_NAME,
                InventoryEntry.COLUMN_PRICE,
                InventoryEntry.COLUMN_QUANTITY,
                InventoryEntry.COLUMN_SUPPLIER_NAME,
                InventoryEntry.COLUMN_IMAGE};
        return new CursorLoader(this, mUri, projection, null, null, null);
    }

    /**
     * Called when created CursorLoader has finished loading
     * Get values out of cursor object and fills them into inputfields of DetailActivity
     */
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        // Get values out of Cursor object
        if (cursor.moveToNext()) {
            String productNameString = cursor.getString(cursor.getColumnIndexOrThrow(InventoryEntry.COLUMN_PRODUCT_NAME));
            String priceString = cursor.getString(cursor.getColumnIndexOrThrow(InventoryEntry.COLUMN_PRICE));
            String quantityString = cursor.getString(cursor.getColumnIndexOrThrow(InventoryEntry.COLUMN_QUANTITY));
            String supplierNameString = cursor.getString(cursor.getColumnIndexOrThrow(InventoryEntry.COLUMN_SUPPLIER_NAME));

            // Transforms Uri in database to Bitmap we can display in our ImageView
            mPictureUri = Uri.parse(cursor.getString(cursor.getColumnIndexOrThrow(InventoryEntry.COLUMN_IMAGE)));
            ParcelFileDescriptor parcelFileDescriptor = null;
            Bitmap image = null;
            try {
                parcelFileDescriptor = getContentResolver().openFileDescriptor(mPictureUri, "r");
                FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
                image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                if(parcelFileDescriptor != null) {
                    parcelFileDescriptor.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Sets inputfields equal to values extracted out of cursor
            mProductEditText.setText(productNameString);
            mPriceEditText.setText(priceString);
            mQuantityEditText.setText(quantityString);
            if (!TextUtils.isEmpty(supplierNameString)) {
                mSupplierEditText.setText(supplierNameString);
            }
            if (image != null) {
                mImageView.setImageBitmap(image);
            } else {
                // Set image in ImageView equal to 'no picture selected'
                mImageView.setImageResource(R.drawable.no_iimage);
            }

        }
    }

    /**
     * Method to make data in CursorLoader unavailable
     */
    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mProductEditText.setText("");
        mPriceEditText.setText("");
        mQuantityEditText.setText("");
        mSupplierEditText.setText("");
        mImageView.setImageURI(null);
    }
}
