package com.talentica.atm.service;

import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataMap;
import com.google.android.gms.wearable.DataMapItem;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

import com.talentica.atm.common.ItemModelCommon;
import com.talentica.atm.common.ListModelCommon;
import com.talentica.atm.ui.ListItemsActivity;
import com.talentica.atm.ui.WearActivity;

import android.os.Looper;
import android.os.Handler;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by ashutosht on 07/04/16.
 */
public class ListenerService extends WearableListenerService {

    private final String TAG = "ListenerService";

    @Override
    public void onDataChanged(DataEventBuffer dataEvents) {
        super.onDataChanged(dataEvents);

        Log.e(TAG,"@!@! ondataChange");

        for (DataEvent event : dataEvents) {
            Log.e(TAG,"@!@! ondataChange11 - "+event.getDataItem().getUri().getPath());

            if(event.getDataItem().getUri().getPath().equals("/allLists"))
            {
                DataMapItem dataMapItem = DataMapItem.fromDataItem(event.getDataItem());
                ArrayList<DataMap> allListsData =
                        dataMapItem.getDataMap().getDataMapArrayList("allList");

                final ListModelCommon[] lists = new ListModelCommon[allListsData.size()];

                for(int i = 0; i<allListsData.size();i++)
                {
                    lists[i] = new ListModelCommon(allListsData.get(i));
                }

                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {

                        WearActivity.listAdapter.updateData(lists);
                        WearActivity.listAdapter.notifyDataSetChanged();

                    }
                });



            }
            else if(event.getDataItem().getUri().getPath().equals("/listItems"))
            {
                DataMapItem dataMapItem = DataMapItem.fromDataItem(event.getDataItem());
                ArrayList<DataMap> allListsData =
                        dataMapItem.getDataMap().getDataMapArrayList("listItems");

                final ItemModelCommon[] items = new ItemModelCommon[allListsData.size()];

                for(int i = 0; i<allListsData.size();i++)
                {
                    items[i] = new ItemModelCommon(allListsData.get(i));
                }

                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {

                        ListItemsActivity.sListItemsAdapter.updateData(items);
                        ListItemsActivity.sListItemsAdapter.notifyDataSetChanged();

                    }
                });



            }
        }
    }

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        super.onMessageReceived(messageEvent);
    }


}
