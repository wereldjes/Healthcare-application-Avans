/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import datalayer.PatientDAO;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;
import model.Doctor;
import model.Patient;

public class OverviewController implements Initializable {

    private PatientAppointmentController patientDossierController;
    private Doctor doctor;

    @FXML
    private Label doctorName;
    @FXML
    private Button newPatient;
    @FXML
    private Button medicine;
    @FXML
    private Button disease;
    @FXML
    private VBox patientList;
    @FXML
    private BorderPane borderPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        for (Patient p : PatientDAO.getInstance().getAllPatientsFromDoctor(doctor)) {
            Button btn = new Button();
            Button deletePatient = new Button();
            Button updatePatient = new Button();
            Label patientName = new Label();
            Region region = new Region();
            HBox hbox = new HBox();

            hbox.setAlignment(Pos.TOP_CENTER);
            hbox.setId("buttonDiv");
            HBox.setHgrow(region, Priority.ALWAYS);
            patientName.setAlignment(Pos.BASELINE_LEFT);
            patientName.setText(p.getFirstName() + " " + p.getLastName());
            deletePatient.getStyleClass().add("deletePatientButton");
            deletePatient.setId(String.valueOf(p.getPatientID()));
            deletePatient.setText("U");
            deletePatient.addEventHandler(ActionEvent.ACTION, (event) -> deletePatient(event, deletePatient));
            updatePatient.getStyleClass().add("updatePatientButton");
            updatePatient.setId(String.valueOf(p.getPatientID()));
            updatePatient.setText("w");
            updatePatient.addEventHandler(ActionEvent.ACTION, (event) -> {
                updatePatient(updatePatient);
            });
            btn.getStyleClass().add("openPatientDossier");
            btn.setId(String.valueOf(p.getPatientID()));
            btn.setText("U");
            btn.addEventHandler(ActionEvent.ACTION, (event) -> {
                openPatientDossier(btn);
            });

            hbox.getChildren().add(patientName);
            hbox.getChildren().add(region);
            hbox.getChildren().add(btn);
            hbox.getChildren().add(updatePatient);
            hbox.getChildren().add(deletePatient);

            patientList.getChildren().add(hbox);
        }

        newPatient.setOnAction(event -> createNewPatient());
        medicine.setOnAction(event -> openMedicinePage());
        disease.setOnAction(event -> openDiseasePage());
        doctorName.setText("Dokter " + doctor.getFirstName().substring(0, 1).toUpperCase() + doctor.getFirstName().substring(1));

    }

    public OverviewController(Stage window, Doctor doctor) {
        this.doctor = doctor;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Overview.fxml"));
        loader.setController(this);
        Parent root;
        try {
            root = loader.load();
            window.getScene().setRoot(root);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(OverviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void openPatientDossier(Button b) {
        try {
            Patient p = PatientDAO.getInstance().getPatient(Integer.valueOf(b.getId()));
            PatientAppointmentController pdc = new PatientAppointmentController(getWindow(), doctor, p);
        } catch (Exception ex) {
            Logger.getLogger(OverviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void updatePatient(Button b) {
        try {
            Patient p = PatientDAO.getInstance().getPatient(Integer.valueOf(b.getId()));
            UpdatePatientController upc = new UpdatePatientController(getWindow(), doctor, p);
        } catch (Exception ex) {
            Logger.getLogger(OverviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void deletePatient(ActionEvent event, Button b) {
        PatientDAO.getInstance().deletePatient(Integer.valueOf(b.getId()));
        OverviewController overviewController = new OverviewController(getWindow(), doctor);
    }

    @FXML
    public void createNewPatient() {
        try {
            PatientFormController patientFormController = new PatientFormController(getWindow(), doctor);
        } catch (Exception ex) {
            Logger.getLogger(OverviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void openMedicinePage() {
        try {
            MedicineController medicineController = new MedicineController(getWindow(), doctor);
        } catch (Exception ex) {
            Logger.getLogger(PatientAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void openDiseasePage() {
        try {
            DiseaseController diseaseController = new DiseaseController(getWindow(), doctor);
        } catch (Exception ex) {
            Logger.getLogger(PatientAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Stage getWindow() {
        Stage stage = (Stage) borderPane.getScene().getWindow();
        return stage;
    }
}
