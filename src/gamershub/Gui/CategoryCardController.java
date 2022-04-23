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
   this.image.setImage(new Image(url,209,114,false,false));
    }
         public void setDescription(String description ) {
        this.description.setText(description); 
    }
    
}
