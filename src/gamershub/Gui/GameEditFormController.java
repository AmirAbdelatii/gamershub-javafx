/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Gui;

import gamershub.Entities.Game;
import gamershub.Entities.User;
import gamershub.Services.GameService;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author MAB
 */
public class GameEditFormController implements Initializable {

    @FXML
    private ImageView image;
    @FXML
    private TextArea description;
    @FXML
    private TextField title;
    @FXML
    private Button update;
    @FXML
    private Button delete;
    @FXML
    private Button cancel;
    private String oldName;

    public void setImage(String url) {
        this.image.setImage(new Image(url,253,337,false,false));
    }

    public void setDescription(String description) {
        this.description.setText(description);
    }

    public void setTitle(String title) {
        this.title.setText(title);
        oldName = title;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleClicks(ActionEvent event) {
        if(event.getSource() == update){
            GameService gs = new GameService();
            try {
                Game game = gs.getGame(oldName);
                game.setName(title.getText());
                game.setDescription(description.getText());
                gs.update(game);
            } catch (SQLException ex) {
                Logger.getLogger(ProfileEditFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(event.getSource() == delete){
            try {
                new GameService().deleteByName(oldName);
                FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("Home.fxml"));
                Parent homeRoot = homeLoader.load();
                HomeController homeCtrl = homeLoader.getController();
                homeCtrl.changePage("games");
                title.getScene().setRoot(homeRoot);
            } catch (Exception ex) {
                Logger.getLogger(ProfileEditFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else {
            try {
                FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("Home.fxml"));
                Parent homeRoot = homeLoader.load();
                HomeController homeCtrl = homeLoader.getController();
                homeCtrl.changePage("games");
                title.getScene().setRoot(homeRoot);
            } catch (Exception ex) {
                Logger.getLogger(ProfileEditFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
