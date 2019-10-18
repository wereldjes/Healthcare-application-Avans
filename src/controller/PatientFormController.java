/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import datalayer.PatientDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
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
 *
 * @author Dominique
 */
public class PatientFormController implements Initializable {

    private OverviewController overviewController;
    private Doctor doctor;

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

    @FXML
    public void patientOverview() {
        Stage stage = (Stage) previousPage.getScene().getWindow();
        OverviewController overviewController = new OverviewController(stage, doctor);
    }

    @FXML
    public void confirmPatientForm() {
        Patient p = new Patient();
        p.setFirstName(voornaam.getText());
        p.setLastName(achternaam.getText());
        p.setBsn(Integer.parseInt(bsn.getText()));
        p.setDateOfBirth(Date.valueOf(geboortedatum.getText()));
        p.setGender(Gender.valueOf(geslacht.getText().toUpperCase()));
        p.setAdress(woonplaats.getText());

        PatientDAO.getInstance().createPatient(p, doctor);
        patientOverview();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        previousPage.setOnAction(event -> patientOverview());
        confirmPatientForm.setOnAction(event -> confirmPatientForm());

    }

    public PatientFormController(Stage stage, Doctor doctor) {
        this.doctor = doctor;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PatientForm.fxml"));
        loader.setController(this);

        try {
            Parent root = loader.load();
            stage.getScene().setRoot(root);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(PatientFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
