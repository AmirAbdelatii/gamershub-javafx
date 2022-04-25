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
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author meriam
 */
public class ProductsContentFormController implements Initializable {

    @FXML
    private FlowPane content;
    @FXML
    private HBox itemC;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ProductsService ps = new ProductsService();
        try {

            List<Products> products = ps.afficher();

            for (Products p : products) {

                FXMLLoader item = new FXMLLoader(getClass().getResource("ProductItemForm.fxml"));
                try {
                    Parent itek = item.load();
                    ProductItemFormController itemController = item.getController();

                    itemController.setCategoryId(p.getCatId() + "");
                    itemController.setQuantity(p.getQuantityStocked() + "");
                    itemController.setDescription(p.getDescription());
                    itemController.setCreationDate(p.getCreationDate() + "");
                    itemController.setIsEnabled(p.getIsEnabled() + "");
                    itemController.setModificationDate(p.getModificationDate() + "");
                    itemController.setProductName(p.getProductName());
                    itemController.setPrice(p.getPrice() + "");
                    itemController.setProductId(p.getId() + "");
                    itemController.setImage("http://127.0.0.1:8000/shop/images/" + p.getImage());

                    content.getChildren().add(itek);
                    if ( p.getQuantityStocked()== 0) {
                        Image img=new Image("gamershub/images/warning.png");
                        Notifications notfBuilder = Notifications.create().title("Quantity warning").text("The quantity of the product number "+p.getId()+" is zero")
                        .darkStyle().graphic(new ImageView (img)).hideAfter(Duration.seconds(10)).position(Pos.TOP_LEFT);
                        notfBuilder.show();
                    }
                } catch (IOException ex) {
                    Logger.getLogger(ProductsContentFormController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductsContentFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
