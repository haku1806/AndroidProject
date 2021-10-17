package com.haku1806.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.haku1806.Model.SMSModel;
import com.haku1806.R;

import java.util.ArrayList;

public class SMSAdapter extends RecyclerView.Adapter<SMSAdapter.ViewHolder> {
    Activity activity;
    ArrayList<SMSModel> arrayList;

    public SMSAdapter(Activity activity, ArrayList<SMSModel> arrayList) {
        this.activity = activity;
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public SMSAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sms, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SMSAdapter.ViewHolder holder, int position) {
        SMSModel model = arrayList.get(position);

        holder.tvNumber.setText(model.getNumber());
        holder.tvBody.setText(model.getBody());
        holder.tvTime.setText(model.getTime());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNumber, tvBody, tvTime;
        public ViewHolder(@Nullable View itemView) {
            super(itemView);

            tvNumber = itemView.findViewById(R.id.tv_sms_number);
            tvBody = itemView.findViewById(R.id.tv_sms_body);
            tvTime = itemView.findViewById(R.id.tv_sms_time);
        }
    }
}
