package com.example.flossycare;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.flossycare.Object.Appointment;
import com.example.flossycare.Object.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AppointmentDetailsActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;
    DatabaseReference databaseUsers,databaseAppoinments;
    private FirebaseUser mFirebaseUser;
    private FragmentHomepage.onFragmentBtnSelected listener;
    private FirebaseDatabase db;
    private DatabaseReference dbUser;
    private String user;
    private String id;

    private TextView Namemail, doctorName, clinicName, date, time;
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
        Namemail = findViewById(R. id. tv_name);


        mFirebaseAuth= FirebaseAuth.getInstance();
        databaseUsers= FirebaseDatabase.getInstance().getReference("users");
        databaseAppoinments=FirebaseDatabase.getInstance().getReference("appointments");

        mFirebaseAuth=FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        id = mFirebaseUser.getUid(); //Do what you need to do with the id
        dbUser = db.getReference("users/"+id);

        Namemail.setText(mFirebaseAuth.getCurrentUser().getEmail());

        if(mFirebaseUser != null) {
            id = mFirebaseUser.getUid(); //Do what you need to do with the id

            dbUser.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    User user = snapshot.getValue(User.class);

                    //userName.setText(user.getUserID());
                    //dt.setDoctor(id);
                    // dt.setUsername(user);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }

        doctorName.setText(dt.getDoctor());
        clinicName.setText(dt.getClinic());
        date.setText(dt.getDate());
        time.setText(dt.getTime());

        btnSubmit = findViewById(R. id. btn_submit_details);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alert = new AlertDialog.Builder(AppointmentDetailsActivity.this);

                alert.setTitle("Appointment Confirmation");
                alert.setMessage("Are you confirm with this appointment setup?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String email=mFirebaseAuth.getCurrentUser().getEmail();
                        String clinic=dt.getClinic();
                        String doctor=dt.getDoctor();
                        String date=dt.getDate();
                        String time=dt.getTime();

                        //String id=mFirebaseAuth.getCurrentUser().getUid();
                        String id=databaseUsers.push().getKey();
                        Appointment newAppointment=new Appointment(id,email,clinic,doctor,date,time);
                        databaseAppoinments.child(id).setValue(newAppointment);

                        Toast.makeText(AppointmentDetailsActivity.this,"Your Appointment Have Been Submited",Toast.LENGTH_LONG).show();
                        GoToMainActivity();
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(AppointmentDetailsActivity.this);

                        builder.setTitle("Cancellation Appointment");
                        builder.setMessage("Do you want to change the details?");
                        builder.setPositiveButton("Yes, I want to change", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(AppointmentDetailsActivity.this, ClinicActivity.class);
                                startActivity(intent);
                            }
                        });
                        builder.setNegativeButton("No, I just want to cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getApplicationContext(), "Your appoitnment setup has been cancelled", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(AppointmentDetailsActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        });
                        builder.show();
                    }
                });
                alert.show();



            }
        });

    }


    private void GoToMainActivity(){

        Intent intent = new Intent(AppointmentDetailsActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}