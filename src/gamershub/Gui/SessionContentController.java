/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Gui;

import gamershub.Entities.Sessioncoaching;
import gamershub.Gamershub;
import gamershub.Services.ServiceCoachs;
import gamershub.Services.ServiceSessions;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class SessionContentController implements Initializable {

    @FXML
    private FlowPane content;
    static int j=0;
    @FXML
    private Button btnaddsession;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceSessions us = new ServiceSessions();
        ServiceCoachs s = new ServiceCoachs();
           
        try {
            if(Gamershub.loggedUser.getUsername().equals(s.getCoachname()))
            {
               btnaddsession.setVisible(true);
               System.out.println(s.getCoachname());
            }
            else 
            {btnaddsession.setVisible(false);}
            List<Sessioncoaching> sessionList = us.afficherr();
            
            for (Sessioncoaching u:sessionList){
                if(Gamershub.loggedUser.getId()==us.getCoachidFromuser())
                {
                    j=1;
                    System.out.println(us.getCoachidFromuser());
                }
                else {
                    j=0;}
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
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                us.archiver();
                System.out.println("working");
            }
            
        }, 0, 60 * 60 * 1000);//24*60*60*1000 delay de 1 jours entre l'execution du CRON d'archivage
    }    

    @FXML
    private void AddSession(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SessionAdd.fxml"));
            Parent root = loader.load();
            content.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(LoginFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
