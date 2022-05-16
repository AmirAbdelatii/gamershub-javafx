/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Gui;

import static gamershub.Gui.BlogFormContentController.currentBlog;
import gamershub.Entities.Comments;
import gamershub.Services.CommentsService;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author amira
 */
public class CommentFormContentController implements Initializable {

    @FXML
    private FlowPane content;
    @FXML
    private Button btnAddComment;
    @FXML
    private Button home;
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            CommentsService us = new CommentsService();

        try {
            List<Comments> CommentsList = us.ShowComments(currentBlog);
             
            for (Comments b : CommentsList) {
                
                content.getChildren().add(makeCommentsModel(b));
                System.out.println(b);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }    


    public Parent makeCommentsModel(Comments b) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("CommentCardForm.fxml")));
            VBox innerContainer = ((VBox) ( ((AnchorPane) parent).getChildren().get(0)));
            
            ((Text) innerContainer.lookup("#comment")).setText( b.getComment());
            

            
         //((Pane) innerContainer.lookup("#ajouCommentaire")).setVisible(true);

         //   ((Button) innerContainer.lookup("#ajouterCom")).setOnAction((event) ->ajouterComAbo());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return parent;
    }
    @FXML
    private void handleClicks(ActionEvent event) {
        try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("AddCommentForm.fxml"));
                Parent root = loader.load();
                content.getScene().setRoot(root);
              } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
    }

    @FXML
    private void homeClicked(ActionEvent event) {
        try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeFront.fxml"));
                Parent root = loader.load();
                content.getScene().setRoot(root);
              } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
    }
    
    
}
