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
import model.Disease;
import model.Doctor;

/**
 * FXML Controller class
 *
 * @author Dominique
 */
public class DiseaseController implements Initializable {

    private static Doctor doctor;
    private static Object controller;

    @FXML
    private VBox diseaseList;
    @FXML
    private Button backButton;
    @FXML
    private Button newDisease;
    @FXML
    private BorderPane borderPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        for (Disease d : DiseaseDAO.getInstance().getAllDiseases()) {
            Button deleteDisease = new Button();
            Button updateDisease = new Button();
            Label diseaseName = new Label();
            Region region = new Region();
            HBox hbox = new HBox();

            hbox.setAlignment(Pos.TOP_CENTER);
            hbox.getStyleClass().add("buttonDiv");
            hbox.setHgrow(region, Priority.ALWAYS);
            diseaseName.setAlignment(Pos.BASELINE_LEFT);
            diseaseName.setText(d.getName());
            deleteDisease.setId(d.getName());
            deleteDisease.setText("Del.");
            deleteDisease.getStyleClass().add("deleteDisease");
            deleteDisease.setOnAction(event -> deleteDisease(deleteDisease));
            updateDisease.setId(d.getName());
            updateDisease.setText("Upd.");
            updateDisease.getStyleClass().add("updateDisease");
            updateDisease.setOnAction(event -> updateDisease(updateDisease));

            hbox.getChildren().add(diseaseName);
            hbox.getChildren().add(region);
            hbox.getChildren().add(updateDisease);
            hbox.getChildren().add(deleteDisease);

            diseaseList.getChildren().add(hbox);
        }

        newDisease.setOnAction(event -> createDisease());
        backButton.setOnAction(event -> openOverview());
    }

    public DiseaseController(Stage stage, Doctor doctor) {
        this.doctor = doctor;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Disease.fxml"));
        loader.setController(this);

        try {
            Parent root = loader.load();
            stage.getScene().setRoot(root);
            stage.show();
        } catch (Exception ex) {
            Logger.getLogger(OverviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void openOverview() {
        try {
            controller = new OverviewController(getWindow(), doctor);
        } catch (Exception ex) {
            Logger.getLogger(OverviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void deleteDisease(Button b) {
        DiseaseDAO.getInstance().deleteDisease(b.getId());
        Stage stage = (Stage) b.getScene().getWindow();
        controller = new DiseaseController(stage, doctor);
    }

    @FXML
    public void createDisease() {
        try {
            controller = new CreateDiseaseController(getWindow(), doctor);
        } catch (Exception ex) {
            Logger.getLogger(PatientAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void updateDisease(Button b) {
        try {
            Disease d = DiseaseDAO.getInstance().getDisease(b.getId());
            controller = new UpdateDiseaseController(getWindow(), d, doctor);
        } catch (Exception ex) {
            Logger.getLogger(PatientAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Stage getWindow() {
        Stage stage = (Stage) borderPane.getScene().getWindow();
        return stage;
    }

}
