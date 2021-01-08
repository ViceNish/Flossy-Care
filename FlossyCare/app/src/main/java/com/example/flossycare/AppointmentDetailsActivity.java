package com.example.flossycare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AppointmentDetailsActivity extends AppCompatActivity {

    TextView userName, doctorName, clinicName, date, time;
    Button btnSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_details);

        //Intent intent = getIntent();
        Details dt = Details.getItnstance();

        doctorName = findViewById(R. id. tv_doctor_name);
        clinicName = findViewById(R. id.tv_clinic_name);
        date = findViewById(R. id. tv_date_details);
        time = findViewById(R. id. tv_time_details);

        doctorName.setText(dt.getDoctor());
        clinicName.setText(dt.getClinic());
        date.setText(dt.getDate());
        time.setText(dt.getTime());

        btnSubmit = findViewById(R. id. btn_submit_details);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AppointmentDetailsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}