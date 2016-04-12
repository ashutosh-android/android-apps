package com.talentica.atm.common;

import com.google.android.gms.wearable.DataMap;

/**
 * Created by ashutosht on 09/04/16.
 */
public class ListModelCommon {

    private String listId;
    private String listName;
    private String listType;
    private Long currentTimel;

    public ListModelCommon(String listId, String listName, String listType, Long currentTimel) {
        this.listId = listId;
        this.listName = listName;
        this.listType = listType;
        this.currentTimel = currentTimel;
    }

    public ListModelCommon(DataMap map) {

        this(map.getString("listId"),map.getString("listName"),map.getString("listType"),map.getLong("currentTimel"));
    }

    public DataMap generateDataMap()
    {
        DataMap map = new DataMap();
        map.putString("listId",listId);
        map.putString("listName",listName);
        map.putString("listType",listType);
        map.putLong("currentTimel",currentTimel);

        return map;
    }

    public String getListId() {
        return listId;
    }

    public String getListName() {
        return listName;
    }

    public String getListType() {
        return listType;
    }
}
