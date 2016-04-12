package com.talentica.atm.common;

import com.google.android.gms.wearable.DataMap;

/**
 * Created by ashutosht on 12/04/16.
 */
public class ItemModelCommon {

    private String itemName;
    private String itemId;
    private String itemStatus;
    private Long currentTimel;

    public ItemModelCommon(String itemName, String itemId, String itemStatus, Long currentTimel) {
        this.itemName = itemName;
        this.itemId = itemId;
        this.itemStatus = itemStatus;
        this.currentTimel = currentTimel;
    }

    public ItemModelCommon(DataMap map) {

        this(map.getString("itemName"),map.getString("itemId"),map.getString("itemStatus"),map.getLong("currentTimel"));
    }

    public DataMap generateDataMap()
    {
        DataMap map = new DataMap();
        map.putString("itemName",itemName);
        map.putString("itemId",itemId);
        map.putString("itemStatus",itemStatus);
        map.putLong("currentTimel",currentTimel);

        return map;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemId() {
        return itemId;
    }

    public String getItemStatus() {
        return itemStatus;
    }
}
