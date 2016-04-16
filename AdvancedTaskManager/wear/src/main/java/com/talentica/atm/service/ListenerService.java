package com.talentica.atm.service;

import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataMap;
import com.google.android.gms.wearable.DataMapItem;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

import com.talentica.atm.common.ItemModelCommon;
import com.talentica.atm.common.ListModelCommon;
import com.talentica.atm.ui.CustomNotificationActivity;
import com.talentica.atm.ui.ListItemsActivity;
import com.talentica.atm.ui.WearActivity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Looper;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
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

                        if(WearActivity.listAdapter != null && lists != null)
                        {
                            WearActivity.listAdapter.updateData(lists);
                            WearActivity.listAdapter.notifyDataSetChanged();
                        }


//                        Intent intent = new Intent(WearActivity.listAdapter.mContext, CustomNotificationActivity.class);
//                        PendingIntent displayPendingIntent =
//                                PendingIntent.getActivity(WearActivity.listAdapter.mContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//
//
//                        // Simple Notification
//
//
//                        Notification.Builder notificationBuilder =
//                                new Notification.Builder(WearActivity.listAdapter.mContext)
//                                        .setSmallIcon(android.R.drawable.ic_dialog_info)
//                                        .extend(new Notification.WearableExtender()
//                                            .setDisplayIntent(displayPendingIntent));
//
//                        ((NotificationManager) getSystemService(NOTIFICATION_SERVICE))
//                                .notify(0,notificationBuilder.build());

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

//                        ListItemsActivity.sListItemsAdapter.updateData(items);
//                        ListItemsActivity.sListItemsAdapter.notifyDataSetChanged();

                        if(ListItemsActivity.sListItemsGridPagerAdapter != null && items != null)
                        {
                            ListItemsActivity.sListItemsGridPagerAdapter.updateData(items);
                            ListItemsActivity.sListItemsGridPagerAdapter.notifyDataSetChanged();

                        }

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
