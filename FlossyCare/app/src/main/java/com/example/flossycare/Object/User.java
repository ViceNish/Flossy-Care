package com.example.flossycare.Object;

public class User {
    private String userID;
    private String userEmail;
   // private String userPassword;
    private String userUsername;

    public User() {
    }

    public User(String userID, String username, String userEmail, String userPassword) {
        this.userID = userID;
        this.userEmail = userEmail;
        //this.userPassword = userPassword;
       this.userUsername = username;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

   // public String getUserPassword() {
     //   return userPassword;
    //}

    //public void setUserPassword(String userPassword) {
     //   this.userPassword = userPassword;
   // }

   public String getUserUsername() {
        return userUsername;
    }

   public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }
}
