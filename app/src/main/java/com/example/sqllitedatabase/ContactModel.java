package com.example.sqllitedatabase;

public class ContactModel {

    private String conID, userName, password;

    public String getConID() {
        return conID;
    }

    public void setConID(String conID) {
        this.conID = conID;
    }

    public String getUserName() {return userName;}

    public String getPassword(){return password;}

    public void setUserName(String userName){this.userName = userName;}

    public void setPassword(String password){this.password=password;}

}
