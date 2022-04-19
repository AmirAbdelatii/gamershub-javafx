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
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author amira
 */
public class UpdateBlogFormController implements Initializable {

    @FXML
    private TextField updateTitle;
    @FXML
    private Button btnUpdateBlog;
    private Blog b;
    @FXML
    private TextArea updateContent;

    public void setProduct() {
        updateTitle.setText(b.getTitle());
        updateContent.setText(b.getDescription());
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void updatePost(ActionEvent event) {
         if (updateTitle.getText().length() != 0 && updateContent.getText().length() != 0){
             b.setTitle(updateTitle.getText());
             b.setDescription(updateContent.getText());
             b.setPublishedAt(new Date(System.currentTimeMillis()));
             
             BlogService bs = new BlogService();
             
             bs.UpdateBlog(b);
             try {
                 
                 FXMLLoader loader = new FXMLLoader(getClass().getResource("ListBlogBack.fxml"));
                 Parent root = loader.load();
                 updateTitle.getScene().setRoot(root);
                 
             } catch (IOException ex) {
                 Logger.getLogger(ListBlogBackController.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
    }
    
}
