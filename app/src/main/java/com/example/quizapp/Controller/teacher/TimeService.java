package com.example.quizapp.Controller.teacher;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.quizapp.R;
import com.example.quizapp.model.retrofit.APICONTROLLER;
import com.example.quizapp.model.retrofit.PostRetrofitModel;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimeService extends Service {

    private static final int NOTIFICATION_ID = 1;
    private static final String CHANNEL_ID = "TimerServiceChannel";

    private CountDownTimer countDownTimer;

    @Override
    public void onCreate() {
        super.onCreate();

    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        long totalTimeInMillis = intent.getLongExtra("totalTimeInMillis", 0 );
        String r_code = intent.getStringExtra("r_code");

        countDownTimer = new CountDownTimer(totalTimeInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Handle timer tick
                updateNotification(formatTime(millisUntilFinished));

            }

            @Override
            public void onFinish() {
                // Handle timer finish
                updateNotification(formatTime(0));
                Toast.makeText(TimeService.this, "Time Up!", Toast.LENGTH_SHORT).show();
                examEnd(r_code);

                stopSelf();
            }
        };

        countDownTimer.start();

        // Create and display a foreground notification
        createNotificationChannel();
        startForeground(NOTIFICATION_ID, createNotification("00:00"));

        return START_STICKY;
    }

    private void examEnd(String r_code) {
        Call<PostRetrofitModel> stopExam = APICONTROLLER.getInstance().getAPI().exam(r_code, "0");
        stopExam.enqueue(new Callback<PostRetrofitModel>() {
            @Override
            public void onResponse(Call<PostRetrofitModel> call, Response<PostRetrofitModel> response) {

            }

            @Override
            public void onFailure(Call<PostRetrofitModel> call, Throwable t) {

            }
        });

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Timer Service Channel",
                    NotificationManager.IMPORTANCE_LOW
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            if (manager != null) {
                manager.createNotificationChannel(channel);
            }
        }
    }

    private Notification createNotification(String content) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Exam Going on...")
                .setContentText(content)
                .setSmallIcon(R.drawable.ic_action_play)
                .setPriority(NotificationCompat.PRIORITY_LOW);

        return builder.build();
    }

    private void updateNotification(String content) {
        Notification notification = createNotification(content);
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        managerCompat.notify(NOTIFICATION_ID, notification);
    }

    private String formatTime(long millis) {
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(millis) % 60;
        return String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
    }




}
