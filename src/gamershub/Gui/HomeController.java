/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Gui;

import gamershub.Entities.User;
import gamershub.Gamershub;
import gamershub.Services.UserService;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author MAB
 */
public class HomeController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button btnOverview;
    @FXML
    private Button btnUsers;
    @FXML
    private Button btnGames;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnSignout;
    @FXML
    private ImageView userImage;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label titleLabel;
    @FXML
    private Pane mainContent;
    @FXML
    private Button btnBlog;
    @FXML
    private Button btnPlayer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usernameLabel.setText(Gamershub.loggedUser.getUsername());
        userImage.setImage(new Image("https://avatars.dicebear.com/api/bottts/" + Gamershub.loggedUser.getUsername() + ".png"));
    }

    @FXML
    private void handleClicks(ActionEvent event) {
        if (event.getSource() == btnSignout) {
            Gamershub.loggedUser = new User();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginForm.fxml"));
                Parent root = loader.load();
                btnSignout.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(LoginFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (event.getSource() == btnUsers) {
            titleLabel.setText("Users");
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("UsersFormContent.fxml"));
                Parent root = loader.load();
                mainContent.getChildren().add(root);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            
        } else if (event.getSource()== btnBlog){
            titleLabel.setText("Forum");  
             try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("ListBlogBack.fxml"));
                Parent root = loader.load();
                mainContent.getChildren().add(root);
              } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
                
        } else if (event.getSource()== btnPlayer){
            titleLabel.setText("Become a player");  
             try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("ListPlayerBack.fxml"));
                Parent root = loader.load();
                mainContent.getChildren().add(root);
              } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
                
        }
        
    }

}
