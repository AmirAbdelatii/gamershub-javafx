/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Gui;

import gamershub.Entities.Player;
import gamershub.Services.PlayerService;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author amira
 */
public class ListPlayerBackController implements Initializable {

    @FXML
    private ListView<Player> tablePlayer;
    @FXML
    private Button btnUpdatePlayer;
    private PlayerService rs = new PlayerService();
    @FXML
    private Button btnBecomePlayer;
    public static Player currentPlayer;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Player> items =FXCollections.observableArrayList();
        List<Player> listplayer = rs.ShowPlayer();
        tablePlayer.setOnMouseClicked(event -> {
           Player selected= tablePlayer.getSelectionModel().getSelectedItem();
           System.out.println(selected);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Nice selection ! ");
        alert.setContentText("You selected:"+selected+ "!");
        alert.show();
        });
        for(Player r : listplayer) {
            String ch = r.toString();
            items.add(r);
        }    
        tablePlayer.setItems(items);       
    }
                

    @FXML
    private void deletePlayer(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm your choice! ");
        alert.setContentText("Do you really want to delete this player ?");
        alert.show();
        rs.DeletePlayer(tablePlayer.getSelectionModel().getSelectedItem().getId());
      ObservableList<Player> items =FXCollections.observableArrayList();
        List<Player> listblog = rs.ShowPlayer();
        for(Player r : listblog) {
            String ch = r.toString();
            items.add(r);
        }
       
    tablePlayer.setItems(items);
    }


    /*@FXML
    private void updatePlayer(ActionEvent event) {
    }*/

    @FXML
    private void becomePlayer(ActionEvent event) {
        try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("BecomePlayerForm.fxml"));
                Parent root = loader.load();
                btnBecomePlayer.getScene().setRoot(root);
              } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
    }

    @FXML
    private void updatePlayer(ActionEvent event) {
        currentPlayer = tablePlayer.getSelectionModel().getSelectedItem();
         try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdatePlayerForm.fxml"));
                Parent root = loader.load();
                btnUpdatePlayer.getScene().setRoot(root);
              } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
    }

}
