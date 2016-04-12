package com.talentica.atm.ui;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.Wearable;

import com.talentica.atm.R;
import com.talentica.atm.common.ItemModelCommon;
import com.talentica.atm.common.ListModelCommon;

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

/**
 * Created by ashutosht on 12/04/16.
 */
public class ListItemsActivity extends Activity implements GoogleApiClient.OnConnectionFailedListener,GoogleApiClient.ConnectionCallbacks {

    private final String TAG = "ListItemsActivity";

    public static ListItemsAdapter sListItemsAdapter;

    private GoogleApiClient mGoogleApiClient;

    private String mListId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_listitems);

        Intent intent = this.getIntent();
        mListId = intent.getStringExtra("listKey");
        if (mListId == null) {
            /* No point in continuing without a valid ID. */
            finish();
            return;
        }



        WearableListView listView =
                (WearableListView) findViewById(R.id.wearable_list_items);

        ItemModelCommon[] items = new ItemModelCommon[0];


        sListItemsAdapter = new ListItemsAdapter(this,items);



        listView.setAdapter(sListItemsAdapter);


        listView.setClickListener(new WearableListView.ClickListener() {
            @Override
            public void onClick(WearableListView.ViewHolder viewHolder) {

                //Log.e(TAG,"OnClick in List - "+((TextView)viewHolder.itemView.findViewById(R.id.list_name)).getText());


            }

            @Override
            public void onTopEmptyRegionClick() {

            }
        });

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Wearable.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        new FetchitemsFromMobile().execute(this);

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private class FetchitemsFromMobile extends AsyncTask<Object,Void,Void>
    {

        @Override
        protected Void doInBackground(Object... params) {

            Log.e(TAG,"FetchitemsFromMobile");


            NodeApi.GetConnectedNodesResult nodes = Wearable.NodeApi.getConnectedNodes( mGoogleApiClient ).await();
            for(Node node : nodes.getNodes()) {

                MessageApi.SendMessageResult result = Wearable.MessageApi.sendMessage(
                        mGoogleApiClient, node.getId(), "/fetchItems", mListId.getBytes() ).await();
                if(result.getStatus().isSuccess())
                {
                    //ToDo
                }
            }
            return null;
        }
    }
}
