package com.furkankurt.hospitalappointmentsystem.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.furkankurt.hospitalappointmentsystem.Models.Hours;
import com.furkankurt.hospitalappointmentsystem.R;

import java.util.List;

public class HourAdapter extends BaseAdapter {
    Activity activity;
    List<Hours> hours;

    public HourAdapter(Activity activity, List<Hours> hours) {
        this.activity = activity;
        this.hours = hours;
    }

    @Override
    public int getCount() {
        return hours.size();
    }

    @Override
    public Object getItem(int position) {
        return hours.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(activity).inflate(R.layout.doctor_hour_row, parent, false);
        TextView textad = convertView.findViewById(R.id.doctor_hour_row_text);
        textad.setText(hours.get(position).Saat);
        return  convertView;
    }
}
