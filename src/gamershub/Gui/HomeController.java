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
    private Button btnSettings;
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
    private Button addBtn;
    @FXML
    private Button btnCategories;
    @FXML
    private Button btnProducts;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usernameLabel.setText(Gamershub.loggedUser.getUsername());
        userImage.setImage(new Image("https://avatars.dicebear.com/api/bottts/" + Gamershub.loggedUser.getUsername() + ".png"));
    }

    public void changePage(String title, Parent node) {
        titleLabel.setText(title);
        addBtn.setVisible(false);
        mainContent.getChildren().removeAll(mainContent.getChildren());
        mainContent.getChildren().add(node);
    }

    public void changePage(String state) {
        addBtn.setVisible(false);
        if (state.equals("users")) {
            titleLabel.setText("Users");
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("UsersFormContent.fxml"));
                Parent root = loader.load();
                mainContent.getChildren().add(root);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } else if (state.equals("games")) {
            titleLabel.setText("Games");
            addBtn.setVisible(true);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("GamesContentForm.fxml"));
                Parent root = loader.load();
                mainContent.getChildren().add(root);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        else if (state.equals("Categories")) {
            titleLabel.setText("Categories");
            addBtn.setVisible(true);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("CategoriesContentForm.fxml"));
                Parent root = loader.load();
                mainContent.getChildren().add(root);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
          else if (state.equals("Products")) {
            titleLabel.setText("Products");
            addBtn.setVisible(true);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ProductsContentForm.fxml"));
                Parent root = loader.load();
                mainContent.getChildren().add(root);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
          }
    }

    @FXML
    private void handleClicks(ActionEvent event) {
        mainContent.getChildren().removeAll(mainContent.getChildren());
        addBtn.setVisible(false);
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

        } else if (event.getSource() == btnGames) {
            titleLabel.setText("Games");
            addBtn.setVisible(true);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("GamesContentForm.fxml"));
                Parent root = loader.load();
                mainContent.getChildren().add(root);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

        }else if (event.getSource() == btnCategories) {
            titleLabel.setText("Categories");
            addBtn.setVisible(true);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("CategoriesContentForm.fxml"));
                Parent root = loader.load();
                mainContent.getChildren().add(root);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

        }
         else if (event.getSource() == btnProducts) {
            titleLabel.setText("Products");
            addBtn.setVisible(true);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ProductsContentForm.fxml"));
                Parent root = loader.load();
                mainContent.getChildren().add(root);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
          }
        else {
            System.out.println(event.getSource());
        }
    }

    @FXML
    private void addClick(ActionEvent event) {
        if(titleLabel.getText().equals("Games")){
            mainContent.getChildren().removeAll(mainContent.getChildren());
            titleLabel.setText("Game - Add");
            addBtn.setVisible(true);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("GameEditForm.fxml"));
                Parent root = loader.load();
                GameEditFormController cont = loader.getController();
                cont.setAdd();
                mainContent.getChildren().add(root);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
           else if(titleLabel.getText().equals("Categories")){
            mainContent.getChildren().removeAll(mainContent.getChildren());
            titleLabel.setText("Category - Add");
            addBtn.setVisible(true);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AddCategory.fxml"));
                Parent root = loader.load();
                AddCategoryController cont = loader.getController();
                mainContent.getChildren().add(root);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
          else if(titleLabel.getText().equals("Products")){
            mainContent.getChildren().removeAll(mainContent.getChildren());
            titleLabel.setText("Product - Add");
            addBtn.setVisible(true);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AddProduct.fxml"));
                Parent root = loader.load();
                AddProductController cont = loader.getController();
                cont.setCombo();
                mainContent.getChildren().add(root);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

}
