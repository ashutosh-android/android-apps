package com.talentica.atm.wear;

import com.talentica.atm.R;
import com.talentica.atm.ui.HomeActivity;

import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

/**
 * Created by ashutosht on 03/04/16.
 */
public class NotificationManagerInternal {

    private final static String NOTIFICATION_GROUP_KEY = "tasko_notifications";



    private static NotificationManagerInternal ourInstance = new NotificationManagerInternal();

    public static NotificationManagerInternal getInstance() {
        return ourInstance;
    }

    private NotificationManagerInternal() {
    }

    public void sendNotification(Activity activity, String message)
    {


        int notificationId = (int) (Math.random()*100);
        Intent viewIntent = new Intent(activity, HomeActivity.class);
        PendingIntent viewPendingIntent =
                PendingIntent.getActivity(activity, 0, viewIntent, 0);

        Intent playStoreIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.google.android.wearable.app"));
        PendingIntent playStorePendingIntent = PendingIntent.getActivity(activity, 0, playStoreIntent, 0);


        // Simple Notification

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(activity)
                        .setSmallIcon(android.R.drawable.ic_dialog_info)
                        .setContentTitle("Tasko")
                        .setContentText(message)
                        .addAction(android.R.drawable.ic_menu_gallery,"PlayStore",playStorePendingIntent) //-> Additional actions
                        .setContentIntent(viewPendingIntent);

        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(activity);

        notificationManager.notify(notificationId, notificationBuilder.build());


        // With Wearable Features

//        NotificationCompat.BigTextStyle secondPageStyle = new NotificationCompat.BigTextStyle();
//        secondPageStyle.setBigContentTitle("New Item Details")
//                .bigText("Too much info to shared...");
//
//        Notification secondPageNotification =
//                new NotificationCompat.Builder(activity)
//                        .setStyle(secondPageStyle)
//                        .build();
//
//        NotificationCompat.WearableExtender wearableExtender =
//                new NotificationCompat.WearableExtender()
//                        .setHintHideIcon(true)
//                        .setContentIcon(R.drawable.icon_pending)
//                        .setBackground(BitmapFactory.decodeResource(activity.getResources(),R.drawable.wear_notification_bg))
//                        .addAction(new NotificationCompat.Action.Builder(android.R.drawable.ic_menu_gallery,"PlayStore-Wear",playStorePendingIntent).build())
//                        .addPage(secondPageNotification);
//
//        NotificationCompat.Builder notificationBuilder =
//                new NotificationCompat.Builder(activity)
//                        .setSmallIcon(android.R.drawable.ic_dialog_info)
//                        .setContentTitle("Tasko")
//                        .setContentText(message)
//                        .addAction(android.R.drawable.ic_menu_gallery,"PlayStore-Mobile",playStorePendingIntent) //-> Additional actions
//                        .extend(wearableExtender)
//                        .setContentIntent(viewPendingIntent);
//
//        NotificationManagerCompat notificationManager =
//                NotificationManagerCompat.from(activity);
//
//        notificationManager.notify(notificationId, notificationBuilder.build());

        // Stacked Notifications

//        NotificationCompat.Builder notificationBuilder =
//                new NotificationCompat.Builder(activity)
//                        .setSmallIcon(android.R.drawable.ic_dialog_info)
//                        .setContentTitle("Tasko")
//                        .setContentText(message)
//                        .setContentIntent(viewPendingIntent)
//                        .setGroup(NOTIFICATION_GROUP_KEY);
//
//        NotificationManagerCompat notificationManager =
//                NotificationManagerCompat.from(activity);
//
//        notificationManager.notify(notificationId, notificationBuilder.build());

        //Custom Notification



    }


}
