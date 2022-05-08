/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Gui;

import gamershub.Entities.Sessioncoaching;
import gamershub.Entities.User;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class SessionEditController implements Initializable {

    @FXML
    private TextField coachnameField;
    @FXML
    private ComboBox<String> comboclient;
    @FXML
    private DatePicker startdate;
    @FXML
    private DatePicker enddate;
    @FXML
    private TextField descField;
    @FXML
    private TextField Prixfield;
    @FXML
    private Label errorMsg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     int id;
    public void remplir(Sessioncoaching c)
    {
        id= c.getId();
        coachnameField.setText(String.valueOf(c.getCoach()));
        comboclient.setValue(String.valueOf(c.getUser()));
        //adrTextField.setText(c.getAdresse());
        startdate.setValue(c.getDate_debut().toLocalDate());
        enddate.setValue(c.getDate_fin().toLocalDate());
        Prixfield.setText(String. valueOf(c.getPrix()));
        descField.setText(c.getDescription());
        try {
             //ServiceCoachs us = new ServiceCoachs();
             //int id=us.getIdUser(Gamershub.loggedUser.getId());
            //coachnameField.setText(String.valueOf(id));
            UserService u = new UserService();
            List<User> list = u.afficher();
            ObservableList<String> listuser = FXCollections.observableArrayList();
            for (User g : list) {
                listuser.add(g.getId() + ":  " + g.getUsername());
            }

            comboclient.setItems(listuser);
        } catch (Exception ex) {
            Logger.getLogger(SessionEditController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    int prix;
    @FXML
    private void updatesessionClick(ActionEvent event) {
         boolean check=false;
        System.out.print(startdate.getValue());
        if (startdate.getValue().isBefore(LocalDate.now())||startdate.getValue()==null) {
                     // Tomorrow is too soon.
                     
                     errorMsg.setText("DAY should be greater than today");
                     check = true;
                 
                 }
        
        if(enddate.getValue().isBefore(startdate.getValue())||enddate.getValue()==null){
            
            errorMsg.setText("End DAY should be greater than Start today");
                     check = true;
        
        }
         if(Prixfield.getText().isEmpty()){
                    errorMsg.setText("fill the field");;
                    check=true;
               }
        
       
        try
                {
                   prix = Integer.parseInt(Prixfield.getText().trim());

                }
                catch (NumberFormatException nfe)
                {
                  Prixfield.setText( "" ) ;
                  Prixfield.setPromptText("The Price should be a number");
                  System.out.println("NumberFormatException: " + nfe.getMessage());
                  check =true;
                }
        if(descField.getText().isEmpty()){
                    errorMsg.setText("Description must be filled");;
                    check=true;
               }
        if (check) return;
        Sessioncoaching c = new Sessioncoaching();
        c.setId(id);
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
            us.update(c);
            try {
                FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("HomeFront.fxml"));
               Parent homeNode = homeLoader.load();
            ((HomeFrontController) homeLoader.getController()).changePage("btnsession");
                Prixfield.getScene().setRoot(homeNode);
            } catch (IOException ex) {
                Logger.getLogger(SessionEditController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void cancelClick(ActionEvent event) {
        try {
                FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("HomeFront.fxml"));
               Parent homeNode = homeLoader.load();
            ((HomeFrontController) homeLoader.getController()).changePage("btnsession");
                Prixfield.getScene().setRoot(homeNode);
            } catch (IOException ex) {
                Logger.getLogger(SessionEditController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
