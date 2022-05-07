/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Gui;


import Entities.Teams;

import gamershub.Services.TeamService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author meriam
 */
public class ItemTeamController implements Initializable {

    @FXML
    private HBox itemC;
    
    @FXML
    private ImageView image;
  
   
    @FXML
    private Label TeamId;
    @FXML
    private Label TeamName;
    @FXML
    private Label GamersNb;
    @FXML
    private Label Rank;
    @FXML
    private Label isVerified;
    @FXML
    private ImageView EditTeam;
    @FXML
    private ImageView deleteTeam;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setTeamId(String TeamId) {
        this.TeamId.setText(TeamId);
    }

    public void setTeamName(String TeamName) {
        this.TeamName.setText(TeamName) ;
    }

    public void setGamersNb(String GamersNb) {
        this.GamersNb.setText(GamersNb);
    }

    public void setRank(String Rank) {
        this.Rank.setText(Rank);
    }

    public void setisVerified(String isVerified) {
        this.isVerified.setText(isVerified);
    }

  
//     public void setImage(String imageUploaded ) {
//        
//         Image img=new Image(imageUploaded);
//         this.image.setImage(img); 
//    }
      public void setImage(String url) {
        this.image.setImage(new Image("file:"+url));
    }

    @FXML
    private void EditTeamButton(MouseEvent event) {
             try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("EditTeam.fxml"));
            Parent root = loader.load();
            EditTeamController controller= loader.getController();
            Teams c =new Teams(Integer.parseInt(TeamId.getText()),TeamName.getText(),Integer.parseInt(GamersNb.getText()),Integer.parseInt(Rank.getText()),Boolean.parseBoolean(isVerified.getText()));
            
            System.out.println(c);
            controller.setProduct(c);
             FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("Home.fxml"));
            Parent homeRoot = homeLoader.load();
            HomeController homeCtrl = homeLoader.getController();
            homeCtrl.changePage( " Team - Edit", root);
            TeamId.getScene().setRoot(homeRoot);

        } catch (IOException ex) {
            Logger.getLogger(MatchListBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void deleteTeamButton(MouseEvent event) {
         
       // System.out.println(id);
        try {
            TeamService ps = new TeamService();
        int id=Integer. parseInt(TeamId.getText());
            ps.delete(id);
        } catch (SQLException ex) {
            Logger.getLogger(ItemMatchController.class.getName()).log(Level.SEVERE, null, ex);
        }
                try {

           FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
            Parent root = loader.load();
              HomeController controller= loader.getController();
            controller.changePage("Teams");
            TeamId.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(MatchListBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
