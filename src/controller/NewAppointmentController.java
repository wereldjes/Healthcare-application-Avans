/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import datalayer.AppointmentDAO;
import datalayer.DiseaseDAO;
import datalayer.MedicineDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Appointment;
import model.Disease;
import model.Doctor;
import model.Medicine;
import model.Patient;

/**
 * FXML Controller class
 *
 * @author Dominique
 */
public class NewAppointmentController implements Initializable {

    private Doctor doctor;
    private Patient patient;

    @FXML
    private ComboBox diseaseName;
    @FXML
    private ComboBox medicineName;
    @FXML
    private Button backButton;
    @FXML
    private Button confirmForm;
    @FXML
    private TextArea patientProblems;
    @FXML
    private TextField startdateMedicine;
    @FXML
    private TextField enddateMedicine;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<String> diseaseNameList = new ArrayList<>();
        ArrayList<String> medicineNameList = new ArrayList<>();

        for (Disease d : DiseaseDAO.getInstance().getAllDiseases()) {
            diseaseNameList.add(d.getName());
        }

        for (Medicine m : MedicineDAO.getInstance().getAllMedicine()) {
            medicineNameList.add(m.getName());
        }

        ObservableList<String> listDisease = FXCollections.observableArrayList(diseaseNameList);
        diseaseName.setItems(listDisease);
        ObservableList<String> listMedicine = FXCollections.observableArrayList(medicineNameList);
        medicineName.setItems(listMedicine);

        backButton.setOnAction(event -> appointmentOverview());
        confirmForm.setOnAction(event -> createAppointment());
    }

    public NewAppointmentController(Stage stage, Doctor doctor, Patient patient) {
        this.doctor = doctor;
        this.patient = patient;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/NewAppointment.fxml"));
        loader.setController(this);

        try {
            Parent root = loader.load();
            stage.getScene().setRoot(root);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(NewAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void appointmentOverview() {
        try {
            Stage stage = (Stage) backButton.getScene().getWindow();
            PatientAppointmentController pac = new PatientAppointmentController(stage, doctor, patient);
        } catch (Exception ex) {
            Logger.getLogger(NewAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void createAppointment() {
        Appointment appointment = new Appointment(patientProblems.getText(), Date.valueOf(startdateMedicine.getText()), Date.valueOf(enddateMedicine.getText()), diseaseName.getValue().toString(), medicineName.getValue().toString(), patient.getPatientID());
        AppointmentDAO.getInstance().createAppointment(appointment);
        appointmentOverview();
    }

}
