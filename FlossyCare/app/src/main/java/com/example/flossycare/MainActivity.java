package com.example.flossycare;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, FragmentAbout.onFragmentBtnSelected,FragmentProfile.onFragmentBtnSelected, FragmentHomepage.onFragmentBtnSelected{

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

   // List<User> users;
  //
  //  DatabaseReference databaseUsers;

  //  private FirebaseDatabase db;
   // private DatabaseReference dbUser;
   // private String user="";
   // private String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R. id. toolbar);
        setSupportActionBar(toolbar);
        //buang title kt actionbar
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        drawerLayout = findViewById(R. id. drawer);

        navigationView = findViewById(R. id. navigationView);
        navigationView.setNavigationItemSelectedListener(this);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

        //String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        //Toast.makeText(this, ""+userid, Toast.LENGTH_LONG).show();



        /*
        mFirebaseAuth=FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        dbUser = db.getReference("users");
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        id = mFirebaseUser.getUid(); //Do what you need to do with the id


        if(mFirebaseUser != null) {
            id = mFirebaseUser.getUid(); //Do what you need to do with the id
            Log.v("MainActivity", id);

            dbUser.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    user = snapshot.child(id).child("userUsername").getValue(String.class);
                    Details dt = Details.getItnstance();
                    dt.setDoctor(id);
                    dt.setUsername(user);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }*/

       // id = mFirebaseUser.getUid();


        /*fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R. id. container_fragment, new FragmentHomepage());
        fragmentTransaction.commit();*/
        getSupportFragmentManager().beginTransaction().add(R. id. container_fragment,new FragmentHomepage()).commit();
    }


    @Override
    protected void onResume() {
        super.onResume();

        mFirebaseAuth=FirebaseAuth.getInstance();
        mFirebaseUser=mFirebaseAuth.getCurrentUser();

        if (mFirebaseUser==null){
            //go to login page
            Intent intent = new Intent(MainActivity.this, StartScreenActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        drawerLayout.closeDrawer(GravityCompat.START);
        if(item.getItemId() == R. id. home_nav){
            getSupportFragmentManager().beginTransaction().replace(R. id. container_fragment,new FragmentHomepage()).addToBackStack(null).commit();
        }
        if(item.getItemId() == R. id. history_nav){
            getSupportFragmentManager().beginTransaction().replace(R. id. container_fragment,new FragmentHistory()).addToBackStack(null).commit();
        }
        if(item.getItemId() == R. id. profile_nav){
            getSupportFragmentManager().beginTransaction().replace(R. id. container_fragment,new FragmentProfile()).addToBackStack(null).commit();
        }
        if(item.getItemId() == R. id. about_nav){
            getSupportFragmentManager().beginTransaction().replace(R. id. container_fragment,new FragmentAbout()).addToBackStack(null).commit();
        }
        if(item.getItemId() == R. id. logout_nav){
            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which){
                        case DialogInterface.BUTTON_POSITIVE:
                            //Yes button clicked


                            //getSupportFragmentManager().beginTransaction().remove(new FragmentHomepage()).commit();
                            mFirebaseAuth.signOut();
                            startActivity(new Intent(MainActivity.this,LoginActivity.class));
                            break;

                        case DialogInterface.BUTTON_NEGATIVE:
                            //No button clicked
                            break;
                    }
                }
            };

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                    .setNegativeButton("No", dialogClickListener).show();
        }

        return true;
    }

    @Override
    public void onBtnPP() {
        Intent intent = new Intent(MainActivity.this, PPActivity.class);
        startActivity(intent);

    }

    @Override
    public void onBtnChangePassword() {
        Intent intent = new Intent(MainActivity.this, ChangePasswordActivity.class);
        startActivity(intent);

    }

    @Override
    public void onBtnDeleteAcc(){

        Details dt = Details.getItnstance();

        AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
        dialog.setTitle("Are You Sure?");
        dialog.setMessage("Deleting this account will result completely removing your account from the system and you won't be able to access the app.");
        dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mFirebaseUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){

                            DatabaseReference dR = FirebaseDatabase.getInstance().getReference("users").child(dt.getUserid().toString());
                            dR.removeValue();

                            Toast.makeText(MainActivity.this,"Account Deleted",Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(MainActivity.this,StartScreenActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }else {
                            Toast.makeText(MainActivity.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog=dialog.create();
        alertDialog.show();
    }


    @Override
    public void onBtnAddAppointment() {
        Intent intent = new Intent(MainActivity.this, ClinicActivity.class);
        startActivity(intent);
    }
}