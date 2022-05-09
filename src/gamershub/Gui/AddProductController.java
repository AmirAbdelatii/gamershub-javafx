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
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author meriam
 */
public class AddProductController implements Initializable {

    @FXML
    private TextField productName;
    @FXML
    private Label productNameError;
    @FXML
    private TextField description;
    @FXML
    private Label descriptionError;
    @FXML
    private TextField image;
    @FXML
    private Button uploadImage;
    @FXML
    private Label imageError;
    @FXML
    private ComboBox<String> category;
    @FXML
    private Label categoryError;
    @FXML
    private TextField price;
    @FXML
    private Label priceError;
    @FXML
    private TextField quantity;
    @FXML
    private Label quantityError;
    @FXML
    private Button cancel;
    @FXML
    private Button addProduct;

    Products p = new Products();
    final FileChooser fileChooser = new FileChooser();



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    
        public void setCombo() {

        try {
            // TODO
            CategoryService cs = new CategoryService();
            List<Category> list = cs.afficher();
            ObservableList<String> listId = FXCollections.observableArrayList();
            for (Category c : list) {
                listId.add(c.getId() + ":  " + c.getCategoryName());
            }

            category.setItems(listId);
        } catch (SQLException ex) {
            Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 

    @FXML
    private void cancel(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
            Parent root = loader.load();
            HomeController controller = loader.getController();
            controller.changePage("Products");
            
            addProduct.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void addProduct(ActionEvent event) {
        productNameError.setText("");
        descriptionError.setText("");
        imageError.setText("");
        categoryError.setText("");
        priceError.setText("");
        quantityError.setText("");
        if (productName.getText().length() != 0 && description.getText().length() != 0 && price.getText().length() != 0 && quantity.getText().length() != 0 && category.getSelectionModel().isEmpty() == false && image.getText().length() != 0) {
            if (Pattern.matches("^[a-zA-Z]*$", productName.getText()) == true && Pattern.matches("^[a-zA-Z]*$", description.getText()) == true && Pattern.matches("^[0-9\\.\\-\\/]*$", price.getText()) == true && Pattern.matches("^[0-9]*$", quantity.getText()) == true && Integer.parseInt(quantity.getText()) > -1 && Float.parseFloat(price.getText()) > -1) {
                p.setProductName(productName.getText());
                p.setDescription(description.getText());

                p.setPrice(Float.parseFloat(price.getText()));
                p.setQuantityStocked(Integer.parseInt(quantity.getText()));
                String[] categoryIdCombo = category.getValue().split(":", 2);
                p.setCatId(Integer.parseInt(categoryIdCombo[0]));

                ProductsService ps = new ProductsService();
                try {
                    ps.ajouter(p);

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
                    Parent root = loader.load();
                    HomeController controller = loader.getController();
                    controller.changePage("Products");

                    addProduct.getScene().setRoot(root);

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            } else {
                if (Pattern.matches("^[a-zA-Z]*$", productName.getText()) == false) {
                    productNameError.setText("This field can only contain letters");
                }
                if (Pattern.matches("^[a-zA-Z]*$", description.getText()) == false) {
                    descriptionError.setText("This field can only contain letters");
                }
                if (Pattern.matches("^[0-9\\.\\-\\/]*$", price.getText()) == false) {
                    priceError.setText("This field can only contain numbers");
                }
                if (Pattern.matches("^[0-9]*$", quantity.getText()) == false) {
                    quantityError.setText("This field can only contain numbers");
                }
                if (Integer.parseInt(quantity.getText()) < 0) {
                    quantityError.setText("This field must be positive");
                }
                if (Float.parseFloat(price.getText()) < 0) {
                    priceError.setText("This field must be positive");
                }
            }
        } else {
            if (productName.getText().length() == 0) {
                productNameError.setText("Fill this Field");
            }

            if (description.getText().length() == 0) {
                descriptionError.setText("Fill this Field ");
            }

            if (image.getText().length() == 0) {
                imageError.setText("Fill this Field");
            }
            if (category.getSelectionModel().isEmpty()) {
                categoryError.setText("select category");
            }
            if (price.getText().length() == 0) {
                priceError.setText("Fill this Field ");
            }

            if (quantity.getText().length() == 0) {
                quantityError.setText("Fill this Field");
            }
            if (Pattern.matches("^[a-zA-Z]*$", productName.getText()) == false) {
                productNameError.setText("This field can only contain letters");
            }
            if (Pattern.matches("^[a-zA-Z]*$", description.getText()) == false) {
                descriptionError.setText("This field can only contain letters");
            }
            if (Pattern.matches("^[0-9\\.\\-\\/]*$", price.getText()) == false) {
                priceError.setText("This field can only contain numbers");
            }
            if (Pattern.matches("^[0-9]*$", quantity.getText()) == false) {
                quantityError.setText("This field can only contain numbers");
            }
            if (Integer.parseInt(quantity.getText()) < 0) {
                quantityError.setText("This field must be positive");
            }
            if (Float.parseFloat(price.getText()) < 0) {
                priceError.setText("This field must be positive");
            }

        }
    }
        //upload image
    private Desktop desktop = Desktop.getDesktop();
    Stage primaryStage;

    @FXML
    private void upload(ActionEvent event) {

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"));
        fileChooser.setTitle("Save");
        File file = fileChooser.showOpenDialog(primaryStage);
        image.clear();

        if (file != null) {
            // generate a fileName
            String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijk" + "lmnopqrstuvwxyz!@#$%&";
            Random rnd = new Random();
            StringBuilder sb = new StringBuilder(10);
            for (int i = 0; i < 10; i++) {
                sb.append(chars.charAt(rnd.nextInt(chars.length())));
            }
            String fileName = sb.toString();

            File source = file;
            File dest = new File("C:\\Users\\bouza\\OneDrive\\Bureau\\nour merge\\public\\shop\\images\\" + fileName + ".png");

            try {
                Files.copy(source.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
            }

            List<File> files = Arrays.asList(file);
            printLog(image, files, fileName);
            p.setImage(fileName + ".png");
        }
    }

    private void printLog(TextField textArea, List<File> files, String fileName) {
        if (files == null || files.isEmpty()) {
            return;
        }
        for (File file : files) {
            textArea.appendText(fileName + ".png");
        }
    }

}
