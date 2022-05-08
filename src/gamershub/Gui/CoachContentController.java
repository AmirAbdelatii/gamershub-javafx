/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Gui;

import gamershub.Entities.Coach;
import gamershub.Gamershub;
import gamershub.Services.ServiceCoachs;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.FlowPane;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class CoachContentController implements Initializable {

    @FXML
    private FlowPane content1;
    @FXML
    private Button btnaddcoach;
    @FXML
    private TextField search;
    
   static int i=0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        search.textProperty().addListener((observable, oldValue, newValue) -> trouver() );
        
        ServiceCoachs us = new ServiceCoachs();
           
        try {
            if(Gamershub.loggedUser.getUsername().equals(us.getCoachname()))
            {
               btnaddcoach.setVisible(false);
               
               
            }
            
            List<Coach> coachList = us.afficherr();
            
            for (Coach u:coachList){
                if(Gamershub.loggedUser.getUsername().equals(u.getusername()))
                {
                    i=1;
                   
                }
                else {
                    i=0;}
                
                FXMLLoader loader =new FXMLLoader(getClass().getResource("Coachitem.fxml"));
                Parent root = loader.load();
                CoachitemController c =loader.getController();
                c.setCoachImage("https://avatars.dicebear.com/api/bottts/" + u.getusername() + ".png");
                c.SetCoachName(u.getusername());
                c.SetGamename(u.getgamename());
                c.Setdesc(u.getDescription());
                c.Setidcoach(String.valueOf(u.getId()));
                c.Setidgame(String.valueOf(u.getGameId()));
                content1.getChildren().add(root);
                
                
                
            }
        } catch (Exception ex) {
            Logger.getLogger(CoachContentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }    

    @FXML
    private void Addcoach(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Addcoach.fxml"));
            Parent root = loader.load();
            content1.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(CoachContentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void trouver()
    {
        content1.getChildren().clear();
       ServiceCoachs us = new ServiceCoachs();
           
        try {
            
            List<Coach> coachList = us.recherche(search.getText());
            
            for (Coach u:coachList){
                FXMLLoader loader =new FXMLLoader(getClass().getResource("Coachitem.fxml"));
                Parent root = loader.load();
                CoachitemController c =loader.getController();
                c.setCoachImage("https://avatars.dicebear.com/api/bottts/" + u.getusername() + ".png");
                c.SetCoachName(u.getusername());
                c.SetGamename(u.getgamename());
                c.Setdesc(u.getDescription());
                c.Setidcoach(String.valueOf(u.getId()));
                c.Setidgame(String.valueOf(u.getGameId()));
                content1.getChildren().add(root);
                
                
            }
        } catch (Exception ex) {
            Logger.getLogger(CoachContentController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
        
                       
    }

    
    
}
