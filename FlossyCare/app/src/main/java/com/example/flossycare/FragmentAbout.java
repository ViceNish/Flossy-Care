package com.example.flossycare;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentAbout extends Fragment {

    private TextView tvPP;


    private onFragmentBtnSelected listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R. layout.fragment_about, container, false);

        tvPP=(TextView) view.findViewById(R.id.about_tv_pp);


        tvPP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onBtnPP();
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

        public void onBtnPP();

    }


}
