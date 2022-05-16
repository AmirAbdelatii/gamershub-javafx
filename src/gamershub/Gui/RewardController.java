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
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Ghofrane
 */
public class RewardController implements Initializable {

    @FXML
    private ImageView userImage;
    @FXML
    private Label usernameLabel;
    @FXML
    private Button btnOverview;
    @FXML
    private Button btnUsers;
    @FXML
    private Button btnGames;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnPackages;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnSignout;
    @FXML
    private Label titleLabel;
    @FXML
    private Pane mainContent;
    @FXML
    private ListView<Rewards> listReward;
    @FXML
    private Button btnGames1;
    @FXML
    private Button btnGames2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        RewardService rs= new RewardService();
        List<Rewards> lr=rs.readRewards();
        ObservableList<Rewards> data=FXCollections.observableArrayList(lr);
        listReward.setItems(data);
    }    

    @FXML
    private void handleClicks(ActionEvent event) {
    }

    @FXML
    private void ajouter(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("AddReward1.fxml"));
            Parent root=loader.load();
            AddRewardController aac=loader.getController();
            listReward.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(RewardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void modifier(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("ModifierReward1.fxml"));
            Parent root=loader.load();
            ModifierRewardController aac=loader.getController();
            listReward.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(RewardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void supprimer(ActionEvent event) {
        Rewards R = new Rewards();
        R = listReward.getSelectionModel().getSelectedItem();
        if (R == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alert");
            alert.setHeaderText("Alert");
            alert.setContentText("please choose the rewrd to be deleted");
            alert.show();
        }else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation ");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure yu want to delet this reward?");
            Optional<ButtonType> action = alert.showAndWait();

            if (action.get() == ButtonType.OK) {
                RewardService RS= new RewardService();
                RS.deleteReward(R.getId());
                JOptionPane.showMessageDialog(null, "Reward deleted");
                loadRewards();
            }

        }
    }
    
    public void loadRewards() {
        RewardService RS = new RewardService();
        ArrayList<Rewards> listeRewards = (ArrayList<Rewards>) RS.readRewards();

        ObservableList observableList = FXCollections.observableArrayList(listeRewards);
        listReward.setItems(observableList);

    }

    @FXML
    private void tournaments(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("Tournament.fxml"));
            Parent root=loader.load();
            TournamentController aac=loader.getController();
            listReward.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(RewardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void rewards(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("Reward.fxml"));
            Parent root=loader.load();
            RewardController aac=loader.getController();
            listReward.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(RewardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
