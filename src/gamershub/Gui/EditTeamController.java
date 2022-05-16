/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Gui;

import Entities.Category;
import Entities.Products;
import gamershub.Entities.Teams;

import gamershub.Services.TeamService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author meriam
 */
public class EditTeamController implements Initializable {

    /**
     * Initializes the controller class.
     */
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
    private TextField productName;
    private TextField description;
    @FXML
    private TextField image;
    private TextField price;
    private TextField quantity;
    @FXML
    private Button cancel;

    private Products product;
    private Teams Teams;
    
    private ComboBox<String> category;
    @FXML
    private Button editProduct;
    @FXML
    private TextField GamersNb;
    @FXML
    private TextField Rank;
    @FXML
    private TextField TeamName;

    public void setProduct(Teams c) {
        this.Teams = c;
       
        TeamName.setText(Teams.getTeamName());
        
        GamersNb.setText(Teams.getGamersNb()+"");
        Rank.setText(Teams.getRank()+"");
        
        
        
   
        
    }

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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
            Parent root = loader.load();
            HomeController controller = loader.getController();
            controller.changePage("Teams");
            
            cancel.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void editTeam(ActionEvent event) {
              
        Teams.setTeamName(TeamName.getText());
        Teams.setGamersNb(Integer.parseInt(GamersNb.getText()));
        
        Teams.setRank(Integer.parseInt(Rank.getText()));
       
       //combo

        TeamService ps = new TeamService();
        ps.modifyTeam(Teams);
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
            Parent root = loader.load();
             HomeController controller = loader.getController();
             controller.changePage("Teams");
            editProduct.getScene().setRoot(root);
            
        } catch (IOException ex) {
            Logger.getLogger(MatchListBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

}
