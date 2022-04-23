/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Gui;

import Entities.Category;
import gamershub.Services.CategoryService;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author meriam
 */
public class AddCategoryController implements Initializable {

    @FXML
    private TextField categoryName;
    @FXML
    private Label categoryNameError;
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
    private Button cancel;
    @FXML
    private Button addCategory;

    final FileChooser fileChooser = new FileChooser();
    Category c = new Category();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }


    @FXML
    private void cancel(ActionEvent event) {
              try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
                    Parent root = loader.load();
                    HomeController controller = loader.getController();
                    controller.changePage("Categories");

                    addCategory.getScene().setRoot(root);

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
    }

    @FXML
    private void add(ActionEvent event) {
        categoryNameError.setText("");
        descriptionError.setText("");
        imageError.setText("");

        if (categoryName.getText().length() != 0 && description.getText().length() != 0 && image.getText().length() != 0) {
            if (Pattern.matches("^[a-zA-Z]*$", categoryName.getText()) == true && Pattern.matches("^[a-zA-Z]*$", description.getText()) == true) {

                c.setCategoryName(categoryName.getText());
                c.setDescription(description.getText());
               // c.setImage(image.getText());
                CategoryService cs = new CategoryService();
                try {
                    cs.ajouter(c);

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
                    Parent root = loader.load();
                    HomeController controller = loader.getController();
                    controller.changePage("Categories");

                    addCategory.getScene().setRoot(root);

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            } else {
                if (Pattern.matches("^[a-zA-Z]*$", categoryName.getText()) == false) {
                    categoryNameError.setText("This field can only contain letters");
                }
                if (Pattern.matches("^[a-zA-Z]*$", description.getText()) == false) {
                    descriptionError.setText("This field can only contain letters");
                }
            }

        } else {
            if (categoryName.getText().length() == 0) {
                categoryNameError.setText("Fill this Field");
            }

            if (description.getText().length() == 0) {
                descriptionError.setText("Fill this Field ");
            }

            if (image.getText().length() == 0) {
                imageError.setText("Fill this Field");
            }
            if (Pattern.matches("^[a-zA-Z]*$", categoryName.getText()) == false) {
                categoryNameError.setText("This field can only contain letters");
            }
            if (Pattern.matches("^[a-zA-Z]*$", description.getText()) == false) {
                descriptionError.setText("This field can only contain letters");
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
            File dest = new File("C:\\Users\\meriam\\Desktop\\webVersionFinal\\public\\shop\\images\\" + fileName + ".png");

            try {
                Files.copy(source.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                Logger.getLogger(AddCategoryController.class.getName()).log(Level.SEVERE, null, ex);
            }

            List<File> files = Arrays.asList(file);
            printLog(image, files,fileName);
            c.setImage(fileName+".png");
        }
    }

    private void printLog(TextField textArea, List<File> files,String fileName) {
        if (files == null || files.isEmpty()) {
            return;
        }
        for (File file : files) {
            textArea.appendText(fileName+".png");
        }
    }

}
