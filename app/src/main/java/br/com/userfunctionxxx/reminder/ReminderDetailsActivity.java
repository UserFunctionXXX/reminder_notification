package br.com.userfunctionxxx.reminder;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.io.Serializable;

public class ReminderDetailsActivity extends AppCompatActivity implements Serializable {
    private TextView reminderTitle;
    private TextView reminderDateTime;
    private CheckBox reminderDone;

    private ReminderRepository repository = ReminderRepository.getInstance();
    private NotificationManagerCompat notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Server","onCreate");
        setContentView(R.layout.activity_main);
        reminderTitle = findViewById(R.id.reminder_title);
        reminderDateTime = findViewById(R.id.reminder_datetime);
        reminderDone = findViewById(R.id.reminder_done);

        notificationManager = NotificationManagerCompat.from(this);

        Bundle bundle = getIntent().getExtras();

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras != null){
                Reminder reminder = (Reminder) extras.getSerializable("reminder");
                if (reminder != null){
                    reminderTitle.setText(reminder.getTitle());
                    reminderDateTime.setText(reminder.getWhen().toString());
                    reminderDone.setChecked(true);
                }
            }
        } else {
            Reminder reminder = (Reminder) bundle.getSerializable("reminder");
            if (reminder != null){
                reminderTitle.setText(reminder.getTitle());
                reminderDateTime.setText(reminder.getWhen().toString());
                reminderDone.setChecked(true);
            }

        }

        // SOLUTION
        onStartService();


    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("Server","onNewIntent");

        //Handle intent here...
    }


    // Starts the IntentService
    public void onStartService() {
        Intent i = new Intent(ReminderDetailsActivity.this, ReminderNotificationService.class);
        i.putExtra("foo", "bar");
        startService(i);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("Server","onActivityResult");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Server","onDestroy");
        notificationManager.cancelAll();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Server","onStop");
    }
}
