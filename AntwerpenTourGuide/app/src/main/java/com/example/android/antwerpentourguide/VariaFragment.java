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

    // ListView die we in dit fragment populaten met Varia
    ListView mListView;

    public VariaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate layout waar ListView zich in bevindt
        View view = inflater.inflate(R.layout.locatie_lijst, container, false);

        // vindt ListView die we gaan populaten met ListView items
        mListView = view.findViewById(R.id.lijst);

        // create lijst met varia
        ArrayList<Locatie> locaties = new ArrayList<>();

        // locaties.add();
        locaties.add(new Locatie(getString(R.string.naam_centraal_station), R.drawable.centraal_station, getString(R.string.adres_centraal_station), 4.5, getString(R.string.link_centraal_station)));
        locaties.add(new Locatie(getString(R.string.naam_grote_markt), R.drawable.grote_markt, getString(R.string.adres_grote_markt), 4.5, getString(R.string.link_grote_markt)));
        locaties.add(new Locatie(getString(R.string.naam_kathedraal), R.drawable.olv_kathedraal, getString(R.string.adres_kathedraal), 4.5, getString(R.string.link_kathedraal)));
        locaties.add(new Locatie(getString(R.string.naam_cogels), R.drawable.cogels_osylei, getString(R.string.adres_cogels), 4.5, getString(R.string.link_cogels)));
        locaties.add(new Locatie(getString(R.string.naam_sint_pauluskerk), R.drawable.sint_paulus_kerk, getString(R.string.adres_sint_pauluskerk), 4.5, getString(R.string.link_sint_pauluskerk)));
        locaties.add(new Locatie(getString(R.string.naam_stadhuis), R.drawable.stadhuis, getString(R.string.adres_stadhuis), 4.5, getString(R.string.link_stadhuis)));
        locaties.add(new Locatie(getString(R.string.naam_st_annas_tunnel), R.drawable.st_anna_tunnel, getString(R.string.adres_st_annas_tunnel), 4, getString(R.string.link_st_annas_tunnel)));
        locaties.add(new Locatie(getString(R.string.naam_carolus_borromeus), R.drawable.carolus_borromeus_kerk, getString(R.string.adres_carolus_borromeus), 4.5, getString(R.string.link_carolus_borromeus)));
        locaties.add(new Locatie(getString(R.string.naam_meir), R.drawable.meir, getString(R.string.adres_meir), 4, getString(R.string.link_meir)));
        locaties.add(new Locatie(getString(R.string.naam_vlaeykensgang), R.drawable.vlaeykensgang, getString(R.string.adres_vlaeykensgang), 4.5, getString(R.string.link_vlaeykensgang)));


        // Maakt nieuwe LocatieAdapter die als data een ArrayList met Locaties krijgt (Varia)
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
