package com.example.android.scoop;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by hansgoos on 25/02/18.
 */

public class NewsAdapter extends ArrayAdapter<NewsArticle> {

    public NewsAdapter(Context context, List<NewsArticle> newsArticles) {
        super(context, 0, newsArticles);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        NewsArticle currentArticle = getItem(position);

        TextView titleTextView = listItemView.findViewById(R.id.title_textview);
        titleTextView.setText(currentArticle.getTitle());

        TextView secionTextView = listItemView.findViewById(R.id.section_textview);
        secionTextView.setText(currentArticle.getSection());
        secionTextView.setBackgroundResource(getsSectionColor(secionTextView.getText().toString()));

        TextView dateTextView = listItemView.findViewById(R.id.date_textview);
        dateTextView.setText(formatDate(currentArticle.getDate()));

        TextView authorTextView = listItemView.findViewById(R.id.author_textview);
        authorTextView.setText(currentArticle.getAuthor());

        ImageView imageView = listItemView.findViewById(R.id.image);

        Picasso.with(getContext()).load(currentArticle.getImage()).into(imageView);

        return listItemView;
    }

    // get date in form dd.mm.yy / uu:mm
    private String formatDate(String date) {
        SimpleDateFormat ISO8601DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX", Locale.GERMANY);
        String formattedDate = date.replaceAll("\\+0([0-9]){1}\\:00", "+0$100");
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd.MMM h:mm a");
            return formatter.format(ISO8601DATEFORMAT.parse(formattedDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    /* private String formatAuthor(String author) {

    } */

    private int getsSectionColor(String sectionName) {
        sectionName = sectionName.toUpperCase();
        switch (sectionName) {
            case "UK NEWS":
                return R.color.colorPrimary;
            case "POLITICS":
                return R.color.politics;
            case "MEDIA":
                return R.color.media;
            case "FILM":
                return R.color.film;
            case "CULTURE":
                return R.color.culture;
            case "GLOBAL DEVELOPMENT":
                return R.color.globalDevelopment;
            case "FOOTBALL":
                return R.color.football;
            case "AUSTRALIA NEWS":
                return R.color.australiaNews;
            case "WORLD NEWS":
                return R.color.worldNews;
            case "TELEVISION & RADIO":
                return R.color.televisionRadio;
            case "BOOKS":
                return R.color.books;
            case "GAMES":
                return R.color.games;
            case "EDUCATION":
                return R.color.education;
            case "VOLUNTARY SECTOR NETWORK":
                return R.color.voluntarySectorNetWork;
            case "MONEY":
                return R.color.money;
            case "OPINION":
                return R.color.opinion;
            case "BUSINESS":
                return R.color.business;
            case "ENVIRONMENT":
                return R.color.environment;
            case "SPORT":
                return R.color.sport;
            case "US NEWS":
                return R.color.usNews;
            case "TRAVEL":
                return R.color.travel;
            case "TECHNOLOGY":
                return R.color.technology;
            case "SCIENCE":
                return R.color.science;
            case "MUSIC":
                return R.color.music;
            case "LIFE AND STYLE":
                return R.color.lifeAndStyle;
            case "FASHION":
                return R.color.fashion;
            default:
                return R.color.notSpecified;
        }
    }
}
