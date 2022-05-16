/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Gui;

import gamershub.Entities.Rewards;
import gamershub.Entities.Tournaments;
import gamershub.Services.RewardService;
import gamershub.Services.TournamentService;
import gamershub.Utils.MyDB;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Ghofrane
 */
public class AddRewardController implements Initializable {

    @FXML
    private ComboBox<Integer> combo_tid;
    @FXML
    private TextField tf_type;
    @FXML
    private TextField tf_quantity;
    @FXML
    private Button cancel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            String req="select id from tournaments";
            PreparedStatement pst = MyDB.getInstance().getCon()
                    .prepareStatement(req);
            ResultSet rs=pst.executeQuery();
            ObservableList<Integer> id = null;
            List<Integer> list = new ArrayList<>();
            while(rs.next()){
                
                list.add(rs.getInt("id"));
                
            }
            id = FXCollections
                    .observableArrayList(list);
            combo_tid.setItems(id);
        } catch (SQLException ex) {
            Logger.getLogger(AddRewardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void ajouterReward(ActionEvent event) {
        try {
            //// SAVE Reward IN DB
            String tounamentId = combo_tid.getSelectionModel().getSelectedItem().toString();
            String type=tf_type.getText();
            String quantity=tf_quantity.getText();
            
                  

            if((tounamentId.equals(""))||(type.equals(""))||(quantity.equals(""))){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerte");
            alert.setHeaderText("Alerte");
            alert.setContentText("Veuillez remplir les champs vides");
            alert.show();
        }
            else
            {
            
            Rewards r = new Rewards(20,Integer.parseInt(tounamentId),type,Integer.parseInt(quantity));

            RewardService pcd = new RewardService();
            pcd.createReward(r);
            JOptionPane.showMessageDialog(null, "Reward ajouté");

            
            //REDIRECTION
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("home.fxml"));
            Parent root = loader.load(); 
            tf_type.getScene().setRoot(root);}
        } catch (IOException ex) {
            Logger.getLogger(AddRewardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      @FXML
    private void cancel(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
            Parent root = loader.load();
            HomeController controller = loader.getController();
            controller.changePage("Reward");
            
            cancel.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    
}
