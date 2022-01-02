package com.haku1806.model;

public class Location {
    private String name;
    private int death;
    private int treating;
    private int cases;
    private int recovered;
    private int casestoday;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    public int getCasestoday() {
        return casestoday;
    }

    public void setCasestoday(int casestoday) {
        this.casestoday = casestoday;
    }
}
