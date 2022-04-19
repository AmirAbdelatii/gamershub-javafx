/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Gui;

import gamershub.Entities.Blog;
import gamershub.Services.BlogService;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author amira
 */
public class BlogCardFormController implements Initializable {

    @FXML
    private Label username;
    @FXML
    private Label publishedAt;
    @FXML
    private TextArea post;
    @FXML
    private ImageView userImage;

    public void setUserImage(String url) {
        this.userImage.setImage(new Image(url));
    }
    
    public void setUsername(String username) {
        this.username.setText(username);
    }
    
   /* public void setPublishedAt(Date publishedAt) {
        this.publishedAt.setDate();
    }*/
    
    public void setPost(String post) {
        this.post.setText(post);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

   /* @FXML
    private void updateUserForm(ActionEvent event) {
        try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateBlogForm.fxml"));
                Parent root = loader.load();
                UpdateBlogForm.getScene().setRoot(root);
              } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
    }*/
    
}
