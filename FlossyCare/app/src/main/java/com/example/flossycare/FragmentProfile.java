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

public class FragmentProfile extends Fragment {

    private TextView tvChangePass, tvUsername;
    private Button btnDeleteAcc;

    private onFragmentBtnSelected listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R. layout.fragment_profile, container, false);


        tvChangePass=(TextView) view.findViewById(R.id.btn_change_password);
        btnDeleteAcc=(Button) view.findViewById(R.id.btn_delete_acc);
        tvUsername = view.findViewById(R.id. tV_profile_username);

        Details dt = Details.getItnstance();

        tvUsername.setText(dt.getUsername());

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

