package com.example.android.antwerpentourguide;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
Zorgt voor weergave van juiste fragment bij het swipen door pagina's
 */



public class OverzichtAdapter extends FragmentPagerAdapter {

    // titels voor de tablayout
    String[] titels = {"Musea", "Restaurant", "Nightlife", "Varia"};

    // constructor die nodig is in MainActivity.java
    public OverzichtAdapter(FragmentManager fm) {
        super(fm);
    }

    // ViewPager roept deze method op, die het juiste fragment teruggeeft op basis van positie in pagina's
    @Override
    public Fragment getItem(int position) {
        if(position == 0) {
            return new MuseaFragment();
        } else if (position == 1) {
            return new RestaurantsFragment();
        } else if (position == 2) {
            return new NightlifeFragment();
        } else {
            return new VariaFragment();
        }
    }

    // ViewPager roept deze method op om te weten hoeveel fragments er zijn om weer te geven
    @Override
    public int getCount() {
        return 4;
    }

    // geeft juiste titel terug voor tablayout
    @Override
    public CharSequence getPageTitle(int position) {
        return titels[position];
    }
}