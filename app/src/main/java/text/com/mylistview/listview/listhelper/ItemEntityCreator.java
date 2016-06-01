/*
 * Project:  msc
 * Module:   app-b
 * File:     ItemEntityCreator.java
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

/**
 * Created by chenupt@gmail.com on 2014/8/13.
 * Description : ItemEntityCreator creator to create SimpleItemEntity
 */
public class ItemEntityCreator {

    public static <T> SimpleItemEntity<T> create(T content) {
        return new SimpleItemEntity<T>(content);
    }
}
