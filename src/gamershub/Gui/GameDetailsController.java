/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Gui;

import gamershub.Services.GameService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author MAB
 */
public class GameDetailsController implements Initializable {

    @FXML
    private ImageView image;
    @FXML
    private Text description;
    @FXML
    private ImageView like;
    @FXML
    private Label likesNumber;

    private boolean isLiked;
    private int gameId;
    @FXML
    private TextArea description1;

    public void setImage(String url) {
        this.image.setImage(new Image(url));
    }

    public void setDescription(String description) {
        this.description.setText(description);
    }

    public void setGameId(int id) {
        this.gameId = id;
    }

    public void setLike(boolean state) {
        isLiked = state;
        if (state) {
            this.like.setImage(new Image(getClass().getResourceAsStream("../images/icons8-heart-30.png")));
        } else {
            this.like.setImage(new Image(getClass().getResourceAsStream("../images/icons8-heart-30-dark.png")));
        }
        try {
            String likes = new GameService().getLikes(gameId);
            likesNumber.setText(likes.equals("1")?"1 like":likes+" likes");
        } catch (SQLException ex) {
            Logger.getLogger(GameDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            String likes = new GameService().getLikes(gameId);
            likesNumber.setText(likes.equals("1")?"1 like":likes+" likes");
        } catch (SQLException ex) {
            Logger.getLogger(GameDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void likeClick(ActionEvent event) {
        GameService gs = new GameService();
        if(!isLiked){
            try {
                gs.addLike(gameId);
            } catch (SQLException ex) {
                Logger.getLogger(GameDetailsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            try {
                gs.removeLike(gameId);
            } catch (SQLException ex) {
                Logger.getLogger(GameDetailsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        setLike(!isLiked);
    }

}
