package com.example.android.scoop;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hansgoos on 25/02/18.
 */

public class QueryUtils {

    public static List<NewsArticle> fetchNewsData(String stringUrl) {
        URL url = createUrl(stringUrl);

        String jsonResponse = makeHttpRequest(url);

        List<NewsArticle> newsArticles = getDataOutOfJson(jsonResponse);
        return newsArticles;

    }

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    private static String makeHttpRequest(URL url) {
        String jsonResponse = "";
        if (url == null) {
            return jsonResponse;
        }

        try {
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200) {
                InputStream inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        InputStreamReader streamReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(streamReader);
        String line = reader.readLine();
        while (line != null) {
            output.append(line);
            line = reader.readLine();
        }
        return output.toString();
    }

    private static List<NewsArticle> getDataOutOfJson(String json) {
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        Log.i("NewsActivity.java", "getDataOutOfJson");
        List<NewsArticle> newsArticles = new ArrayList<NewsArticle>();
        String title = "";
        String section = "";
        String date = "";
        String author = "";
        String image = "";
        String webPage = "";

        try {
            JSONObject rootObject = new JSONObject(json);
            JSONObject responseObject = rootObject.getJSONObject("response");
            JSONArray resultsArray = responseObject.getJSONArray("results");
            for(int i = 0; i < resultsArray.length(); i++) {
                JSONObject resultObject = resultsArray.getJSONObject(i);
                title = resultObject.getString("webTitle");
                section = resultObject.getString("sectionName");
                String webPublicationDate = resultObject.getString("webPublicationDate");
                if(!TextUtils.isEmpty(webPublicationDate)) {
                    date = webPublicationDate;
                }
                String webUrl = resultObject.getString("webUrl");
                if(!TextUtils.isEmpty(webUrl)) {
                    webPage = webUrl;
                }

                JSONObject fieldsObject = resultObject.optJSONObject("fields");
                if(fieldsObject != null) {
                    image = fieldsObject.getString("thumbnail");
                }
                JSONArray tagsArray = resultObject.optJSONArray("tags");
                if(tagsArray != null || tagsArray.length() > 0) {
                    JSONObject authorObject = tagsArray.optJSONObject(0);
                    if(authorObject != null) {
                        author = authorObject.getString("webTitle");
                    }
                }
                newsArticles.add(new NewsArticle(title, section, date, author, image, webPage));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return newsArticles;
    }

}
