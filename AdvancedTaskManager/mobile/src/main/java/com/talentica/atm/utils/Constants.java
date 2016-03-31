package com.talentica.atm.utils;

/**
 * Created by ashutosht on 25/03/16.
 */

import com.talentica.atm.BuildConfig;

public final class Constants {

    public static final String FIREBASE_URL = BuildConfig.UNIQUE_FIREBASE_ROOT_URL;

    /**
     * Constants related to locations in Firebase, such as the name of the node
     * where active lists are stored (ie "activeLists")
     */
    public static final String FIREBASE_LOCATION_ALL_LISTS = "allLists";

    public static final String FIREBASE_URL_ALL_LISTS = FIREBASE_URL + "/" + FIREBASE_LOCATION_ALL_LISTS;


    /**
     * Constants for Firebase object properties
     */
    public static final String FIREBASE_PROPERTY_LIST_NAME = "listName";
    public static final String FIREBASE_PROPERTY_LIST_TYPE = "listType";
    public static final String FIREBASE_PROPERTY_TIMESTAMP_LAST_CHANGED = "timestampLastChanged";
    public static final String FIREBASE_PROPERTY_TIMESTAMP = "timestamp";
}
