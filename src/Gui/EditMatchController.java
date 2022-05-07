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
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author meriam
 */
public class EditMatchController implements Initializable {

    @FXML
    private Button btnOverview;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button btnMenus;
    @FXML
    private Button btnPackages;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnSignout;
    @FXML
    private Pane pnlOverview;
  
    private TextField image;
    @FXML
    private Button cancel;
    @FXML
    private Button EditCategory;

    private Category category;
    @FXML
    private Label categoryNameError;
    @FXML
    private Label descriptionError;
    @FXML
    private Label imageError;
    @FXML
    private TextField MatchName;
    @FXML
    private DatePicker MatchDate;
    @FXML
    private TextField Result;
    private Matchs Matchs;



    public void setMatch(Matchs c) {
        this.Matchs = c;
        MatchName.setText(Matchs.getMatchName());
        Result.setText(Matchs.getResult()+"");
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    @FXML
    private void handleClicks(ActionEvent event) {
    }

    @FXML
    private void cancel(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("MatchListBack.fxml"));
            Parent root = loader.load();
            EditCategory.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(MatchListBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Edit(ActionEvent event) {
        

        if (MatchName.getText().length() != 0 ) {
            if (Pattern.matches("^[a-zA-Z]*$",  MatchName.getText()) == true ) {

                Matchs.setMatchName(MatchName.getText());
                Matchs.setResult(Integer.parseInt(Result.getText()));
              
                MatchService cs = new MatchService();
                cs.modifyMatch(Matchs);
                try {
                    
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("MatchListBack.fxml"));
                    Parent root = loader.load();
                    EditCategory.getScene().setRoot(root);
                    
                } catch (IOException ex) {
                    Logger.getLogger(MatchListBackController.class.getName()).log(Level.SEVERE, null, ex);
                }}

    }

    }

    @FXML
    private void Edit1(MouseEvent event) {if (MatchName.getText().length() != 0 ) {
            if (Pattern.matches("^[a-zA-Z]*$",  MatchName.getText()) == true ) {

                Matchs.setMatchName(MatchName.getText());
                Matchs.setResult(Integer.parseInt(Result.getText()));
              
                MatchService cs = new MatchService();
                cs.modifyMatch(Matchs);
                try {
                    
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("MatchListBack.fxml"));
                    Parent root = loader.load();
                    EditCategory.getScene().setRoot(root);
                    
                } catch (IOException ex) {
                    Logger.getLogger(MatchListBackController.class.getName()).log(Level.SEVERE, null, ex);
                }
      
    }

    }
    }

    }


