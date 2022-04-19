/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Gui;

import gamershub.Services.ServiceCoachs;
import gamershub.Services.ServiceSessions;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class SessionitemController implements Initializable {

    @FXML
    private ImageView coachimg;
    @FXML
    private Label idsession;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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

    @FXML
    private void updateClick(ActionEvent event) {
    }

    @FXML
    private void deleteClick(ActionEvent event) {
        ServiceSessions cs = new ServiceSessions();
        int id=Integer. parseInt(idsession.getText());
        try {
            cs.deletesession(id);
        } catch (SQLException ex) {
            Logger.getLogger(SessionitemController.class.getName()).log(Level.SEVERE, null, ex);
        }
                try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Session.fxml"));
            Parent root = loader.load();
            desc.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(SessionitemController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
}
