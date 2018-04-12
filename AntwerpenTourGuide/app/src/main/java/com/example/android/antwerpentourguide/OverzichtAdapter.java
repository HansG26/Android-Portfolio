package com.example.android.antwerpentourguide;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Zorgt voor weergave van juiste fragment bij het swipen door pagina's
 */


public class OverzichtAdapter extends FragmentPagerAdapter {

    private Context mContext;

    // constructor die nodig is in MainActivity.java
    OverzichtAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    // ViewPager roept deze method op, die het juiste fragment teruggeeft op basis van positie in pagina's
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MuseaFragment();
            case 1:
                return new RestaurantsFragment();
            case 2:
                return new NightlifeFragment();
            case 3:
                return new VariaFragment();
        }
        return null;
    }

    // ViewPager roept deze method op om te weten hoeveel fragments er zijn om weer te geven
    @Override
    public int getCount() {
        return 4;
    }

    // geeft juiste titel terug voor tablayout
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return mContext.getResources().getString(R.string.tab_musea_titel);
            case 1:
                return mContext.getResources().getString(R.string.tab_restaurant_titel);
            case 2:
                return mContext.getResources().getString(R.string.tab_nightlife_titel);
            case 3:
                return mContext.getResources().getString(R.string.tab_varia_titel);
        }
        return null;
    }
}