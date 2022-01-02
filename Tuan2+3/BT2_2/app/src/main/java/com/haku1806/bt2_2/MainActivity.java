package com.haku1806.bt2_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Khai bao
    Button btnSubmit, btnNext;
    TextView txtCboResult;
    CheckBox cboChicken, cboEgg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    // Create Controls
    private void addControls() {
        btnNext = findViewById(R.id.btnNext);
        btnSubmit = findViewById(R.id.btnSubmit);
        txtCboResult = findViewById(R.id.txtCboResult);
        cboChicken = findViewById(R.id.cboChicken);
        cboEgg = findViewById(R.id.cboEgg);
    }

    // Create Events
    private void addEvents() {
        View.OnClickListener checkboxListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == cboChicken) {
                    cboChicken.setChecked(true);
                    cboEgg.setChecked(false);
                } else if (view == cboEgg) {
                    cboEgg.setChecked(true);
                    cboChicken.setChecked(false);
                }
            }
        };
        cboChicken.setOnClickListener(checkboxListener);
        cboEgg.setOnClickListener(checkboxListener);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String result = "có trước!";
                if (cboChicken.isChecked()) {
                    result = cboChicken.getText() + " " + result;
                }
                if (cboEgg.isChecked()) {
                    result = cboEgg.getText() + " " + result;
                }

                txtCboResult.setText(result);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, radiobutton.class);
                startActivity(intent);
            }
        });
    }
}