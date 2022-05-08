/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Gui;

import gamershub.Entities.Sessioncoaching;
import gamershub.Gamershub;
import gamershub.Services.ServiceSessions;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class SessionCardController implements Initializable {

    @FXML
    private ImageView coachimg;
    @FXML
    private Label coachid;
    @FXML
    private Label username;
    @FXML
    private Label desc;
    @FXML
    private Label prix;
    @FXML
    private Label start;
    @FXML
    private Label end;
    @FXML
    private Label idsession;
    @FXML
    private Label userid;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btndelete;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //SessionContentController session = new SessionContentController();
        //System.out.println(session.j);
        ServiceSessions us = new ServiceSessions();
        try {
            if(Gamershub.loggedUser.getId()==us.getCoachidFromuser())
            {
                btndelete.setVisible(true);
                btnupdate.setVisible(true);
            }
            else
            {btndelete.setVisible(false);
            btnupdate.setVisible(false);}
        } catch (SQLException ex) {
            Logger.getLogger(SessionCardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void setCoachImage(String url) {
        this.coachimg.setImage(new Image(url));
    }
    
    public void SetCoachid(String coachid){
       this.coachid.setText(coachid);
    }
    
    public void SetUsername(String username){
       this.username.setText(username);
    }
    
    public void Setdesc(String desc){
       this.desc.setText(desc);
    }
    
    public void Setidsession(String idsession){
       this.idsession.setText(idsession);
    }
    
    public void Setprix(String prix){
       this.prix.setText(prix);
    }
    public void Setdatedebut(String start){
       this.start.setText(start);
    }
    public void Setdatefin(String end){
       this.end.setText(end);
    }
    public void Setuserid(String userid){
       this.userid.setText(userid);
    }

    @FXML
    private void updateClick(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SessionEdit.fxml"));
            Parent root = loader.load();
            SessionEditController c =loader.getController();
            Sessioncoaching s = new Sessioncoaching(Integer.parseInt(idsession.getText()),Date.valueOf(start.getText()),Date.valueOf(end.getText()),Float.parseFloat(prix.getText()),desc.getText(),username.getText(),Integer.parseInt(userid.getText()),Integer.parseInt(coachid.getText())); 
            System.out.println(s);
            c.remplir(s);
            desc.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(LoginFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void deleteClick(ActionEvent event) {
        ServiceSessions cs = new ServiceSessions();
        int id=Integer. parseInt(idsession.getText());
        try {
            cs.deletesession(id);
        } catch (SQLException ex) {
            Logger.getLogger(SessionCardController.class.getName()).log(Level.SEVERE, null, ex);
        }
                try {

            FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("HomeFront.fxml"));
               Parent homeNode = homeLoader.load();
            ((HomeFrontController) homeLoader.getController()).changePage("btnsession");
                userid.getScene().setRoot(homeNode);

        } catch (IOException ex) {
            Logger.getLogger(SessionCardController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
}
