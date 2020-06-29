package com.scrum.importili.payload.request;


public class FrontalUser {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private int cin;
    private String phoneNumber;
    private String role;
    private String password;
    private boolean active;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public FrontalUser(Long id, String email, String firstName, String lastName, int cin, String phoneNumber, String role, String password, boolean active) {
        this.id = id;
        this.email=email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cin = cin;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.password = password;
        this.active = active;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public Long getId() {
        return id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getLastName() {
        return lastName;
    }

    public int getCin() {
        return cin;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isActive() {
        return active;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}