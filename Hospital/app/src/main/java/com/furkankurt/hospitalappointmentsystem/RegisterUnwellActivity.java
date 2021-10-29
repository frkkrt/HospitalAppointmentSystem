package com.furkankurt.hospitalappointmentsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.furkankurt.hospitalappointmentsystem.Models.RegisterModel;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterUnwellActivity extends AppCompatActivity {
    FirebaseDatabase db=FirebaseDatabase.getInstance();
    EditText edit_unwell_name ,edit_unwell_surname,edit_unwell_tc,edit_unwell_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_unwell);
        Button btnsaveregister=findViewById(R.id.btn_save_register_unwell);
        edit_unwell_name=findViewById(R.id.edit_unwell_name);
        edit_unwell_surname=findViewById(R.id.edit_unwell_surname);
        edit_unwell_tc=findViewById(R.id.edit_unwell_tc);
        edit_unwell_password=findViewById(R.id.edit_unwell_password);
        btnsaveregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterModel registerModel=new RegisterModel(edit_unwell_name.getText().toString(),edit_unwell_surname.getText().toString(),edit_unwell_tc.getText().toString(),edit_unwell_password.getText().toString(),"Hasta");
                String ÜretilenKey = db.getReference().child("Register").push().getKey();
                db.getReference().child("Register/" + ÜretilenKey).setValue(registerModel);
                Intent intent=new Intent(RegisterUnwellActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}