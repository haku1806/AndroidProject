package com.haku1806.model;

import java.util.ArrayList;

public class ModelCommom {
    private ArrayList<Location> locations = new ArrayList<>();
    private ArrayList<OverviewInfo> overview = new ArrayList<>();

    private Today today;
    private Total total;

    public ArrayList<Location> getLocations() {
        return locations;
    }

    public void setLocations(ArrayList<Location> locations) {
        this.locations = locations;
    }

    public ArrayList<OverviewInfo> getOverview() {
        return overview;
    }

    public void setOverview(ArrayList<OverviewInfo> overview) {
        this.overview = overview;
    }

    public Today getToday() {
        return today;
    }

    public void setToday(Today today) {
        this.today = today;
    }

    public Total getTotal() {
        return total;
    }

    public void setTotal(Total total) {
        this.total = total;
    }
}
