/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Gui;

import gamershub.Entities.User;
import gamershub.Services.ServiceCoachs;
import gamershub.Utils.JavaMail;
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
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class CoachitemController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ImageView coachimg;

    @FXML
    private Label coachname;

    @FXML
    private Label gamename;
    @FXML
    private Label desc;
    @FXML
    private Label idcoach;

    @FXML
    void deleteClick(ActionEvent event) {
             ServiceCoachs cs = new ServiceCoachs();
        int id=Integer. parseInt(idcoach.getText());
        try {
            cs.deletecoach(id);
        } catch (SQLException ex) {
            Logger.getLogger(CoachitemController.class.getName()).log(Level.SEVERE, null, ex);
        }
                try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Coach.fxml"));
            Parent root = loader.load();
            desc.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(CoachitemController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @FXML
    void updateClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Editcoach.fxml"));
            Parent root = loader.load();
            desc.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(LoginFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setCoachImage(String url) {
        this.coachimg.setImage(new Image(url));
    }
    
    public void SetCoachName(String coachname){
       this.coachname.setText(coachname);
    }
    
    public void SetGamename(String gamename){
       this.gamename.setText(gamename);
    }
    
    public void Setdesc(String desc){
       this.desc.setText(desc);
    }
    
    public void Setidcoach(String idcoach){
       this.idcoach.setText(idcoach);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        coachname.setTextFill(Color.web("#000000"));
        
    }    

    @FXML
    private void contatctClick(ActionEvent event) {
        User user = new User();
        try {
            JavaMail.sendMail("ghub2441@gmail.com");
        } catch (Exception ex) {
            Logger.getLogger(CoachitemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
