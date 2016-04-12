package com.talentica.atm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.firebase.client.ServerValue;
import com.talentica.atm.utils.Constants;

import java.util.HashMap;

/**
 * Created by ashutosht on 25/03/16.
 */
public class ListModel {

    private String listName;
    private String owner;
    private String listType;
    private HashMap<String, Object> timestampLastChanged;
    private HashMap<String, Object> timestampCreated;
    private boolean newlyCreated ;


    public ListModel() {
    }


    public boolean isNewlyCreated() {
        return newlyCreated;
    }

    public ListModel(String listName, String owner, String listType, HashMap<String, Object> timestampCreated) {
        this.listName = listName;
        this.owner = owner;
        this.listType = listType;
        this.timestampCreated = timestampCreated;
        HashMap<String, Object> timestampNowObject = new HashMap<String, Object>();
        timestampNowObject.put(Constants.FIREBASE_PROPERTY_TIMESTAMP, ServerValue.TIMESTAMP);
        this.timestampLastChanged = timestampNowObject;
        this.newlyCreated = true;
    }

    public String getListName() {
        return listName;
    }

    public String getOwner() {
        return owner;
    }

    public String getListType() {
        return listType;
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
