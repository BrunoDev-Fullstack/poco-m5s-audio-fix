package br.com.poco.audiofix;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);

        TextView text = new TextView(this);
        text.setTextSize(18);
        text.setPadding(40, 80, 40, 40);
        text.setText(
                "POCO Audio Fix\n\n" +
                "Servico de audio ativo em segundo plano.\n\n" +
                "Speaker inferior selecionado."
        );

        setContentView(text);

        Intent service =
                new Intent(this, AudioFixService.class);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(service);
        } else {
            startService(service);
        }
    }
}