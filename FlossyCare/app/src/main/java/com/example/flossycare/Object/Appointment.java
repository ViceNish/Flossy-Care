package com.example.flossycare.Object;

public class Appointment {

    private String IDDetails;
    private String emailDetails;
    private String clinicDetails;
    private String doctorDetails;

    private String dateDetails;
    private String timeDetails;

    public Appointment() {
    }

    public Appointment(String IDDetails, String emailDetails, String  clinicDetails, String doctorDetails, String dateDetails, String timeDetails) {

        this.IDDetails=IDDetails;
        this.emailDetails=emailDetails;
        this.clinicDetails = clinicDetails;
        this.doctorDetails = doctorDetails;

        this.dateDetails = dateDetails;
        this.timeDetails = timeDetails;
    }

    public String getIDDetails() {
        return IDDetails;
    }

    public void setIDDetails(String IDDetails) {
        this.IDDetails = IDDetails;
    }

    public String getEmailDetails() {
        return emailDetails;
    }

    public void setEmailDetails(String emailDetails) {
        this.emailDetails = emailDetails;
    }

    public String getClinicDetails() {
        return clinicDetails;
    }

    public void setClinicDetails(String clinicDetails) {
        this.clinicDetails = clinicDetails;
    }


    public String getDoctorDetails() {
        return doctorDetails;
    }

    public void setDoctorDetails(String doctorDetails) {
        this.doctorDetails = doctorDetails;
    }


    public String getDateDetails() {
        return dateDetails;
    }

    public void setDateDetails(String dateDetails) {
        this.dateDetails = dateDetails;
    }

    public String getTimeDetails() {
        return timeDetails;
    }

    public void setTimeDetails(String timeDetails) {
        this.timeDetails = timeDetails;
    }
}
