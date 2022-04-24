/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Gui;

import gamershub.Entities.Coach;
import gamershub.Entities.Game;
import gamershub.Entities.Sessioncoaching;
import gamershub.Entities.User;
import gamershub.Gamershub;
import gamershub.Services.GameService;
import gamershub.Services.ServiceCoachs;
import gamershub.Services.ServiceSessions;
import gamershub.Services.UserService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AddsessionController implements Initializable {

    @FXML
    private TextField coachnameField;
    @FXML
    private ComboBox<String> comboclient;
    @FXML
    private DatePicker startdate;
    @FXML
    private DatePicker enddate;
    @FXML
    private TextField Prixfield;
    @FXML
    private Label errorMsg;
    @FXML
    private TextField descField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
             ServiceCoachs us = new ServiceCoachs();
             int id=us.getIdUser(Gamershub.loggedUser.getId());
            coachnameField.setText(String.valueOf(id));
            UserService u = new UserService();
            List<User> list = u.afficher();
            ObservableList<String> listuser = FXCollections.observableArrayList();
            for (User g : list) {
                listuser.add(g.getId() + ":  " + g.getUsername());
            }

            comboclient.setItems(listuser);
        } catch (Exception ex) {
            Logger.getLogger(SessionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     int prix;
    @FXML
    private void addsessionClick(ActionEvent event) {
        boolean check=false;
        System.out.print(startdate.getValue());
        if (startdate.getValue().isBefore(LocalDate.now())||startdate.getValue()==null) {
                     // Tomorrow is too soon.
                     
                     startdate.setStyle("-fx-background-color: #ff4444;");
                     check = true;
                 
                 }
        
        if(enddate.getValue().isBefore(startdate.getValue())||enddate.getValue()==null){
            
            enddate.setStyle("-fx-background-color: #ff4444;");
                     check = true;
        
        }
         if(Prixfield.getText().isEmpty()){
                    Prixfield.setStyle("-fx-text-box-border: #FF0000; -fx-focus-color: #FF0000;");
                    check=true;
               }
        
       
        try
                {
                   prix = Integer.parseInt(Prixfield.getText().trim());

                }
                catch (NumberFormatException nfe)
                {
                  Prixfield.setStyle("-fx-text-box-border: #FF0000; -fx-focus-color: #FF0000;");
 
                  Prixfield.setText( "" ) ;
                  Prixfield.setPromptText("Le prix doit être un entier");
                  System.out.println("NumberFormatException: " + nfe.getMessage());
                  check =true;
                }
        if(descField.getText().isEmpty()){
                    descField.setStyle("-fx-text-box-border: #FF0000; -fx-focus-color: #FF0000;");
                    check=true;
               }
        if (check) return;
        Sessioncoaching c = new Sessioncoaching();
        System.out.println("ghjhjjh");
        c.setCoach(Integer.parseInt(coachnameField.getText()));
        String[] userlist = comboclient.getValue().split(":", 2);
        System.out.println("hi" + coachnameField.getText());
        c.setUser(Integer.parseInt(userlist[0]));
        c.setDescription(descField.getText());
        c.setPrix(Float.parseFloat(Prixfield.getText()));
        c.setDate_debut(Date.valueOf(startdate.getValue()));
        c.setDate_fin(Date.valueOf(enddate.getValue()));
        ServiceSessions us = new ServiceSessions();
        System.out.println(c);
        try {
            us.ajouterr(c);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Coach.fxml"));
                Parent root = loader.load();
                coachnameField.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(AddsessionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    //   public void setCombo(){
//    try {
//            // TODO
//            UserService u = new UserService();
//            List<User> list = u.afficher();
//            ObservableList<String> listuser = FXCollections.observableArrayList();
//            for (User c : list) {
//                listuser.add(c.getId() + ":  " + c.getUsername());
//            }
//
//            gamecombo.setItems(listuser);
//        } catch (Exception ex) {
//             Logger.getLogger(CoachController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
