package com.haku1806.model;

public class OverviewInfo {
    private String date;
    private int death;
    private int treating;
    private int cases;
    private int revocered;
    private int avgCases7day;
    private int avgRecovered7day;
    private int avgDeath7day;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDeath() {
        return death;
    }

    public void setDeath(int death) {
        this.death = death;
    }

    public int getTreating() {
        return treating;
    }

    public void setTreating(int treating) {
        this.treating = treating;
    }

    public int getCases() {
        return cases;
    }

    public void setCases(int cases) {
        this.cases = cases;
    }

    public int getRevocered() {
        return revocered;
    }

    public void setRevocered(int revocered) {
        this.revocered = revocered;
    }

    public int getAvgCases7day() {
        return avgCases7day;
    }

    public void setAvgCases7day(int avgCases7day) {
        this.avgCases7day = avgCases7day;
    }

    public int getAvgRecovered7day() {
        return avgRecovered7day;
    }

    public void setAvgRecovered7day(int avgRecovered7day) {
        this.avgRecovered7day = avgRecovered7day;
    }

    public int getAvgDeath7day() {
        return avgDeath7day;
    }

    public void setAvgDeath7day(int avgDeath7day) {
        this.avgDeath7day = avgDeath7day;
    }
}