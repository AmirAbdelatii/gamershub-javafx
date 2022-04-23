/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Gui;

import Entities.Category;
import gamershub.Services.CategoryService;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author meriam
 */
public class CategoriesContentFormController implements Initializable {

    @FXML
    private FlowPane content;
    @FXML
    private HBox itemC;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         CategoryService cs = new CategoryService();
        try {
            List<Category> catList = cs.afficher();

            for (Category c : catList) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("CategoryItemForm.fxml"));
                Parent root = loader.load();
                CategoryItemFormController cont = loader.getController();
                 
                    cont.setCategoryName(c.getCategoryName());
                    cont.setCategoryId(c.getId()+"");
                    cont.setDescription(c.getDescription());
                    cont.setCreationDate(c.getCreationDate()+"");
                    cont.setIsEnabled(c.isIsEnabled()+"");
                    cont.setModificationDate(c.getModificationDate()+"");
                    cont.setImage("http://127.0.0.1:8000/shop/images/"+c.getImage());
                    
                content.getChildren().add(root);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }    
    
}
