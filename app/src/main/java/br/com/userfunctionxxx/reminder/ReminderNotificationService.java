package br.com.userfunctionxxx.reminder;

import android.app.IntentService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.logging.Logger;

public class ReminderNotificationService extends IntentService {
    private static final String TAG = "serviceReminder";
    private static final String CHANNEL_ID = "000025";
    private NotificationManagerCompat notificationManager;

    public ReminderNotificationService() {
        super(ReminderNotificationService.class.getSimpleName());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Server", "onCreate>>>handlingIntent()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, startId, startId);

        return START_STICKY;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("Server", "start>>>handlingIntent()");

        createNotification(4);

        Log.d("Server", "end>>>handlingIntent()");
    }

    protected void createNotification(int idNotification) {
        Intent intentActivity = new Intent(this, ReminderDetailsActivity.class);
        intentActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        //intentActivity.putExtra("item_id","Message Notification");
        Reminder reminder = new Reminder(3, "Comprar carne churrasco", 51511);
        reminder.setDone(true);
        intentActivity.putExtra("reminder", reminder);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 20, intentActivity, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_notification_overlay)
                .setContentTitle("LEMBRETE")
                .setContentText(reminder.getTitle())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        notificationManager = NotificationManagerCompat.from(this);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(idNotification, builder.build());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
