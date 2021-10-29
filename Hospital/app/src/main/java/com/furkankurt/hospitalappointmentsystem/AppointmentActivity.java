package com.furkankurt.hospitalappointmentsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.furkankurt.hospitalappointmentsystem.Adapters.HourAdapter;
import com.furkankurt.hospitalappointmentsystem.Models.Hours;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentActivity extends AppCompatActivity {
    FirebaseDatabase db=FirebaseDatabase.getInstance();
    List<Hours> hours=new ArrayList<>();
    ListView listView;
    SharedPreferences Saat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        listView=findViewById(R.id.listview_date);
        Saat=this.getSharedPreferences("Saat", Context.MODE_PRIVATE);
        try {
            new Asenkron().execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView txt = (TextView) view.findViewById(R.id.doctor_hour_row_text);
                String hour = txt.getText().toString();
                SharedPreferences.Editor editor1= Saat.edit();
                editor1.putString("hour",hour);
                editor1.commit();
                Intent intent=new Intent(AppointmentActivity.this,ResultActivity.class);
                startActivity(intent);
            }
        });
    }

    ProgressDialog progressDialog;
    public class Asenkron extends AsyncTask<Void,Void,Void> {

        public Asenkron() throws IOException {
        }

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(AppointmentActivity.this);
            progressDialog.setMessage("Lütfen Bekleyiniz..");
            progressDialog.setCancelable(false);
            progressDialog.show();
            super.onPreExecute();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                db.getReference().child("Hours").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull  DataSnapshot snapshot) {
                        for (DataSnapshot data:snapshot.getChildren())
                        {
                            Hours model=new Hours(
                                    data.child("Saat").getValue().toString());
                            hours.add(model);
                        }

                        HourAdapter adapter=new HourAdapter(AppointmentActivity.this,hours);
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
            if(progressDialog.isShowing())
            {
                progressDialog.dismiss();
            }
            super.onPostExecute(unused);
        }
    }

}
