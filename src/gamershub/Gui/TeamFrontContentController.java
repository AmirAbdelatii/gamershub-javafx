/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Gui;

import gamershub.Entities.Teams;
import gamershub.Services.TeamService;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class TeamFrontContentController implements Initializable {

    @FXML
    private AnchorPane mainPane;
    @FXML
    private VBox mainVBox;
    TeamService rs=new TeamService();
    @FXML
    private Button btnAddTeam;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        List<Teams> list = rs.readTeam();
        
          System.out.println(rs.readTeam());
        Collections.reverse(list);

        if (!list.isEmpty()) {
           
            for (Teams abo1 : list) {
              
                mainVBox.getChildren().add(makeAboModel(abo1));
            }
        }
        else {
            
        }
    }
public Parent makeAboModel(Teams abo1) {
        Parent parent = null;

        try {
            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("TeamFront.fxml")));

            Pane innerContainer = (((Pane) ( (AnchorPane) parent).getChildren().get(0)));
           // ((ImageView) innerContainer.lookup("#myimageview")).setImage(file.toString());
            ((Text) innerContainer.lookup("#username")).setText("Username : " + abo1.getTeamName());
            ((Text) innerContainer.lookup("#gamersnb")).setText("gamersnb : " + abo1.getGamersNb());
             ((Text) innerContainer.lookup("#rank")).setText("rank : " + abo1.getRank());
//             ((Text) innerContainer.lookup("#verified")).setText("verified : " + abo1.getIsVerified());
          

        
        }

        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return parent;
    }    
    @FXML
    private void AddTeam(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddTeamFront.fxml"));
            Parent root = loader.load();
            mainPane.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(LoginFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
