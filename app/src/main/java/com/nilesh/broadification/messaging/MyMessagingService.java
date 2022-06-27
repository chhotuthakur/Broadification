package com.nilesh.broadification.messaging;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.nilesh.broadification.MainActivity;
import com.nilesh.broadification.R;

import java.util.Random;

public class MyMessagingService extends FirebaseMessagingService {


    private final String CHANNEL_ID="channel_id";


    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);
        Intent ione= new Intent(this,MainActivity.class);

        NotificationManager manager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        int notificationId = new Random().nextInt();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(manager);
        }

        ione.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent itwo = PendingIntent.getActivities(this, 0, new Intent[]{ione}, PendingIntent.FLAG_ONE_SHOT);



        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle(message.getData().get("title"))
                .setContentText(message.getData().get("message"))
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setAutoCancel(true)
                .setContentIntent(itwo)
                .build();

        manager.notify(notificationId, notification);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannel(NotificationManager manager) {

        NotificationChannel notificationChannel= new NotificationChannel(CHANNEL_ID,"channelName",NotificationManager.IMPORTANCE_HIGH);
        notificationChannel.setDescription("my desc");
        notificationChannel.enableLights(true);
        notificationChannel.setLightColor(Color.WHITE);
        manager.createNotificationChannel(notificationChannel);
    }


}