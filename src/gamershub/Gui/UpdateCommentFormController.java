/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Gui;

import gamershub.Entities.Comments;
import static gamershub.Gui.ListCommentsBackController.currentComment;
import gamershub.Services.CommentsService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author amira
 */
public class UpdateCommentFormController implements Initializable {

    @FXML
    private TextField updateComment;
    @FXML
    private Button btnUpdateComment;
    private Comments b = currentComment;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void updateComment(ActionEvent event) {
         if (updateComment.getText().length() != 0){
             b.setComment(updateComment.getText());
             b.setCommentedAt(new Date(System.currentTimeMillis()));
             
             CommentsService bs = new CommentsService();
             
             
             try {
                 bs.UpdateComments(b);
                 FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
                 Parent root = loader.load();
                 updateComment.getScene().setRoot(root);
                 
             } catch (IOException ex) {
                 Logger.getLogger(ListCommentsBackController.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
    }
    
}
