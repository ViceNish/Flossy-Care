package com.example.flossycare;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class CalendarActivity extends AppCompatActivity {

    TextView tVdate, tVtime;
    int Hour, Minute;
    String flag;
    DatePickerDialog.OnDateSetListener setListener;
    CalendarView calendarView;
    Button btnFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        Intent intent = getIntent();

        tVdate = findViewById(R. id. dateView);
        calendarView = findViewById(R. id. calendarView);
        //can't select past date
        calendarView.setMinDate(System.currentTimeMillis() - 1000);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                month +=1;
                String date = dayOfMonth + "/" + month + "/" +year;

                tVdate.setText(date);
            }
        });

        tVtime = findViewById(R. id. timeView);
        tVtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timepicker = new TimePickerDialog(
                        CalendarActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                Hour = hourOfDay;
                                Minute = minute;
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(0,0,0,Hour,Minute);
                                tVtime.setText(android.text.format.DateFormat.format("hh:mm aa", calendar));
                            }
                        }, 12, 0 , false);
                timepicker.updateTime(Hour, Minute);
                timepicker.show();
            }
        });

        btnFinish = findViewById(R. id. btn_finish);
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Details dt = Details.getItnstance();
                dt.setDate(tVdate.getText().toString());
                dt.setTime(tVtime.getText().toString());
                String flag = dt.getTime().toString();
                flag = flag.substring(6);

                if(dt.getTime().toString().equals(" --:-- ")  && dt.getDate().toString().equals("dd/mm/yy")){
                    Toast.makeText(getApplicationContext(), "Please enter the date and time", Toast.LENGTH_LONG).show();

                }else if (dt.getTime().toString().equals(" --:-- ")){
                    Toast.makeText(getApplicationContext(), "Please enter the time", Toast.LENGTH_LONG).show();

                }else if (dt.getDate().toString().equals("dd/mm/yy")){
                    Toast.makeText(getApplicationContext(), "Please enter the date by tap on the calendar", Toast.LENGTH_LONG).show();

                }else if(flag.toString().equals("AM") && Hour <= 7 && Hour >= 0){

                    Toast.makeText(getApplicationContext(), "The clinic open from 8AM - 5PM only", Toast.LENGTH_LONG).show();

                }else if(flag.toString().equals("PM") &&( Hour <= 23 && Hour >= 18)){

                    Toast.makeText(getApplicationContext(), "The clinic open from 8AM - 6PM only", Toast.LENGTH_LONG).show();

                }else{

                    //Toast.makeText(getApplicationContext(), flag+" "+flag.length()+" "+Hour, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(CalendarActivity.this, AppointmentDetailsActivity.class);
                    startActivity(intent);
                }

            }
        });

    }
}