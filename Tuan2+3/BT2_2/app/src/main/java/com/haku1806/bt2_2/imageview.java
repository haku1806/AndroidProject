package com.haku1806.bt2_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;

public class imageview extends AppCompatActivity {

    RadioButton rdo1, rdo2, rdo3;
    ImageView imgView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageview);
        addControls();
        addEvents();
    }

    private void addControls() {
        rdo1 = findViewById(R.id.rdo1);
        rdo2 = findViewById(R.id.rdo2);
        rdo3 = findViewById(R.id.rdo3);
        imgView = findViewById(R.id.imgView);
    }

    private void addEvents() {
        rdo1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    imgView.setImageResource(R.drawable.gaixinh1);
                }
            }
        });

        rdo2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    imgView.setImageResource(R.drawable.gaixinh2);
                }
            }
        });

        rdo3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    imgView.setImageResource(R.drawable.gaixinh3);
                }
            }
        });

        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}