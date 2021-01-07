package com.example.flossycare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePasswordActivity extends AppCompatActivity {

    protected Button btnresetPass;
    protected EditText  etNewPass;

    private FirebaseAuth mFirebaseAuth;

    private FirebaseUser mFirebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        btnresetPass=(Button) findViewById(R.id.btn_resetpass);

       // etCurrentPass=(EditText) findViewById(R.id.et_current_password);
        etNewPass=(EditText) findViewById(R.id.et_new_password);


        mFirebaseAuth=FirebaseAuth.getInstance();
        mFirebaseUser=mFirebaseAuth.getCurrentUser();

        btnresetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePassword();
            }
        });



    }



    private void changePassword(){
        //String currentpass=etCurrentPass.getText().toString().trim();
        String newpass=etNewPass.getText().toString().trim();

        /*if (currentpass.isEmpty()){
            etCurrentPass.setError("Must Not Be Empty");
            etCurrentPass.requestFocus();
            return;
        }
        if (currentpass.equals(mFirebaseUser.get)){
            etCurrentPass.setError("Must Not Be Empty");
            etCurrentPass.requestFocus();
            return;
        }
        if (newpass.equals(currentpass)){
            etNewPass.setError("Must Not Be The Same Password");
            etNewPass.requestFocus();
            return;
        }*/


        if (newpass.isEmpty()){
            etNewPass.setError("Must Not Be Empty");
            etNewPass.requestFocus();
            return;
        }



        mFirebaseUser.updatePassword(newpass).addOnSuccessListener(new OnSuccessListener<Void>(){
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(ChangePasswordActivity.this, "Password reset successfully", Toast.LENGTH_LONG).show();
                mFirebaseAuth.signOut();
                Intent intent=new Intent(ChangePasswordActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        }).addOnFailureListener(new OnFailureListener(){
            @Override
            public void onFailure(@NonNull Exception e){
                Toast.makeText(ChangePasswordActivity.this, "Password reset failed", Toast.LENGTH_LONG).show();
            }

        });



    }
}