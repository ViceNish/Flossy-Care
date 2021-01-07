package com.example.flossycare;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R. layout.fragment_homepage, container, false);

        tvUsername=(TextView) view.findViewById(R.id.tvUsername);

        mFirebaseAuth=FirebaseAuth.getInstance();

        databaseUsers= FirebaseDatabase.getInstance().getReference("users");

       //getUsername();

      //  listener.onTvUsername();
       // tvUsername.setText("Welcome "+ mFirebaseAuth.getCurrentUser().getEmail());

        return view;


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
    


}
