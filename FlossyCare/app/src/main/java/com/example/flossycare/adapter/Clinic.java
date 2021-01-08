package com.example.flossycare.adapter;

public class Clinic {

    String clinicName;
    int clinicImage;

    public Clinic(String clinicName, int clinicImage) {
        this.clinicName = clinicName;
        this.clinicImage = clinicImage;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public int getClinicImage() {
        return clinicImage;
    }

    public void setClinicImage(int clinicImage) {
        this.clinicImage = clinicImage;
    }
}
