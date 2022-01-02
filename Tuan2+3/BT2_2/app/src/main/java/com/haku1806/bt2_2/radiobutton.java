package com.haku1806.bt2_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class radiobutton extends AppCompatActivity {

    Button btnSubmit, btnNext;
    TextView txtResult;
    RadioButton rdoChicken, rdoEgg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radiobutton);
        addControls();
        addEvents();
    }

    private void addControls() {
        btnNext = findViewById(R.id.btnNextRdo);
        btnSubmit = findViewById(R.id.btnSubmit);
        rdoChicken = findViewById(R.id.rdoChicken);
        rdoEgg = findViewById(R.id.rdoEgg);
        txtResult = findViewById(R.id.txtResult);
    }

    private void addEvents() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String result = "";
                if (rdoChicken.isChecked()) {
                    result = rdoChicken.getText().toString();
                }
                if (rdoEgg.isChecked()) {
                    result = rdoEgg.getText().toString();
                }

                txtResult.setText(result + " có trước");
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(radiobutton.this, imageview.class);
                startActivity(intent);
            }
        });
    }



}