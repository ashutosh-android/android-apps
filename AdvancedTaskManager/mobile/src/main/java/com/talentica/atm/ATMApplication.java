package com.talentica.atm;

import com.firebase.client.Firebase;

import android.app.Application;

/**
 * Created by ashutosht on 23/03/16.
 */
public class ATMApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
