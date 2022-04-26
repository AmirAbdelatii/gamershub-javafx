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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author MAB
 */
public class GameCardFrontController implements Initializable {

    @FXML
    private ImageView gameImage;
    @FXML
    private Label gameName;
    @FXML
    private ImageView like;
    
    private boolean isLiked;

    public void setGameImage(String url) {
        this.gameImage.setImage(new Image(url, 120, 160, false, false));
    }

    public void setGameName(String gameName) {
        this.gameName.setText(gameName);
    }

    public void setLike(boolean state) {
        isLiked = state;
        if (state) {
            this.like.setImage(new Image(getClass().getResourceAsStream("../images/icons8-heart-30.png")));
        } else {
            this.like.setImage(new Image(getClass().getResourceAsStream("../images/icons8-heart-30-dark.png")));
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void detailsClick(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameDetails.fxml"));
        try {
            Game game = new GameService().getGame(gameName.getText());
            Parent root = loader.load();
            GameDetailsController cont = loader.getController();
            cont.setImage("http://127.0.0.1:8000/games/images/" + game.getImage());
            cont.setDescription(game.getDescription());
            cont.setGameId(game.getId());
            cont.setLike(isLiked);

            FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("HomeFront.fxml"));
            Parent homeRoot = homeLoader.load();
            HomeFrontController homeCtrl = homeLoader.getController();
            homeCtrl.changePage(game.getName(), root);
            gameName.getScene().setRoot(homeRoot);

        } catch (Exception ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
