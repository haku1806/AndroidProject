package com.haku1806.karaoke.model;

import java.io.Serializable;

public class Song implements Serializable {
    private String id;
    private String name;
    private String lyric;
    private String singer;
    private String category;
    private int state;
    private int stt;

    public Song() {
    }

    public Song(String id, String name, String lyric, String singer, String category, int state, int stt) {
        this.id = id;
        this.name = name;
        this.lyric = lyric;
        this.singer = singer;
        this.category = category;
        this.state = state;
        this.stt = stt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }
}
