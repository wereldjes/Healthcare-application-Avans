/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import datalayer.AppointmentDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Appointment;
import model.Doctor;
import model.Patient;

/**
 * FXML Controller class
 *
 * @author Dominique
 */
public class PatientAppointmentController implements Initializable {

    private static Doctor doctor;
    private Patient patient;

    @FXML
    private Button backButton;
    @FXML
    private Label name;
    @FXML
    private Label bsn;
    @FXML
    private Label dateOfBirth;
    @FXML
    private Label gender;
    @FXML
    private Label adress;
    @FXML
    private Label doctorName;
    @FXML
    private VBox allAppointmentsHolder;
    @FXML
    private Button newAppointment;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        for (Appointment a : AppointmentDAO.getInstance().getAllAppointmentsFromPatient(patient.getPatientID())) {
            VBox descriptionHolder = new VBox();
            HBox startToEndDate = new HBox();
            Label problemDescription = new Label();
            Label diseaseName = new Label();
            Label medicineName = new Label();
            Label startDate = new Label();
            Label endDate = new Label();
            Region marginator = new Region();
            Region marginator2 = new Region();

            marginator.getStyleClass().add("marginator");
            marginator2.getStyleClass().add("marginator");
            diseaseName.getStyleClass().add("diseaseName");

            descriptionHolder.setAlignment(Pos.TOP_CENTER);
            descriptionHolder.setId("appointment");

            medicineName.setText("Medicijn: " + a.getMedicineName());
            startDate.setText(String.valueOf("gebruik medicijnen van: " + a.getStartdateMedicine()) + " tot ");
            endDate.setText(String.valueOf(a.getEnddateMedicine()));
            problemDescription.setText("Omschrijving klachten patient: " + a.getProblemDescription());
            diseaseName.setText(a.getDiseaseName());

            descriptionHolder.getChildren().add(diseaseName);
            descriptionHolder.getChildren().add(marginator);
            descriptionHolder.getChildren().add(medicineName);
            startToEndDate.getChildren().add(startDate);
            startToEndDate.getChildren().add(endDate);
            startToEndDate.setAlignment(Pos.CENTER);
            descriptionHolder.getChildren().add(startToEndDate);
            descriptionHolder.getChildren().add(marginator2);
            descriptionHolder.getChildren().add(problemDescription);
            allAppointmentsHolder.getChildren().add(descriptionHolder);
        }

        backButton.setOnAction(event -> patientOverview());
        newAppointment.setOnAction(event -> newAppointmentOverview());

        name.setText(patient.getFirstName() + " " + patient.getLastName());
        bsn.setText(String.valueOf(patient.getBsn()));
        dateOfBirth.setText(String.valueOf(patient.getDateOfBirth()));
        gender.setText(String.valueOf(patient.getGender()));
        adress.setText(patient.getAdress());
        doctorName.setText("Dokter " + doctor.getFirstName().substring(0, 1).toUpperCase() + doctor.getFirstName().substring(1));
    }

    public PatientAppointmentController(Stage stage, Doctor doctor, Patient patient) {
        this.doctor = doctor;
        this.patient = patient;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PatientAppointment.fxml"));
        loader.setController(this);

        try {
            Parent root = loader.load();
            stage.getScene().setRoot(root);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(PatientAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void patientOverview() {
        try {
            Stage stage = (Stage) backButton.getScene().getWindow();
            OverviewController oc = new OverviewController(stage, doctor);
        } catch (Exception ex) {
            Logger.getLogger(PatientAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void newAppointmentOverview() {
        try {
            Stage stage = (Stage) newAppointment.getScene().getWindow();
            NewAppointmentController nac = new NewAppointmentController(stage, doctor, patient);
        } catch (Exception ex) {
            Logger.getLogger(PatientAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
