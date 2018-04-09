package com.example.android.antwerpentourguide;

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

import java.util.ArrayList;

/**
 * Created by hansgoos on 2/02/18.
 */

public class LocatieAdapter extends ArrayAdapter<Locatie> {

    Context context;

    public LocatieAdapter(Context context, ArrayList<Locatie> locaties) {
        super(context, 0, locaties);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.locatie_item, parent, false);
        }

        Locatie locatie = getItem(position);

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.afbeelding);
        Picasso.with(context).load(locatie.getImageResourceId()).fit().into(imageView);

        TextView naamTextView = (TextView) listItemView.findViewById(R.id.naam);
        naamTextView.setText(locatie.getNaam());

        TextView adresTextView = (TextView) listItemView.findViewById(R.id.adres);
        adresTextView.setText(locatie.getAdres());

        TextView beoordelingTextView = (TextView) listItemView.findViewById(R.id.beoordeling);
        beoordelingTextView.setText(String.valueOf(locatie.getBeoordeling()));

        return listItemView;
    }

}
