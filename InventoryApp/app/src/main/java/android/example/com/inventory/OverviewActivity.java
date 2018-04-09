package android.example.com.inventory;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.example.com.inventory.data.InventoryAdapter;
import android.example.com.inventory.data.InventoryContract.InventoryEntry;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class OverviewActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    /** ID for loader we want to instantiate */
    private static final int LOADER_ID = 0;

    /** Adapter to populate ListView */
    private InventoryAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        // Setting up FloatingActionButton
        // When it is clicked, DetailActivity launches to add a new product
        FloatingActionButton floatingActionButton = findViewById(R.id.fab_overview);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OverviewActivity.this, DetailActivity.class);
                startActivity(intent);
            }
        });

        ListView listView = findViewById(R.id.list_view);

        // Create inventory adapter that takes null as cursor argument (is set in onLoadFinished())
        mAdapter = new InventoryAdapter(this, null);

        // Links ListView to CursorAdapter
        listView.setAdapter(mAdapter);

        // Set Empty View on ListItem, View that is displayed when there is no data in InventoryAdapter
        listView.setEmptyView(findViewById(R.id.empty_view));

        // Set OnItemClickListener on the ListView
        // When item in list is clicked "edit product"-version of activity_detail.xml is opened
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                // Uri of the item being clicked (address to item in inventory table)
                Log.i("OverviewActivity.java", "CLICKED!!!");
                Uri currentUri = ContentUris.withAppendedId(InventoryEntry.CONTENT_URI, id);
                // Setting up and starting Intent
                Intent intent = new Intent(OverviewActivity.this, DetailActivity.class);
                intent.setData(currentUri);
                startActivity(intent);
            }
        });

        // Method that makes sure loader exists and otherwise makes sure that one is created
        getLoaderManager().initLoader(LOADER_ID, null, this);
    }

    /** Method that inflates our overview_menu.xml inside activity_overview */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.overview_menu, menu);
        return true;
    }

    /** Method for handling click events on overview_menu.xml */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.delete_all:
                showDialogDeleteAll();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /** Method for popping dialog 'delete all"*/
    private void showDialogDeleteAll() {
        AlertDialog.Builder builder = new AlertDialog.Builder(OverviewActivity.this);
        builder.setMessage(R.string.delete_all_dialog_message)
                .setPositiveButton(R.string.delete_all_dialog_positive, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        getContentResolver().delete(InventoryEntry.CONTENT_URI, null, null);
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

    /** Instantiates a new Loader for a given ID*/
    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] projection = {InventoryEntry._ID,
                InventoryEntry.COLUMN_PRODUCT_NAME,
                InventoryEntry.COLUMN_PRICE,
                InventoryEntry.COLUMN_QUANTITY};
        return new CursorLoader(this, InventoryEntry.CONTENT_URI, projection, null, null, null);
    }

    /** Called when created CursorLoader has finished loading*/
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        mAdapter.swapCursor(cursor);
    }

    /** Method to make data in CursorLoader unavailable */
    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }
}
