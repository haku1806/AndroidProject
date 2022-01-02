package com.haku1806.model;

import com.google.gson.annotations.SerializedName;

public class Total {
    @SerializedName("internal")
    private Info infoInternal;

    @SerializedName("world")
    private Info infoWorld;

    public Info getInfoInternal() {
        return infoInternal;
    }

    public void setInfoInternal(Info infoInternal) {
        this.infoInternal = infoInternal;
    }

    public Info getInfoWorld() {
        return infoWorld;
    }

    public void setInfoWorld(Info infoWorld) {
        this.infoWorld = infoWorld;
    }
}
