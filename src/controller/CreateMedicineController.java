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
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Doctor;
import model.Medicine;

/**
 * FXML Controller class
 *
 * @author Dominique
 */
public class CreateMedicineController implements Initializable {

    private Doctor doctor;

    @FXML
    private TextField medicineName;
    @FXML
    private TextField medicineDescription;
    @FXML
    private Button confirmForm;
    @FXML
    private Button backButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        confirmForm.setOnAction(event -> confirmMedicineForm());
        backButton.setOnAction(event -> medicineOverview());
    }

    public CreateMedicineController(Stage stage, Doctor doctor) {
        this.doctor = doctor;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CreateMedicine.fxml"));
        loader.setController(this);
        try {
            Parent root = loader.load();
            stage.getScene().setRoot(root);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(CreateMedicineController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    public void confirmMedicineForm() {
        Medicine m = new Medicine(medicineName.getText(), medicineDescription.getText());
        MedicineDAO.getInstance().createMedicine(m);
        medicineOverview();
    }

    @FXML
    public void medicineOverview() {
        try {
            Stage stage = (Stage) confirmForm.getScene().getWindow();
            MedicineController controller = new MedicineController(stage, doctor);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
