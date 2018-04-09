package com.example.android.booklistingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by hansgoos on 16/02/18.
 */

public class BookAdapter extends ArrayAdapter<Book> {
    public BookAdapter(Context context, ArrayList<Book> books) {
        super(context, 0,books);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Book currentBook = getItem(position);

        TextView titleTextView = (TextView) listItemView.findViewById(R.id.title_textview);
        titleTextView.setText(currentBook.getTitle());

        TextView authorTextView = (TextView) listItemView.findViewById(R.id.author_textview);
        authorTextView.setText(currentBook.getAuthor());

        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date_textview);
        dateTextView.setText(formatDate(currentBook.getDate()));

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.imageview);
        Picasso.with(getContext()).load(currentBook.getImage()).into(imageView);

        return listItemView;
    }

    private String formatDate(String date) {
        if(date != null && date.length() >= 4) {
            return date.substring(0, 4);
        } else {
            return "No date specified";
        }

    }
}
