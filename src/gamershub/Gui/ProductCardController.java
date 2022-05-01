/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import static gamershub.Gamershub.cartMap;
import static gamershub.Gamershub.loggedUser;
import gamershub.Services.WishListService;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author meriam
 */
public class ProductCardController implements Initializable {

    @FXML
    private Pane categoryCard;
    @FXML
    private ImageView image;
    @FXML
    private Label name;
    @FXML
    private Label price;
    @FXML
    private Label prodId;
    @FXML
    private ImageView cartbtn;
    @FXML
    private ImageView wishlistIcon;

    /**
     * Initializes the controller class.
     */
    public void setProductName(String categoryName) {
        this.name.setText(categoryName);
    }

    public void setImage(String url) {
        this.image.setImage(new Image(url, 209, 114, false, false));
    }

    public void setWishList(String url) {
        this.wishlistIcon.setImage(new Image(url, 32, 32, false, false));
    }

    public void setPrice(String price) {
        this.price.setText(price);
    }

    public void setId(String id) {
        this.prodId.setText(id);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void addProductToCart(MouseEvent event) {
        int qt = 0;
        if (cartMap.containsKey(Integer.parseInt(prodId.getText()))) {
            qt = cartMap.get(Integer.parseInt(prodId.getText()));
            cartMap.put(Integer.parseInt(prodId.getText()), qt + 1);
             Notifications notfBuilder = Notifications.create().title("Quantity Ordered increased")
                    .hideAfter(Duration.seconds(3)).position(Pos.TOP_RIGHT);
            notfBuilder.show();
        } else {
            cartMap.put(Integer.parseInt(prodId.getText()), 1);
            System.out.print(cartMap.keySet());

            Notifications notfBuilder = Notifications.create().title("Item added to Cart")
                    .hideAfter(Duration.seconds(3)).position(Pos.TOP_RIGHT);
            notfBuilder.show();
        }

    }

    @FXML
    private void addRemove(MouseEvent event) {
        try {
            String img = "http://127.0.0.1:8000/shop/images/";
            WishListService ws = new WishListService();
            if (ws.afficher().contains(Integer.parseInt(prodId.getText()))) {
                ws.remove(Integer.parseInt(prodId.getText()));
                wishlistIcon.setImage(new Image(img + "empty.png", 32, 32, false, false));
            } else {
                ws.add(Integer.parseInt(prodId.getText()), loggedUser.getId());
                wishlistIcon.setImage(new Image(img + "full.png", 32, 32, false, false));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductCardController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
