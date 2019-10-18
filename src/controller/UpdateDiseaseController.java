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
import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
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
public class UpdateDiseaseController implements Initializable {

    private Doctor doctor;
    private Disease disease;

    @FXML
    private TextField diseaseName;
    @FXML
    private TextField diseaseDescription;
    @FXML
    private Button confirmForm;
    @FXML
    private Button backButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        diseaseName.setText(disease.getName());
        diseaseDescription.setText(disease.getDescription());
        confirmForm.setOnAction(event -> confirmDiseaseForm());
        backButton.setOnAction(event -> diseaseOverview());
    }

    public UpdateDiseaseController(Stage stage, Disease disease, Doctor doctor) {
        this.disease = disease;
        this.doctor = doctor;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/UpdateDisease.fxml"));
        loader.setController(this);

        try {
            Parent root = loader.load();
            stage.getScene().setRoot(root);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(UpdateDiseaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void confirmDiseaseForm() {
        Disease d = new Disease(diseaseName.getText(), diseaseDescription.getText());
        DiseaseDAO.getInstance().updateDisease(d, disease.getName());
        diseaseOverview();
    }

    @FXML
    public void diseaseOverview() {
        try {
            Stage stage = (Stage) confirmForm.getScene().getWindow();
            DiseaseController controller = new DiseaseController(stage, doctor);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
