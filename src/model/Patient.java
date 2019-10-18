/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author Dominique
 */
public class Patient {

    private int patientID;
    private String firstName;
    private String lastName;
    private int bsn;
    private Date dateOfBirth;
    private String adress;
    private Gender gender;
    private int doctorID;

    public Patient(String firstName, String lastName, int bsn, Date dateOfBirth, String adress, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bsn = bsn;
        this.dateOfBirth = dateOfBirth;
        this.adress = adress;
        this.gender = gender;
    }

    public Patient(int patientID, String firstName, String lastName, int bsn, Date dateOfBirth, String adress, Gender gender, int doctorID) {
        this.patientID = patientID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bsn = bsn;
        this.dateOfBirth = dateOfBirth;
        this.adress = adress;
        this.gender = gender;
        this.doctorID = doctorID;
    }

    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    public Patient() {

    }

    @Override
    public String toString() {
        return "Patient{" + "patientID=" + patientID + ", firstName=" + firstName + ", lastName=" + lastName + ", bsn=" + bsn + ", dateOfBirth=" + dateOfBirth + ", adress=" + adress + ", gender=" + gender + ", doctorID=" + doctorID + '}';
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getBsn() {
        return bsn;
    }

    public void setBsn(int bsn) {
        this.bsn = bsn;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

}
