package com.haku1806.karaoke;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import com.haku1806.karaoke.adapter.AdapterSong;
import com.haku1806.karaoke.model.Song;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TabHost tabHost;

    ListView lstViewSong, lstViewFavouriteSong;
    AdapterSong adapterSong, adapterFavouriteSong;
    ArrayList<Song> lstSong , lstFavouriteSong;

    // Database
    String DB_NAME = "Arirang.sqlite";
    private String DB_PATH = "/databases/";
    SQLiteDatabase database = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        processCopyDatabase2Assets();
        addControls();
        addEvents();
        showAllKaraokeList();
    }

    private void addEvents() {
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                if (s.equalsIgnoreCase("tab2")) {
                    updateFavouriteList();
                }
            }
        });
    }

    private void addControls() {
        lstSong = new ArrayList<>();
        lstFavouriteSong = new ArrayList<>();
        tabHost = findViewById(R.id.tabhost);
        tabHost.setup();
        createTab();
        createListView();
    }

    private void createTab() {
        // Tab
        TabHost.TabSpec tabSpec1;
        tabSpec1 = tabHost.newTabSpec("tab1");
        tabSpec1.setContent(R.id.tab1);
        tabSpec1.setIndicator("Songs");
        tabHost.addTab(tabSpec1);

        // Tab Favourite
        TabHost.TabSpec tabSpec2;
        tabSpec2 = tabHost.newTabSpec("tab2");
        tabSpec2.setContent(R.id.tab2);
        tabSpec2.setIndicator("Favoutite");
        tabHost.addTab(tabSpec2);
    }

    private void showAllKaraokeList() {
        // Open database
        database = openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
        // Raw query
        Cursor cursor = database.query("ArirangSongList", null, null, null, null, null, null);
        // Clear data arrList
        lstSong.clear();

        while (cursor.moveToNext()) {
            String id = cursor.getString(0);
            String name = cursor.getString(1);
            String lyric = cursor.getString(2);
            String singer = cursor.getString(3);
            String category = cursor.getString(4);
            int state = cursor.getInt(5);
            int stt = cursor.getInt(6);

            lstSong.add(new Song(id, name, lyric, singer, category, state, stt));
        }
        cursor.close();
        adapterSong.notifyDataSetChanged();
    }

    private void createListView() {
        lstViewSong = findViewById(R.id.lstViewSong);
        lstViewFavouriteSong = findViewById(R.id.lstViewFavouriteSong);

        adapterSong = new AdapterSong(
                MainActivity.this, R.layout.song, lstSong
        );
        lstViewSong.setAdapter(adapterSong);

        adapterFavouriteSong = new AdapterSong(
                MainActivity.this, R.layout.song, lstFavouriteSong
        );
        lstViewFavouriteSong.setAdapter(adapterFavouriteSong);
    }

    public void updateFavouriteList() {
        lstFavouriteSong.clear();
        for (Song song : lstSong) {
            if (song.getState() == 1) {
                lstFavouriteSong.add(song);
            }
        }
        adapterFavouriteSong.notifyDataSetChanged();
        adapterSong.notifyDataSetChanged();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Karaoke");
        return super.onCreateOptionsMenu(menu);
    }

    private void processCopyDatabase2Assets() {
        File dbFile = getDatabasePath(DB_NAME);
        // Check if exists
        if (!dbFile.exists()) {
            copyDatabase();
            Toast.makeText(this, "Dữ liệu được sao chép thành công", Toast.LENGTH_SHORT).show();
        } else {
            dbFile.delete();
            copyDatabase();
        }
    }

    private void copyDatabase() {
        try {
            InputStream myInput = getAssets().open(DB_NAME);
            String outFileName = getApplicationInfo().dataDir + DB_PATH + DB_NAME;
            File f = new File(getApplicationInfo().dataDir + DB_PATH);
            if (!f.exists()) {
                f.mkdir();
            }

            OutputStream myOutPut = new FileOutputStream(outFileName);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = myInput.read(buffer)) > 0) {
                myOutPut.write(buffer, 0, len);
            }

            myOutPut.flush();
            myInput.close();
            myOutPut.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.e("Lỗi sao chép:", ex.toString());
        }
    }


//    private void processUpdateFavourite(String id, int state) {
//        ContentValues row = new ContentValues();
//        row.put("YEUTHICH", state);
//        database.update("ArirangSongList", row, "MABH=?", new String[]{id});
//    }

//    private void processResetFavourite() {
//        ContentValues row = new ContentValues();
//        row.put("YEUTHICH", 0);
//        database.update("ArirangSongList", row, "0=?", new String[]{"0"});
//        database.execSQL("Update ArirangSongList SET YEUTHICH = 0");
//    }

//    private void updateStateSongListDb() {
//        processResetFavourite();
//        for (Song song : lstSong) {
//            if (song.getState() == 1) {
//                processUpdateFavourite(song.getId(), 1);
//            }
//        }
//    }
}