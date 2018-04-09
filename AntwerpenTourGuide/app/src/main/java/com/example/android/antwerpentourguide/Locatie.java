package com.example.android.antwerpentourguide;

/**
 * Created by hansgoos on 1/02/18.
 */

// class die de verschillende features voorstelt in een list_item
public class Locatie {

    private String mNaam;
    private int mImageResourceId;
    private String mAdres;
    private double mBeoordeling;
    private String mWebsite;

    public Locatie(String naam, int imageResourceId, String adres, double beoordeling, String website) {
        this.mNaam = naam;
        this.mImageResourceId = imageResourceId;
        this.mAdres = adres;
        this.mBeoordeling = beoordeling;
        this.mWebsite = website;
    }

    public String getNaam() {
        return mNaam;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }

    public String getAdres() {
        return mAdres;
    }

    public double getBeoordeling() {
        return mBeoordeling;
    }

    public String getWebsite() {
        return mWebsite;
    }
}
