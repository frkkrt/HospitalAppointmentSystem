package com.furkankurt.hospitalappointmentsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.furkankurt.hospitalappointmentsystem.Models.DoctorModel;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;

public class RegisterActivity extends AppCompatActivity {
    FirebaseDatabase db=FirebaseDatabase.getInstance();
    EditText editname ,editsurname,edittc,editsifre,editunvan,edityas;
    SharedPreferences Kontrol;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Kontrol = this.getSharedPreferences("GirisKontrol", MODE_PRIVATE);

        setContentView(R.layout.activity_register);
        Button btnsaveregister=findViewById(R.id.btn_save_register);
        editname=findViewById(R.id.editperson_name);
        editunvan=findViewById(R.id.editperson_unvan);
        edityas=findViewById(R.id.editperson_yas);
        editsurname=findViewById(R.id.editperson_surname);
        edittc=findViewById(R.id.edittc);
        editsifre=findViewById(R.id.editperson_password);

        btnsaveregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    DoctorModel hourmodel=new DoctorModel(editname.getText().toString().toUpperCase(Locale.ROOT),editsurname.getText().toString().toUpperCase(),editunvan.getText().toString(),edityas.getText().toString(),edittc.getText().toString(),editsifre.getText().toString(),"Doktor");
                    String ÜretilenKey = db.getReference().child("Register").push().getKey();
                    db.getReference().child("Doctors/" + ÜretilenKey).setValue(hourmodel);
                    SharedPreferences.Editor editor = Kontrol.edit();
                    editor.putString("tc", edittc.getText().toString());
                    editor.commit();
                    Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
                    startActivity(intent);

            }
        });

    }
}