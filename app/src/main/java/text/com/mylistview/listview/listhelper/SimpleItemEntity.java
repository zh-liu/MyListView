/*
 * Project:  msc
 * Module:   app-b
 * File:     SimpleItemEntity.java
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

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleItemEntity<T> implements Serializable {

    private long id;
    /**
     * 模板显示内容数据
     */
    private T content;
    /**
     * 当前项是否选中
     */
    private boolean isCheck;
    /**
     * 当前状态
     */
    private int status;
    /**
     * 模板类型，默认使用类名
     */
    private String modelType;
    /**
     * 模板显示view
     */
    private Class<?> modelView;
    /**
     * 数据缓存时间戳
     */
    private long timestamp;
    /**
     * 额外保存的数据
     */
    private Object extraData;

    /**
     * 该数据是否在列表中只有一条
     * 设置true时，列表只进行一次bingView，从而提高列表滑动效率
     */
    private boolean isSingleton;

    /**
     * 设置单一项的tag，方便寻找到特定的item
     */
    private String tag = "";

    /**
     * 附件属性
     */
    private Map<String, Object> attrs;

    public SimpleItemEntity() {
        // 默认设置数据缓存时间为当前时间戳
        setTimestamp(System.currentTimeMillis());
    }

    public SimpleItemEntity(T t) {
        this.content = t;
        // 默认设置数据缓存时间为当前时间戳
        setTimestamp(System.currentTimeMillis());
    }

    public long getId() {
        return id;
    }

    public SimpleItemEntity setId(long id) {
        this.id = id;
        return this;
    }

    public T getContent() {
        return content;
    }

    public SimpleItemEntity setContent(T content) {
        this.content = content;
        return this;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public SimpleItemEntity setCheck(boolean isCheck) {
        this.isCheck = isCheck;
        return this;
    }

    public SimpleItemEntity setStatus(int status) {
        this.status = status;
        return this;
    }

    public int getStatus() {
        return this.status;
    }

    public String getModelType() {
        return modelType;
    }

    public SimpleItemEntity setModelType(String modelType) {
        this.modelType = modelType;
        return this;
    }

    public Class<?> getModelView() {
        return modelView;
    }

    public SimpleItemEntity setModelView(Class<?> modelView) {
        // 未设置模板类型时
        if (modelType == null) {
            // 默认使用类名做modelType
            setModelType(modelView.getName());
        }
        this.modelView = modelView;
        return this;
    }

    public long getTimestamp() {
        return timestamp;
    }

    /**
     * 当数据为singleton时，可传递从服务端获取而来的timestamp，可不传
     */
    public SimpleItemEntity setTimestamp(long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public boolean isSingleton() {
        return isSingleton;
    }

    /**
     * 设置该数据显示的view是否在列表中唯一，例如海报位
     */
    public SimpleItemEntity setSingleton(boolean isSingleton) {
        this.isSingleton = isSingleton;
        return this;
    }

    public Object getExtraData() {
        return extraData;
    }

    public SimpleItemEntity setExtraData(Object extraData) {
        this.extraData = extraData;
        return this;
    }

    public String getTag() {
        return tag;
    }

    public SimpleItemEntity setTag(String tag) {
        this.tag = tag;
        return this;
    }

    public Map<String, Object> getAttrs() {
        return attrs;
    }

    public SimpleItemEntity<T> setAttrs(Map<String, Object> attrs) {
        this.attrs = attrs;
        return this;
    }

    public SimpleItemEntity<T> addAttr(String key, Object value) {
        if (attrs == null) {
            attrs = new HashMap<String, Object>();
        }
        attrs.put(key, value);
        return this;
    }

    public <T> T getAttr(String key, Class<T> c) {
        if (attrs == null) {
            return null;
        }
        return (T) attrs.get(key);
    }

    public boolean hasAttr(String key) {
        if (attrs == null) {
            return false;
        }
        if (attrs.get(key) == null) {
            return false;
        }
        return true;
    }

    public void attach(List<SimpleItemEntity> list) {
        list.add(this);
    }
}
