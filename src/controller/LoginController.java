/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import datalayer.AccountDAO;
import datalayer.DoctorDAO;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Account;
import model.Doctor;

/**
 *
 * @author Dominique
 */
public class LoginController implements Initializable {

    private OverviewController overviewController;

    @FXML
    private TextField username;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Label error;

    @FXML
    private void validation() {
        Account account = AccountDAO.getInstance().getAccount(username.getText());

        if (account != null) {
            if (account.getUsername().equals(username.getText()) && account.getPassword().equals(passwordField.getText())) {
                try {
                    Stage stage = (Stage) loginButton.getScene().getWindow();
                    overviewController = new OverviewController(stage, getDoctor());
                } catch (Exception ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                error.setText("Onjuist wachtwoord");
            }
        } else {
            error.setText("Dit account bestaat niet");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loginButton.setOnAction(event -> validation());
    }

    public Doctor getDoctor() {

        return DoctorDAO.getInstance().getDoctorByAccountUsername(username.getText());
    }

}
