package com.example.android.booklistingapp;

/**
 * Created by hansgoos on 15/02/18.
 */

public class Book {

    private String mTitle;
    private String mAuthor;
    private String mDate;
    private String mImage;
    private String mInfoPage;

    public Book(String title, String author, String date, String image, String infoPage) {
        this.mTitle = title;
        this.mAuthor = author;
        this.mDate = date;
        this.mImage = image;
        this.mInfoPage = infoPage;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getDate() {
        return mDate;
    }

    public String getImage() {
        return mImage;
    }

    public String getInfoPage() {
        return mInfoPage;
    }

}
