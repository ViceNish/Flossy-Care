package com.example.flossycare.Object;

public class Appointment {

    private String doctorDetails;
    private String clinicDetails;
    private String dateDetails;
    private String timeDetails;

    public Appointment(String doctorDetails, String clinicDetails, String dateDetails, String timeDetails) {
        this.doctorDetails = doctorDetails;
        this.clinicDetails = clinicDetails;
        this.dateDetails = dateDetails;
        this.timeDetails = timeDetails;
    }

    public String getDoctorDetails() {
        return doctorDetails;
    }

    public void setDoctorDetails(String doctorDetails) {
        this.doctorDetails = doctorDetails;
    }

    public String getClinicDetails() {
        return clinicDetails;
    }

    public void setClinicDetails(String clinicDetails) {
        this.clinicDetails = clinicDetails;
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
