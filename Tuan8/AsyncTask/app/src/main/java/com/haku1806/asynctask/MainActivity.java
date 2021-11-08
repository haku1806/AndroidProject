package com.haku1806.asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView txt_percent;
    EditText txt_number;
    Button btn_load;
    ProgressBar progressBar;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number = Integer.parseInt(txt_number.getText().toString());
                ButtonTask buttonTask = new ButtonTask();
                buttonTask.execute(number);
            }
        });
    }

    private void addControls() {
        txt_percent = findViewById(R.id.txt_percent);
        txt_number = findViewById(R.id.txt_number);
        btn_load = findViewById(R.id.btn_load);
        progressBar = findViewById(R.id.progressBar);
        linearLayout = findViewById(R.id.linerLayout);
    }

    class ButtonTask extends AsyncTask<Integer, Integer, Void> {
        @Override
        protected void onPreExecute() {
            linearLayout.removeAllViews();
            progressBar.setProgress(0);
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            int n = integers[0];
            Random r = new Random();
            for (int i = 0; i < n; i++) {
                int percent = i*100/n;
                int value = r.nextInt(999-100)+100;
                publishProgress(percent, value);
                SystemClock.sleep(100);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            int value = values[1];
            int percent = values[0];
            txt_percent.setText(percent + "%");
            progressBar.setProgress(percent);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
            );

            Button button = new Button(MainActivity.this);
            button.setLayoutParams(params);
            button.setText(value + "");
            linearLayout.addView(button);
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            progressBar.setProgress(100);
        }
    }
}