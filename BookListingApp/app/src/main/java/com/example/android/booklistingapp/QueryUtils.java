package com.example.android.booklistingapp;

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
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hansgoos on 15/02/18.
 */

public class QueryUtils {

    public static List<Book> fetchBookData(String stringUrl) {
        List<Book> books = null;
        String jsonResponse = "";

        // Create a URL object from a stringUrl
        URL url = createUrl(stringUrl);

        // If the URL was not valid return an empty List
        if(url == null) {
            return books;
        }

        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e("QueryUtils.java", "Something went wrong with closing InputStream", e);
        }


        if(TextUtils.isEmpty(jsonResponse)) {
            return books;
        }

        books = convertJsonToBooks(jsonResponse);

        return books;
    }

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException urlException) {
            Log.e("QueryUtils.java", "Something went wrong with converting stringUrl to Url", urlException);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException{
        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            if(urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }
        } catch (IOException e) {
            Log.e("QueryUtils.java", "Something went wrong with HTTP request", e);
            return jsonResponse;
        } finally {
            if(urlConnection != null) {
                urlConnection.disconnect();
            }
            if(inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    // method for reading from inputstream we get from our HTTP request
    private static String readFromStream(InputStream inputStream) throws IOException{
        StringBuilder output = new StringBuilder();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
        BufferedReader reader = new BufferedReader(inputStreamReader);
        String line = reader.readLine();
        while(line != null) {
            output.append(line);
            line = reader.readLine();
        }
        return output.toString();
    }

    private static List<Book> convertJsonToBooks(String jsonResponse) {
        List<Book> books = new ArrayList<Book>();
        try {
            JSONObject rootObject = new JSONObject(jsonResponse);
            JSONArray itemsArray = rootObject.optJSONArray("items");
            if(itemsArray != null) {
                for(int i = 0; i < itemsArray.length(); i++) {
                    JSONObject bookObject = itemsArray.optJSONObject(i);
                    JSONObject volumeInfoObject = bookObject.optJSONObject("volumeInfo");
                    String title = volumeInfoObject.optString("title");
                    String date = volumeInfoObject.optString("publishedDate");
                    String infoPage = volumeInfoObject.optString("canonicalVolumeLink");
                    String authorName = "author unknown";
                    String image = "https://cdn.browshot.com/static/images/not-found.png";
                    JSONArray authorsArray = volumeInfoObject.optJSONArray("authors");
                    if(authorsArray != null) {
                        authorName = authorsArray.getString(0);
                    } else {
                        authorName = "no author found";
                    }
                    JSONObject imageLinksObject = volumeInfoObject.optJSONObject("imageLinks");
                    if(imageLinksObject != null) {
                        image = imageLinksObject.optString("thumbnail");
                    }
                    books.add(new Book(title, authorName, date, image, infoPage));
                }
            }
        } catch (JSONException e) {
            Log.e("QueryUtils.java", "Something went wrong parsing the JSON response", e);
            return null;
        }
        return books;
    }
}