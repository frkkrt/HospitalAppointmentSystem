package com.furkankurt.hospitalappointmentsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Ref;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase db=FirebaseDatabase.getInstance();
    SharedPreferences UnwellTc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UnwellTc=this.getSharedPreferences("UnwellTc", Context.MODE_PRIVATE);

        setContentView(R.layout.activity_main);
        Button btnregister=findViewById(R.id.btn_save_doctor);
        Button btnregister_unwell=findViewById(R.id.btn_save_unwell);
        Button btn_giris=findViewById(R.id.btn_ınput);
        EditText edt_tc=findViewById(R.id.edit_tc);
        EditText edt_password=findViewById(R.id.edit_password);
        btn_giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt_tc.getText().toString().trim().equals("")||edt_password.getText().toString().trim().equals(""))
                {
                    Toast.makeText(MainActivity.this, "Lütfen Tüm Alanları Doldurunuz!!!", Toast.LENGTH_LONG).show();
                }
                else
                {
                    db.getReference().child("Register").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot data : snapshot.getChildren()) {
                                if (data.child("Tc").getValue().toString().equals(edt_tc.getText().toString()) && data.child("Sifre").getValue().toString().equals(edt_password.getText().toString())) {
                                    androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                    builder.setTitle("Kullanıcı Girişi");
                                    builder.setMessage("Kullanıcı Girişi Yapıldı");
                                    builder.setPositiveButton("Devam Et", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent=new Intent(MainActivity.this,DoctorActivity.class);
                                            startActivity(intent);
                                            SharedPreferences.Editor editor2= UnwellTc.edit();
                                            editor2.putString("unwelltc",edt_tc.getText().toString());
                                            editor2.commit();
                                        }
                                    });
                                    builder.show();
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }


                        });
                }

            }
        });
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        btnregister_unwell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(MainActivity.this,RegisterUnwellActivity.class);
                startActivity(intent2);
            }
        });
    }


}