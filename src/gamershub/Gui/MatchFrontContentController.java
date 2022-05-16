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
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class MatchFrontContentController implements Initializable {

    @FXML
    private AnchorPane mainPane;
    @FXML
    private VBox mainVBox;
    MatchService rs=new MatchService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         List<Matchs> list = rs.readMatch();
        
          System.out.println(rs.readMatch());
        Collections.reverse(list);

        if (!list.isEmpty()) {
           
            for (Matchs abo1 : list) {
              
                mainVBox.getChildren().add(makeAboModel(abo1));
            }
        }
    }
public Parent makeAboModel(Matchs abo1) {
        Parent parent = null;

        try { 
            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MatchFront.fxml")));

            Pane innerContainer = (((Pane) ( (AnchorPane) parent).getChildren().get(0)));
           // ((ImageView) innerContainer.lookup("#myimageview")).setImage(file.toString());
            ((Text) innerContainer.lookup("#MatchName")).setText("Match Name : " + abo1.getMatchName());
            ((Text) innerContainer.lookup("#MatchResult")).setText("Match Result : " + abo1.getResult());
             ((Text) innerContainer.lookup("#MatchDate")).setText("Match Date : " + abo1.getMatchDate());
//             ((Text) innerContainer.lookup("#verified")).setText("verified : " + abo1.getIsVerified());
          

        
        }

        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return parent;
    }        
    
}
