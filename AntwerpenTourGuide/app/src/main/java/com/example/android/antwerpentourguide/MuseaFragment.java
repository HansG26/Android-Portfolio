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
        ArrayList<Locatie> locaties = new ArrayList<>();

        locaties.add(new Locatie(getString(R.string.naam_plantin_moretus), R.drawable.museum_plantin_moretus, getString(R.string.adres_plantin_moretus), 4.5, getString(R.string.link_plantin_moretus)));
        locaties.add(new Locatie(getString(R.string.naam_red_star_line), R.drawable.red_star_line_museum, getString(R.string.adres_red_star_line), 4.5, getString(R.string.link_red_star_line)));
        locaties.add(new Locatie(getString(R.string.naam_museum_aan_de_stroom), R.drawable.museum_aan_de_stroom, getString(R.string.adres_museum_aan_de_stroom), 4.5, getString(R.string.link_museum_aan_de_stroom)));
        locaties.add(new Locatie(getString(R.string.naam_rubenshuis), R.drawable.rubenshuis_museum, getString(R.string.adres_rubenshuis), 4.5, getString(R.string.link_rubenshuis)));
        locaties.add(new Locatie(getString(R.string.naam_middelheim), R.drawable.middelheimmuseum, getString(R.string.adres_middelheim), 4.5, getString(R.string.link_middelheim)));
        locaties.add(new Locatie(getString(R.string.naam_mayer_van_den_bergh), R.drawable.mayer_van_den_bergh_museum, getString(R.string.adres_mayer_van_den_bergh), 4.5, getString(R.string.link_mayer_van_den_bergh)));
        locaties.add(new Locatie(getString(R.string.naam_het_steen), R.drawable.het_steen, getString(R.string.adres_het_steen), 3.5, getString(R.string.link_het_steen)));
        locaties.add(new Locatie(getString(R.string.naam_snijders_rockoxhuis), R.drawable.rockoxhuis, getString(R.string.adres_snijders_rockoxhuis), 4.5, getString(R.string.link_snijders_rockoxhuis)));
        locaties.add(new Locatie(getString(R.string.naam_de_reede), R.drawable.museum_de_reede, getString(R.string.adres_de_reede), 4.5, getString(R.string.link_de_reede)));
        locaties.add(new Locatie(getString(R.string.naam_paleis_op_de_meir), R.drawable.paleis_op_de_meir, getString(R.string.adres_paleis_op_de_meir), 4, getString(R.string.link_paleis_op_de_meir)));

        // Maakt nieuwe LocatieAdapter die als data een ArrayList met Locaties krijgt (Musea)
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
