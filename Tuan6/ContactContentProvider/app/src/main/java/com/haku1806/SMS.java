package com.haku1806;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.Telephony;
import android.widget.Toast;

import com.haku1806.Adapter.ContractAdapter;
import com.haku1806.Adapter.SMSAdapter;
import com.haku1806.Model.ContactModel;
import com.haku1806.Model.SMSModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class SMS extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<SMSModel> arrayList = new ArrayList<SMSModel>();
    SMSAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        addControls();
        checkPermission();
    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(SMS.this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(SMS.this, new String[]{Manifest.permission.READ_SMS}, 100);
        } else {
            getSMSList();
        }
    }

    @SuppressLint("Range")
    private void getSMSList() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

//        Uri uri = Uri.parse("content://sms/inbox");

        Cursor cursor = getContentResolver().query(
                Telephony.Sms.CONTENT_URI, null, null, null, null
        );

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String smsDate = cursor.getString(cursor.getColumnIndex(
                        Telephony.Sms.DATE
                ));
                String number = cursor.getString(cursor.getColumnIndex(
                        Telephony.Sms.ADDRESS
                ));

                String body = cursor.getString(cursor.getColumnIndex(
                        Telephony.Sms.BODY
                ));

                SMSModel model = new SMSModel();
                model.setNumber(number);
                model.setBody(body);
                model.setTime(sdf.format(Long.parseLong(smsDate)));
                arrayList.add(model);


            }
            cursor.close();
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new SMSAdapter(this, arrayList);
            recyclerView.setAdapter(adapter);
        }
    }

    private void addControls() {
        recyclerView = findViewById(R.id.recycler_view_sms);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getSMSList();
        } else {
            Toast.makeText(SMS.this, "Permission Denied", Toast.LENGTH_SHORT).show();
            checkPermission();
        }
    }
}