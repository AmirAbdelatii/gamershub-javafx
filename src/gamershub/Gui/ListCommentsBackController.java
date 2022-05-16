/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Gui;

import gamershub.Entities.Comments;
import gamershub.Services.CommentsService;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author amira
 */
public class ListCommentsBackController implements Initializable {

    @FXML
    private Button btnUpdateComments;
    @FXML
    private ListView<Comments> tableComments;
    private CommentsService rs = new CommentsService();
    public static Comments currentComment;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Comments> items =FXCollections.observableArrayList();
        List<Comments> listcomment = rs.ShowCommentsBack();
        tableComments.setOnMouseClicked(event -> {
           Comments selected= tableComments.getSelectionModel().getSelectedItem();
           System.out.println(selected);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Nice selection ! ");
        alert.setContentText("You selected:"+selected+ "!");
        alert.show();
        });
        for(Comments r : listcomment) {
            String ch = r.toString();
            items.add(r);
        }    
        tableComments.setItems(items);        
    }    

    @FXML
    private void deleteComments(ActionEvent event) {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm your choice! ");
        alert.setContentText("Do you really want to delete this post ?");
        alert.show();
        rs.DeleteComments(tableComments.getSelectionModel().getSelectedItem().getId());
      ObservableList<Comments> items =FXCollections.observableArrayList();
        List<Comments> listcomment = rs.ShowCommentsBack();
        for(Comments r : listcomment) {
            String ch = r.toString();
            items.add(r);
        }
       
    tableComments.setItems(items);
    }

    @FXML
    private void updateComments(ActionEvent event) {
        currentComment = tableComments.getSelectionModel().getSelectedItem();
         try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateCommentForm.fxml"));
                Parent root = loader.load();
                btnUpdateComments.getScene().setRoot(root);
              } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
    }


    
}
