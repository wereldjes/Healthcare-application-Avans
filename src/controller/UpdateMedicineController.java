/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import datalayer.MedicineDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Doctor;
import model.Medicine;

/**
 * FXML Controller class
 *
 * @author Dominique
 */
public class UpdateMedicineController implements Initializable {

    private final Doctor doctor;

    private Medicine medicine;

    @FXML
    private Button confirmForm;
    @FXML
    private TextField medicineName;
    @FXML
    private TextField medicineDescription;
    @FXML
    private Button backButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        confirmForm.setOnAction(event -> updateMedicine());
        backButton.setOnAction(event -> medicineOverview());
        medicineName.setText(medicine.getName());
        medicineDescription.setText(medicine.getDescription());
    }

    public UpdateMedicineController(Stage stage, Doctor doctor, Medicine m) {
        this.doctor = doctor;
        this.medicine = m;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/updateMedicine.fxml"));
        loader.setController(this);
        try {
            Parent root = loader.load();
            stage.getScene().setRoot(root);
            stage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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

    @FXML
    public void updateMedicine() {
        Medicine m = new Medicine(medicineName.getText(), medicineDescription.getText());
        MedicineDAO.getInstance().updateMedicine(m, medicine.getName());
        medicineOverview();

    }

}
