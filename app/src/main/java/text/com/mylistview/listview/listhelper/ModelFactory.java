/*
 * Project:  msc
 * Module:   app-b
 * File:     ModelFactory.java
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

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ModelFactory {

    public static final String TAG = "ModelFactory";

    public Builder builder;

    private ModelFactory(Builder builder) {
        this.builder = builder;
    }

    public BaseItemModel createModel(Context context, String modelType) {
        Log.d(TAG, "createModel: " + modelType);
        BaseItemModel baseItemModel = null;
        Class<?> owner = builder.viewMap.get(modelType);
        try {
            // baseItemModel = (BaseItemModel) viewMap.get(modelType).getConstructor(Context.class).newInstance(context);
            // 使用AndroidAnnotation的viewGroup初始化调用build方法
            baseItemModel = (BaseItemModel) owner.getMethod("build", new Class[] { Context.class })
                    .invoke(owner, context);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baseItemModel;
    }

    /**
     * 通过模板类型获取模板指针
     */
    public int getViewType(String modelType) {
        if (!builder.indexMap.containsKey(modelType)) {
            Log.d(TAG, builder.indexMap.toString());
            throw new RuntimeException("The list does not contain the modelView:'"
                    + modelType
                    + "'. Please check the ModelFactory.");
        }
        //        Log.d(TAG, "getViewType:" + builder.indexMap.get(modelType));
        return builder.indexMap.get(modelType);
    }

    /**
     * 获取模板数量
     */
    public int getViewTypeCount() {
        //        Log.d(TAG, "getViewTypeCount:" + builder.viewMap.size());
        return builder.viewMap.size();
    }

    /**
     * 当前模板是否可以固定头部
     */
    public boolean isItemViewTypePinned(int type) {
        if (type >= 0) {
            return builder.pinnedMap.get(type);
        } else {
            return false;
        }
    }

    /**
     * get the tag item at the start.
     *
     * @param list list data
     * @param tag tag value
     * @return item model
     */
    public SimpleItemEntity getStartItemByTag(List<SimpleItemEntity> list, String tag) {
        for (SimpleItemEntity entity : list) {
            if (entity.getTag().equals(tag)) {
                return entity;
            }
        }
        return null;
    }

    /**
     * get the tag item at the end.
     *
     * @param list list data
     * @param tag tag value
     * @return item model
     */
    public SimpleItemEntity getEndItemByTag(List<SimpleItemEntity> list, String tag) {
        Collections.reverse(list);
        for (SimpleItemEntity entity : list) {
            if (entity.getTag().equals(tag)) {
                Collections.reverse(list);
                return entity;
            }
        }
        return null;
    }

    //------------创建ModelFactory需添加Model---------

    public static class Builder {

        private HashMap<String, Class<?>> viewMap;  // 模板类型 -> 模板展示View
        private HashMap<String, Integer> indexMap;  // 模板类型 -> 模板指针
        private HashMap<Integer, Boolean> pinnedMap;// 模板指针 -> View是否固定

        public Builder() {
            viewMap = new HashMap<String, Class<?>>();
            indexMap = new HashMap<String, Integer>();
            pinnedMap = new HashMap<Integer, Boolean>();
        }

        public ModelFactory build() {
            return new ModelFactory(this);
        }

        public Builder addModel(Class<?> viewClass) {
            return addModel(viewClass, false);
        }

        public Builder addModel(Class<?> viewClass, boolean isPinned) {
            return addToMap(getModelTypeName(viewClass), viewClass, isPinned);
        }

        public Builder addModel(String modelType, Class<?> viewClass) {
            return addModel(modelType, viewClass, false);
        }

        public Builder addModel(String modelType, Class<?> viewClass, boolean isPinned) {
            return addToMap(modelType, viewClass, isPinned);
        }

        private Builder addToMap(String modelType, Class<?> viewClass, boolean isPinned) {
            if (!viewMap.containsKey(modelType)) {
                viewMap.put(modelType, viewClass);
                int viewType = viewMap.size() - 1;
                indexMap.put(modelType, viewType);
                pinnedMap.put(viewType, isPinned);
            }
            return this;
        }

        private String getModelTypeName(Class<?> modelView) {
            return modelView.getName();
        }

        // FIXME 动态加载
        //        private Builder addListToMap(List<SimpleItemEntity> entityList){
        //            for(int i = 0; i < entityList.size(); i ++){
        //                SimpleItemEntity entity = entityList.get(i);
        //
        //                String modelType = "default_model_type";
        //                if(entity.getModelType() == null){
        //                    modelType = modelType + modelTypeSuf;
        //                    modelTypeSuf ++;
        //                }else{
        //                    modelType = entity.getModelType();
        //                }
        //                addModel(modelType, entity.getModelView(), entity.isPinned());
        //            }
        //            return this;
        //        }
    }
}
