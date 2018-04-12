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
public class NightlifeFragment extends Fragment {

    // ListView die we in dit fragment populaten met Nightlife
    ListView mListView;

    public NightlifeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate layout waar ListView zich in bevindt
        View view = inflater.inflate(R.layout.locatie_lijst, container, false);

        // vindt ListView die we gaan populaten met ListView items
        mListView = view.findViewById(R.id.lijst);

        // create lijst nightlife locaties
        ArrayList<Locatie> locaties = new ArrayList<>();

        locaties.add(new Locatie("Dogma Cocktails", R.drawable.dogma_cocktails, "Wijngaardstraat 5", 4.5, "http://www.dogmacocktails.be/"));
        locaties.add(new Locatie("Kulminator", R.drawable.kulminator, "Vleminckveld 32", 4.5, "http://www.facebook.com/pages/Kulminator-friends-/222416071143354?sk=wall"));
        locaties.add(new Locatie("Cocktails at Nine", R.drawable.cocktails_at_nine, "Lijnwaadmarkt 9", 4, "http://www.cocktailsatnine.be/"));
        locaties.add(new Locatie("Jones & Co", R.drawable.jones_co, "Pelgrimstraat 7", 4.5, "https://newplacestobe.com/jones-co-antwerpen/"));
        locaties.add(new Locatie("Jazzcafe de Muze", R.drawable.jazzcafe_de_muze, "Melkmarkt 10", 4, "http://jazzcafedemuze.be/"));
        locaties.add(new Locatie("'t Antwaerps Bierhuiske", R.drawable.antwaerps_bierhuiske, "Hoogstraat 14", 5, "https://www.antwaerpsbierhuyske.com"));
        locaties.add(new Locatie("Molly's Irish Pub", R.drawable.mollys, "Jezuietenrui 4", 5, "https://gwoodhouse1.wixsite.com/mollys"));
        locaties.add(new Locatie("Bar Burbure", R.drawable.bar_burbure, "Vlaamsekaai 41", 4, "http://www.barburbure.be/"));
        locaties.add(new Locatie("Bar Cartagena", R.drawable.bar_cartagena, "Vlasmarkt 31-33", 5, "http://www.barcartagena.be/"));
        locaties.add(new Locatie("Copa Cava", R.drawable.copa_cava, "Vlasmarkt 32", 4.5, "http://www.copacava.be/"));

        // Maakt nieuwe LocatieAdapter die als data een ArrayList met Locaties krijgt (Nightlife)
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
