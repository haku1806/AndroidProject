package com.haku1806.asynctask_loadimageonline;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button button;
    ImageView img;

    ArrayList<String> list_img = new ArrayList<>();
    ProgressDialog progressDialog;

    Random r = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = r.nextInt(7);

                ImageTask imageTask = new ImageTask();
                imageTask.execute(list_img.get(num));
            }
        });
    }

    private void addControls() {
        button = findViewById(R.id.button);
        img = findViewById(R.id.img);

        list_img.add("https://64.media.tumblr.com/747c69e72a8ab512eb6ccd3aa241c39e/61b7ed2ea8c522a2-3d/s1280x1920/8566f0fedc75bc18219916ac90ad0eafeeaee21b.jpg");
        list_img.add("https://64.media.tumblr.com/5dad3379f042cf0b8b314ef6a10602c9/f33c24ed2ae7aebf-07/s1280x1920/2faa6e4f7b825a1ca7c9914e62893454e75d8db0.jpg");
        list_img.add("https://64.media.tumblr.com/1ce11f1704c10f3a251b172e66f3b33f/e953929053ba4bf6-5a/s1280x1920/9414019b3deab2d8e54271679ea49e3036841742.jpg");
        list_img.add("https://64.media.tumblr.com/cbf6ba9c3d33881bf0d4574482e57f3a/tumblr_pujoq942GF1w89qpgo1_640.jpg");
        list_img.add("https://64.media.tumblr.com/2284fb738e27a747fbfb4f5e4942a515/4dc7965a963d36e2-f3/s640x960/a4446325a3406b8d6479827aa11c268d995e9b72.jpg");
        list_img.add("https://64.media.tumblr.com/5082bbe3b75a4eb6216b97d731258710/3edb0d6e3cfe803c-48/s1280x1920/0e7079bf7dcf6eee3c9efb15bd62812b861c2d31.jpg");
        list_img.add("https://64.media.tumblr.com/5092194a42ca82cb834659c3d9c05073/0e370ee0a618d556-7e/s1280x1920/475995f6b9b1daa626545fa85e854d94ce404322.jpg");

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Thông báo");
        progressDialog.setMessage("Đang tải hình ảnh");
        
        progressDialog.setCanceledOnTouchOutside(false);
    }

    class ImageTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            String link = strings[0];
            try {
                Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(link).getContent());
                return bitmap;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            img.setImageBitmap(bitmap);
            progressDialog.dismiss();
        }
    }
}