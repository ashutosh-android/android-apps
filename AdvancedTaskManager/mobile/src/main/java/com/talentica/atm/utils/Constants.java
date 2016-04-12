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
    public static final String FIREBASE_LOCATION_LIST_ITEMS = "listItems";

    public static final String FIREBASE_URL_ALL_LISTS = FIREBASE_URL + "/" + FIREBASE_LOCATION_ALL_LISTS;
    public static final String FIREBASE_URL_LIST_ITEMS = FIREBASE_URL + "/" + FIREBASE_LOCATION_LIST_ITEMS;


    /**
     * Constants for Firebase object properties
     */
    public static final String FIREBASE_PROPERTY_LIST_NAME = "listName";
    public static final String FIREBASE_PROPERTY_LIST_TYPE = "listType";
    public static final String FIREBASE_PROPERTY_TIMESTAMP_LAST_CHANGED = "timestampLastChanged";
    public static final String FIREBASE_PROPERTY_TIMESTAMP = "timestamp";
    public static final String FIREBASE_PROPERTY_NEWLYCREATED = "newlyCreated";

    public static final String FIREBASE_PROPERTY_ITEM_NAME = "itemName";
    public static final String FIREBASE_PROPERTY_ITEM_STATUS = "itemStatus";

    public static final String KEY_LIST_NAME = "LIST_NAME";
    public static final String KEY_LIST_ID = "LIST_ID";
    public static final String KEY_LIST_ITEM_NAME = "ITEM_NAME";
    public static final String KEY_LIST_ITEM_ID = "LIST_ITEM_ID";

    public static final int GOOGLE_API_CLIENT_TIMEOUT_S = 10; // 10 seconds
    public static final String GOOGLE_API_CLIENT_ERROR_MSG =
            "Failed to connect to GoogleApiClient (error code = %d)";
}
