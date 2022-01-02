package com.haku1806.service;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.haku1806.model.Location;
import com.haku1806.model.ModelCommom;
import com.haku1806.model.OverviewInfo;
import com.haku1806.model.Today;
import com.haku1806.model.Total;
import com.haku1806.model.VolleyResponseListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class VolleyService {
    public static void getRequest(Context context, final VolleyResponseListener listener){
        ModelCommom modelCommom = new ModelCommom();
        String url = ServiceInfo.Base_URL+"data.json";

        // Request thanh cong => lay doi tuong jsonobject trong method onResponse
        // Nguoc lai => thong tin loi trong method onErrorResponse
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                url, new Response.Listener<JSONObject>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(JSONObject response) {
                if (response!=null){
                    JSONObject total = response.optJSONObject("total");
                    JSONObject today = response.optJSONObject("today");
                    JSONArray overview = response.optJSONArray("overview");
                    JSONArray locations = response.optJSONArray("locations");

                    Gson gson = new Gson();
                    JsonParser parser = new JsonParser();
                    JsonElement mJson = parser.parse(String.valueOf(total));
                    Total objectTotal = gson.fromJson(mJson,Total.class);
                    mJson = parser.parse(String.valueOf(total));
                    Today objectToday = gson.fromJson(mJson, Today.class);

                    mJson = parser.parse(String.valueOf(overview));
                    OverviewInfo[] objectOverviewInfo = gson.fromJson(mJson,
                            OverviewInfo[].class);

                    ArrayList<OverviewInfo> overviewInfos = new ArrayList<>();
                    Arrays.stream(objectOverviewInfo).forEach(overviewInfos::add);

                    mJson = parser.parse(String.valueOf(locations));
                    Location[] objectLocation =  gson.fromJson(mJson,Location[].class);

                    ArrayList<Location> locationList = new ArrayList<>();
                    Arrays.stream(objectLocation).forEach(locationList::add);

                    modelCommom.setToday(objectToday);
                    modelCommom.setTotal(objectTotal);
                    modelCommom.setLocations(locationList);
                    modelCommom.setOverview(overviewInfos);

                    listener.onResponse(modelCommom);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("LOG",error.toString());
            }
        }
        );
        // Doi cac request tiep
        requestQueue.add(jsonObjectRequest);
    }
}