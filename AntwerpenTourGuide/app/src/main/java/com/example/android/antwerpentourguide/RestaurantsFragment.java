package com.example.android.antwerpentourguide;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class RestaurantsFragment extends Fragment {

    // ListView die we in dit fragment populaten met Restaurants
    ListView mListView;

    public RestaurantsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate layout waar ListView zich in bevindt
        View view = inflater.inflate(R.layout.locatie_lijst, container, false);

        // vindt ListView die we gaan populaten met ListView items
        mListView = (ListView) view.findViewById(R.id.lijst);

        // create lijst restaurants
        ArrayList<Locatie> locaties = new ArrayList<>();

        locaties.add(new Locatie("Fish a'gogo", R.drawable.fish_a_gogo, "Handschoenmarkt 1", 4.5, "https://www.facebook.com/fishagogoantwerp"));
        locaties.add(new Locatie("Meat Factory", R.drawable.meat_factory, "Grote Markt 28", 4.5, "http://meat-factory.be/"));
        locaties.add(new Locatie("The Jane", R.drawable.the_jane, "Paradeplein 1", 4.5, "https://www.thejaneantwerp.com/"));
        locaties.add(new Locatie("Sombat Thai Cuisine", R.drawable.sombat_thai_cuisine, "Desguinlei 196", 4.5, "http://www.sombat.be/sombat_thai_cuisine/HOME_DINING.html"));
        locaties.add(new Locatie("Falafel Tof", R.drawable.falafel_tof, "Hoogstraat 32", 4.5, "http://nl-nl.facebook.com/falafeltofantwerpen"));
        locaties.add(new Locatie("Billie's Bier Kafetaria", R.drawable.billies_bier_kafetaria, "Kammenstraat 12", 4.5, "http://www.facebook.com/Billies-Bier-Kaf%C3%A9taria-172133052995844/"));
        locaties.add(new Locatie("Bia Mara", R.drawable.bia_mara, "Maalderijstraat 1", 4.5, "http://www.biamara.com/"));
        locaties.add(new Locatie("De Pottekijker", R.drawable.de_pottekijker, "Kaasrui 5", 4.5, "https://www.depottekijker.be/"));
        locaties.add(new Locatie("Restaurant Lux", R.drawable.restaurant_lux, "Adriaan Brouwerstraat 13", 4.5, "http://www.luxantwerp.com/"));
        locaties.add(new Locatie("Restaurant De Bomma", R.drawable.restaurant_de_bomma, "Suikerrui 16", 4, "http://www.restaurantdebomma.be/"));


        // Maakt nieuwe LocatieAdapter die als data een ArrayList met Locaties krijgt (Restaurants)
        LocatieAdapter adapter = new LocatieAdapter(getContext(), locaties);

        // Koppel adapter aan ListView, zodat adapter list item views aan ListView kan aanbieden
        mListView.setAdapter(adapter);

        // zorgt voor openen juiste url bij klikken op list item
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Locatie locatie = (Locatie) mListView.getItemAtPosition(i);
                String website = locatie.getWebsite();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(website));

                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        return view;


    }

}
