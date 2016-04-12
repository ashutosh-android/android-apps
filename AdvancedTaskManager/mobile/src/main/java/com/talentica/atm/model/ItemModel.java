package com.talentica.atm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.firebase.client.ServerValue;
import com.talentica.atm.utils.Constants;

import java.util.HashMap;

/**
 * Created by ashutosht on 31/03/16.
 */
public class ItemModel {

    private String itemName;
    private String itemStatus;
    private HashMap<String, Object> timestampLastChanged;
    private HashMap<String, Object> timestampCreated;
    private boolean newlyCreated ;

    public boolean isNewlyCreated() {
        return newlyCreated;
    }

    public ItemModel() {
    }

    public ItemModel(String itemName, String itemStatus, HashMap<String, Object> timestampCreated) {
        this.itemName = itemName;
        this.itemStatus = itemStatus;
        this.timestampCreated = timestampCreated;

        HashMap<String, Object> timestampNowObject = new HashMap<String, Object>();
        timestampNowObject.put(Constants.FIREBASE_PROPERTY_TIMESTAMP, ServerValue.TIMESTAMP);
        this.timestampLastChanged = timestampNowObject;
        this.newlyCreated = true;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemStatus() {
        return itemStatus;
    }

    public HashMap<String, Object> getTimestampLastChanged() {
        return timestampLastChanged;
    }

    public HashMap<String, Object> getTimestampCreated() {
        return timestampCreated;
    }

    @JsonIgnore
    public long getTimestampLastChangedLong() {

        return (long) timestampLastChanged.get(Constants.FIREBASE_PROPERTY_TIMESTAMP);
    }

    @JsonIgnore
    public long getTimestampCreatedLong() {
        return (long) timestampLastChanged.get(Constants.FIREBASE_PROPERTY_TIMESTAMP);
    }
}
