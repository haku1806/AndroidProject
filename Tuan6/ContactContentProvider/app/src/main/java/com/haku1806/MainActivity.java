package com.haku1806;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CONTACTS_ASK_PERMISSIONS = 1001;
    private static final int REQUEST_SMS_ASK_PERMISSIONS = 1002;

    Button btnContact, btnSMS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
    }

    private void addEvents() {
        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityContact();
            }
        });
        
        btnSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivitySMS();
            }
        });
    }

    private void openActivitySMS() {
        Intent intent = new Intent(MainActivity.this, Contact.class);
        intent.setClassName("com.haku1806", "com.haku1806.SMS");
        startActivity(intent);
    }

    private void openActivityContact() {
        Intent intent = new Intent(MainActivity.this, SMS.class);
        intent.setClassName("com.haku1806", "com.haku1806.Contact");
        startActivity(intent);
    }

    private void addControls() {
        btnContact = findViewById(R.id.btnContact);
        btnSMS = findViewById(R.id.btnSMS);
    }
}