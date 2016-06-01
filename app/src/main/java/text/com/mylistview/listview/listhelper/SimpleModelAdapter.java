/*
 * Project:  msc
 * Module:   app-b
 * File:     SimpleModelAdapter.java
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
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class SimpleModelAdapter extends BaseListAdapter<SimpleItemEntity> {

    public static final String TAG = "SimpleModelAdapter";

    protected ModelFactory modelFactory;

    public SimpleModelAdapter(Context context, ModelFactory modelFactory) {
        super(context);
        this.modelFactory = modelFactory;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Log.d(TAG, "getView:" + i);
        if (view == null) {
            view = modelFactory.createModel(getContext(), getItem(i).getModelType());
        }
        if (view == null) {
            throw new RuntimeException(getItem(i).getModelType() + " is null");
        }
        ((BaseItemModel) view).setViewPosition(i);
        ((BaseItemModel) view).setModel(getItem(i), getList());
        ((BaseItemModel) view).setAdapter(this);
        return view;
    }

    @Override
    public int getItemViewType(int position) {
        String type = getItem(position).getModelType();
        return modelFactory.getViewType(type);
    }

    @Override
    public int getViewTypeCount() {
        return modelFactory.getViewTypeCount();
    }
}
