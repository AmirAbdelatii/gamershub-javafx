/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Gui;

import Entities.Products;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;
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
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author meriam
 */
public class CategoryCardController implements Initializable {

    @FXML
    private ImageView image;
    @FXML
    private Label name;
    @FXML
    private Label description;
    @FXML
    private Pane categoryCard;
    @FXML
    private Label catId;

    List<Products> products;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setCategoryName(String categoryName) {
        this.name.setText(categoryName);
    }

    public void setImage(String url) {
        this.image.setImage(new Image(url, 209, 114, false, false));
    }

    public void setDescription(String description) {
        this.description.setText(description);
    }

    public void setId(String id) {
        this.catId.setText(id);
    }

}


