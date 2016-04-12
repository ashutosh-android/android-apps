package com.talentica.atm.ui;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.talentica.atm.R;
import com.talentica.atm.model.ItemModel;
import com.talentica.atm.model.ListModel;
import com.talentica.atm.utils.Constants;
import com.talentica.atm.wear.DataSync;
import com.talentica.atm.wear.NotificationManagerInternal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

/**
 * Created by ashutosht on 25/03/16.
 */
public class ListItemsActivity extends AppCompatActivity {

    private ListItemAdapter mListItemAdapter;
    private ListView mListView;
    private String mListId;
    private String mListName;

    private ListModel mListModel;

    private Firebase mListRef;
    private ValueEventListener mListItemUpdateListener;

    private final String TAG = ListItemsActivity.class.getSimpleName();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_items);

        /* Get the push ID from the extra passed by ShoppingListFragment */
        Intent intent = this.getIntent();
        mListId = intent.getStringExtra(Constants.KEY_LIST_ID);
        if (mListId == null) {
            /* No point in continuing without a valid ID. */
            finish();
            return;
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_item);
        setSupportActionBar(toolbar);

        mListView = (ListView) findViewById(R.id.list_view_item);

        /* Add back button to the action bar */
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addNewItem();


            }
        });

        mListRef = new Firebase(Constants.FIREBASE_URL_ALL_LISTS).child(mListId);
        Firebase itemRef = new Firebase(Constants.FIREBASE_URL_LIST_ITEMS).child(mListId);
//        Firebase itemStatusRef = new Firebase(Constants.FIREBASE_URL_LIST_ITEMS).child()

        mListItemAdapter = new ListItemAdapter(this, ItemModel.class,R.layout.single_item,itemRef,mListId);
        mListView.setAdapter(mListItemAdapter);

        mListItemUpdateListener = mListRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ListModel listModel = dataSnapshot.getValue(ListModel.class);

                mListModel = listModel;

                mListItemAdapter.updateListModel(mListModel);

                mListName = listModel.getListName();

                setTitle(listModel.getListName());

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


        itemRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.e(TAG,"onChildAdded  "+dataSnapshot.getKey());

                if((boolean)dataSnapshot.child(Constants.FIREBASE_PROPERTY_NEWLYCREATED).getValue())
                {

                    dataSnapshot.getRef().child(Constants.FIREBASE_PROPERTY_NEWLYCREATED).setValue(false);
                    NotificationManagerInternal notificationManagerInternal = NotificationManagerInternal.getInstance();
                    notificationManagerInternal.sendNotification(ListItemsActivity.this,"New Item '"+dataSnapshot.child(Constants.FIREBASE_PROPERTY_ITEM_NAME).getValue()+"'added in list "+mListName);

                    DataSync.getInstance().updateListItemsToWear(ListItemsActivity.this,mListId);
                }

                Firebase itemStatusRef = new Firebase(Constants.FIREBASE_URL_LIST_ITEMS).child(mListId).child(dataSnapshot.getKey()+"/itemStatus");
                itemStatusRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        Log.e(TAG,"itemStatus - onDataChange  "+dataSnapshot.getValue());

                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {



            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void addNewItem()
    {

        AddNewItemDialog dialogFragment = AddNewItemDialog.newInstance();
        dialogFragment.setListId(mListId);
        dialogFragment.show(ListItemsActivity.this.getFragmentManager(),"AddNewItemDialog");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            super.onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mListItemAdapter.cleanup();
    }
}
