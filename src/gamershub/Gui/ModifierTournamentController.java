/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Gui;

import gamershub.Entities.Tournaments;
import gamershub.Services.TournamentService;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Ghofrane
 */
public class ModifierTournamentController implements Initializable {

    @FXML
    private ComboBox<Integer> combo_id;
    @FXML
    private TextField tf_name;
    @FXML
    private TextField tf_description;
    @FXML
    private TextField tf_teamsize;
    @FXML
    private TextField tf_maxteam;
    @FXML
    private TextField tf_images;
    @FXML
    private DatePicker startDate;
    @FXML
    private DatePicker endDate;

    /**
     * Initializes the controller class.
     */
    Tournaments t = new Tournaments();
    TournamentService ts= new TournamentService();
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
            combo_id.setItems(id);
        } catch (SQLException ex) {
            Logger.getLogger(ModifierTournamentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    private void getId(MouseEvent event) {
        combo_id.setOnAction(e ->{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
            String req="select name,decription,team_size,start_date,finish_date,max_t,images from tournaments where id=?";
            try {
                    PreparedStatement pst = MyDB.getInstance().getCon()
                    .prepareStatement(req);             
                    pst.setInt(1,(Integer)combo_id.getSelectionModel().getSelectedItem());
                ResultSet rs=pst.executeQuery();
                while(rs.next()){
                    tf_name.setText(rs.getString("name"));
                    tf_description.setText(rs.getString("decription"));
                    tf_teamsize.setText(rs.getString("team_size"));
                    startDate.setValue(LocalDate.parse(rs.getDate("start_date").toString(),formatter));
                    endDate.setValue(LocalDate.parse(rs.getDate("finish_date").toString(),formatter));
                    tf_maxteam.setText(rs.getString("max_t"));
                    tf_images.setText(rs.getString("images"));
                 
                }
            } catch (SQLException ex) {
                Logger.getLogger(ModifierTournamentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    @FXML
    private void modifierTournament(ActionEvent event) {
        try {
            
        ts.editTournament(new Tournaments(combo_id.getSelectionModel().getSelectedItem(), tf_name.getText(), tf_description.getText(), Integer.parseInt(tf_teamsize.getText()),Date.valueOf(startDate.getValue()),Date.valueOf(endDate.getValue()),Integer.parseInt(tf_maxteam.getText()),tf_images.getText()));       
        JOptionPane.showMessageDialog(null, "Tournament modifié");
        
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("home.fxml"));
            Parent root = loader.load(); 
            tf_name.getScene().setRoot(root);
        }catch (IOException ex) {
            Logger.getLogger(ModifierTournamentController.class.getName()).log(Level.SEVERE, null, ex);
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
