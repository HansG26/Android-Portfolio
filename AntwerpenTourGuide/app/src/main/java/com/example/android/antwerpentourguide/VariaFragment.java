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
public class VariaFragment extends Fragment {


    public VariaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate layout waar ListView zich in bevindt
        View view = inflater.inflate(R.layout.locatie_lijst, container, false);

        // vindt ListView die we gaan populaten met ListView items
        final ListView listView = (ListView) view.findViewById(R.id.lijst);

        // create lijst met varia
        ArrayList<Locatie> locaties = new ArrayList<Locatie>();

        // locaties.add();
        locaties.add(new Locatie("Centraal Station", R.drawable.centraal_station, "Koningin Astridplein 27", 4.5, "http://www.belgianrail.be/en/stations-and-train/search-a-station/6/antwerpen-centraal.aspx"));
        locaties.add(new Locatie("Grote Markt van Antwerpen", R.drawable.grote_markt, "Historisch Centrum", 4.5, "https://www.visitantwerpen.be/"));
        locaties.add(new Locatie("Onze-Lieve-Vrouwekathedraal", R.drawable.olv_kathedraal, "Groenplaats 21", 4.5, "http://www.dekathedraal.be/"));
        locaties.add(new Locatie("Cogels Osylei", R.drawable.cogels_osylei, "Zurenborg", 4.5, "http://www.cogelsosylei.com/cogels-oyslei-antwerp-berchem"));
        locaties.add(new Locatie("Sint-Pauluskerk", R.drawable.sint_paulus_kerk, "Veemarkt 14", 4.5, "https://www.kerknet.be/organisatie/sint-paulusparochie-antwerpen"));
        locaties.add(new Locatie("Stadhuis", R.drawable.stadhuis, "Grote markt", 4.5, "https://www.antwerpen.be/nl/overzicht/stadhuis-van-antwerpen"));
        locaties.add(new Locatie("St Anna's tunnel", R.drawable.st_anna_tunnel, "Sint-Jansvliet", 4, "https://www.visitantwerpen.be/en/transport-antwerp/pedestrian-and-cyclists-tunnel"));
        locaties.add(new Locatie("Carolus Borromeus kerk", R.drawable.carolus_borromeus_kerk, "Hendrik Conscienceplein 12", 4.5, "http://carolusborromeus.com/"));
        locaties.add(new Locatie("Meir", R.drawable.meir, "Leysstraat", 4, "http://www.allesoverantwerpen.nl/shopping/winkelstraten/meir.htm"));
        locaties.add(new Locatie("Vlaeykensgang", R.drawable.vlaeykensgang, "Oude Koornmarkt 16", 4.5, "https://www.visitantwerpen.be/nl/zien-doen/bezienswaardigheden/unieke-plekjes/vlaeykensgang-171277"));



        // Maak Locatieadapter die juiste list item views aanbiedt aan de listview
        LocatieAdapter adapter = new LocatieAdapter(getContext(), locaties);

        // Koppel de adapter aan de listview
        listView.setAdapter(adapter);

        // zorgt voor openen juiste url bij klikken op list item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Locatie locatie = (Locatie) listView.getItemAtPosition(i);
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
