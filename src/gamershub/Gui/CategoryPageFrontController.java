/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Gui;

import Entities.Category;
import gamershub.Services.CategoryService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

/**
 * FXML Controller class
 *
 * @author meriam
 */
public class CategoryPageFrontController implements Initializable {

    @FXML
    private FlowPane content;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         CategoryService cs = new CategoryService();
        try {

            List<Category> categories = cs.afficher();

//            try {
            for (Category c : categories) {

                FXMLLoader item = new FXMLLoader(getClass().getResource("CategoryCard.fxml"));
                try {
                    Parent itek = item.load();
                    CategoryCardController categoryCardController = item.getController();
                    //System.out.print(itemController);

                    categoryCardController.setCategoryName(c.getCategoryName());
                    categoryCardController.setDescription(c.getDescription());
                    categoryCardController.setImage("http://127.0.0.1:8000/shop/images/" + c.getImage());

                    content.getChildren().add(itek);
                } catch (IOException ex) {
                    Logger.getLogger(CategoryPageFrontController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(CategoryPageFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    
}
