/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Gui;

import gamershub.Entities.Blog;
import gamershub.Services.BlogService;
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
public class ListBlogBackController implements Initializable {

    @FXML
    private ListView<Blog> tableBlog;
    private BlogService rs = new BlogService();
    @FXML
    private Button btnUpdatePost;
    @FXML
    private Button btnAddPost;
    public static Blog currentBlog;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Blog> items =FXCollections.observableArrayList();
        List<Blog> listblog = rs.ShowBlog();
        tableBlog.setOnMouseClicked(event -> {
           Blog selected= tableBlog.getSelectionModel().getSelectedItem();
           System.out.println(selected);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Nice selection ! ");
        alert.setContentText("You selected:"+selected+ "!");
        alert.show();
        });
        for(Blog r : listblog) {
            String ch = r.toString();
            items.add(r);
        }    
        tableBlog.setItems(items);        
    }

    @FXML
    public void deletePost(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm your choice! ");
        alert.setContentText("Do you really want to delete this post ?");
        alert.show();
        rs.DeleteBlog(tableBlog.getSelectionModel().getSelectedItem().getId());
      ObservableList<Blog> items =FXCollections.observableArrayList();
        List<Blog> listblog = rs.ShowBlog();
        for(Blog r : listblog) {
            String ch = r.toString();
            items.add(r);
        }
       
    tableBlog.setItems(items);
  
    }

    @FXML
    private void updatePost(ActionEvent event) {
        currentBlog = tableBlog.getSelectionModel().getSelectedItem();
         try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateBlogForm.fxml"));
                Parent root = loader.load();
                btnUpdatePost.getScene().setRoot(root);
              } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
    }

    @FXML
    private void addPost(ActionEvent event) {
        try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("AddBlogForm.fxml"));
                Parent root = loader.load();
                btnAddPost.getScene().setRoot(root);
              } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
    }
    
}
