package com.talentica.atm.service;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataMap;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.Wearable;
import com.google.android.gms.wearable.WearableListenerService;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.talentica.atm.common.ListModelCommon;
import com.talentica.atm.utils.Constants;
import com.talentica.atm.wear.DataSync;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by ashutosht on 09/04/16.
 */
public class ListernerServiceWear extends WearableListenerService {

    private final String TAG = "ListernerServiceWear";

    @Override
    public void onDataChanged(DataEventBuffer dataEvents) {
        super.onDataChanged(dataEvents);
        for (DataEvent event : dataEvents) {
            Log.e(TAG,"onDataChange - "+event.getDataItem());

        }

    }

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        super.onMessageReceived(messageEvent);

        Log.e(TAG,"onMessageReceived - "+messageEvent.getPath());

        if(messageEvent.getPath().equalsIgnoreCase("/fetchData"))
        {
            DataSync.getInstance().updateAllListToWear(this);
        }
        else if(messageEvent.getPath().equalsIgnoreCase("/fetchItems"))
        {
            DataSync.getInstance().updateListItemsToWear(this,new String(messageEvent.getData()));
        }


    }


}
