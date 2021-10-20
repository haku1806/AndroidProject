package com.haku1806.karaoke.model;

import java.io.Serializable;

public class Song implements Serializable {
    private String id;
    private String name;
    private String singer;
    private boolean state;

    public Song() {

    }

    public Song(String id, String name, String singer, boolean state) {
        this.id = id;
        this.name = name;
        this.singer = singer;
        this.state = state;
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

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
