package com.talentica.atm.wear;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataMap;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.Wearable;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.talentica.atm.common.ItemModelCommon;
import com.talentica.atm.common.ListModelCommon;
import com.talentica.atm.utils.Constants;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by ashutosht on 12/04/16.
 */
public class DataSync {
    private static DataSync ourInstance = new DataSync();

    public static DataSync getInstance() {
        return ourInstance;
    }

    private DataSync() {
    }

    public void updateAllListToWear(final Context context)
    {
        Firebase allListRef = new Firebase(Constants.FIREBASE_URL_ALL_LISTS);

        allListRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                new UpdateAllListsAsync().execute(context,dataSnapshot);



            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void updateListItemsToWear(final Context context,final String listKey)
    {
        Firebase itemRef = new Firebase(Constants.FIREBASE_URL_LIST_ITEMS).child(listKey);

        itemRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                new UpdateListItemsAsync().execute(context,dataSnapshot);



            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }




    private class UpdateAllListsAsync extends AsyncTask<Object,Void,Void>
    {

        @Override
        protected Void doInBackground(Object... params) {

            Context context = (Context)params[0];
            DataSnapshot dataSnapshot = (DataSnapshot)params[1];


            GoogleApiClient googleApiClient = new GoogleApiClient.Builder(context)
                    .addApi(Wearable.API)
                    .build();

            ConnectionResult connectionResult = googleApiClient.blockingConnect(
                    Constants.GOOGLE_API_CLIENT_TIMEOUT_S, TimeUnit.SECONDS);

            if (connectionResult.isSuccess() && googleApiClient.isConnected())
            {
                ArrayList<DataMap> allListsData = new ArrayList<DataMap>((int) dataSnapshot.getChildrenCount());

                for(DataSnapshot childData: dataSnapshot.getChildren())
                {
                    allListsData.add((new ListModelCommon(childData.getKey(),(String) childData.child(Constants.FIREBASE_PROPERTY_LIST_NAME).getValue(),(String) childData.child(Constants.FIREBASE_PROPERTY_LIST_TYPE).getValue(),System.currentTimeMillis())).generateDataMap());
                }



                PutDataMapRequest putDataMapReq = PutDataMapRequest.create("/allLists");
                putDataMapReq.getDataMap().putDataMapArrayList("allList",allListsData);
                PutDataRequest putDataReq = putDataMapReq.asPutDataRequest();
                putDataReq.setUrgent();

                DataApi.DataItemResult result =
                        Wearable.DataApi.putDataItem(googleApiClient, putDataReq).await();

                if (!result.getStatus().isSuccess()) {
                    Log.e("LSW", String.format("Error sending data using DataApi (error code = %d)",
                            result.getStatus().getStatusCode()));
                }


            }

            return null;

        }
    }


    private class UpdateListItemsAsync extends AsyncTask<Object,Void,Void>
    {

        @Override
        protected Void doInBackground(Object... params) {

            Context context = (Context)params[0];
            DataSnapshot dataSnapshot = (DataSnapshot)params[1];


            GoogleApiClient googleApiClient = new GoogleApiClient.Builder(context)
                    .addApi(Wearable.API)
                    .build();

            ConnectionResult connectionResult = googleApiClient.blockingConnect(
                    Constants.GOOGLE_API_CLIENT_TIMEOUT_S, TimeUnit.SECONDS);

            if (connectionResult.isSuccess() && googleApiClient.isConnected())
            {
                ArrayList<DataMap> allListsData = new ArrayList<DataMap>((int) dataSnapshot.getChildrenCount());

                for(DataSnapshot childData: dataSnapshot.getChildren())
                {
                    allListsData.add((new ItemModelCommon((String) childData.child("itemName").getValue(),childData.getKey(),(String) childData.child("itemStatus").getValue(),System.currentTimeMillis())).generateDataMap());
                }



                PutDataMapRequest putDataMapReq = PutDataMapRequest.create("/listItems");
                putDataMapReq.getDataMap().putDataMapArrayList("listItems",allListsData);
                PutDataRequest putDataReq = putDataMapReq.asPutDataRequest();
                putDataReq.setUrgent();

                DataApi.DataItemResult result =
                        Wearable.DataApi.putDataItem(googleApiClient, putDataReq).await();

                if (!result.getStatus().isSuccess()) {
                    Log.e("DataSync", String.format("Error sending data using DataApi (error code = %d)",
                            result.getStatus().getStatusCode()));
                }


            }

            return null;

        }
    }
}
