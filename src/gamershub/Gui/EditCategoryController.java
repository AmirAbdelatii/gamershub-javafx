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
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author meriam
 */
public class EditCategoryController implements Initializable {

    @FXML
    private TextField categoryName;
    @FXML
    private Label categoryNameError;
    @FXML
    private TextField description;
    @FXML
    private Label descriptionError;
    @FXML
    private Button cancel;
    @FXML
    private Button editCategory;
    private Category category;

    public void setCategory(Category c) {
        this.category = c;
        categoryName.setText(category.getCategoryName());
        description.setText(category.getDescription());
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void cancel(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
            Parent root = loader.load();
            HomeController controller = loader.getController();
            controller.changePage("Categories");

            editCategory.getScene().setRoot(root);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void edit(ActionEvent event) {
        if (categoryName.getText().length() != 0 && description.getText().length() != 0) {
            if (Pattern.matches("^[a-zA-Z]*$", categoryName.getText()) == true && Pattern.matches("^[a-zA-Z]*$", description.getText()) == true) {

                category.setCategoryName(categoryName.getText());
                category.setDescription(description.getText());

                CategoryService cs = new CategoryService();
                try {
                    cs.update(category);
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
                        Parent root = loader.load();
                        HomeController controller = loader.getController();
                        controller.changePage("Categories");

                        editCategory.getScene().setRoot(root);

                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            } else {
                if (Pattern.matches("^[a-zA-Z]*$", categoryName.getText()) == false) {
                    categoryNameError.setText("This field can only contain letters");
                }
                if (Pattern.matches("^[a-zA-Z]*$", description.getText()) == false) {
                    descriptionError.setText("This field can only contain letters");
                }
            }
        } else {
            if (categoryName.getText().length() == 0) {
                categoryNameError.setText("Fill this Field");
            }

            if (description.getText().length() == 0) {
                descriptionError.setText("Fill this Field ");
            }
            if (Pattern.matches("^[a-zA-Z]*$", categoryName.getText()) == false) {
                categoryNameError.setText("This field can only contain letters");
            }
            if (Pattern.matches("^[a-zA-Z]*$", description.getText()) == false) {
                descriptionError.setText("This field can only contain letters");
            }
        }
    }

}
