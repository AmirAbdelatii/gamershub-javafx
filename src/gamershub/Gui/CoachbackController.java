/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Gui;

import gamershub.Entities.Coach;
import gamershub.Entities.Sessioncoaching;
import gamershub.Gamershub;
import static gamershub.Gui.CoachContentController.i;
import static gamershub.Gui.SessionContentController.j;
import gamershub.Services.ServiceCoachs;
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
public class CoachbackController implements Initializable {

    @FXML
    private FlowPane content;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
                c.Setidgame(String.valueOf(u.getGameId()));
                content.getChildren().add(root);
                
                
                
            }
        } catch (Exception ex) {
            Logger.getLogger(CoachContentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
