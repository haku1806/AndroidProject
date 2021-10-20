package com.haku1806.karaoke;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TabHost;

import com.haku1806.karaoke.adapter.AdapterSong;
import com.haku1806.karaoke.model.Song;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TabHost tabHost;

    ListView lstViewSong, lstViewFavouriteSong;
    AdapterSong adapterSong, adapterFavouriteSong;
    ArrayList<Song> lstSong, lstFavouriteSong;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
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
        tabHost = findViewById(R.id.tabhost);
        tabHost.setup();
        createTab();
        createListView();
    }

    private void createTab() {
        TabHost.TabSpec tabSpec1;
        tabSpec1 = tabHost.newTabSpec("tab1");
        tabSpec1.setContent(R.id.tab1);
        tabSpec1.setIndicator("Songs");
        tabHost.addTab(tabSpec1);

        TabHost.TabSpec tabSpec2;
        tabSpec2 = tabHost.newTabSpec("tab2");
        tabSpec2.setContent(R.id.tab2);
        tabSpec2.setIndicator("Favoutite");
        tabHost.addTab(tabSpec2);
    }

    private void createListView() {
        lstViewSong = findViewById(R.id.lstViewSong);
        lstViewFavouriteSong = findViewById(R.id.lstViewFavouriteSong);

        lstSong = new ArrayList<>();
        lstFavouriteSong = new ArrayList<>();
        lstSong.add(new Song("000001", "Em không quay về", "Hoàng Tôn", false ));
        lstSong.add(new Song("000002", "Người ấy", "Trịnh Thăng Bình", true ));
        lstSong.add(new Song("000003", "Mưa của ngày xưa", "Hồ Quang Hiếu", false ));
        lstSong.add(new Song("000004", "Tìm em", "Hồ Quang Hiếu", false ));
        lstSong.add(new Song("000005", "Giá như chưa từng quen", "HKT", false ));
        lstSong.add(new Song("000006", "Công chúa bong bóng", "Bảo Thy", false ));
        lstSong.add(new Song("000007", "Love song", "Lương Minh Trang", false ));
        adapterSong = new AdapterSong(
                MainActivity.this, R.layout.song, lstSong
        );
        lstViewSong.setAdapter(adapterSong);
    }

    public void updateFavouriteList() {
        lstFavouriteSong.clear();
        for (Song song : lstSong) {
            if (song.isState()) {
                lstFavouriteSong.add(song);
            }
        }
        adapterFavouriteSong = new AdapterSong(
                MainActivity.this, R.layout.song, lstFavouriteSong
        );
        lstViewFavouriteSong.setAdapter(adapterFavouriteSong);
        adapterFavouriteSong.notifyDataSetChanged();
        adapterSong.notifyDataSetChanged();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Karaoke");
        return super.onCreateOptionsMenu(menu);
    }
}