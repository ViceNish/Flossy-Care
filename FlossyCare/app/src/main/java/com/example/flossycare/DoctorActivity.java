package com.example.flossycare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.flossycare.adapter.Doctor;

import java.util.ArrayList;
import java.util.List;

public class DoctorActivity extends AppCompatActivity {

    LinearLayoutManager linearLayoutManager;
    String clinic_choice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        Intent intent = getIntent();
        clinic_choice = intent.getStringExtra("clinicName");

        RecyclerView recyclerView = findViewById(R. id. recycle_doctor);

        linearLayoutManager = new LinearLayoutManager(DoctorActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);

        List<Doctor> allDoctorInfo = getAllDoctorInfo(clinic_choice);
        DoctorRecyclerViewAdapter doctorRecyclerViewAdapter = new DoctorRecyclerViewAdapter( allDoctorInfo, DoctorActivity.this);
        recyclerView.setAdapter(doctorRecyclerViewAdapter);

    }

    private List<Doctor> getAllDoctorInfo(String clinic) {

        List<Doctor> allDoctor = new ArrayList<Doctor>();

        switch (clinic){

            case "Dutamas Dental Clinic":
                allDoctor.add(new Doctor("Dr. Alex Yee", R. drawable.dr__alex_yee_ji_ziang));
                allDoctor.add(new Doctor("Dr. Chu Wooi", R. drawable.dr__chu_wooi_cheat));
                break;

            case "Lou Dental Surgery":
                allDoctor.add(new Doctor("Dr. Eileen Teoh", R. drawable.dr__eileen_teoh_ee_ling));
                allDoctor.add(new Doctor("Dr. Johnson", R. drawable.dr__johnson));
                break;

            case "Mahkota Dental Centre":
                allDoctor.add(new Doctor("Dr. Lou Yii", R. drawable.dr__lou_yii_ping));
                allDoctor.add(new Doctor("Dr. Ong Chian", R. drawable.dr__ong_chian_na_winnie));
                break;

            case "Oasis Dental Clinic":
                allDoctor.add(new Doctor("Dr. Siti Hafizatun", R. drawable.dr__siti_hafizatun_binti_abdul_shookor));
                allDoctor.add(new Doctor("Dr. Aric Chong", R. drawable.dr_aric_chong_kah_wai));
                break;

            case "Smile Avenue Dental Surgery":
                allDoctor.add(new Doctor("Dr. Dhanya Panicker", R. drawable.dr_dhanya_panicker));
                allDoctor.add(new Doctor("Dr. Emillia Chua", R. drawable.dr_emillia_chua_siew_li));
                break;

            case "Sparkle Care Dental Clinic":
                allDoctor.add(new Doctor("Dr. Philip Han", R. drawable.dr_philip_han_sheng_hui));
                allDoctor.add(new Doctor("Dr. Saliana", R. drawable.dr_saliana_binti_a_aziz));
                break;
        }

        return allDoctor;
    }
}