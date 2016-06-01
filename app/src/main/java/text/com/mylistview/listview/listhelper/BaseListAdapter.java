/*
 * Project:  msc
 * Module:   app-b
 * File:     BaseListAdapter.java
 * Modifier: lmyu
 * Modified: 2015-06-08 20:55
 * Copyright (c) 2014 Wisorg All Rights Reserved.
 * Copying of this document or code and giving it to others and the
 * use or communication of the contents thereof, are forbidden without
 * expressed authority. Offenders are liable to the payment of damages.
 * All rights reserved in the event of the grant of a invention patent
 * or the registration of a utility model, design or code.
 */

package text.com.mylistview.listview.listhelper;

import android.content.Context;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseListAdapter<T> extends BaseAdapter {

    protected List<T> list;
    protected List<T> lazyList;
    protected Context context;

    public BaseListAdapter(Context context) {
        list = new ArrayList<T>();
        lazyList = new ArrayList<T>();
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public T getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public List<T> getList() {
        return list;
    }

    public void addList(List<T> list) {
        if (list != null) {
            this.list.addAll(list);
        }
    }

    public void addListToHead(List<T> list) {
        if (list != null) {
            this.list.addAll(0, list);
        }
    }

    public void removeList(List<T> list) {
        this.list.removeAll(list);
    }

    //    public void addLazyList(List<T> list){
    //        if(list != null){
    //            this.lazyList.addAll(list);
    //        }
    //    }

    /**
     * 将缓存的数据添加到list
     */
    public void addLazyDataToList() {
        this.list.addAll(lazyList);
    }

    /**
     * 保存缓存数据
     */
    public void saveLazyList(List<T> list) {
        if (list != null) {
            this.lazyList = list;
        }
    }

    public void addItem(T item) {
        this.list.add(item);
    }

    public void clearList() {
        this.list.clear();
    }

    public void remove(int position) {
        this.list.remove(position);
    }

    public void remove(T item) {
        this.list.remove(item);
    }

    public Context getContext() {
        return context;
    }
}
