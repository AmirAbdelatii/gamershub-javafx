/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Gui;

import gamershub.Gamershub;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author MAB
 */
public class ProfilePageController implements Initializable {

    @FXML
    private ImageView userImage;
    @FXML
    private Label username;
    @FXML
    private Pane verified;
    @FXML
    private Label name;
    @FXML
    private Label secondName;
    @FXML
    private Label coins;
    @FXML
    private Button updateProfile;
    @FXML
    private Button updatePass;
    @FXML
    private Button deleteAcc;

    public void setInfos() {
        this.username.setText(Gamershub.loggedUser.getUsername());
        this.userImage.setImage(new Image("https://avatars.dicebear.com/api/bottts/" + Gamershub.loggedUser.getUsername() + ".png"));
        this.coins.setText(Integer.toString(Gamershub.loggedUser.getCoins()));
        this.name.setText(Gamershub.loggedUser.getName());
        this.secondName.setText(Gamershub.loggedUser.getSecondName());
        this.verified.setVisible(Gamershub.loggedUser.getIsVerified()==1);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.setInfos();
    }    

    @FXML
    private void handleClicks(ActionEvent event) {
    }
    
}
