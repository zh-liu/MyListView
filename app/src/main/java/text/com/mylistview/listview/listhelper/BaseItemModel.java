/*
 * Project:  msc
 * Module:   app-b
 * File:     BaseItemModel.java
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
import android.util.AttributeSet;
import android.widget.FrameLayout;

import java.util.List;

public abstract class BaseItemModel<T> extends FrameLayout {

    protected SimpleItemEntity<T> model;
    protected List<SimpleItemEntity<T>> modelList;
    protected int viewPosition;
    protected SimpleItemEntity<T> groupModel;
    protected int groupPosition;

    protected BaseListAdapter adapter;

    public BaseItemModel(Context context) {
        this(context, null);
    }

    public BaseItemModel(Context context, AttributeSet attrs) {
        super(context, attrs);
        /**
         * Injects dependencies into the fields and methods of instance.
         * Ignores the presence or absence of an injectable constructor.
         Whenever Guice creates an instance, it performs this injection
         automatically (after first performing constructor injection),
         so if you're able to let Guice create all your objects for you,
         you'll never need to use this method.
         */
//        MscGuice.getInjector().injectMembers(this);
    }

    public abstract void bindView();

    public void setModel(SimpleItemEntity<T> model, List<SimpleItemEntity<T>> modelList) {
        if (this.model != null) {
            // 判断数据是否唯一项，如果数据未过期则不进行bindView，显示缓存的view
            if (this.model.isSingleton() && this.model.getTimestamp() == model.getTimestamp()) {
                return;
            }
        }

        this.model = model;
        this.modelList = modelList;
        bindView();
    }

    ;

    public void setModel(SimpleItemEntity<T> model) {
        setModel(model, null);
    }

    ;

    public void setViewPosition(int position) {
        this.viewPosition = position;
    }

    public void setGroupPosition(int groupPosition) {
        this.groupPosition = groupPosition;
    }

    public void setGroupModel(SimpleItemEntity<T> groupModel) {
        this.groupModel = groupModel;
    }

    public BaseListAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(BaseListAdapter adapter) {
        this.adapter = adapter;
    }

    public void notifyDataSetChanged() {
        adapter.notifyDataSetChanged();
    }



}
