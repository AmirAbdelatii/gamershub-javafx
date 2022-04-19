/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Gui;

import gamershub.Entities.Coach;
import gamershub.Entities.Sessioncoaching;
import gamershub.Services.ServiceCoachs;
import gamershub.Services.ServiceSessions;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class CoachController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private MenuItem Coachitem;


    @FXML
    private ScrollPane scroll;

    @FXML
    private TextField search;

    @FXML
    private MenuItem sessionitem;
    @FXML
    private FlowPane content;
    
    

    @FXML
    void addcoachclick(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Addcoach.fxml"));
            Parent root = loader.load();
            content.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(LoginFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @FXML
    void coachitemClick(ActionEvent event) {
       ServiceCoachs us = new ServiceCoachs();
           
        try {
            
            List<Coach> coachList = us.afficherr();
            
            for (Coach u:coachList){
                FXMLLoader loader =new FXMLLoader(getClass().getResource("Coachitem.fxml"));
                Parent root = loader.load();
                CoachitemController c =loader.getController();
                c.setCoachImage("https://avatars.dicebear.com/api/bottts/" + u.getusername() + ".png");
                c.SetCoachName(u.getusername());
                c.SetGamename(u.getgamename());
                c.Setdesc(u.getDescription());
                c.Setidcoach(String.valueOf(u.getId()));
                content.getChildren().add(root);
                
                
            }
        } catch (Exception ex) {
            Logger.getLogger(CoachController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
            
       

    }

    @FXML
    void searchclick(ActionEvent event) {

    }

    @FXML
    void sessionitemClick(ActionEvent event) {
                ServiceSessions us = new ServiceSessions();
           
        try {
            
            List<Sessioncoaching> sessionList = us.afficherr();
            
            for (Sessioncoaching u:sessionList){
                FXMLLoader loader =new FXMLLoader(getClass().getResource("Sessionitem.fxml"));
                Parent root = loader.load();
                SessionitemController c =loader.getController();
                c.setCoachImage("https://avatars.dicebear.com/api/bottts/" + u.getCoach()+ ".png");
                c.SetCoachid(String.valueOf(u.getCoach()));
                c.SetUsername(u.getClient());
                c.Setdesc(u.getDescription());
                c.Setidsession(String.valueOf(u.getId()));
                c.Setdatedebut(u.getDate_debut().toString());
                c.Setdatefin(u.getDate_fin().toString());
                c.Setprix(Float. toString(u.getPrix()));
                content.getChildren().add(root);
                
                
            }
        } catch (Exception ex) {
            Logger.getLogger(SessionController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
            
       
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
