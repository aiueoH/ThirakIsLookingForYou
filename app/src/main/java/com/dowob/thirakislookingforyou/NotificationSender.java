package com.dowob.thirakislookingforyou;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

/**
 * Created by Wei on 2016/7/14.
 */
public class NotificationSender {
    public static final int REQUEST_CODE = 0;

    private static int notificationId = 0;

    public static void send(Context context, String title, String text) {
        send(context, title, text, MainActivity.class);
    }

    public static void send(Context context, String title, String text, Class intentClass) {
        int requestCode = REQUEST_CODE;
        int notificationId = NotificationSender.notificationId++;
        Intent intent = new Intent(context, intentClass);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                context,
                requestCode,
                intent,
                PendingIntent.FLAG_ONE_SHOT
        );
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context);
        notificationBuilder
                .setSmallIcon(R.drawable.ic_stat_name)
                .setContentTitle(title)
                .setContentText(text)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationId, notificationBuilder.build());
    }
}
