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
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.furkankurt.hospitalappointmentsystem.Adapters.DoctorAdapter;
import com.furkankurt.hospitalappointmentsystem.Models.DoctorModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DoctorActivity extends AppCompatActivity {
    ListView listView;
    List<DoctorModel> doctorModels=new ArrayList<>();
    DatabaseReference reff;
    SharedPreferences DocName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        DocName = this.getSharedPreferences("DocName", Context.MODE_PRIVATE);
        listView=findViewById(R.id.listview_doctor);
        Button btn_info=findViewById(R.id.btn_info);
        btn_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DoctorActivity.this,Doktor_Info_Activty.class);
                startActivity(intent);
            }
        });



        try {
            new Asenkron().execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView txt = (TextView) view.findViewById(R.id.doctor_row_text);
                String s = txt.getText().toString();
                SharedPreferences.Editor editor= DocName.edit();
                editor.putString("name",s);
                editor.commit();
                    Intent intent=new Intent(DoctorActivity.this,AppointmentActivity.class);
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
            progressDialog = new ProgressDialog(DoctorActivity.this);
            progressDialog.setMessage("Lütfen Bekleyiniz..");
            progressDialog.setCancelable(false);
            progressDialog.show();
            super.onPreExecute();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                reff = FirebaseDatabase.getInstance().getReference().child("Doctors");
                Query query=reff.orderByChild("DocSecim").equalTo("Doktor");
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
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

                            doctorModels.add(model);
                        }

                        DoctorAdapter adapter=new DoctorAdapter(doctorModels,DoctorActivity.this);
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