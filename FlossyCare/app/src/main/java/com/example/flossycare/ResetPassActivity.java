package com.example.flossycare;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPassActivity extends AppCompatActivity {

    protected EditText etEmail;

    protected Button btnReset;


    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass);
        etEmail=(EditText) findViewById(R.id.login_et_email);

        btnReset=(Button) findViewById(R.id.btn_reset);


        mFirebaseAuth=FirebaseAuth.getInstance();

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResetPassword();
            }
        });

    }


    private void ResetPassword(){
        String email=etEmail.getText().toString().trim();

        if (email.isEmpty()){
            etEmail.setError("Must Not Be Empty");
            etEmail.requestFocus();
            return;

        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etEmail.setError("Please Provide Valid Email");
            etEmail.requestFocus();
            return;
        }

        mFirebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(ResetPassActivity.this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(ResetPassActivity.this, "Check Your Email To Reset Your Password!",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(ResetPassActivity.this, task.getException().getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}
