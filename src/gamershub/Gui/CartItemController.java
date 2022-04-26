/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Gui;

import Entities.Products;
import static gamershub.Gamershub.cartMap;
import gamershub.Services.ProductsService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author meriam
 */
public class CartItemController implements Initializable {

    @FXML
    private Label prodId;
    @FXML
    private Label quantity;
    @FXML
    private Pane categoryCard;
    @FXML
    private ImageView minus;
    @FXML
    private ImageView plus;
    @FXML
    private Label ProdIdHidden;

    public void setProdId(String prodName) {
        this.prodId.setText(prodName);
    }

    public void setProdIdHidden(int prodIdHidden) {
        this.ProdIdHidden.setText(prodIdHidden + "");
    }

    public void setQuantity(int quantity) {
        this.quantity.setText(quantity + "");
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void removeQuantity(MouseEvent event) {
        int qt = 0;

        qt = cartMap.get(Integer.parseInt(ProdIdHidden.getText()));
        if (qt > 1) {
            cartMap.put(Integer.parseInt(ProdIdHidden.getText()), qt - 1);
            int s = qt - 1;
            quantity.setText(s + "");
        }
    }

    @FXML
    private void addQuantity(MouseEvent event) {

        int qt = 0;
        qt = cartMap.get(Integer.parseInt(ProdIdHidden.getText()));
        cartMap.put(Integer.parseInt(ProdIdHidden.getText()), qt + 1);
        int s = qt + 1;
        quantity.setText(s + "");

    }

}
