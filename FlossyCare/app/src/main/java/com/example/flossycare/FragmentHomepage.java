package com.example.flossycare;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FragmentHomepage extends Fragment {

    private TextView tvUsername;
    private FirebaseAuth mFirebaseAuth;
    DatabaseReference databaseUsers;
    private FirebaseUser mFirebaseUser;
    private onFragmentBtnSelected listener;
    private FirebaseDatabase db;
    private DatabaseReference dbUser;
    private String user;
    private String id;

    private Button btnAddappointment;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R. layout.fragment_homepage, container, false);

        Details dt = Details.getItnstance();

        tvUsername=(TextView) view.findViewById(R.id.tvUsername);

        mFirebaseAuth=FirebaseAuth.getInstance();
        databaseUsers= FirebaseDatabase.getInstance().getReference("users");

        //perlu letak try n catch sbb "The problem seems to be that your user is not yet logged in or even registered. So calling mAuth.getCurrentUser() returns null."
        //basically, kalau x letak nnty dye crash mase first time buka
        try {
            tvUsername.setText(mFirebaseAuth.getCurrentUser().getEmail());
        }catch (Exception e){

        }

        dt.setEmail(tvUsername.getText().toString());


        /*Details dt = Details.getItnstance();

        mFirebaseAuth=FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        id = mFirebaseUser.getUid(); //Do what you need to do with the id
        dbUser = db.getReference("users/"+id);

        if(mFirebaseUser != null) {
            id = mFirebaseUser.getUid(); //Do what you need to do with the id

            dbUser.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    user = snapshot.child("userUsername").getValue(String.class);

                    tvUsername.setText(user);
                    //dt.setDoctor(id);
                   // dt.setUsername(user);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }*/

        /*if(dt.getUsername() == null){
            Toast.makeText(getActivity(),"null value",Toast.LENGTH_LONG).show();
            tvUsername.setText(dt.getUsername());
        }else{
            tvUsername.setText("Hello "+dt.getUsername());
        }*/



        btnAddappointment = view.findViewById(R. id. btn_add_appoint);
        btnAddappointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onBtnAddAppointment();
            }
        });
       //getUsername();

      //  listener.onTvUsername();
       // tvUsername.setText("Welcome "+ mFirebaseAuth.getCurrentUser().getEmail());

        return view;


    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if(context instanceof FragmentHomepage.onFragmentBtnSelected){
            listener = (FragmentHomepage.onFragmentBtnSelected) context;
        }else{
            throw new ClassCastException(context.toString() + " must implement listener");
        }
    }

    /*private void getUsername(){
        String id = mFirebaseAuth.getCurrentUser().getUid();
        DatabaseReference username = databaseUsers.child(id).child("userUsername");

        username.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String username = dataSnapshot.getValue().toString();
                tvUsername.setText(""+username);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        username.runTransaction(new Transaction.Handler() {
            @NonNull
            @Override
            public Transaction.Result doTransaction(@NonNull MutableData currentData) {
                String name = currentData.getValue(String.class);
                return Transaction.success(currentData);
            }

            @Override
            public void onComplete(@Nullable DatabaseError error, boolean committed, @Nullable DataSnapshot currentData) {
                String username = currentData.getValue(String.class);
                if(username != null)
                    tvUsername.setText("Welcome back " + username);

            }
        });*/
    public interface onFragmentBtnSelected {
        public void onBtnAddAppointment();
    }


}
