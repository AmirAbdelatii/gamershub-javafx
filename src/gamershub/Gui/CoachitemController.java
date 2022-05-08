/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Gui;

import gamershub.Entities.Coach;
import gamershub.Entities.User;
import gamershub.Gamershub;
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
import javafx.scene.control.Button;
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
    private Label gameid;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btndelete;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        coachname.setTextFill(Color.web("#000000"));
        ServiceCoachs us= new ServiceCoachs();
        CoachContentController coachC = new CoachContentController();
        System.out.println(coachC.i);
        if(coachC.i==0)
        {
            btndelete.setVisible(false);
            btnupdate.setText("Contact");
        }
        else if(coachC.i==1){
        btndelete.setVisible(true);
            btnupdate.setText("Update");
        }
    }    

    @FXML
    private void updateClick(ActionEvent event) {
        
        if(!btnupdate.getText().equals("Update"))
        {
            User user = new User();
        try {
            JavaMail.sendMail("ghub2441@gmail.com");
        } catch (Exception ex) {
            Logger.getLogger(CoachitemController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        else if (btnupdate.getText().equals("Update")) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Editcoach.fxml"));
            Parent root = loader.load();
            EditcoachController controller =loader.getController();
            Coach c = new Coach(Integer.parseInt(idcoach.getText()),coachname.getText(),gamename.getText(),desc.getText(),Integer.parseInt(gameid.getText()));
            controller.remplir(c);
            desc.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(LoginFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }

    @FXML
    private void deleteClick(ActionEvent event) {
        ServiceCoachs cs = new ServiceCoachs();
        int id=Integer. parseInt(idcoach.getText());
        try {
            cs.deletecoach(id);
        } catch (SQLException ex) {
            Logger.getLogger(CoachitemController.class.getName()).log(Level.SEVERE, null, ex);
        }
                try {

            FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("HomeFront.fxml"));
               Parent homeNode = homeLoader.load();
            ((HomeFrontController) homeLoader.getController()).changePage("Coachs");
                coachname.getScene().setRoot(homeNode);

        } catch (IOException ex) {
            Logger.getLogger(CoachitemController.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public void Setidgame(String gameid){
       this.gameid.setText(gameid);
    }
    
    
}
