package com.example.flossycare;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.flossycare.Object.Appointment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FragmentHistory extends Fragment {

    DatabaseReference databaseHistory;
    List<Appointment> history;
    ListView historyView;
    String user;
    private FirebaseAuth mFirebaseAuth;
    DatabaseReference databaseUsers;
    private FirebaseUser mFirebaseUser;

    private ArrayAdapter<String> lvHistory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R. layout.fragment_history, container, false);

        historyView = view.findViewById(R. id. lv_history);
        lvHistory = new ArrayAdapter<String>(getContext(), android.R. layout. simple_list_item_2, android. R. id. text2);
        historyView.setAdapter(lvHistory);

        databaseHistory = FirebaseDatabase.getInstance().getReference("appointments");
        history = new ArrayList<>();

        mFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        user = mFirebaseUser.getUid();

        Details dt = Details.getItnstance();
        //Toast.makeText(getContext(), user, Toast.LENGTH_LONG).show();
        //Toast.makeText(getContext(), "this"+dt.getEmail().length(), Toast.LENGTH_LONG).show();


        databaseHistory.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                history.clear();
                lvHistory.clear();

                for(DataSnapshot historySnapshot : snapshot.getChildren()){

                    //String appointment = snapshot.child(user).getValue(String.class);
                    Appointment appointment = historySnapshot.getValue(Appointment.class);

                    if(appointment.getEmailDetails().toString().equals(dt.getEmail())) {

                        history.add(appointment);
                        lvHistory.add("Email : "+appointment.getEmailDetails()+ "\nClinic : " + appointment.getClinicDetails() + "\nDoctor : " + appointment.getDoctorDetails() + "\nDate and Time : " + appointment.getDateDetails() + " , " + appointment.getTimeDetails()+"\n");
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return view;
    }
}
