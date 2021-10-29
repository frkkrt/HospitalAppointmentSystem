package com.furkankurt.hospitalappointmentsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ListView;

import com.furkankurt.hospitalappointmentsystem.Adapters.Doctor_Info_Adapter;
import com.furkankurt.hospitalappointmentsystem.Models.DoctorModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Doktor_Info_Activty extends AppCompatActivity {
    ListView listView;
    List<DoctorModel> doctor_info_model=new ArrayList<>();
    FirebaseDatabase db=FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doktor_info_activty);

        listView=findViewById(R.id.list_info);

        try {
            new Asenkron().execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    ProgressDialog progressDialog;
    public class Asenkron extends AsyncTask<Void,Void,Void> {

        public Asenkron() throws IOException {
        }

        @Override
        protected void onPreExecute() {

            progressDialog = new ProgressDialog(Doktor_Info_Activty.this);
            progressDialog.setMessage("Lütfen Bekleyiniz..");
            progressDialog.setCancelable(false);
            progressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                db.getReference().child("Doctors").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull  DataSnapshot snapshot) {
                        for (DataSnapshot data:snapshot.getChildren())
                        {
                            DoctorModel model=new DoctorModel(
                                    data.child("DocAd").getValue().toString(),
                                    data.child("DocSoyad").getValue().toString(),
                                    data.child("Doc_Unvan").getValue().toString(),
                                    data.child("Doc_Yas").getValue().toString(),
                                    data.child("DocTc").getValue().toString(),
                                    data.child("DocSifre").getValue().toString(),
                                    data.child("DocSecim").getValue().toString());
                            doctor_info_model.add(model);

                        }

                        Doctor_Info_Adapter adapter=new Doctor_Info_Adapter(Doktor_Info_Activty.this,doctor_info_model);
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            } catch (Exception e) {
                System.out.println("YÜKLENEMEDİ");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    progressDialog.dismiss();
                }
            }, 2500);

            super.onPostExecute(unused);

        }
    }

}