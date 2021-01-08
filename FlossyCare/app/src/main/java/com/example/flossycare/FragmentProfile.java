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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FragmentProfile extends Fragment {

    private TextView tvChangePass, tvEmail;
    private Button btnDeleteAcc;


    private onFragmentBtnSelected listener;

    private FirebaseAuth mFirebaseAuth;

    DatabaseReference databaseUsers;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R. layout.fragment_profile, container, false);


        tvChangePass=(TextView) view.findViewById(R.id.btn_change_password);
        btnDeleteAcc=(Button) view.findViewById(R.id.btn_delete_acc);
        tvEmail = (TextView) view.findViewById(R.id. tV_profile_email);

       // Details dt = Details.getItnstance();

        //tvUsername.setText(dt.getUsername());

        mFirebaseAuth=FirebaseAuth.getInstance();
        databaseUsers= FirebaseDatabase.getInstance().getReference("users");

        tvEmail.setText(mFirebaseAuth.getCurrentUser().getEmail());

        tvChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onBtnChangePassword();
            }
        });

        btnDeleteAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onBtnDeleteAcc();
            }
        });
        return view;


    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if(context instanceof onFragmentBtnSelected){
            listener = (onFragmentBtnSelected) context;
        }else{
            throw new ClassCastException(context.toString() + " must implement listener");
        }
    }


    public interface onFragmentBtnSelected {
        public void onBtnChangePassword();
        public void onBtnDeleteAcc();
    }
}

