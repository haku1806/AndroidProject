package com.haku1806.webjson.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.haku1806.webjson.R;
import com.haku1806.webjson.model.Coin;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;

public class AdapterCoin extends ArrayAdapter<Coin> {

    Activity context;
    int resource;
    List<Coin> objects;

    public AdapterCoin(@NonNull Activity context, int resource, @NonNull List<Coin> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View row, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = this.context.getLayoutInflater();
        row = layoutInflater.inflate(this.resource,null);
        ImageView image = row.findViewById(R.id.image);
        TextView _name = row.findViewById(R.id.txt_name);
        TextView _rank = row.findViewById(R.id.txt_rank);
        TextView _currentPrice = row.findViewById(R.id.txt_current_price);
        TextView _priceChange24h = row.findViewById(R.id.txt_price_change_percentage_24h);
        TextView _marketCap = row.findViewById(R.id.txt_market_cap);
        TextView _totalVolumn = row.findViewById(R.id.txt_total_volume);

        DecimalFormat df = new DecimalFormat("#.##");

        Coin coin = this.objects.get(position);

        image.setImageBitmap(coin.getBitmap_image());
        _name.setText(coin.getName() + " (" + coin.getSymbol().toUpperCase() + ")");
        _rank.setText("#" + String.valueOf(coin.getMarket_cap_rank()));
        _currentPrice.setText(String.valueOf(coin.getCurrent_price()));
        _priceChange24h.setText(df.format(coin.getPrice_change_percentage_24h()) + "%");
        if (coin.getPrice_change_percentage_24h() > 0.0) {
            _priceChange24h.setTextColor(Color.parseColor("#16c784"));
        } else {
            _priceChange24h.setTextColor(Color.parseColor("#e9454f"));
        }
        _marketCap.setText(String.format("$%,.0f", coin.getMarket_cap()));
        _totalVolumn.setText(String.format("$%,.0f", coin.getTotal_volume()));

        return row;
    }
}
