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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author meriam
 */
public class CategoryItemFormController implements Initializable {

    @FXML
    private HBox itemC;
    @FXML
    private Label categoryId;
    @FXML
    private Label categoryName;
    @FXML
    private Label description;
    @FXML
    private ImageView image;
    @FXML
    private Label creationDate;
    @FXML
    private Label modificationDate;
    @FXML
    private Label isEnabled;
    @FXML
    private ImageView EditCategory;
    @FXML
    private ImageView deleteCategory;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setCategoryId(String categoryId) {
        this.categoryId.setText(categoryId);
    }

    public void setCategoryName(String categoryName) {
        this.categoryName.setText(categoryName);
    }

    public void setCreationDate(String creationDate) {
        this.creationDate.setText(creationDate);
    }

    public void setIsEnabled(String isEnabled) {
        this.isEnabled.setText(isEnabled);
    }

    public void setImage(String url) {
        this.image.setImage(new Image(url,60,40,false,false));
    }

    public void setModificationDate(String modificationDate) {
        this.modificationDate.setText(modificationDate);
    }

    public void setDescription(String description) {
        this.description.setText(description);
    }

    @FXML
    private void deleteCategoryButton(MouseEvent event) {
        CategoryService cs = new CategoryService();
        int id = Integer.parseInt(categoryId.getText());
        try {
            cs.delete(id);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
            Parent root = loader.load();
            HomeController controller= loader.getController();
            controller.changePage("Categories");
            
            categoryId.getScene().setRoot(root);

        } catch (Exception ex) {
            Logger.getLogger(CategoryItemFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    @FXML
    private void EditCategoryButton(MouseEvent event) {

            try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("EditCategory.fxml"));
            Parent root = loader.load();
            EditCategoryController controller= loader.getController();
            Category c =new Category(Integer.parseInt(categoryId.getText()),categoryName.getText(),description.getText(),Boolean.parseBoolean(isEnabled.getText()));
            System.out.println(c);
            controller.setCategory(c);
            
            FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("Home.fxml"));
            Parent homeRoot = homeLoader.load();
            HomeController homeCtrl = homeLoader.getController();
            homeCtrl.changePage( " Category - Edit", root);
            categoryId.getScene().setRoot(homeRoot);

        } catch (IOException ex) {
            Logger.getLogger(CategoriesContentFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
