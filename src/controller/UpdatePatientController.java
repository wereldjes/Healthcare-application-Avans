/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import datalayer.PatientDAO;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Doctor;
import model.Gender;
import model.Patient;

/**
 * FXML Controller class
 *
 * @author Dominique
 */
public class UpdatePatientController implements Initializable {

    private final Doctor doctor;
    private Patient patient;

    @FXML
    private Button previousPage;
    @FXML
    private TextField voornaam;
    @FXML
    private TextField achternaam;
    @FXML
    private TextField bsn;
    @FXML
    private TextField geboortedatum;
    @FXML
    private TextField geslacht;
    @FXML
    private TextField woonplaats;
    @FXML
    private Button confirmPatientForm;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        previousPage.setOnAction(event -> patientOverview());
        confirmPatientForm.setOnAction(event -> updatePatientForm());

        voornaam.setText(patient.getFirstName());
        achternaam.setText(patient.getLastName());
        bsn.setText(String.valueOf(patient.getBsn()));
        geboortedatum.setText(String.valueOf(patient.getDateOfBirth()));
        geslacht.setText(String.valueOf(patient.getGender()));
        woonplaats.setText(patient.getAdress());
    }

    public UpdatePatientController(Stage stage, Doctor doctor, Patient patient) {
        this.doctor = doctor;
        this.patient = patient;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/UpdatePatient.fxml"));
        loader.setController(this);

        try {
            Parent root = loader.load();
            stage.getScene().setRoot(root);
            stage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void patientOverview() {
        Stage stage = (Stage) previousPage.getScene().getWindow();
        OverviewController overviewController = new OverviewController(stage, doctor);
    }

    @FXML
    public void updatePatientForm() {
        Patient p = new Patient();

        p.setPatientID(patient.getPatientID());
        p.setFirstName(voornaam.getText());
        p.setLastName(achternaam.getText());
        p.setBsn(Integer.parseInt(bsn.getText()));
        p.setDateOfBirth(Date.valueOf(geboortedatum.getText()));
        p.setGender(Gender.valueOf(geslacht.getText().toUpperCase()));
        p.setAdress(woonplaats.getText());

        PatientDAO.getInstance().updatePatient(p, doctor);
        patientOverview();
    }

}
