/*
 * Project:  msc
 * Module:   app-b
 * File:     PagerModelFactory.java
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

import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class PagerModelFactory {

    public final static String DATA = "data";
    public final static String EXTRA_DATA = "extraData";

    private List<CharSequence> titleList;
    private List<Fragment> fragmentList;
    private List<? extends Serializable> extraData;

    public PagerModelFactory() {
        titleList = new ArrayList<CharSequence>();
        fragmentList = new ArrayList<Fragment>();
    }

    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    public int getFragmentCount() {
        return fragmentList.size();
    }

    public boolean hasTitles() {
        return titleList.size() != 0;
    }

    public CharSequence getTitle(int position) {
        return titleList.get(position);
    }

    public void setTitle(int position, CharSequence title) {
        titleList.set(position, title);
    }

    public void setTitleList(List<CharSequence> titleList) {
        this.titleList = titleList;
    }

    public List<Fragment> getFragmentList(){return this.fragmentList;}

    public void removeAllFragment(){
        this.fragmentList.clear();
    }

    public void removeAllTitle(){
        this.titleList.clear();
    }

    public PagerModelFactory addFragment(Fragment fragment, String title) {
        titleList.add(title);
        addFragment(fragment);
        return this;
    }

    public PagerModelFactory addFragment(Fragment fragment) {
        fragmentList.add(fragment);
        return this;
    }

    public PagerModelFactory addCommonFragment(Class<?> c, List<? extends Serializable> list,
            List<String> titleList) {
        this.titleList.addAll(titleList);
        addCommonFragment(c, list);
        return this;
    }

    public PagerModelFactory addCommonFragment(Class<?> c, List<? extends Serializable> list) {
        try {
            for (int i = 0; i < list.size(); i++) {
                Fragment fragment = (Fragment) c.newInstance();
                Bundle bundle = new Bundle();
                bundle.putSerializable(DATA, list.get(i));
                bundle.putSerializable(EXTRA_DATA, extraData.get(i));
                fragment.setArguments(bundle);
                fragmentList.add(fragment);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return this;
    }

    public PagerModelFactory putData(List<? extends Serializable> dataList) {
        this.extraData = dataList;
        return this;
    }

    public PagerModelFactory addCommonFragment(List<? extends Fragment> list) {
        fragmentList.addAll(list);
        return this;
    }

    public PagerModelFactory addCommonFragment(List<? extends Fragment> list,
            List<String> titleList) {
        this.titleList.addAll(titleList);
        addCommonFragment(list);
        return this;
    }
}
