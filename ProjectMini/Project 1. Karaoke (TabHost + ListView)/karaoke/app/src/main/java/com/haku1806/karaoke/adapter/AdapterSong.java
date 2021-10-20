package com.haku1806.karaoke.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.haku1806.karaoke.MainActivity;
import com.haku1806.karaoke.R;
import com.haku1806.karaoke.model.Song;

import org.w3c.dom.Text;

import java.util.List;

public class AdapterSong extends ArrayAdapter<Song> {
    Activity context;
    int resource;
    List<Song> objects;

    public AdapterSong(@NonNull Activity context, int resource, @NonNull List<Song> objects) {
        super(context, resource, objects);
        this.context =context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View row, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = this.context.getLayoutInflater();
        row = layoutInflater.inflate(this.resource, null);

        TextView _SongId = row.findViewById(R.id.txtSongId);
        TextView _SongName = row.findViewById(R.id.txtSongName);
        TextView _SongSinger = row.findViewById(R.id.txtSongSinger);
        ImageButton _ibtnHeart = row.findViewById(R.id.ibtnHeart);
        ImageButton _ibtnPlay = row.findViewById(R.id.ibtnPlay);

        Song song = this.objects.get(position);
        _SongId.setText(song.getId());
        _SongName.setText(song.getName());
        _SongSinger.setText(song.getSinger());

        // Check heart | unheart
        if (song.isState()) {
            _ibtnHeart.setImageResource(R.drawable.heart_30px);
        } else {
            _ibtnHeart.setImageResource(R.drawable.unheart_30px);
        }

        _ibtnHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (song.isState()) {
                    _ibtnHeart.setImageResource(R.drawable.unheart_30px);
                    song.setState(false);
                    Toast.makeText(context, "Đã xóa " + song.getName() + "khỏi danh sách yêu thích!", Toast.LENGTH_SHORT).show();
                } else {
                    _ibtnHeart.setImageResource(R.drawable.heart_30px);
                    song.setState(true);
                    Toast.makeText(context, "Đã thêm " + song.getName() + "vào danh sách yêu thích!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        _ibtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Tính năng hiện đang được phát triển!", Toast.LENGTH_SHORT).show();
            }
        });

        return row;
    }
}
