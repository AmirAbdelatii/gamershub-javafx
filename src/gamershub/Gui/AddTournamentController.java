/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Gui;

import gamershub.Entities.Tournaments;
import gamershub.Services.TournamentService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Ghofrane
 */
public class AddTournamentController implements Initializable {

    @FXML
    private TextField tf_name;
    @FXML
    private TextField tf_description;
    @FXML
    private TextField tf_teamsize;
    @FXML
    private TextField tf_maxteam;
    @FXML
    private DatePicker startDate;
    @FXML
    private DatePicker endDate;
    @FXML
    private TextField tf_images;
    @FXML
    private Button cancel;
    @FXML
    private Button uploadImage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterTournament(ActionEvent event) {
        try {
            //// SAVE Tournament IN DB
            String name = tf_name.getText();
            String description = tf_description.getText();
            String teamSize = tf_teamsize.getText();
            String teamMax = tf_maxteam.getText();
            String images=tf_images.getText();
            
            Date debutDate = Date.valueOf(startDate.getValue());
            Date finDate = Date.valueOf(endDate.getValue());
            

            if((name.equals(""))||(description.equals(""))||(teamSize.equals(""))||(teamMax.equals(""))||(images.equals(""))||(debutDate.equals(""))||(finDate.equals(""))){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerte");
            alert.setHeaderText("Alerte");
            alert.setContentText("Please fill in the empty fields");
            alert.show();
        }
            else
            {
            
            Tournaments t = new Tournaments(20, name, description,Integer.parseInt(teamSize),debutDate,finDate,Integer.parseInt(teamMax),images);

            TournamentService pcd = new TournamentService();
            pcd.createTournament(t);
            JOptionPane.showMessageDialog(null, "Tournament added");

            
            //REDIRECTION
            FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
            Parent root = loader.load(); 
            tf_name.getScene().setRoot(root);}
        } catch (IOException ex) {
            Logger.getLogger(AddTournamentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     @FXML
    private void cancel(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
            Parent root = loader.load();
            HomeController controller = loader.getController();
            controller.changePage("Tournament");
            
            cancel.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
}
