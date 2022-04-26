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
import gamershub.Services.WishListService;
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
import javafx.scene.layout.FlowPane;

/**
 * FXML Controller class
 *
 * @author meriam
 */
public class ProductsPageFrontController implements Initializable {

    @FXML
    public FlowPane content;
    public void getProductsList(List<Products> listProd) {
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
     
        ProductsService ps = new ProductsService();
        try {

            List<Products> products = ps.afficher();


            for (Products p : products) {

                FXMLLoader item = new FXMLLoader(getClass().getResource("ProductCard.fxml"));
                try {
                    Parent itek = item.load();
                    ProductCardController itemController = item.getController();

                    itemController.setProductName(p.getProductName());
                    itemController.setPrice(p.getPrice() + "");
                    itemController.setId(p.getId() + "");
                    itemController.setImage("http://127.0.0.1:8000/shop/images/" + p.getImage());
                    
                    //wishList
                    WishListService ws=new WishListService();
                    if( ws.afficher().contains(p.getId())){
                    itemController.setWishList("http://127.0.0.1:8000/shop/images/" +"full.png");
                    }
                    content.getChildren().add(itek);
                } catch (IOException ex) {
                    Logger.getLogger(ProductsPageFrontController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductsContentFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    
}
