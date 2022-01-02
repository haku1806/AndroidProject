package com.haku1806.karaoke;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class LyricActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyric);

        TextView name = findViewById(R.id.txtSongName_lyric);
        TextView lyric = findViewById(R.id.lyric);

        Bundle extras = getIntent().getExtras();

        String varName = extras.getString("name");
        String varLyric = extras.getString("lyric");

        name.setText(varName);
        lyric.setText(varLyric);
    }
}