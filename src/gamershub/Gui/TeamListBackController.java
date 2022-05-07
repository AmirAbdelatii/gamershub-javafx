/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Gui;


import Entities.Teams;
import Entities.Matchs;

import gamershub.Services.TeamService;
import gamershub.Services.MatchService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author meriam
 */
public class TeamListBackController implements Initializable {

    @FXML
    private Button btnOverview;
    @FXML
    private Button btnPackages;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnSignout;
    @FXML
    private Pane pnlOverview;
    @FXML
    private HBox itemC;
    @FXML
    private VBox ListProd;
    
    @FXML
    private Button addTeamButton;
    @FXML
    private Button btnMatch;
    @FXML
    private Button btnTeam;
    @FXML
    private Button excel;
    @FXML
    private Button btnStatitcs;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
             TeamService ps = new TeamService();
             List<Teams> Teams = ps.readTeam();
             //            try {
             for (Teams p : Teams) {
                 
                 
                 FXMLLoader item = new FXMLLoader(getClass().getResource("ItemTeam.fxml"));
                 try {
                     Parent itek = item.load();
                     ItemTeamController itemController =item.getController();
                     //System.out.print(itemController);
                     
                     itemController.setTeamId(p.getId()+"");
                     itemController.setTeamName(p.getTeamName());
                     itemController.setGamersNb(p.getGamersNb()+"");
                     itemController.setRank(p.getRank()+"");
                     itemController.setisVerified(p.getIsVerified()+"");
                     itemController.setImage("..\\..\\..\\..\\..\\Desktop\\webVersionFinal\\public\\shop\\images\\"+p.getImage());
                     
                     
                     ListProd.getChildren().add(itek);
                 } catch (IOException ex) {
                     Logger.getLogger(MatchListBackController.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
    }    

    @FXML
    private void handleClicks(ActionEvent event) {
           try {
           
           
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddTeam.fxml"));
            Parent root = loader.load();
            AddTeamController cont=loader.getController();
            
            addTeamButton.getScene().setRoot(root);
           
        } catch (IOException ex) {
            Logger.getLogger(MatchListBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void showMatchs(ActionEvent event) {
                 try {
           
           
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MatchListBack.fxml"));
            Parent root = loader.load();
            btnMatch.getScene().setRoot(root);
           
        } catch (IOException ex) {
            Logger.getLogger(MatchListBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showTeams(ActionEvent event) {
             try {
           
           
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TeamListBack.fxml"));
            Parent root = loader.load();
            btnTeam.getScene().setRoot(root);
           
        } catch (IOException ex) {
            Logger.getLogger(MatchListBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    private void showTeam(ActionEvent event) {
    }

    @FXML
    private void ShowStatitcs(ActionEvent event) {
        try {
           
           
            FXMLLoader loader = new FXMLLoader(getClass().getResource("statics.fxml"));
            Parent root = loader.load();
            addTeamButton.getScene().setRoot(root);
           
        } catch (IOException ex) {
            Logger.getLogger(MatchListBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void excel(ActionEvent event) {
         TeamService ps = new TeamService();
         ps.generateExcel();
          
    }
    
}
