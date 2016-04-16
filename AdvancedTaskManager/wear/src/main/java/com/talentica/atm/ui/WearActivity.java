package com.talentica.atm.ui;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.Wearable;
import com.google.android.gms.wearable.WearableListenerService;

import com.talentica.atm.R;
import com.talentica.atm.common.ListModelCommon;
import com.talentica.atm.util.Constants;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.wearable.view.WearableListView;
import android.util.Log;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

/**
 * Created by ashutosht on 04/04/16.
 */
public class WearActivity extends Activity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    // Sample dataset for the list


    private final String TAG = "WearActivity";

    public static AllListAdapter listAdapter;

    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wear);


// UI Section

//        WearableListView listView =
//                (WearableListView) findViewById(R.id.wearable_list);
//
//        ListModelCommon[] lists = new ListModelCommon[4];
//        lists[0] = new ListModelCommon("1212","Dummy List1","Personal",11111l);
//        lists[1] = new ListModelCommon("1212","Dummy List2","Work",11111l);
//        lists[2] = new ListModelCommon("1212","Dummy List3","Work",11111l);
//        lists[3] = new ListModelCommon("1212","Dummy List4","Personal",11111l);
//
//
//        listAdapter = new AllListAdapter(this,lists);
//
//
//
//        listView.setAdapter(listAdapter);
//
//
//        listView.setClickListener(new WearableListView.ClickListener() {
//            @Override
//            public void onClick(WearableListView.ViewHolder viewHolder) {
//
//                Intent intent = new Intent(WearActivity.this,ListItemsActivity.class);
//                intent.putExtra("listKey",((TextView)viewHolder.itemView.findViewById(R.id.list_key)).getText());
//                WearActivity.this.startActivity(intent);
//
//
//                Log.e(TAG,"OnClick in List - "+((TextView)viewHolder.itemView.findViewById(R.id.list_key)).getText());
//
//
//           }
//
//            @Override
//            public void onTopEmptyRegionClick() {
//
//            }
//        });


        //DataSync Section
//        mGoogleApiClient = new GoogleApiClient.Builder(this)
//                .addApi(Wearable.API)
//                .addConnectionCallbacks(this)
//                .addOnConnectionFailedListener(this)
//                .build();
//
//        mGoogleApiClient.connect();




    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG,"onStart");
        //DataSync Section
//        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //DataSync Section
//        mGoogleApiClient.disconnect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.e(TAG,"onConnected");

        //DataSync Section
//        new FetchDataFromMobile().execute(this);

    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.e(TAG,"onConnectionSuspended");

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.e(TAG,"onConnectionFailed");

    }


    private class FetchDataFromMobile extends AsyncTask<Context,Void,Void>
    {

        @Override
        protected Void doInBackground(Context... contexts) {

            //DataSync Section

//            Log.e(TAG,"FetchDataFromMobile");
//
//            String text = "FetchData";
//
//            NodeApi.GetConnectedNodesResult nodes = Wearable.NodeApi.getConnectedNodes( mGoogleApiClient ).await();
//            for(Node node : nodes.getNodes()) {
//
//                MessageApi.SendMessageResult result = Wearable.MessageApi.sendMessage(
//                        mGoogleApiClient, node.getId(), "/fetchData", text.getBytes() ).await();
//                if(result.getStatus().isSuccess())
//                {
//                    //ToDo
//                }
//            }
            return null;
        }
    }
}
