/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Gui;

import gamershub.Entities.Coach;
import gamershub.Entities.Game;
import gamershub.Entities.User;
import gamershub.Gamershub;
import gamershub.Services.GameService;
import gamershub.Services.ServiceCoachs;
import gamershub.Services.UserService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AddcoachController implements Initializable {

    @FXML
    private ComboBox<String> gamecombo;
    @FXML
    private TextField descField;
    @FXML
    private Label errorMsg;
    @FXML
    private TextField usernamefield;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      try {
            // TODO
            usernamefield.setText(Gamershub.loggedUser.getUsername());
            GameService u = new GameService();
            List<Game> list = u.afficher();
            ObservableList<String> listgame = FXCollections.observableArrayList();
            for (Game g : list) {
                listgame.add(g.getId()+ ":  " +g.getName());
            }

            gamecombo.setItems(listgame);
        } catch (Exception ex) {
             Logger.getLogger(CoachController.class.getName()).log(Level.SEVERE, null, ex);
        }   
      
         
    }    
    

    
    
    
    
    

    @FXML
    private void createcoachClick(ActionEvent event) {
        Coach c = new Coach();
        usernamefield.setText(String.valueOf(Gamershub.loggedUser.getId()));
        System.out.println("ghjhjjh");
        c.setUserId(Integer.parseInt(usernamefield.getText()));
        String [] gamelist = gamecombo.getValue().split(":", 2);
        //System.out.println(gamelist);
        c.setGameId(Integer.parseInt(gamelist[0]));
        c.setDescription(descField.getText());
        ServiceCoachs us = new ServiceCoachs();
        System.out.println(c);
        try {
            us.ajouterr(c);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Coach.fxml"));
                Parent root = loader.load();
                usernamefield.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(LoginFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

//        public void setCombo(){
//    try {
//            // TODO
//            GameService u = new GameService();
//            List<Game> list = u.afficher();
//            ObservableList<String> listgame = FXCollections.observableArrayList();
//            for (Game g : list) {
//                listgame.add(g.getName());
//            }
//
//            gamecombo.setItems(listgame);
//        } catch (Exception ex) {
//             Logger.getLogger(CoachController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    @FXML
    private void cancelClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Coach.fxml"));
            Parent root = loader.load();
            gamecombo.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(LoginFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
