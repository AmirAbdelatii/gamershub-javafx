/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Gui;

import gamershub.Entities.User;
import gamershub.Services.UserService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author MAB
 */
public class SignupFormController implements Initializable {

    @FXML
    private TextField usernameField;
    @FXML
    private TextField emailField;
//    @FXML
//    private DatePicker birthdatePicker;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private Label errorMsg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void signUpClick(ActionEvent event) {
        User u = new User();
//        u.setBirthDate(Date.valueOf(birthdatePicker.getValue()));
        u.setEmail(emailField.getText());
        u.setUsername(usernameField.getText());
        u.setPassword(passwordField.getText());
        UserService us = new UserService();
        System.out.println(u);
        try {
            us.ajouter(u);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginForm.fxml"));
                Parent root = loader.load();
                usernameField.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(LoginFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void signInClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginForm.fxml"));
            Parent root = loader.load();
            usernameField.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(LoginFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
