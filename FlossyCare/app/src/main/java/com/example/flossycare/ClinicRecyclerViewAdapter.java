package com.example.flossycare;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flossycare.adapter.Clinic;

import java.util.List;

public class ClinicRecyclerViewAdapter extends RecyclerView.Adapter<ClinicRecyclerViewAdapter.ClinicViewHolder>{

    public List<Clinic> clinicList;
    private Context context;

    public ClinicRecyclerViewAdapter(List<Clinic> clinicList, Context context) {
        this.clinicList = clinicList;
        this.context = context;
    }


    @NonNull
    @Override
    public ClinicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View clinic_row = LayoutInflater.from(parent.getContext()).inflate(R. layout. clinic_row, null);
        ClinicViewHolder clinicVH = new ClinicViewHolder(clinic_row);
        return clinicVH;
    }

    @Override
    public void onBindViewHolder(@NonNull ClinicViewHolder holder, int position) {

        holder.tvClinicName.setText(clinicList.get(position).getClinicName());
        holder.imgClinic.setImageResource(clinicList.get(position).getClinicImage());
    }

    @Override
    public int getItemCount() {
        return clinicList.size();
    }

    public class ClinicViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView tvClinicName;
        public ImageView imgClinic;

        public ClinicViewHolder (@NonNull View itemView){

            super(itemView);
            tvClinicName = itemView.findViewById(R. id.tv_doctor_name);
            imgClinic = itemView.findViewById(R. id.img_doctor);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            Details dt = Details.getItnstance();
            dt.setClinic(clinicList.get(getAdapterPosition()).getClinicName());

            //Toast.makeText(v.getContext(),"Clinic Name " + clinicList.get(getAdapterPosition()).getClinicName(),Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(v.getContext(), DoctorActivity.class );
            intent.putExtra("clinicName",clinicList.get(getAdapterPosition()).getClinicName());

            v.getContext().startActivity(intent);

        }
    }
}
