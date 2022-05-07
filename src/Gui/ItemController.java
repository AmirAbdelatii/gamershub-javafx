/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Category;
import Entities.Matchs;

import Services.CategoryService;
import Services.MatchService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author meriam
 */
public class ItemController implements Initializable {

    @FXML
    private HBox itemC;
    private Label categoryId;
    private Label categoryName;
    private Label description;
    private Label creationDate;
    private Label modificationDate;
    private Label isEnabled;
    @FXML
    private ImageView EditCategory;
    @FXML
    private ImageView deleteCategory;
    @FXML
    private Label MatchId;
    @FXML
    private Label MatchName;
    @FXML
    private Label Result;
    @FXML
    private Label MatchDate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    public void setMatchId(String MatchId ) {
        this.MatchId.setText(MatchId);
    }

    public void setMatchName(String MatchName) {
        this.MatchName.setText(MatchName); 
    }

    public void setMatchDate(String myFormattedDate) {
       // this.MatchDate.setText(MatchDate);
        
    }

   

   

    public void setResult(String Result ) {
        this.Result.setText(Result); 
    }
    

    @FXML
    private void deleteMatchButton(MouseEvent event) {
        MatchService cs = new MatchService();
        int id=Integer.parseInt(MatchId.getText());
        try {
            cs.delete(id);
        } catch (SQLException ex) {
            Logger.getLogger(ItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
                try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("MatchListBack.fxml"));
            Parent root = loader.load();
            MatchId.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(MatchListBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void EditMatchButton(MouseEvent event) {
        
            try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("EditMatch.fxml"));
            Parent root = loader.load();
            EditMatchController controller= loader.getController();
            Matchs c =new Matchs(Integer.parseInt(MatchId.getText()),MatchName.getText(),Integer.parseInt(Result.getText()));
            System.out.println(c);
            controller.setMatch(c);
            MatchId.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(MatchListBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
