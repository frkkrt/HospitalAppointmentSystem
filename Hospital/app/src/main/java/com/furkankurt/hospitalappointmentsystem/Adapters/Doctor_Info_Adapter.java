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

public class Doctor_Info_Adapter extends BaseAdapter {
    Activity activity;
    List<DoctorModel> doctor_info_modelList;

    public Doctor_Info_Adapter(Activity activity, List<DoctorModel> doctor_info_modelList) {
        this.activity = activity;
        this.doctor_info_modelList = doctor_info_modelList;
    }


    @Override
    public int getCount() {
        return doctor_info_modelList.size();
    }

    @Override
    public Object getItem(int position) {
        return doctor_info_modelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(activity).inflate(R.layout.doctor_info_row, parent, false);

        TextView textinfo1 = convertView.findViewById(R.id.doctor_info_text1);
        TextView txtinfo2= convertView.findViewById(R.id.doctor_info_text2);
        TextView txtinfo3 = convertView.findViewById(R.id.doctor_info_text3);
        TextView txtinfo4= convertView.findViewById(R.id.doctor_info_text4);

        textinfo1.setText("Ad:"+doctor_info_modelList.get(position).DocAd);
        txtinfo2.setText("Soyad:"+doctor_info_modelList.get(position).DocSoyad);
        txtinfo3.setText("Unvan:"+doctor_info_modelList.get(position).Doc_Unvan);
        txtinfo4.setText("Yaş:"+doctor_info_modelList.get(position).Doc_Yas);

        return convertView;
    }
}
