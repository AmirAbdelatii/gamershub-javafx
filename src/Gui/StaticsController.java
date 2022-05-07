/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;
import Entities.Category;
import Entities.Products;
import Entities.Teams;
import Services.CategoryService;
import Services.ProductsService;
import Services.TeamService;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class StaticsController implements Initializable {

    @FXML
    private PieChart categChart;
    @FXML
    private Label l_Tcategorie;
    @FXML
    private Label l_TProduit;
    @FXML
    private Button btnPackages;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnSignout;
    @FXML
    private Pane pnlOverview;
    @FXML
    private Button cancel;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          // ObservableList<PieChart.Data> pieChartData = null;
         TeamService sa = new TeamService();
       HashMap l = sa.CountPdtByCat();
        System.out.println(l);
       Iterator it = l.entrySet().iterator();
         int c1 = sa.countNotVerifid();
         int c2 = sa.countVerifid();
         l_Tcategorie.setText(String.valueOf(c1));
         l_TProduit.setText(String.valueOf(c2));
        // statistics for pie charts ----->>>> NEXTT >>>>>>---------
        //----PIE CHART
     
       ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList( 
            new PieChart.Data("Verified", c2), 
            new PieChart.Data("NotVerified", c1)
            );

       categChart.getData().addAll(pieChartData);
        // categChart.setData(pieChartData);
        categChart.setTitle("nombre des equipes verifié");
        categChart.setClockwise(true);
        categChart.setLabelLineLength(50);
        categChart.setLabelsVisible(true);
        categChart.setStartAngle(180);
       
       
       
    }

    @FXML
    private void cancel(ActionEvent event) {
        
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("MatchListBack.fxml"));
            Parent root = loader.load();
            cancel.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(MatchListBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }
 
   
      
    

                   