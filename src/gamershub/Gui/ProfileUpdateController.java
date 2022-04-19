/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Gui;

import gamershub.Gamershub;
import gamershub.Services.UserService;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

/**
 * FXML Controller class
 *
 * @author MAB
 */
public class ProfileUpdateController implements Initializable {

    @FXML
    private Button update;
    @FXML
    private Button cancel;
    @FXML
    private TextField name;
    @FXML
    private TextField secondName;
    @FXML
    private TextField email;
    @FXML
    private DatePicker birthdate;

    /**
     * Initializes the controller class.
     */
    public void setInfos() {
        this.name.setText(Gamershub.loggedUser.getName());
        this.secondName.setText(Gamershub.loggedUser.getSecondName());
        this.email.setText(Gamershub.loggedUser.getEmail());
        if (Gamershub.loggedUser.getBirthDate() != null) {
            this.birthdate.setValue(Gamershub.loggedUser.getBirthDate().toLocalDate());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setInfos();
    }

    @FXML
    private void handleClicks(ActionEvent event) {
        if (event.getSource() == update) {
            Gamershub.loggedUser.setName(name.getText());
            Gamershub.loggedUser.setSecondName(secondName.getText());
            Gamershub.loggedUser.setEmail(email.getText());
            if(birthdate.getValue() != null)
                Gamershub.loggedUser.setBirthDate(Date.valueOf(birthdate.getValue()));
            UserService us = new UserService();
            try {
                us.update(Gamershub.loggedUser);
            } catch (SQLException ex) {
                Logger.getLogger(ProfileUpdateController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("HomeFront.fxml"));
            Parent homeNode = homeLoader.load();
            ((HomeFrontController) homeLoader.getController()).changePage("profile");
            cancel.getScene().setRoot(homeNode);
        } catch (Exception ex) {
            Logger.getLogger(ProfileEditFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
