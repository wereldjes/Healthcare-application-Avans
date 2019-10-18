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
public class Appointment {

    private int id;
    private String problemDescription;
    private Date startdateMedicine;
    private Date enddateMedicine;
    private String diseaseName;
    private String medicineName;
    private int patientId;

    public Appointment(int id, String problemDescription, Date startdateMedicine, Date enddateMedicine, String diseaseName, String medicineName, int patientId) {
        this.id = id;
        this.problemDescription = problemDescription;
        this.startdateMedicine = startdateMedicine;
        this.enddateMedicine = enddateMedicine;
        this.diseaseName = diseaseName;
        this.medicineName = medicineName;
        this.patientId = patientId;
    }

    public Appointment(String problemDescription, Date startdateMedicine, Date enddateMedicine, String diseaseName, String medicineName, int patientId) {
        this.problemDescription = problemDescription;
        this.startdateMedicine = startdateMedicine;
        this.enddateMedicine = enddateMedicine;
        this.diseaseName = diseaseName;
        this.medicineName = medicineName;
        this.patientId = patientId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public Date getStartdateMedicine() {
        return startdateMedicine;
    }

    public void setStartdateMedicine(Date startdateMedicine) {
        this.startdateMedicine = startdateMedicine;
    }

    public Date getEnddateMedicine() {
        return enddateMedicine;
    }

    public void setEnddateMedicine(Date enddateMedicine) {
        this.enddateMedicine = enddateMedicine;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

}
