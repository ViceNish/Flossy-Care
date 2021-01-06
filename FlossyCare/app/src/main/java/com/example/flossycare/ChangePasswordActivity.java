package com.example.flossycare;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ChangePasswordActivity extends AppCompatActivity {

    protected Button btnresetPass;
    protected EditText etCurrentPass, etNewPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        btnresetPass=(Button) findViewById(R.id.btn_resetpass);

        etCurrentPass=(EditText) findViewById(R.id.et_current_password);
        etNewPass=(EditText) findViewById(R.id.et_new_password);

        btnresetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}