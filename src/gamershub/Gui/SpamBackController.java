/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Gui;

import gamershub.Entities.Spam;
import gamershub.Services.BlogService;
import gamershub.Services.SpamService;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author amira
 */
public class SpamBackController implements Initializable {

    @FXML
    private ListView<Spam> tableSpam;
    @FXML
    private Button btnDeleteSpam;
    @FXML
    private Button btnKeepPost;
    private SpamService rs = new SpamService();
        private BlogService bs = new BlogService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
ObservableList<Spam> items =FXCollections.observableArrayList();
        List<Spam> listspam = rs.ShowSpamBack();
        tableSpam.setOnMouseClicked(event -> {
           Spam selected= tableSpam.getSelectionModel().getSelectedItem();
           System.out.println(selected);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Nice selection ! ");
        alert.setContentText("You selected:"+selected+ "!");
        alert.show();
        });
        for(Spam r : listspam) {
            String ch = r.toString();
            items.add(r);
        }    
        tableSpam.setItems(items);            }    


    @FXML
    private void keepPost(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm your choice! ");
        alert.setContentText("Do you really want to keep this post ?");
        alert.show();
        rs.DeleteSpam(tableSpam.getSelectionModel().getSelectedItem().getId());
        bs.DeleteBlog(tableSpam.getSelectionModel().getSelectedItem().getBlog().getId());
      ObservableList<Spam> items =FXCollections.observableArrayList();
        List<Spam> listspam = rs.ShowSpamBack();
        for(Spam r : listspam) {
            String ch = r.toString();
            items.add(r);
        }
       
    tableSpam.setItems(items);
    
    }

    @FXML
    private void deleteSpam(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm your choice! ");
        alert.setContentText("Do you really want to delete this post ?");
        alert.show();
        rs.DeleteSpam(tableSpam.getSelectionModel().getSelectedItem().getId());
      ObservableList<Spam> items =FXCollections.observableArrayList();
        List<Spam> listspam = rs.ShowSpamBack();
        for(Spam r : listspam) {
            String ch = r.toString();
            items.add(r);
        }
       
    tableSpam.setItems(items);
    
    }
    
}
