package com.furkankurt.hospitalappointmentsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.furkankurt.hospitalappointmentsystem.Models.AppointmentModel;
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
import java.util.Locale;

public class ResultActivity extends AppCompatActivity {
    SharedPreferences DocName;
    FirebaseDatabase db=FirebaseDatabase.getInstance();
    SharedPreferences Saat;
    SharedPreferences UnwellTc;
    DatabaseReference reff;
    TextView txtname;
    TextView txt_saat_result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        UnwellTc=this.getSharedPreferences("UnwellTc", Context.MODE_PRIVATE);
        Saat=this.getSharedPreferences("Saat", Context.MODE_PRIVATE);
        DocName = this.getSharedPreferences("DocName", Context.MODE_PRIVATE);

        TextView txt_tc_result=findViewById(R.id.txtsonuc_tc);
        txt_tc_result.setText(UnwellTc.getString("unwelltc",null));

        txtname=findViewById(R.id.txtsonuc_name);
        txt_saat_result=findViewById(R.id.txt_randevu_saat);

        txtname.setText(DocName.getString("name",null).toUpperCase(Locale.ROOT));
        txt_saat_result.setText(Saat.getString("hour",null));
        String name2=txtname.getText().toString();
        String hour2=txt_saat_result.getText().toString();
        Button btnsaveap=findViewById(R.id.btn_result);
        btnsaveap.setOnClickListener(e->
        {
            DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
            DatabaseReference tripsRef = rootRef.child("Appointments");
            ValueEventListener valueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    int sayac=0;
                    for(DataSnapshot ds : dataSnapshot.getChildren()) {
                        System.out.println("GİRDİ");
                        String name = ds.child("DocNameSurname").getValue(String.class);
                        String hour = ds.child("Appointment_Hour").getValue(String.class);
                        if(name.equals(name2)&&hour.equals(hour2))
                        {
                            Toast.makeText(ResultActivity.this, "Randevu Dolu!!!", Toast.LENGTH_SHORT).show();
                            sayac++;
                        }

                    }
                    if(sayac!=1)
                    {
                        AppointmentModel hourmodel=new AppointmentModel(txt_tc_result.getText().toString(),txtname.getText().toString(),txt_saat_result.getText().toString());
                        String ÜretilenKey = db.getReference().child("Appointments").push().getKey();
                        db.getReference().child("Appointments/" + ÜretilenKey).setValue(hourmodel);
                        Toast.makeText(ResultActivity.this, "RANDEVUNUZ BAŞARI İLE OLUŞTURULDU", Toast.LENGTH_SHORT).show();
                    }



                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            };
            tripsRef.addListenerForSingleValueEvent(valueEventListener);



        });
    }




}