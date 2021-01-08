package com.example.flossycare.adapter;

public class Doctor {

    String doctorName;
    int doctorImage;

    public Doctor(String doctorName, int doctorImage) {
        this.doctorName = doctorName;
        this.doctorImage = doctorImage;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public int getDoctorImage() {
        return doctorImage;
    }

    public void setDoctorImage(int doctorImage) {
        this.doctorImage = doctorImage;
    }
}
