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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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
    @FXML
    private Button btnMatch;
    @FXML
    private Button btnTeam;
    @FXML
    private Button btnStatitics;
    @FXML
    private Button btncoach;
    @FXML
    private Button btnsession;
    @FXML
    private Button btnlivreur;
    @FXML
    private Button btnlivraison;
    @FXML
    private Button btnTour;
    @FXML
    private Button btnReward;
    @FXML
    private Button btnBlog;
    @FXML
    private Button btnComments;
    @FXML
    private Button btnSpam;
    @FXML
    private Button btnPlayers;

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
        else if (state.equals("Matchs")) {
            titleLabel.setText("Matchs");
            addBtn.setVisible(true);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("MatchListBack.fxml"));
                Parent root = loader.load();
                mainContent.getChildren().add(root);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
          }
        else if (state.equals("Teams")) {
            titleLabel.setText("Teams");
            addBtn.setVisible(true);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("TeamListBack.fxml"));
                Parent root = loader.load();
                mainContent.getChildren().add(root);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
          }
         else if (state.equals("Tournaments")) {
            titleLabel.setText("Tournaments");
            addBtn.setVisible(true);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Tournament.fxml"));
                Parent root = loader.load();
                mainContent.getChildren().add(root);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
          }
          else if (state.equals("Rewards")) {
            titleLabel.setText("Rewards");
            addBtn.setVisible(true);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Reward.fxml"));
                Parent root = loader.load();
                mainContent.getChildren().add(root);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
          }
        else if (state.equals("Coachs")) {
            titleLabel.setText("Coachs");
            addBtn.setVisible(true);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Coachback.fxml"));
                Parent root = loader.load();
                mainContent.getChildren().add(root);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
          }
        else if (state.equals("btsession")) {
            titleLabel.setText("Coaching Session");
            addBtn.setVisible(true);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Sessionback.fxml"));
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
 
        }else if (event.getSource() == btnTour) {
            titleLabel.setText("Tournaments");
            addBtn.setVisible(true);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Tournament.fxml"));
                Parent root = loader.load();
                mainContent.getChildren().add(root);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            } 
 
        }else if (event.getSource() == btnReward) {
            titleLabel.setText("Rewards");
            addBtn.setVisible(true);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Reward.fxml"));
                Parent root = loader.load();
                mainContent.getChildren().add(root);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            } 
 
        }else if (event.getSource() == btnBlog) {
            titleLabel.setText("Forum");
            addBtn.setVisible(true);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ListBlogBack.fxml"));
                Parent root = loader.load();
                mainContent.getChildren().add(root);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            } 
 
        }else if (event.getSource() == btnComments) {
            titleLabel.setText("Comments");
            addBtn.setVisible(true);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ListCommentsBack.fxml"));
                Parent root = loader.load();
                mainContent.getChildren().add(root);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            } 
 
        }else if (event.getSource() == btnSpam) {
            titleLabel.setText("Spam");
            addBtn.setVisible(true);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("SpamBack.fxml"));
                Parent root = loader.load();
                mainContent.getChildren().add(root);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            } 
 
        }else if (event.getSource() == btnPlayers) {
            titleLabel.setText("Forum");
            addBtn.setVisible(true);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ListPlayerBack.fxml"));
                Parent root = loader.load();
                mainContent.getChildren().add(root);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            } 
 
        }
        else if (event.getSource() == btnCategories) {
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
         else if (event.getSource() == btnMatch) {
            titleLabel.setText("Matchs");
            addBtn.setVisible(true);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("MatchListBack.fxml"));
                Parent root = loader.load();
                mainContent.getChildren().add(root);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
          }
         else if (event.getSource() == btnTeam) {
            titleLabel.setText("Teams");
            addBtn.setVisible(true);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("TeamListBack.fxml"));
                Parent root = loader.load();
                mainContent.getChildren().add(root);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
          }
          else if (event.getSource() == btnStatitics) {
            titleLabel.setText("Statitcs");
            addBtn.setVisible(true);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("statics.fxml"));
                Parent root = loader.load();
                mainContent.getChildren().add(root);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
          }
          else if (event.getSource() == btncoach) {
             System.out.println("hhhh");
            titleLabel.setText("Coachs");
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Coachback.fxml"));
                Parent root = loader.load();
                mainContent.getChildren().add(root);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

        }
          else if (event.getSource() == btnsession) {
            titleLabel.setText("Coaching Session");
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Sessionback.fxml"));
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
        else if(titleLabel.getText().equals("Matchs")){
            mainContent.getChildren().removeAll(mainContent.getChildren());
            titleLabel.setText("Match - Add");
            addBtn.setVisible(true);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AddMatch.fxml"));
                Parent root = loader.load();
                AddMatchController cont = loader.getController();
                
                mainContent.getChildren().add(root);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        else if(titleLabel.getText().equals("Tournaments")){
            mainContent.getChildren().removeAll(mainContent.getChildren());
            titleLabel.setText("Tournament - Add");
            addBtn.setVisible(true);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AddTournament.fxml"));
                Parent root = loader.load();
                AddTournamentController cont = loader.getController();
                
                mainContent.getChildren().add(root);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        else if(titleLabel.getText().equals("Rewards")){
            mainContent.getChildren().removeAll(mainContent.getChildren());
            titleLabel.setText("Reward - Add");
            addBtn.setVisible(true);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AddReward.fxml"));
                Parent root = loader.load();
                AddRewardController cont = loader.getController();
                
                mainContent.getChildren().add(root);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        else if(titleLabel.getText().equals("Teams")){
            mainContent.getChildren().removeAll(mainContent.getChildren());
            titleLabel.setText("Team - Add");
            addBtn.setVisible(true);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AddTeam.fxml"));
                Parent root = loader.load();
                AddTeamController cont = loader.getController();
                
                mainContent.getChildren().add(root);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        
       
    }

    private void showMatchs(ActionEvent event) {
          try {
           
           
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MatchListBack.fxml"));
            Parent root = loader.load();
            btnMatch.getScene().setRoot(root);
           
        } catch (IOException ex) {
            Logger.getLogger(MatchListBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showTeams(ActionEvent event) {
        try {
           
           
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TeamListBack.fxml"));
            Parent root = loader.load();
            btnTeam.getScene().setRoot(root);
           
        } catch (IOException ex) {
            Logger.getLogger(MatchListBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showStatitcs(ActionEvent event) {
         try {
           
           
            FXMLLoader loader = new FXMLLoader(getClass().getResource("statics.fxml"));
            Parent root = loader.load();
            //addTeamButton.getScene().setRoot(root);
           
        } catch (IOException ex) {
            Logger.getLogger(MatchListBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
@FXML
    private void LivreurScene(ActionEvent event) throws IOException {
        Parent view3=FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                Scene scene3=new Scene(view3);
                Stage window =new Stage();
                window.setScene(scene3);
                window.show();
    }
    
    @FXML
    private void LivraisonScene(ActionEvent event) throws IOException {
        Parent view3=FXMLLoader.load(getClass().getResource("livraison.fxml"));
                Scene scene3=new Scene(view3);
                Stage window =new Stage();
                window.setScene(scene3);
                window.show();
    }
}
