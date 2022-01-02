package com.haku1806.webjson;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.haku1806.webjson.adapter.AdapterCoin;
import com.haku1806.webjson.model.Coin;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lsv_coin;
    ArrayList<Coin> ds_coin;
    AdapterCoin adapterCoin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Today's Cryptocurrency Prices");
        addControls();
    }

    private void addControls() {
        lsv_coin = findViewById(R.id.lsv_coin);
        ds_coin = new ArrayList<Coin>();
        adapterCoin = new AdapterCoin(
                MainActivity.this, R.layout.item, ds_coin
        );
        lsv_coin.setAdapter(adapterCoin);

        CoinTask contactTask = new CoinTask();
        contactTask.execute();
    }

    class CoinTask extends AsyncTask<Void, Void, ArrayList<Coin>> {


        @Override
        protected ArrayList<Coin> doInBackground(Void... voids) {
            ArrayList<Coin> ds = new ArrayList<>();
            // URL API
            try {
                URL url = new URL("https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc");
                // Request api
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-Type","application/json; charset-utf-8");
                connection.setRequestProperty("User-Agent","Mozilla/5.0 (compatible)");
                connection.setRequestProperty("Accept","*/*");

                // Reader
                InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
                StringBuilder stringBuilder = new StringBuilder();
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = bufferedReader.readLine();
                while (line != null) {
                    stringBuilder.append(line);
                    line = bufferedReader.readLine();
                }
                inputStreamReader.close();
//                JSONObject obj = new JSONObject(stringBuilder.toString());

//                System.out.println(obj.toString());
//                System.out.println();
//                System.out.println(jsonObject.getJSONArray("results").toString());
//                System.out.println(jsonObject.getJSONArray("results").getJSONObject(0).toString());
//                System.out.println(jsonObject.getJSONArray("results").getJSONObject(0).getJSONObject("name").toString());
//                System.out.println(jsonObject.getJSONArray("results").getJSONObject(0).getJSONObject("dob").toString());
//                System.out.println(jsonObject.getJSONArray("results").getJSONObject(0).getJSONObject("location").toString());
//                System.out.println(jsonObject.getJSONArray("results").getJSONObject(0).getJSONObject("location").getString("city").toString());
//                if (jsonObject.has("results")) {
//                    Contact2 contact = new Contact2();
//                    contact.setfName(jsonObject.getJSONArray("results").getJSONObject(0).getJSONObject("name").getString("first"));
//                    contact.setlName(jsonObject.getJSONArray("results").getJSONObject(0).getJSONObject("name").getString("last"));
//                    contact.setAge(jsonObject.getJSONArray("results").getJSONObject(0).getJSONObject("dob").getInt("age"));
//                    contact.setCity(jsonObject.getJSONArray("results").getJSONObject(0).getJSONObject("location").getString("city"));
//                    contact.setCountry(jsonObject.getJSONArray("results").getJSONObject(0).getJSONObject("location").getString("country"));
//                    System.out.println(contact.toString());
//                    ds.add(contact);
//                }



                JSONArray jsonArray = new JSONArray(stringBuilder.toString());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
//                    Contact contact = new Contact();
                    Coin coin = new Coin();
                    if (jsonObject.has("image")) {
                        coin.setImage(jsonObject.getString("image"));
                        url = new URL(coin.getImage());
                        connection = (HttpURLConnection) url.openConnection();
                        connection.setRequestMethod("GET");
                        connection.setRequestProperty("Content-Type","application/json; charset-utf-8");
                        connection.setRequestProperty("User-Agent","Mozilla/5.0 (compatible)");
                        connection.setRequestProperty("Accept","*/*");
                        Bitmap bitmap = BitmapFactory.decodeStream(connection.getInputStream());
                        Bitmap bitmapScaled = Bitmap.createScaledBitmap(bitmap,100,80,true);
                        coin.setBitmap_image(bitmapScaled);
                    }
                    coin.setId(jsonObject.getString("id"));
                    coin.setSymbol(jsonObject.getString("symbol"));
                    coin.setName(jsonObject.getString("name"));
                    coin.setCurrent_price(jsonObject.getDouble("current_price"));
                    coin.setMarket_cap(jsonObject.getDouble("market_cap"));
                    coin.setMarket_cap_rank(jsonObject.getInt("market_cap_rank"));
                    coin.setTotal_volume(jsonObject.getDouble("total_volume"));
                    coin.setPrice_change_percentage_24h(jsonObject.getDouble("price_change_percentage_24h"));
//                    if (jsonObject.has("Name")) {
//                        contact.setName(jsonObject.getString("Name"));
//                    }
//                    if (jsonObject.has("City")) {
//                        contact.setCity(jsonObject.getString("City"));
//                    }
//                    if (jsonObject.has("Country")) {
//                        contact.setCountry(jsonObject.getString("Country"));
//                    }
                    ds.add(coin);
//                    System.out.println(jsonObject.getString("id"));
                }
            } catch (Exception e) {
                Log.e("err: ", String.valueOf(e));
            }
            return ds;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            adapterCoin.clear();
        }

        @Override
        protected void onPostExecute(ArrayList<Coin> Coins) {
            super.onPostExecute(Coins);

            adapterCoin.clear();
            adapterCoin.addAll(Coins);
        }
    }
}