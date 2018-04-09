package com.example.android.scoop;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by hansgoos on 25/02/18.
 */

public class NewsLoader extends AsyncTaskLoader<List<NewsArticle>> {

    private String mUrl;

    public NewsLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<NewsArticle> loadInBackground() {
        List<NewsArticle> articles = QueryUtils.fetchNewsData(mUrl);
        return articles;
    }
}
