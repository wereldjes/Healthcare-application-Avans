/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import datalayer.DiseaseDAO;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Disease;
import model.Doctor;

/**
 * FXML Controller class
 *
 * @author Dominique
 */
public class CreateDiseaseController implements Initializable {

    private static Doctor doctor;
    private static Object controller;

    @FXML
    private Button backButton;
    @FXML
    private Button confirmForm;
    @FXML
    private TextField diseaseName;
    @FXML
    private TextField diseaseDescription;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        backButton.setOnAction(event -> diseaseOverview());
        confirmForm.setOnAction(event -> confirmDiseaseForm());
    }

    public CreateDiseaseController(Stage stage, Doctor doctor) {
        this.doctor = doctor;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CreateDisease.fxml"));
        loader.setController(this);

        try {
            Parent root = loader.load();
            stage.getScene().setRoot(root);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(CreateDiseaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void diseaseOverview() {
        try {
            Stage stage = (Stage) backButton.getScene().getWindow();
            controller = new DiseaseController(stage, doctor);
        } catch (Exception ex) {
            Logger.getLogger(CreateDiseaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void confirmDiseaseForm() {
        Disease d = new Disease(diseaseName.getText(), diseaseDescription.getText());
        DiseaseDAO.getInstance().createDisease(d);
        diseaseOverview();
    }

}
