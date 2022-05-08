/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Gui;

import gamershub.Entities.Sessioncoaching;
import gamershub.Services.ServiceSessions;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.FlowPane;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class SessionbackController implements Initializable {

    @FXML
    private FlowPane content;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ServiceSessions us = new ServiceSessions();
        try{
            List<Sessioncoaching> sessionList = us.afficherr();
            for (Sessioncoaching u:sessionList){
                FXMLLoader loader =new FXMLLoader(getClass().getResource("SessionCard.fxml"));
                Parent root = loader.load();
                SessionCardController c =loader.getController();
                c.setCoachImage("https://avatars.dicebear.com/api/bottts/" + u.getCoach()+ ".png");
                c.SetCoachid(String.valueOf(u.getCoach()));
                c.SetUsername(u.getClient());
                c.Setdesc(u.getDescription());
                c.Setidsession(String.valueOf(u.getId()));
                c.Setdatedebut(u.getDate_debut().toString());
                c.Setdatefin(u.getDate_fin().toString());
                c.Setprix(Float. toString(u.getPrix()));
                c.Setuserid(String.valueOf(u.getUser()));
                content.getChildren().add(root);
                
                
            }
        } catch (Exception ex) {
            Logger.getLogger(SessionContentController.class.getName()).log(Level.SEVERE, null, ex);
    }
            }
      
    
    
}
        
    

