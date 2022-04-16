/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author MAB
 */
public class UsersCardFormController implements Initializable {

    @FXML
    private ImageView userImage;
    @FXML
    private Label username;

    public void setUserImage(String url) {
        this.userImage.setImage(new Image(url));
    }

    public void setUsername(String username) {
        this.username.setText(username);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        username.setTextFill(Color.web("#000000"));
    }    

    @FXML
    private void viewClick(ActionEvent event) {
    }
    
}
