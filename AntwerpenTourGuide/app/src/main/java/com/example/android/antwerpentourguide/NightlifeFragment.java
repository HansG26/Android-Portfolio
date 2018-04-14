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

        locaties.add(new Locatie(getString(R.string.naam_dogma_cocktails), R.drawable.dogma_cocktails, getString(R.string.adres_dogma_cocktails), 4.5, getString(R.string.link_dogma_cocktails)));
        locaties.add(new Locatie(getString(R.string.naam_kulminator), R.drawable.kulminator, getString(R.string.adres_kulminator), 4.5, getString(R.string.link_kulminator)));
        locaties.add(new Locatie(getString(R.string.naam_cocktails_at_nine), R.drawable.cocktails_at_nine, getString(R.string.adres_cocktails_at_nine), 4, getString(R.string.link_cocktails_at_nine)));
        locaties.add(new Locatie(getString(R.string.naam_jones_co), R.drawable.jones_co, getString(R.string.adres_jones_co), 4.5, getString(R.string.link_jones_co)));
        locaties.add(new Locatie(getString(R.string.naam_de_muze), R.drawable.jazzcafe_de_muze, getString(R.string.adres_de_muze), 4, getString(R.string.link_de_muze)));
        locaties.add(new Locatie(getString(R.string.naam_bierhuiske), R.drawable.antwaerps_bierhuiske, getString(R.string.adres_bierhuiske), 5, getString(R.string.link_bierhuiske)));
        locaties.add(new Locatie(getString(R.string.naam_mollys), R.drawable.mollys, getString(R.string.adres_mollys), 5, getString(R.string.link_mollys)));
        locaties.add(new Locatie(getString(R.string.naam_burbure), R.drawable.bar_burbure, getString(R.string.adres_burbure), 4, getString(R.string.link_burbure)));
        locaties.add(new Locatie(getString(R.string.naam_cartagena), R.drawable.bar_cartagena, getString(R.string.adres_cartagena), 5, getString(R.string.link_cartagena)));
        locaties.add(new Locatie(getString(R.string.naam_copa_cava), R.drawable.copa_cava, getString(R.string.adres_copa_cava), 4.5, getString(R.string.link_copa_cava)));

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
