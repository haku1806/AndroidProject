package com.haku1806;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.haku1806.model.Location;

import java.util.List;

public class LocationAdapter extends ArrayAdapter<Location> {

    Activity context;
    int resource;
    @NonNull List<Location> objects;
    public LocationAdapter(@NonNull Activity context, int resource, @NonNull List<Location> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View row, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        row = inflater.inflate(this.resource,null);

        TextView name = row.findViewById(R.id.name);
        TextView death = row.findViewById(R.id.death);
        TextView cases = row.findViewById(R.id.cases);
        TextView casesToday = row.findViewById(R.id.casesToday);

        name.setText(""+this.objects.get(position).getName());
        death.setText(""+this.objects.get(position).getDeath());
        cases.setText(""+this.objects.get(position).getCases());
        casesToday.setText(""+this.objects.get(position).getCasestoday());
        return row;
    }
}