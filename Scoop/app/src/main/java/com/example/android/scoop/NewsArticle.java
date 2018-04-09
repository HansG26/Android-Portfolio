package com.example.android.scoop;

/**
 * Created by hansgoos on 25/02/18.
 */

public class NewsArticle {
    private String mTitle;
    private String mSection;
    private String mDate;
    private String mAuthor;
    private String mImage;
    private String mWebPage;

    // Constructs a new NewsArticle containing: title, section, date, author, imageResourceID
    public NewsArticle(String title, String section, String date, String author, String image, String webPage) {
        this.mTitle = title;
        this.mSection = section;
        this.mDate = date;
        this.mAuthor = author;
        this.mImage = image;
        this.mWebPage = webPage;
    }

    /**
     * @return the title of the newsarticle
     */
    public String getTitle() {
        return mTitle;
    }

    /**
     * @return the section of the newsarticle
     */
    public String getSection() {
        return mSection;
    }

    /**
     * @return the date of the newsarticle
     */
    public String getDate() {
        return mDate;
    }

    /**
     * @return the author of the newsarticle
     */
    public String getAuthor() {
        return mAuthor;
    }

    /**
     * @return the imageResourceID of the newsarticle
     */
    public String getImage() {
        return mImage;
    }

    /**
     * @return the webpage of the newsarticle
     */
    public String getWebPage() {
        return mWebPage;
    }

}
