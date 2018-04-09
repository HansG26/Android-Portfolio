package com.example.android.booklistingapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>> {

    private String mUrl = "";
    private BookAdapter mAdapter = null;
    private ProgressBar progressBar;
    private EditText editText;
    private TextView emptyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set layout to layout containing ListView
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.loading_spinner);
        editText = findViewById(R.id.edittext);
        emptyTextView = findViewById(R.id.empty_textview);
        mAdapter = new BookAdapter(this, new ArrayList<Book>());

        ListView listView = findViewById(R.id.list);

        // Attach listView to mAdapter
        listView.setAdapter(mAdapter);

        // If adapter linked to ListView is empty, display emptyTextView
        listView.setEmptyView(emptyTextView);

        // checks for internet connectivity
        final ConnectivityManager cm =
                (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        // No internet connection, set text emptyTextView to specified text
        if(!isConnected) {
            emptyTextView.setText(R.string.no_internet_connection);
        }

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    emptyTextView.setText("");

                    NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                    boolean isConnected = activeNetwork != null &&
                            activeNetwork.isConnectedOrConnecting();

                    if(!isConnected) {
                        emptyTextView.setText(R.string.no_internet_connection);
                    }

                    progressBar.setVisibility(View.VISIBLE);

                    mUrl = convertSearchTermToUrl(editText.getText().toString());

                    // Call to re-create loader associated with ID 0
                    getLoaderManager().restartLoader(0, null, MainActivity.this);

                    return true;
                }
                return false;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Book currentBook = mAdapter.getItem(i);
                Uri webpage = Uri.parse(currentBook.getInfoPage());
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
    }


    @Override
    public Loader<List<Book>> onCreateLoader(int i, Bundle bundle) {
        return new BookLoader(this, mUrl);
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> books) {
        progressBar.setVisibility(View.INVISIBLE);
        emptyTextView.setText(R.string.no_books_found);
        mAdapter.clear();
        if(books != null) {
            mAdapter.addAll(books);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        mAdapter.clear();
    }

    private String convertSearchTermToUrl(String searchTerm) {
        String polishedSearchTerm = null;
        try {
            polishedSearchTerm = searchTerm.trim();
            if(polishedSearchTerm.equals("")) {
                return null;
            }
            polishedSearchTerm = URLEncoder.encode(searchTerm,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return getString(R.string.google_url) + polishedSearchTerm + getString(R.string.url_parameters);
    }
}
