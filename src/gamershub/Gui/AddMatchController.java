/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Gui;


import gamershub.Entities.Matchs;

import gamershub.Services.MatchService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import java.sql.Date;
import javafx.scene.control.DatePicker;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author meriam
 */
public class AddMatchController implements Initializable {

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
    private TextField categoryName;
    private TextField description;
    private TextField image;
    @FXML
    private Button cancel;
    @FXML
    private Button addCategory;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void add(ActionEvent event) {
        categoryNameError.setText("");
        descriptionError.setText("");
        imageError.setText("");
          LocalDate myDate = MatchDate.getValue();
          String myFormattedDate = myDate.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy"));
           

        if (MatchName.getText().length() != 0  && Result.getText().length() != 0) {
            if (Pattern.matches("^[a-zA-Z]*$", MatchName.getText()) == true ) {
                Matchs c = new Matchs();
                c.setMatchName(MatchName.getText());
                
                c.setMatchDate(myFormattedDate);
                c.setResult(Integer.parseInt(Result.getText()));
                MatchService cs = new MatchService();
                cs.createMatch(c);
                try {
                     FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
                    Parent root = loader.load();
                    HomeController controller = loader.getController();
                    controller.changePage("Matchs");
                   
                    addCategory.getScene().setRoot(root);
                      Notifications notificationBuilder = Notifications.create()
                        .title("ajout  avec succce")
                        .text("Bonne chance dans votre match")
                        .graphic(null)
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.TOP_RIGHT)
                        .onAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            System.out.println("Clicked on Notification");        }
    });
                notificationBuilder.showConfirm();
                    
                } catch (IOException ex) {
                    Logger.getLogger(MatchListBackController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                if (Pattern.matches("^[a-zA-Z]*$", MatchName.getText()) == false) {
                    categoryNameError.setText("This field can only contain letters");
                }
               
            }

        } else {
            if (MatchName.getText().length() == 0) {
                categoryNameError.setText("Fill this Field");
            }

           

            if (Result.getText().length() == 0) {
                imageError.setText("Fill this Field");
            }
            if (Pattern.matches("^[0-9]*$", Result.getText()) == false) {
                categoryNameError.setText("This field can only contain letters");
            }
            

        }
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
                    controller.changePage("Matchs");

                    addCategory.getScene().setRoot(root);

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
    }

}
