package com.example.android.antwerpentourguide;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

// Launcht activity_main.xml en koppelt ViewPager in deze layout aan een OverzichtsAdapter voor het lanceren van juiste fragment

public class MainActivity extends AppCompatActivity {

    // ViewPager die toelaat om links en rechts te swipen tussen pagina's
    ViewPager viewPager;

    // Horizontale layout met tabs
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // verwijdert schaduw onder AppBar
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().setElevation(0);

        setContentView(R.layout.activity_main);

        // vindt de ViewPager in de main_activity.xml
        viewPager = findViewById(R.id.viewpager);

        // creatie OverzichtAdapter
        OverzichtAdapter adapter = new OverzichtAdapter(getSupportFragmentManager());

        // koppelt OverzichtAdapter aan ViewPager
        viewPager.setAdapter(adapter);

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        // Zet de Tabindicator kleur juist voor verschillende pagina's
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                viewPager.setCurrentItem(position);
                switch (position) {
                    case 0:
                        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.musea));
                        break;
                    case 1:
                        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.restaurants));
                        break;
                    case 2:
                        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.nightlife));
                        break;
                    case 3:
                        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.varia));
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
