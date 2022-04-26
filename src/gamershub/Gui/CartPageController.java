/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Gui;

import Entities.Products;
import gamershub.Entities.Order;
import static gamershub.Gamershub.cartMap;
import static gamershub.Gamershub.loggedUser;
import gamershub.Services.OrderService;
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
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author meriam
 */
public class CartPageController implements Initializable {

    @FXML
    private FlowPane content;
    @FXML
    private Button validateOrder;
    @FXML
    private Label userName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ProductsService ps = new ProductsService();

        for (Map.Entry<Integer, Integer> entry : cartMap.entrySet()) {
            System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
            FXMLLoader item = new FXMLLoader(getClass().getResource("CartItem.fxml"));
            try {
                Parent itek = item.load();
                CartItemController CartItemController = item.getController();
                try {
                    Products p = ps.getProductById(entry.getKey());
                    CartItemController.setProdId(p.getProductName());
                } catch (SQLException ex) {
                    Logger.getLogger(CartPageController.class.getName()).log(Level.SEVERE, null, ex);
                }

                CartItemController.setQuantity(entry.getValue());
                CartItemController.setProdIdHidden(entry.getKey());
                userName.setText(loggedUser.getUsername());

                content.getChildren().add(itek);
            } catch (IOException ex) {
                Logger.getLogger(CartPageController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    @FXML
    private void addOrder(ActionEvent event) {
        ProductsService ps = new ProductsService();
        double totalPrice = 0;

        for (Map.Entry<Integer, Integer> entry : cartMap.entrySet()) {
            try {
                System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
                Products p = ps.getProductById(entry.getKey());
                if (p.getQuantityStocked() > entry.getValue()) {
                    totalPrice += p.getPrice() * entry.getValue();
                    p.setQuantityStocked(p.getQuantityStocked() - entry.getValue());
                    ps.update(p);
                    OrderService os = new OrderService();
                    Order o = new Order();
                    o.setTotalPrice(totalPrice);
                    o.setUser_Id(loggedUser.getId());

                    os.add(o);
                    //change page 

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("OrderValidate.fxml"));
                    Parent root = loader.load();
                    OrderValidateController controller = loader.getController();

                    controller.setTotalPrice(o.getTotalPrice());

                    FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("HomeFront.fxml"));
                    Parent homeRoot = homeLoader.load();
                    HomeFrontController homeCtrl = homeLoader.getController();
                    homeCtrl.changePage(" Order-CheckOut", root);
                    userName.getScene().setRoot(homeRoot);

                } else {
                    Image img = new Image("gamershub/images/warning.png");
                    Notifications notfBuilder = Notifications.create().title("Quantity warning").text("Not enough quantity stocked")
                            .darkStyle().graphic(new ImageView(img)).hideAfter(Duration.seconds(10)).position(Pos.TOP_LEFT);
                    notfBuilder.show();
                }

            } catch (Exception ex) {
                Logger.getLogger(CartPageController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }
}
