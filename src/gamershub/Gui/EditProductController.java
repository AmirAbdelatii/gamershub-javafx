/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Gui;

import Entities.Category;
import Entities.Products;
import gamershub.Services.CategoryService;
import gamershub.Services.ProductsService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author meriam
 */
public class EditProductController implements Initializable {

    @FXML
    private TextField productName;
    @FXML
    private Label productNameError;
    @FXML
    private TextField description;
    @FXML
    private Label descriptionError;
    @FXML
    private ComboBox<String> category;
    @FXML
    private Label categoryError;
    @FXML
    private TextField price;
    @FXML
    private Label priceError;
    @FXML
    private TextField quantity;
    @FXML
    private Label quantityError;
    @FXML
    private Button cancel;
    @FXML
    private Button editProduct;

    private Products product;

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
            controller.changePage("Products");

            cancel.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setProduct(Products c) {
        this.product = c;

        productName.setText(product.getProductName());
        description.setText(product.getDescription());
        quantity.setText(product.getQuantityStocked() + "");
        price.setText(product.getPrice() + "");
        category.setValue(product.getCatId() + "");

        //set combobox
        try {
            CategoryService cs = new CategoryService();
            List<Category> list = cs.afficher();
            ObservableList<String> listId = FXCollections.observableArrayList();
            for (Category cat : list) {
                listId.add(cat.getId() + ":  " + cat.getCategoryName());
            }

            category.setItems(listId);
        } catch (SQLException ex) {
            Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void editProduct(ActionEvent event) {
        productNameError.setText("");
        descriptionError.setText("");
        priceError.setText("");
        quantityError.setText("");
        if (productName.getText().length() != 0 && description.getText().length() != 0 && price.getText().length() != 0 && quantity.getText().length() != 0) {
            if (Pattern.matches("^[a-zA-Z]*$", productName.getText()) == true && Pattern.matches("^[a-zA-Z]*$", description.getText()) == true && Pattern.matches("^[0-9\\.\\-\\/]*$", price.getText()) == true && Pattern.matches("^[0-9]*$", quantity.getText()) == true && Integer.parseInt(quantity.getText()) > -1 && Float.parseFloat(price.getText()) > -1) {
                product.setProductName(productName.getText());
                product.setDescription(description.getText());
                product.setPrice(Float.parseFloat(price.getText()));
                product.setQuantityStocked(Integer.parseInt(quantity.getText()));
                String[] categoryIdCombo = category.getValue().split(":", 2);
                product.setCatId(Integer.parseInt(categoryIdCombo[0]));
                //combo

                ProductsService ps = new ProductsService();
                try {
                    ps.update(product);
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
                        Parent root = loader.load();
                        HomeController controller = loader.getController();
                        controller.changePage("Products");

                        cancel.getScene().setRoot(root);
                    } catch (IOException ex) {
                        Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            } else {
                if (Pattern.matches("^[a-zA-Z]*$", productName.getText()) == false) {
                    productNameError.setText("This field can only contain letters");
                }
                if (Pattern.matches("^[a-zA-Z]*$", description.getText()) == false) {
                    descriptionError.setText("This field can only contain letters");
                }
                if (Pattern.matches("^[0-9\\.\\-\\/]*$", price.getText()) == false) {
                    priceError.setText("This field can only contain numbers");
                }
                if (Pattern.matches("^[0-9]*$", quantity.getText()) == false) {
                    quantityError.setText("This field can only contain numbers");
                }
                if (Integer.parseInt(quantity.getText()) < 0) {
                    quantityError.setText("This field must be positive");
                }
                if (Float.parseFloat(price.getText()) < 0) {
                    priceError.setText("This field must be positive");
                }
            }
        } else {
            if (productName.getText().length() == 0) {
                productNameError.setText("Fill this Field");
            }

            if (description.getText().length() == 0) {
                descriptionError.setText("Fill this Field ");
            }
            if (price.getText().length() == 0) {
                priceError.setText("Fill this Field ");
            }

            if (quantity.getText().length() == 0) {
                quantityError.setText("Fill this Field");
            }
            if (Pattern.matches("^[a-zA-Z]*$", productName.getText()) == false) {
                productNameError.setText("This field can only contain letters");
            }
            if (Pattern.matches("^[a-zA-Z]*$", description.getText()) == false) {
                descriptionError.setText("This field can only contain letters");
            }
            if (Pattern.matches("^[0-9\\.\\-\\/]*$", price.getText()) == false) {
                priceError.setText("This field can only contain numbers");
            }
            if (Pattern.matches("^[0-9]*$", quantity.getText()) == false) {
                quantityError.setText("This field can only contain numbers");
            }
            if (Integer.parseInt(quantity.getText()) < 0) {
                quantityError.setText("This field must be positive");
            }
            if (Float.parseFloat(price.getText()) < 0) {
                priceError.setText("This field must be positive");
            }

        }
    }

}
