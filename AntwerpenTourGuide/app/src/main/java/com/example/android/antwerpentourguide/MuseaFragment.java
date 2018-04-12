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
public class MuseaFragment extends Fragment {

    // ListView die we in dit fragment populaten met Musea
    ListView mListView;

    public MuseaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // inflate van Layout waar ListView zich in bevindt
        View view = inflater.inflate(R.layout.locatie_lijst, container, false);

        // vindt ListView die we gaan populaten met locatie items
        mListView = view.findViewById(R.id.lijst);

        // creatie lijst musea
        ArrayList<Locatie> locaties = new ArrayList<Locatie>();

        locaties.add(new Locatie("Museum Plantin-Moretus", R.drawable.museum_plantin_moretus, "Vrijdagmarkt 22", 4.5, "http://www.museumplantinmoretus.be/"));
        locaties.add(new Locatie("Red Star Line Museum", R.drawable.red_star_line_museum, "Montevideostraat 3", 4.5, "http://www.redstarline.be/nl"));
        locaties.add(new Locatie("Museum aan de Stroom", R.drawable.museum_aan_de_stroom, "Hanzestedenplaats 1", 4.5, "http://www.mas.be/nl"));
        locaties.add(new Locatie("Rubenshuis", R.drawable.rubenshuis_museum, "Wapper 9-11", 4.5, "http://www.rubenshuis.be/nl/Museum_Rubenshuis_EN/Rubenshuis_EN.html"));
        locaties.add(new Locatie("Middelheimmuseum", R.drawable.middelheimmuseum, "Middelheimlaan 61", 4.5, "http://www.middelheimmuseum.be/nl"));
        locaties.add(new Locatie("Museum Mayer van den Bergh", R.drawable.mayer_van_den_bergh_museum, "Lange Gasthuisstraat 19", 4.5, "http://www.museummayervandenbergh.be/"));
        locaties.add(new Locatie("Het Steen", R.drawable.het_steen, "Steenplein 1", 3.5, "https://www.visitantwerpen.be/en/sightseeing/architecture-monuments/steen-castle"));
        locaties.add(new Locatie("Snijders&Rockoxhuis", R.drawable.rockoxhuis, "Keizerstraat 12", 4.5, "https://www.snijdersrockoxhuis.be/"));
        locaties.add(new Locatie("Museum De Reede", R.drawable.museum_de_reede, "Ernest Van Dijckkaai 7", 4.5, "https://museum-dereede.com/"));
        locaties.add(new Locatie("Paleis op de Meir", R.drawable.paleis_op_de_meir, "Meir 50", 4, "http://herita.be/erfgoedbeleving/erfgoedsite/paleis-op-de-meir-antwerpen"));

        final LocatieAdapter adapter = new LocatieAdapter(getContext(), locaties);

        mListView.setAdapter(adapter);

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
