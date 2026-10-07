package br.com.poco.audiofix;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

public class AudioFixService extends Service {

    private static final String TAG = "POCOAudioFix";
    private static final String CHANNEL_ID = "poco_audio_fix";

    private static final String FIX =
            "play_select_spk=right-spk-factory";

    private AudioManager audioManager;

    @Override
    public void onCreate() {
        super.onCreate();

        Log.i(TAG, "AudioFixService iniciado");

        audioManager =
                (AudioManager) getSystemService(AUDIO_SERVICE);

        createNotificationChannel();

        Notification.Builder builder;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder =
                    new Notification.Builder(this, CHANNEL_ID);
        } else {
            builder =
                    new Notification.Builder(this);
        }

        Notification notification =
                builder
                        .setContentTitle("POCO Audio Fix")
                        .setContentText("Speaker inferior ativo")
                        .setSmallIcon(
                                android.R.drawable.ic_lock_silent_mode_off
                        )
                        .setOngoing(true)
                        .build();

        startForeground(1001, notification);

        applyFix();
    }

    private void applyFix() {

        try {

            if (audioManager == null) {
                audioManager =
                        (AudioManager)
                                getSystemService(AUDIO_SERVICE);
            }

            audioManager.setParameters(FIX);

            String result =
                    audioManager.getParameters(
                            "play_select_spk"
                    );

            Log.i(
                    TAG,
                    "FIX aplicado: " +
                    FIX +
                    " | retorno=" +
                    result
            );

        } catch (Throwable e) {

            Log.e(
                    TAG,
                    "Erro ao aplicar FIX",
                    e
            );
        }
    }

    @Override
    public int onStartCommand(
            Intent intent,
            int flags,
            int startId) {

        Log.i(TAG, "onStartCommand");

        applyFix();

        return START_STICKY;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {

        Log.i(
                TAG,
                "App removido dos recentes; servico permanece"
        );

        applyFix();

        super.onTaskRemoved(rootIntent);
    }

    private void createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel =
                    new NotificationChannel(
                            CHANNEL_ID,
                            "POCO Audio Fix",
                            NotificationManager.IMPORTANCE_LOW
                    );

            channel.setDescription(
                    "Mantem o speaker inferior funcionando"
            );

            NotificationManager manager =
                    getSystemService(
                            NotificationManager.class
                    );

            manager.createNotificationChannel(channel);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}