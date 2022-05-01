/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Gui;

import Entities.Products;
import static gamershub.Gamershub.cartMap;
import static gamershub.Gamershub.loggedUser;
import gamershub.Services.ProductsService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Map;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author meriam
 */
public class OrderValidateController implements Initializable {

    @FXML
    private Label userName;
    @FXML
    private Button finishOrder;
    
    double totalPrice=0;
    @FXML
    private Label totalPriceText;
    @FXML
    private ImageView QrCode;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      
        userName.setText(loggedUser.getUsername());
         ProductsService ps = new ProductsService();
         String data="";
        for (Map.Entry<Integer, Integer> entry : cartMap.entrySet()) {
            try {
                Products p = ps.getProductById(entry.getKey());
                data+="The Product Name Is :"+p.getProductName()+" The price is :"+p.getPrice()+" The quantity Ordered : "+ entry.getValue()+" ";
                totalPrice+=p.getPrice()*entry.getValue();
            } catch (SQLException ex) {
                Logger.getLogger(OrderValidateController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        String qr="http://api.qrserver.com/v1/create-qr-code/?data="+data+"  The Total price is :"+totalPrice+" ";
        System.out.print("price"+totalPrice);
         QrCode.setImage(new Image(qr, 275, 297, false, false));
    }    

    @FXML
    private void clearCart(ActionEvent event) {
        try {
            cartMap.clear();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ProductsPageFront.fxml"));
            Parent root = loader.load();
            ProductsPageFrontController controller = loader.getController();
            
            FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("HomeFront.fxml"));
            Parent homeRoot = homeLoader.load();
            HomeFrontController homeCtrl = homeLoader.getController();
            homeCtrl.changePage("Products", root);
            userName.getScene().setRoot(homeRoot);
        } catch (IOException ex) {
            Logger.getLogger(OrderValidateController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void setTotalPrice(Double t) {
          
          totalPriceText.setText(t+" "+"Coins");
        
    }
    
}
