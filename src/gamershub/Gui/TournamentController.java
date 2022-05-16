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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import static javafx.scene.input.KeyCode.L;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Ghofrane
 */
public class TournamentController implements Initializable {

    @FXML
    private ListView<Tournaments> listTour;
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
    private Button btnGames1;
    @FXML
    private Button btnGames11;
    @FXML
    private TextField tf_search;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TournamentService ts= new TournamentService();
        List<Tournaments> lt=ts.readTournaments();
        ObservableList<Tournaments> data=FXCollections.observableArrayList(lt);
        listTour.setItems(data);
        
    }    

    @FXML
    private void handleClicks(ActionEvent event) {
    }

    @FXML
    private void ajouter(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("AddTournament.fxml"));
            Parent root=loader.load();
            AddTournamentController aac=loader.getController();
            listTour.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(TournamentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void modifier(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("ModifierTournament1.fxml"));
            Parent root=loader.load();
            ModifierTournamentController aac=loader.getController();
            listTour.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(TournamentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void supprimer(ActionEvent event) {
        Tournaments T = new Tournaments();
        T = listTour.getSelectionModel().getSelectedItem();
        if (T == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alert");
            alert.setHeaderText("Alert");
            alert.setContentText("please choose the tournament to delete");
            alert.show();
        }else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation ");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete this tournament ?");
            Optional<ButtonType> action = alert.showAndWait();

            if (action.get() == ButtonType.OK) {
                TournamentService TS= new TournamentService();
                TS.deleteTournaments(T.getId());
                JOptionPane.showMessageDialog(null, "Tournament deleted");
                loadTournaments();
            }

        }
    }
    
    public void loadTournaments() {
        TournamentService TS = new TournamentService();
        ArrayList<Tournaments> listeTournaments = (ArrayList<Tournaments>) TS.readTournaments();

        ObservableList observableList = FXCollections.observableArrayList(listeTournaments);
        listTour.setItems(observableList);

    }

    @FXML
    private void tournaments(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("Tournament.fxml"));
            Parent root=loader.load();
            TournamentController aac=loader.getController();
            listTour.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(TournamentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void rewards(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("Reward.fxml"));
            Parent root=loader.load();
            RewardController aac=loader.getController();
            listTour.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(TournamentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void search(ActionEvent event) {
        TournamentService TS = new TournamentService();
        ArrayList<Tournaments> listeTournaments = (ArrayList<Tournaments>) TS.searchTournaments(tf_search.getText());

        ObservableList observableList = FXCollections.observableArrayList(listeTournaments);
        listTour.setItems(observableList);
    }

    @FXML
    private void trier(ActionEvent event) {
        TournamentService TS = new TournamentService();
        ArrayList<Tournaments> listeTournaments = (ArrayList<Tournaments>) TS.trierTournaments();

        ObservableList observableList = FXCollections.observableArrayList(listeTournaments);
        listTour.setItems(observableList);
    }

    @FXML
    private void stats(ActionEvent event) {
        try {
        // Load the fxml file and create a new stage for the popup.
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXBarChart.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Statistiques tournaments");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        Window primaryStage = null;
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Set the persons into the controller.
        FXBarChartController controller = loader.getController();
       TournamentService ts= new TournamentService();
        List<Tournaments> lt=ts.readTournaments();
        ObservableList<Tournaments> data=FXCollections.observableArrayList(lt);
        listTour.setItems(data);
        controller.setArticleData(data);

        dialogStage.show();

    } catch (IOException e) {
        e.printStackTrace();
    }
    }



    
}
