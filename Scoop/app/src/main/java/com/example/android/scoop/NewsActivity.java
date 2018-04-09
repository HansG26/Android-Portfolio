package com.example.android.scoop;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<NewsArticle>> {

    private NewsAdapter mAdapter;
    private String mUrl = "https://content.guardianapis.com/search?api-key=test&page-size=30&show-tags=contributor&show-fields=thumbnail";
    private TextView mEmptyTextView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        // Find the ListView inside list_view
        ListView listView = findViewById(R.id.list_view);

        // Create a new ArrayList to add NewsArticles to
        List arrayList = new ArrayList<NewsArticle>();

        mAdapter = new NewsAdapter(this, arrayList);

        listView.setAdapter(mAdapter);

        mEmptyTextView = findViewById(R.id.empty_textview);
        mProgressBar = findViewById(R.id.progressbar);

        listView.setEmptyView(mEmptyTextView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                NewsArticle currentArticle = mAdapter.getItem(i);
                String webPage = currentArticle.getWebPage();
                Uri webpage = Uri.parse(webPage);
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        ConnectivityManager cm =
                (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        if(!isConnected) {
            mProgressBar.setVisibility(View.INVISIBLE);
            mEmptyTextView.setVisibility(View.VISIBLE);
            mEmptyTextView.setText(R.string.no_internet_connection);
        }

        getLoaderManager().restartLoader(1, null, this);
    }

    @Override
    public Loader<List<NewsArticle>> onCreateLoader(int i, Bundle bundle) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        String section = sharedPrefs.getString(
                getString(R.string.settings_section_key),
                getString(R.string.settings_section_default)
        );

        System.out.println(section);

        if(section.equals("All") || section.equals("all")) {
            return new NewsLoader(this, mUrl);
        }

        Uri baseUri = Uri.parse(mUrl);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        uriBuilder.appendQueryParameter("section", section);

        return new NewsLoader(this, uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<NewsArticle>> loader, List<NewsArticle> newsArticles) {
        mAdapter.clear();
        if(newsArticles == null) {
            mEmptyTextView.setVisibility(View.VISIBLE);
            return;
        }
        mProgressBar.setVisibility(View.INVISIBLE);
        mEmptyTextView.setVisibility(View.INVISIBLE);
        mAdapter.addAll(newsArticles);
    }

    @Override
    public void onLoaderReset(Loader<List<NewsArticle>> loader) {
        mAdapter.clear();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
