/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Gui;

import gamershub.Entities.Coach;
import gamershub.Entities.Game;
import gamershub.Gamershub;
import gamershub.Services.GameService;
import gamershub.Services.ServiceCoachs;
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
    private TextField usernameField;
    @FXML
    private ComboBox<String> gamecombo;
    @FXML
    private TextField descField;
    @FXML
    private Label errorMsg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            usernameField.setText(Gamershub.loggedUser.getUsername());
            GameService u = new GameService();
            List<Game> list = u.afficher();
            ObservableList<String> listgame = FXCollections.observableArrayList();
            for (Game g : list) {
                listgame.add(g.getId()+ ":  " +g.getName());
            }

            gamecombo.setItems(listgame);
        } catch (Exception ex) {
             Logger.getLogger(AddcoachController.class.getName()).log(Level.SEVERE, null, ex);
        }   
      
         
    }    
        

    @FXML
    private void createcoachClick(ActionEvent event) {
        boolean check=false;
        if(descField.getText().isEmpty())
        {
            errorMsg.setText("fill the field");
            check=true;
        }
        if(gamecombo.getValue().isEmpty())
        {
            errorMsg.setText("choose a game ");
            check=true;
        }
        if (check) return;
        Coach c = new Coach();
        usernameField.setText(String.valueOf(Gamershub.loggedUser.getId()));
        System.out.println("ghjhjjh");
        c.setUserId(Integer.parseInt(usernameField.getText()));
        String [] gamelist = gamecombo.getValue().split(":", 2);
        //System.out.println(gamelist);
        c.setGameId(Integer.parseInt(gamelist[0]));
        c.setDescription(descField.getText());
        ServiceCoachs us = new ServiceCoachs();
        System.out.println(c);
        try {
            us.ajouterr(c);
            try {
                FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("HomeFront.fxml"));
               Parent homeNode = homeLoader.load();
            ((HomeFrontController) homeLoader.getController()).changePage("Coachs");
                usernameField.getScene().setRoot(homeNode);
            } catch (IOException ex) {
                Logger.getLogger(LoginFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void cancelClick(ActionEvent event) {
        try {
            FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("HomeFront.fxml"));
               Parent homeNode = homeLoader.load();
            ((HomeFrontController) homeLoader.getController()).changePage("Coachs");
                usernameField.getScene().setRoot(homeNode);
        } catch (IOException ex) {
            Logger.getLogger(EditcoachController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
