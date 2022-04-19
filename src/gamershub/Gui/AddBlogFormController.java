/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Gui;

import gamershub.Entities.Blog;
import gamershub.Entities.User;
import gamershub.Gamershub;
import gamershub.Services.BlogService;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author amira
 */
public class AddBlogFormController implements Initializable {

    @FXML
    private TextField addTitle;
    @FXML
    private TextArea addContent;
    @FXML
    private Button postBlog;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addPost(ActionEvent event) {

        Blog b = new Blog();
        b.setTitle(addTitle.getText());
        b.setDescription(addContent.getText());
        b.setPublishedAt(new Date(System.currentTimeMillis()));
        b.setUser(Gamershub.loggedUser);
        BlogService bs = new BlogService();
        try {
        bs.AddBlog(b);
        System.out.println(b);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Post added");
        alert.setContentText("Post added successfully");
        alert.show();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
