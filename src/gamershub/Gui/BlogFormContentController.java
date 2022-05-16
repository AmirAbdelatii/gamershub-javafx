/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Gui;

import static gamershub.Gamershub.loggedUser;
import gamershub.Entities.Blog;
import gamershub.Entities.Spam;
import gamershub.Services.BlogService;
import gamershub.Entities.User;
import gamershub.Gamershub;
import gamershub.Services.SpamService;
import gamershub.Services.UserService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author amira
 */
public class BlogFormContentController implements Initializable {
    public static Blog currentBlog;
    BlogService us = new BlogService();
    @FXML
    private VBox content;
    @FXML
    private Button btnAddPost;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        try {
            List<Blog> blogsList = us.ShowBlog();
             
            for (Blog b : blogsList) {
                                System.out.println(b);
                us.BadWords(b);
                content.getChildren().add(makeBlogModel(b));
                System.out.println(b);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }    


    public Parent makeBlogModel(Blog b) {
        Parent parent = null;
        BlogService us = new BlogService();
        try {
            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("BlogCardForm.fxml")));
            VBox innerContainer = ((VBox) ( ((AnchorPane) parent).getChildren().get(0)));
            
            ((Text) innerContainer.lookup("#username")).setText(b.getUser().getUsername());
            ((Text) innerContainer.lookup("#post")).setText(b.getDescription());
            ((Text) innerContainer.lookup("#views")).setText("Views =" +b.getViews()+"");

            




            
         //((Pane) innerContainer.lookup("#ajouCommentaire")).setVisible(true);
        ((Button) innerContainer.lookup("#comments")).setOnAction((event) -> showCommentsb(b));

          ((Button) innerContainer.lookup("#spam1")).setOnAction((event) ->addSpam(b,event));

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return parent;
    }
    
    
    private void showCommentsb(Blog b){
        currentBlog = b;
        try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("CommentFormContent.fxml"));
                Parent root = loader.load();
                content.getScene().setRoot(root);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        us.increment(b);
    }
    
    private void addSpam(Blog b, ActionEvent event){
        currentBlog = b;
        User u = Gamershub.loggedUser;
        Spam s = new Spam();
        s.setBlog(b);
        s.setUser(Gamershub.loggedUser);
        SpamService bs = new SpamService();
        try {
        bs.AddSpam(s,b,u);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Reported !");
        alert.setContentText("Post is now in review");
        alert.show();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @FXML
    private void handleClicks(ActionEvent event) {
        System.out.println("Works");
        try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("AddBlogForm.fxml"));
             
                Parent root = loader.load();
                btnAddPost.getScene().setRoot(root);
                //content.getChildren().add(root);
                        System.out.println("Works");

                
              } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
    }
    
}
