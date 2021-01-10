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

import com.example.flossycare.adapter.Doctor;

import java.util.List;

public class DoctorRecyclerViewAdapter extends RecyclerView.Adapter<DoctorRecyclerViewAdapter.DoctorViewHolder> {

    public List<Doctor> doctorList;
    private Context context;

    public DoctorRecyclerViewAdapter(List<Doctor> doctorList, Context context) {
        this.doctorList = doctorList;
        this.context = context;
    }

    @NonNull
    @Override
    public DoctorRecyclerViewAdapter.DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View doctor_row = LayoutInflater.from(parent.getContext()).inflate(R. layout. doctor_row, null);
        DoctorRecyclerViewAdapter.DoctorViewHolder doctorVH = new DoctorRecyclerViewAdapter.DoctorViewHolder(doctor_row);
        return doctorVH;
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorRecyclerViewAdapter.DoctorViewHolder holder, int position) {
        holder.tvDoctorName.setText(doctorList.get(position).getDoctorName());
        holder.imgDoctor.setImageResource(doctorList.get(position).getDoctorImage());
    }

    @Override
    public int getItemCount() {
        return doctorList.size();
    }

    public class DoctorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView tvDoctorName;
        public ImageView imgDoctor;

        public DoctorViewHolder(@NonNull View itemView){

            super(itemView);
            tvDoctorName = itemView.findViewById(R. id.tv_doctor_name);
            imgDoctor = itemView.findViewById(R. id.img_doctor);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            Details dt = Details.getItnstance();
            dt.setDoctor(doctorList.get(getAdapterPosition()).getDoctorName());

            //Toast.makeText(v.getContext(),"Doctor Name " + doctorList.get(getAdapterPosition()).getDoctorName(),Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(v.getContext(), CalendarActivity.class );

            v.getContext().startActivity(intent);

        }
    }
}
