package com.example.android.booklistingapp;

import android.content.Context;
import android.text.TextUtils;
import android.content.AsyncTaskLoader;

import java.util.List;

/**
 * Created by hansgoos on 15/02/18.
 */

// class that provides AsyncTask to fetch a list of books from the Google books API
public class BookLoader extends AsyncTaskLoader<List<Book>> {
    // url where we want to fetch our books data from
    // url gets set when search button is clicked inside the MainActivity class
    private final String mUrl;

    // url gets added in constructor so we can use it in loadInBackground()
    // constructor gets called after onCreateLoader()
    public BookLoader(Context context, String mUrl) {
        super(context);
        this.mUrl = mUrl;
    }

    // method that runs in backgroundthread that returns a list of books
    // method that runs after a new BookLoader object is instantiated
    @Override
    public List<Book> loadInBackground() {
        // if the url is empty or null, return null reference
        if (TextUtils.isEmpty(mUrl)) {
            return null;
        }

        // helper method to return ArrayList of books using the Google books API
        List<Book> books = QueryUtils.fetchBookData(mUrl);

        return books;
    }

    @Override
    protected void onStartLoading() {
        // This needs to be called!!
        forceLoad();
    }
}