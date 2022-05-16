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
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author MAB
 */
public class HomeFrontController implements Initializable {

    @FXML
    private Button games;
    @FXML
    private Button logout;
    @FXML
    private Button profile;
    @FXML
    private Label title;
    @FXML
    private Pane content;
    @FXML
    private MenuItem btnCategories;
    @FXML
    private MenuItem productsbtn;
    @FXML
    private MenuItem btnCart;
    @FXML
    private MenuItem btnWishList;
    @FXML
    private MenuItem btncoachs;
    @FXML
    private MenuItem btnsession;
    @FXML
    private MenuItem btnTeam;
    @FXML
    private MenuItem btnMatch;
    @FXML
    private MenuItem blog;
    @FXML
    private MenuItem player;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void changePage(String title, Parent node) {
        this.title.setText(title);
        content.getChildren().removeAll(content.getChildren());
        content.getChildren().add(node);
    }
    public void changePage(String state) {
        if(state == "profile"){
            title.setText("MY PROFILE");
            try {
                FXMLLoader profileLoader = new FXMLLoader(getClass().getResource("ProfilePage.fxml"));
                Parent profileNode = profileLoader.load();
                content.getChildren().add(profileNode);
            } catch (Exception ex) {
                Logger.getLogger(ProfileEditFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(state == "logout"){
            Gamershub.loggedUser = new User();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginForm.fxml"));
                Parent root = loader.load();
                this.title.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(LoginFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                  if(state == "Products"){
                title.setText("Products");
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ProductsPageFront.fxml"));
                Parent root = loader.load();
                
                content.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(ProductsPageFrontController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
          if(state == "Order-CheckOut"){
                title.setText("Order-CheckOut");
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("OrderValidate.fxml"));
                Parent root = loader.load();
                
                content.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(ProductsPageFrontController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
          if(state == "Coachs"){
              title.setText("Coachs");
            try {
                FXMLLoader profileLoader = new FXMLLoader(getClass().getResource("CoachContent.fxml"));
                Parent profileNode = profileLoader.load();
                content.getChildren().add(profileNode);
            } catch (Exception ex) {
                Logger.getLogger(ProfileEditFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
           if(state == "btnsession"){
              title.setText("Session Coaching");
            try {
                FXMLLoader profileLoader = new FXMLLoader(getClass().getResource("SessionContent.fxml"));
                Parent profileNode = profileLoader.load();
                content.getChildren().add(profileNode);
            } catch (Exception ex) {
                Logger.getLogger(ProfileEditFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
           if(state == "blog"){
            title.setText("FORUM");
            try {
                FXMLLoader profileLoader = new FXMLLoader(getClass().getResource("BlogFormContent.fxml"));
                Parent root = profileLoader.load();
                content.getChildren().add(root);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
           
    }

    @FXML
    private void handleClicks(ActionEvent event) {
        content.getChildren().removeAll(content.getChildren());
        if(event.getSource() == profile){
            title.setText("MY PROFILE");
            try {
                FXMLLoader profileLoader = new FXMLLoader(getClass().getResource("ProfilePage.fxml"));
                Parent profileNode = profileLoader.load();
                content.getChildren().add(profileNode);
            } catch (Exception ex) {
                Logger.getLogger(ProfileEditFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(event.getSource() == games){
            title.setText("GAMES");
            try {
                FXMLLoader gameLoader = new FXMLLoader(getClass().getResource("GamesFront.fxml"));
                Parent gameNode = gameLoader.load();
                content.getChildren().add(gameNode);
            } catch (Exception ex) {
                Logger.getLogger(ProfileEditFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(event.getSource() == logout){
            Gamershub.loggedUser = new User();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginForm.fxml"));
                Parent root = loader.load();
                content.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(LoginFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         if(event.getSource() == btnCategories){
                title.setText("Categories");
          try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("CategoryPageFront.fxml"));
                Parent categoryNode = loader.load();
                content.getChildren().add(categoryNode);
            } catch (Exception ex) {
                Logger.getLogger(CategoryPageFrontController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
           if(event.getSource() == productsbtn){
                title.setText("Products");
          try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ProductsPageFront.fxml"));
                Parent productNode = loader.load();
                content.getChildren().add(productNode);
            } catch (Exception ex) {
                Logger.getLogger(ProductsPageFrontController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
           
               if(event.getSource() == btnCart){
                title.setText("Cart");
          try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("CartPage.fxml"));
                Parent cartNode = loader.load();
                content.getChildren().add(cartNode);
            } catch (Exception ex) {
                Logger.getLogger(CartPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
              if(event.getSource() == btnWishList){
                title.setText("Products I Liked ");
          try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("WishListPage.fxml"));
                Parent cartNode = loader.load();
                content.getChildren().add(cartNode);
            } catch (Exception ex) {
                Logger.getLogger(WishListPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
              if(event.getSource() == btncoachs){
                title.setText("Coachs");
          try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("CoachContent.fxml"));
                Parent coachNode = loader.load();
                content.getChildren().add(coachNode);
            } catch (Exception ex) {
                Logger.getLogger(CategoryPageFrontController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
              if(event.getSource() == btnsession){
                title.setText("Session coaching");
          try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("SessionContent.fxml"));
                Parent coachNode = loader.load();
                content.getChildren().add(coachNode);
            } catch (Exception ex) {
                Logger.getLogger(CategoryPageFrontController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                 if(event.getSource() == btnTeam){
                title.setText("Teams");
          try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("TeamFrontContent.fxml"));
                Parent coachNode = loader.load();
                content.getChildren().add(coachNode);
            } catch (Exception ex) {
                Logger.getLogger(CategoryPageFrontController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                       if(event.getSource() == btnMatch){
                title.setText("Matchs");
          try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("MatchFrontContent.fxml"));
                Parent coachNode = loader.load();
                content.getChildren().add(coachNode);
            } catch (Exception ex) {
                Logger.getLogger(CategoryPageFrontController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                               if(event.getSource() == blog){
            title.setText("FORUM");
            try {
                FXMLLoader profileLoader = new FXMLLoader(getClass().getResource("BlogFormContent.fxml"));
                Parent root = profileLoader.load();
                content.getChildren().add(root);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        if(event.getSource() == player){
            title.setText("Become a player");
            try {
                FXMLLoader profileLoader = new FXMLLoader(getClass().getResource("BecomePlayerForm.fxml"));
                Parent root = profileLoader.load();
                content.getChildren().add(root);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
}
