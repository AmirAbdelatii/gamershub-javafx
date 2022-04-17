/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Gui;

import gamershub.Entities.Game;
import gamershub.Services.GameService;
import gamershub.Services.UserService;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author MAB
 */
public class GamesCardFormController implements Initializable {

    @FXML
    private ImageView gameImage;
    @FXML
    private Label gameName;
    @FXML
    private Button details;

    public void setGameImage(String url) {
        this.gameImage.setImage(new Image(url, 120, 160, false, false));
    }

    public void setGameName(String gameName) {
        this.gameName.setText(gameName);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void detailsClick(ActionEvent event) {
        System.out.println(gameName.getText());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameEditForm.fxml"));
        try {
            Game game = new GameService().getGame(gameName.getText());
            Parent root = loader.load();
            GameEditFormController cont = loader.getController();
            System.out.println(game);
            cont.setTitle(game.getName());
            cont.setImage("http://127.0.0.1:8000/games/images/" + game.getImage());
            cont.setDescription(game.getDescription());

            FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("Home.fxml"));
            Parent homeRoot = homeLoader.load();
            HomeController homeCtrl = homeLoader.getController();
            homeCtrl.changePage(game.getName() + " - Edit", root);
            gameName.getScene().setRoot(homeRoot);

        } catch (Exception ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
