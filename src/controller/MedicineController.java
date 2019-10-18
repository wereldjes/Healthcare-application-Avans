/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import datalayer.MedicineDAO;
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
import model.Doctor;
import model.Medicine;

/**
 * FXML Controller class
 *
 * @author Dominique
 */
public class MedicineController implements Initializable {

    private OverviewController overviewController;
    private Doctor doctor;
    private Object controller;

    @FXML
    private Button backButton;
    @FXML
    private Label doctorName;
    @FXML
    private VBox medicineList;
    @FXML
    private Button newMedicine;
    @FXML
    private BorderPane borderPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        for (Medicine m : MedicineDAO.getInstance().getAllMedicine()) {
            Button deleteMedicine = new Button();
            Button updateMedicine = new Button();
            Label medicineName = new Label();
            Region region = new Region();
            HBox hbox = new HBox();

            hbox.setAlignment(Pos.TOP_CENTER);
            hbox.getStyleClass().add("buttonDiv");
            hbox.setHgrow(region, Priority.ALWAYS);
            medicineName.setAlignment(Pos.BASELINE_LEFT);
            medicineName.setText(m.getName());
            deleteMedicine.setId(m.getName());
            deleteMedicine.setText("Del.");
            deleteMedicine.getStyleClass().add("deleteMedicine");
            deleteMedicine.setOnAction(event -> deleteMedicine(deleteMedicine));
            updateMedicine.setId(m.getName());
            updateMedicine.setText("Upd.");
            updateMedicine.getStyleClass().add("updateMedicine");
            updateMedicine.setOnAction(event -> updateMedicine(updateMedicine));

            hbox.getChildren().add(medicineName);
            hbox.getChildren().add(region);
            hbox.getChildren().add(updateMedicine);
            hbox.getChildren().add(deleteMedicine);

            medicineList.getChildren().add(hbox);
        }

        backButton.setOnAction(event -> openOverviewPage());
        newMedicine.setOnAction(event -> createMedicine());
        doctorName.setText("Dokter " + doctor.getFirstName().substring(0, 1).toUpperCase() + doctor.getFirstName().substring(1));
    }

    public MedicineController(Stage window, Doctor doctor) {
        this.doctor = doctor;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Medicine.fxml"));
        loader.setController(this);
        Parent root;
        try {
            root = loader.load();
            window.getScene().setRoot(root);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(MedicineController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void openOverviewPage() {
        try {
            controller = new OverviewController(getWindow(), doctor);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void deleteMedicine(Button b) {
        MedicineDAO.getInstance().deleteMedicine(b.getId());
        controller = new MedicineController(getWindow(), doctor);
    }

    @FXML
    public void createMedicine() {
        try {
            CreateMedicineController createMedicineController = new CreateMedicineController(getWindow(), doctor);
        } catch (Exception ex) {
            Logger.getLogger(PatientAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void updateMedicine(Button b) {
        try {
            Medicine m = MedicineDAO.getInstance().getMedicine(b.getId());
            UpdateMedicineController updateMedicineController = new UpdateMedicineController(getWindow(), doctor, m);

        } catch (Exception ex) {
            Logger.getLogger(PatientAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Stage getWindow() {
        Stage stage = (Stage) borderPane.getScene().getWindow();
        return stage;
    }

}
