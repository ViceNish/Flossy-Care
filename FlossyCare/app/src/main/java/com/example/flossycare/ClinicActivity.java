package com.example.flossycare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.flossycare.adapter.Clinic;

import java.util.ArrayList;
import java.util.List;

public class ClinicActivity extends AppCompatActivity{

    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic);

        RecyclerView recyclerView = findViewById(R. id. recycle_clinic);

        linearLayoutManager = new LinearLayoutManager( ClinicActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);

        List<Clinic> allClinicInfo = getAllClinicInfo();
        ClinicRecyclerViewAdapter clinicRecyclerViewAdapter = new ClinicRecyclerViewAdapter( allClinicInfo, ClinicActivity.this);
        recyclerView.setAdapter(clinicRecyclerViewAdapter);

    }

    private List<Clinic> getAllClinicInfo() {

        List<Clinic> allClinic = new ArrayList<Clinic>();

        allClinic.add(new Clinic("Dutamas Dental Clinic", R. drawable.dutamas_dental_clinic));
        allClinic.add(new Clinic("Lou Dental Surgery", R. drawable.lou_dental_surgery));
        allClinic.add(new Clinic("Mahkota Dental Centre", R. drawable.mahkota_dental_centre));
        allClinic.add(new Clinic("Oasis Dental Clinic", R. drawable.oasis_dental_clinic_ara_damansara));
        allClinic.add(new Clinic("Smile Avenue Dental Surgery", R. drawable.smile_avenue_dental_surgery));
        allClinic.add(new Clinic("Sparkle Care Dental Clinic", R. drawable.sparkle_care_dental_clinic));
        allClinic.add(new Clinic("Metro Perdana Dental Clinic", R. drawable.metro_perdana_dental_clinic));

        return allClinic;
    }
}