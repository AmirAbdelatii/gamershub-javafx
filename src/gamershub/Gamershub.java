/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub;

import Entities.Products;
import gamershub.Entities.User;
import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author MAB
 */
public class Gamershub extends Application {

    /**
     * @param args the command line arguments
     */
    public static User loggedUser = null;
    public static Map<Integer,Integer> cartMap =new HashMap();

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Gui/LoginForm.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        primaryStage.setTitle("GamersHub");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }

}
