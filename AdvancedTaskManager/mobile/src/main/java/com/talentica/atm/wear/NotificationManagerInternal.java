package com.talentica.atm.wear;

import com.talentica.atm.ui.HomeActivity;

import android.app.Activity;
import android.app.PendingIntent;

import android.content.Intent;
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
// Build intent for notification content
        Intent viewIntent = new Intent(activity, HomeActivity.class);
        PendingIntent viewPendingIntent =
                PendingIntent.getActivity(activity, 0, viewIntent, 0);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(activity)
                        .setSmallIcon(android.R.drawable.ic_dialog_info)
                        .setContentTitle("Tasko")
                        .setContentText(message)
                        .setContentIntent(viewPendingIntent)
                        .setGroup(NOTIFICATION_GROUP_KEY);

// Get an instance of the NotificationManager service
        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(activity);

// Build the notification and issues it with notification manager.
        notificationManager.notify(notificationId, notificationBuilder.build());

    }


}
