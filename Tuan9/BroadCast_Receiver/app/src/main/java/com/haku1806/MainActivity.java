package com.haku1806;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Thông báo Internet");
        button = findViewById(R.id.button);
    }

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(
                    context.CONNECTIVITY_SERVICE
            );
            if (connectivityManager.getActiveNetwork() != null) {
                // Co wifi
                if (button != null) {
                    button.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(), "Có Internet", Toast.LENGTH_SHORT).show();
                }
            } else {
                // Khong co wifi
                if (button != null) {
                    button.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(), "Không có Internet", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    // Dang ky BroadcastReceiver
    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(receiver, intentFilter);
    }

    // Huy dang ky BroadcastReceiver

    @Override
    protected void onPause() {
        super.onPause();
        if (receiver != null) {
            unregisterReceiver(receiver);
        }
    }
}