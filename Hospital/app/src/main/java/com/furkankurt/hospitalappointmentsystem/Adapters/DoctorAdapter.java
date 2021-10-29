package com.furkankurt.hospitalappointmentsystem.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.furkankurt.hospitalappointmentsystem.Models.DoctorModel;
import com.furkankurt.hospitalappointmentsystem.R;

import java.util.List;

public class DoctorAdapter extends BaseAdapter {
    List<DoctorModel> doctorModels;
    Activity activity;

    public DoctorAdapter(List<DoctorModel> doctorModels, Activity activity) {
        this.doctorModels = doctorModels;
        this.activity = activity;
    }


    @Override
    public int getCount() {
        return doctorModels.size();
    }

    @Override
    public Object getItem(int position) {
        return doctorModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(activity).inflate(R.layout.doctor_listview_row, parent, false);
        TextView textad = convertView.findViewById(R.id.doctor_row_text);
        textad.setText(doctorModels.get(position).DocAd+" "+doctorModels.get(position).DocSoyad);


        return convertView;
    }
}
