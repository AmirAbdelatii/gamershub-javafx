/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Gui;
import static gamershub.Gui.BlogFormContentController.currentBlog;

import gamershub.Entities.Blog;
import gamershub.Entities.Comments;
import gamershub.Gamershub;
import gamershub.Services.BlogService;
import gamershub.Services.CommentsService;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author amira
 */
public class AddCommentFormController implements Initializable {

    @FXML
    private TextField addComment;
    @FXML
    private Button postComment;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addComment(ActionEvent event) {
        Comments b = new Comments();
        b.setComment(addComment.getText());
        CommentsService bs = new CommentsService();
        if (addComment.getText().length() != 0){
        try {
                    System.out.println(addComment.getText());
        bs.AddComments(b, currentBlog, Gamershub.loggedUser);
        System.out.println(b);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Comment added");
        alert.setContentText("Comment added successfully");
        alert.show();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        } else if (addComment.getText().length() == 0 ){
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Check your Comment ");
        alert.setContentText("Your comment cannot be empty !");
        alert.show();
        }
        
        try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeFront.fxml"));
                Parent root = loader.load();
                addComment.getScene().setRoot(root);
              } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
    }
    
}
