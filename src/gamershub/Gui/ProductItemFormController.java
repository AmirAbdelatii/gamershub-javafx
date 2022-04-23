/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Gui;

import Entities.Products;
import gamershub.Services.ProductsService;
import java.io.IOException;
import java.net.URL;
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
public class ProductItemFormController implements Initializable {

    @FXML
    private HBox itemC;
    @FXML
    private Label productId;
    @FXML
    private Label categoryId;
    @FXML
    private Label productName;
    @FXML
    private ImageView image;
    @FXML
    private Label price;
    @FXML
    private Label quantity;
    @FXML
    private Label description;
    @FXML
    private Label creationDate;
    @FXML
    private Label modificationDate;
    @FXML
    private Label isEnabled;
    @FXML
    private ImageView EditProduct;
    @FXML
    private ImageView deleteProduct;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void EditProductButton(MouseEvent event) {
                   try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("EditProduct.fxml"));
            Parent root = loader.load();
            EditProductController controller= loader.getController();
            Products c =new Products(Integer.parseInt(productId.getText()),Integer.parseInt(categoryId.getText()),productName.getText(),Float.parseFloat(price.getText()),Integer.parseInt(quantity.getText()),description.getText(),Boolean.parseBoolean(isEnabled.getText()));
            controller.setProduct(c);
            
            FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("Home.fxml"));
            Parent homeRoot = homeLoader.load();
            HomeController homeCtrl = homeLoader.getController();
            homeCtrl.changePage( " Product - Edit", root);
            
            categoryId.getScene().setRoot(homeRoot);

        } catch (IOException ex) {
            Logger.getLogger(EditProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void deleteProductButton(MouseEvent event) {
           ProductsService cs = new ProductsService();
        int id = Integer.parseInt(productId.getText());
        try {
            cs.delete(id);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
            Parent root = loader.load();
            HomeController controller= loader.getController();
            controller.changePage("Products");
            
            productId.getScene().setRoot(root);

        } catch (Exception ex) {
            Logger.getLogger(CategoryItemFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    public void setCategoryId(String categoryId) {
        this.categoryId.setText(categoryId);
    }

    public void setDescription(String description) {
        this.description.setText(description);
    }

    public void setCreationDate(String creationDate) {
        this.creationDate.setText(creationDate);
    }

    public void setModificationDate(String modificationDate) {
        this.modificationDate.setText(modificationDate);
    }

    public void setIsEnabled(String isEnabled) {
        this.isEnabled.setText(isEnabled);
    }

    public void setProductId(String productId) {
        this.productId.setText(productId);
    }

    public void setProductName(String productName) {
        this.productName.setText(productName);
    }

    public void setPrice(String price) {
        this.price.setText(price);
    }

    public void setQuantity(String quantity) {
        this.quantity.setText(quantity);
    }

    public void setImage(String url) {
        this.image.setImage(new Image(url,60,40,false,false));
    }

}
