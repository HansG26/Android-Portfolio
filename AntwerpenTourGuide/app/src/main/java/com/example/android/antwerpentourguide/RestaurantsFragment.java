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

        locaties.add(new Locatie(getString(R.string.naam_fish_agogo), R.drawable.fish_a_gogo, getString(R.string.adres_fish_agogo), 4.5, getString(R.string.link_fish_agogo)));
        locaties.add(new Locatie(getString(R.string.naam_meat_factory), R.drawable.meat_factory, getString(R.string.adres_meat_factory), 4.5, getString(R.string.link_meat_factory)));
        locaties.add(new Locatie(getString(R.string.naam_the_jane), R.drawable.the_jane, getString(R.string.adres_the_jane), 4.5, getString(R.string.link_the_jane)));
        locaties.add(new Locatie(getString(R.string.naam_sombat_thai_cuisine), R.drawable.sombat_thai_cuisine, getString(R.string.adres_sombat_thai_cuisine), 4.5, getString(R.string.link_sombat_thai_cuisine)));
        locaties.add(new Locatie(getString(R.string.naam_falafel_tof), R.drawable.falafel_tof, getString(R.string.adres_falafel_tof), 4.5, getString(R.string.link_falafel_tof)));
        locaties.add(new Locatie(getString(R.string.naam_billies_bier_kafetaria), R.drawable.billies_bier_kafetaria, getString(R.string.adres_billes_bier_kafetaria), 4.5, getString(R.string.link_billies_bier_kafetaria)));
        locaties.add(new Locatie(getString(R.string.naam_bia_mara), R.drawable.bia_mara, getString(R.string.adres_bia_mara), 4.5, getString(R.string.link_bia_mara)));
        locaties.add(new Locatie(getString(R.string.naam_de_pottekijker), R.drawable.de_pottekijker, getString(R.string.adres_de_pottekijker), 4.5, getString(R.string.link_de_pottekijker)));
        locaties.add(new Locatie(getString(R.string.naam_restaurant_lux), R.drawable.restaurant_lux, getString(R.string.adres_restaurant_lux), 4.5, getString(R.string.link_restaurant_lux)));
        locaties.add(new Locatie(getString(R.string.naam_restaurant_de_bomma), R.drawable.restaurant_de_bomma, getString(R.string.adres_restaurant_de_bomma), 4, getString(R.string.link_restaurant_de_bomma)));


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
