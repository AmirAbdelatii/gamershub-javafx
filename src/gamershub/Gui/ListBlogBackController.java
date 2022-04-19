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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Blog> items =FXCollections.observableArrayList();
        List<Blog> listblog = rs.ShowBlog();
        for(Blog r : listblog) {
            String ch = r.toString();
            items.add(r);
        }    
        tableBlog.setItems(items);        
    }

    @FXML
    private void deletePost(ActionEvent event) {
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
         rs.UpdateBlog(tableBlog.getSelectionModel().getSelectedItem());
         try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateBlogForm.fxml"));
                Parent root = loader.load();
                btnUpdatePost.getScene().setRoot(root);
              } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
    }
    
}
