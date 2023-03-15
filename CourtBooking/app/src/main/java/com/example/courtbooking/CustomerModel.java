package com.example.courtbooking;

public class CustomerModel {

    private int id;
    private String name;
    private double contact;
    private String email;
    private String date;

    @Override
    public String toString() {

        return "Name: " + name + " | " + "Schedule: " + date;

    }

    public CustomerModel(int id, String name, double contact, String email, String date) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.email = email;
        this.date = date;
    }

    //getter setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getdate(){
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
