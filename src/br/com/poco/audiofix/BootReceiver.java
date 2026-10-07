package br.com.poco.audiofix;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

public class BootReceiver extends BroadcastReceiver {

    private static final String TAG = "POCOAudioFix";

    @Override
    public void onReceive(
            Context context,
            Intent intent) {

        if (Intent.ACTION_BOOT_COMPLETED.equals(
                intent.getAction())) {

            Log.i(
                    TAG,
                    "BOOT_COMPLETED recebido"
            );

            Intent service =
                    new Intent(
                            context,
                            AudioFixService.class
                    );

            if (Build.VERSION.SDK_INT >=
                    Build.VERSION_CODES.O) {

                context.startForegroundService(service);

            } else {

                context.startService(service);
            }
        }
    }
}