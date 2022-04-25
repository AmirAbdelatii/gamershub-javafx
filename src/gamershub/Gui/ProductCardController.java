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

    /**
     * Initializes the controller class.
     */
    public void setProductName(String categoryName) {
        this.name.setText(categoryName);
    }

    public void setImage(String url) {
        this.image.setImage(new Image(url, 209, 114, false, false));
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
        } else {
            cartMap.put(Integer.parseInt(prodId.getText()), 1);
            System.out.print(cartMap.keySet());
        }

    }

}
