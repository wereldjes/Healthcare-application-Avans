/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.PatientAppointmentController;
import datalayer.AppointmentDAO;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Appointment;
import model.Doctor;
import model.Patient;

/**
 * FXML Controller class
 *
 * @author Dominique
 */
public class UpdateAppointmentController implements Initializable {

    private Appointment appointment;
    private Doctor doctor;
    private Patient patient;

    @FXML
    private BorderPane borderPane;
    @FXML
    private Button backButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        backButton.setOnAction(event -> AppointmentOverview());
    }

    public UpdateAppointmentController(Stage stage) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/UpdateAppointment.fxml"));
        loader.setController(this);
        try {
            Parent root = loader.load();
            stage.getScene().setRoot(root);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(UpdateAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void updateAppointment() {
        //  Appointment a = new Appointment(0, problemDescription, startdateMedicine, enddateMedicine, diseaseName, medicineName, 0);
        //  AppointmentDAO.getInstance().updateAppointment(a);
        AppointmentOverview();
    }

    @FXML
    public void AppointmentOverview() {
        try {
            Stage stage = (Stage) borderPane.getScene().getWindow();
            PatientAppointmentController pac = new PatientAppointmentController(stage, doctor, patient);
        } catch (Exception ex) {
            Logger.getLogger(UpdateAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
