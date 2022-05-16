/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Gui;

import gamershub.Entities.Rewards;
import gamershub.Entities.Tournaments;
import gamershub.Services.RewardService;
import gamershub.Utils.MyDB;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Ghofrane
 */
public class ModifierRewardController implements Initializable {

    @FXML
    private ComboBox<Integer> combo_id;
    @FXML
    private ComboBox<Integer> combo_tid;
    @FXML
    private TextField tf_type;
    @FXML
    private TextField tf_quantity;

    /**
     * Initializes the controller class.
     */
    Rewards r = new Rewards();
    RewardService rs =new RewardService();
    @FXML
    private Button cancel;
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
            Logger.getLogger(ModifierRewardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            String req="select id from rewards";
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
            combo_id.setItems(id);
        } catch (SQLException ex) {
            Logger.getLogger(ModifierRewardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    private void getId(MouseEvent event) {
        combo_id.setOnAction(e ->{
            String req="select tournament_id_id,type,quantity from rewards where id=?";
            try {
                    PreparedStatement pst = MyDB.getInstance().getCon()
                    .prepareStatement(req);             
                    pst.setInt(1,(Integer)combo_id.getSelectionModel().getSelectedItem());
                ResultSet rs=pst.executeQuery();
                while(rs.next()){
                    combo_tid.setPromptText(rs.getString("tournament_id_id"));
                    tf_type.setText(rs.getString("type"));
                    tf_quantity.setText(rs.getString("quantity"));
                    
                 
                }
            } catch (SQLException ex) {
                Logger.getLogger(ModifierRewardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    @FXML
    private void modifierReward(ActionEvent event) {
        try {
            
        rs.editReward(new Rewards(combo_id.getSelectionModel().getSelectedItem(), combo_tid.getSelectionModel().getSelectedItem(), tf_type.getText(), Integer.parseInt(tf_quantity.getText())));       
        JOptionPane.showMessageDialog(null, "Reward modified");
        
            FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
            Parent root = loader.load(); 
            tf_type.getScene().setRoot(root);
        }catch (IOException ex) {
            Logger.getLogger(ModifierRewardController.class.getName()).log(Level.SEVERE, null, ex);
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
