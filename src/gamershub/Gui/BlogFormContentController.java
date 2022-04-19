/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Gui;

import gamershub.Entities.Blog;
import gamershub.Services.BlogService;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author amira
 */
public class BlogFormContentController implements Initializable {


    @FXML
    private FlowPane content;
    @FXML
    private Button btnAddPost;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
               
        try {
            BlogService us = new BlogService();
            List<Blog> blogsList = us.ShowBlog();

            for (Blog u : blogsList) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("BlogCardForm.fxml"));
                Parent root = loader.load();
                BlogCardFormController cont = loader.getController();
              System.out.println(u.getUser());
                cont.setUserImage("https://avatars.dicebear.com/api/bottts/" + u.getUser().getUsername() + ".png");
                cont.setUsername(u.getUser().getUsername());
                //cont.setPublishedAt(u.getPublishedAt());
                cont.setPost(u.getDescription());
                content.getChildren().add(root);
            }

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
              } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
    }
    
}
