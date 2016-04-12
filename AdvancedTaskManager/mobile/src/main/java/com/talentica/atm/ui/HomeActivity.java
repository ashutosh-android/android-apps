package com.talentica.atm.ui;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.talentica.atm.R;
import com.talentica.atm.model.ListModel;
import com.talentica.atm.wear.DataSync;
import com.talentica.atm.wear.NotificationManagerInternal;
import com.talentica.atm.utils.Constants;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private AllListAdapter mAllListAdapter;
    private ListView mListView;
    private final String TAG= HomeActivity.class.getSimpleName();
    Firebase allListRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mListView = (ListView) findViewById(R.id.list_view_all_lists);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                addNewList();


            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        allListRef = new Firebase(Constants.FIREBASE_URL_ALL_LISTS);

        mAllListAdapter = new AllListAdapter(this,ListModel.class,R.layout.single_list,allListRef);

        mListView.setAdapter(mAllListAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                ListModel selectedList = mAllListAdapter.getItem(i);
                if(selectedList != null)
                {
                    Intent intent = new Intent(HomeActivity.this,ListItemsActivity.class);

                    String listId = mAllListAdapter.getRef(i).getKey();
                    intent.putExtra(Constants.KEY_LIST_ID, listId);
                    HomeActivity.this.startActivity(intent);

                }

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG,"onResume");

        allListRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Log.e(TAG,"onChildAdded - "+dataSnapshot.child(Constants.FIREBASE_PROPERTY_LIST_NAME).getValue());

                if((boolean)dataSnapshot.child(Constants.FIREBASE_PROPERTY_NEWLYCREATED).getValue())
                {

                    dataSnapshot.getRef().child(Constants.FIREBASE_PROPERTY_NEWLYCREATED).setValue(false);
                    NotificationManagerInternal notificationManagerInternal = NotificationManagerInternal.getInstance();
                    notificationManagerInternal.sendNotification(HomeActivity.this,"New List '"+dataSnapshot.child(Constants.FIREBASE_PROPERTY_LIST_NAME).getValue()+"'created");

                    DataSync.getInstance().updateAllListToWear(HomeActivity.this);
                }




            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                Log.e(TAG,"onChildChanged - "+dataSnapshot.child(Constants.FIREBASE_PROPERTY_LIST_NAME).getValue());




            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

                Log.e(TAG,"onChildRemoved - "+dataSnapshot.child(Constants.FIREBASE_PROPERTY_LIST_NAME).getValue());

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                Log.e(TAG,"onChildMoved - "+dataSnapshot.child(Constants.FIREBASE_PROPERTY_LIST_NAME).getValue());


            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

                Log.e(TAG,"onCancelled ");

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAllListAdapter.cleanup();
    }

    public void addNewList() {

        DialogFragment dialogFragment = AddNewListDialog.newInstance();
        dialogFragment.show(HomeActivity.this.getFragmentManager(),"AddNewListDialog");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.home, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_subscribe) {
            // Handle the camera action
        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_logout) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
