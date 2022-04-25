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

    public void setProdId(int prodId) {
        this.prodId.setText(prodId+"");
    }

    public void setQuantity(int quantity) {
        this.quantity.setText(quantity+"");
    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
