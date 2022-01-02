package com.haku1806.webjson.model;

import android.graphics.Bitmap;

public class Coin {
    private String id;
    private String symbol;
    private String name;
    private String image;
    private Bitmap bitmap_image;
    private double current_price;
    private double market_cap;
    private int market_cap_rank;
    private double total_volume;
    private double price_change_percentage_24h;

    public Coin() {
    }

    public Coin(String id, String symbol, String name, String image, Bitmap bitmap_image, double current_price, double market_cap, int market_cap_rank, double total_volume, double price_change_percentage_24h) {
        this.id = id;
        this.symbol = symbol;
        this.name = name;
        this.image = image;
        this.bitmap_image = bitmap_image;
        this.current_price = current_price;
        this.market_cap = market_cap;
        this.market_cap_rank = market_cap_rank;
        this.total_volume = total_volume;
        this.price_change_percentage_24h = price_change_percentage_24h;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Bitmap getBitmap_image() {
        return bitmap_image;
    }

    public void setBitmap_image(Bitmap bitmap_image) {
        this.bitmap_image = bitmap_image;
    }

    public double getCurrent_price() {
        return current_price;
    }

    public void setCurrent_price(double current_price) {
        this.current_price = current_price;
    }

    public double getMarket_cap() {
        return market_cap;
    }

    public void setMarket_cap(double market_cap) {
        this.market_cap = market_cap;
    }

    public int getMarket_cap_rank() {
        return market_cap_rank;
    }

    public void setMarket_cap_rank(int market_cap_rank) {
        this.market_cap_rank = market_cap_rank;
    }

    public double getTotal_volume() {
        return total_volume;
    }

    public void setTotal_volume(double total_volume) {
        this.total_volume = total_volume;
    }

    public double getPrice_change_percentage_24h() {
        return price_change_percentage_24h;
    }

    public void setPrice_change_percentage_24h(double price_change_percentage_24h) {
        this.price_change_percentage_24h = price_change_percentage_24h;
    }
}


