/*
 * Project:  msc
 * Module:   app-b
 * File:     SimplePagerAdapter.java
 * Modifier: lmyu
 * Modified: 2015-06-08 20:55
 * Copyright (c) 2014 Wisorg All Rights Reserved.
 * Copying of this document or code and giving it to others and the
 * use or communication of the contents thereof, are forbidden without
 * expressed authority. Offenders are liable to the payment of damages.
 * All rights reserved in the event of the grant of a invention patent
 * or the registration of a utility model, design or code.
 */

package text.com.mylistview.listview.listhelper.pager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class SimplePagerAdapter extends FragmentPagerAdapter {

    protected PagerModelFactory pagerModelFactory;

    public SimplePagerAdapter(FragmentManager fm, PagerModelFactory pagerModelFactory) {
        super(fm);
        this.pagerModelFactory = pagerModelFactory;
    }

    @Override
    public Fragment getItem(int position) {
        return pagerModelFactory.getItem(position);
    }

    @Override
    public int getCount() {
        return pagerModelFactory.getFragmentCount();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (pagerModelFactory.hasTitles()) {
            return pagerModelFactory.getTitle(position);
        }
        return super.getPageTitle(position);
    }
}
