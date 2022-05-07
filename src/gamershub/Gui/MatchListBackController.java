/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Gui;

import Entities.Category;
import gamershub.Entities.Matchs;

import gamershub.Services.MatchService;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import static java.nio.file.Files.list;
import static java.rmi.Naming.list;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author meriam
 */
public class MatchListBackController implements Initializable {

    @FXML
    private Button btnOverview;
    @FXML
    private Button btnPackages;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnSignout;
    @FXML
    private Pane pnlOverview;
  
    @FXML
    private VBox ListCat;
    @FXML
    private HBox itemC;
    @FXML
    private Button addCategoryButton;
    @FXML
    private Button btnCategories;
    @FXML
    private Button btnProducts;
    @FXML
    private Button btnStatitics;
    @FXML
    private Button addCategoryButton1;
    @FXML
    private TextField tsrechercher;
 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         MatchService cs = new MatchService();
         List<Matchs> Matchs = cs.readMatch();
         //            try {
         for (Matchs c : Matchs) {
             
             
             FXMLLoader item = new FXMLLoader(getClass().getResource("ItemMatch.fxml"));
             try {
                 Parent itek = item.load();
                 
                 ItemMatchController itemController =item.getController();
                 //System.out.print(itemController);
                 
                 itemController.setMatchName(c.getMatchName());
                 itemController.setMatchId(c.getId()+"");
                 
                 itemController.setMatchDate(c.getMatchDate()+"");
                 
                 itemController.setResult(c.getResult()+"");
                 
                 ListCat.getChildren().add(itek);
                   FilteredList<Matchs> filteredData;
                   FilteredList<Matchs> list = null;
                
   
             } catch (IOException ex) {
                 Logger.getLogger(MatchListBackController.class.getName()).log(Level.SEVERE, null, ex);
             }
         }}


@FXML
        private void handleClicks(ActionEvent event) {
        try {
           
           
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddMatch.fxml"));
            Parent root = loader.load();
            addCategoryButton.getScene().setRoot(root);
           
        } catch (IOException ex) {
            Logger.getLogger(MatchListBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void showMatchs(ActionEvent event) {
              try {
           
           
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MatchListBack.fxml"));
            Parent root = loader.load();
            addCategoryButton.getScene().setRoot(root);
           
        } catch (IOException ex) {
            Logger.getLogger(MatchListBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       
    

    @FXML
    private void showTeams(ActionEvent event) {
          try {
           
           
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TeamListBack.fxml"));
            Parent root = loader.load();
            addCategoryButton.getScene().setRoot(root);
           
        } catch (IOException ex) {
            Logger.getLogger(MatchListBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void showStatitcs(ActionEvent event) {
         try {
           
           
            FXMLLoader loader = new FXMLLoader(getClass().getResource("statics.fxml"));
            Parent root = loader.load();
            addCategoryButton.getScene().setRoot(root);
           
        } catch (IOException ex) {
            Logger.getLogger(MatchListBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  

    
    @FXML
    private void pdf(ActionEvent event) throws   ClassNotFoundException, SQLException, DocumentException, BadElementException, IOException, URISyntaxException{
     
      try {
              Class.forName("com.mysql.jdbc.Driver");
                  Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/integrationdb2", "root", "");
      PreparedStatement pt = cnx.prepareStatement("select * from matchs");
            ResultSet rs = pt.executeQuery();
            
                       /* Step-2: Initialize PDF documents - logical objects */

                       Document my_pdf_report = new Document();

                       PdfWriter.getInstance(my_pdf_report, new FileOutputStream("La liste des matchs.pdf"));
                       
                        my_pdf_report.open();  
                      
//                            Image img = Image.getInstance("C:\image.png");
//                            my_pdf_report.add(img);
                             

                       my_pdf_report.add(new Paragraph("Liste des matchs"));
                       my_pdf_report.addCreationDate();
              
                       
                       //we have four columns in our table
                       PdfPTable my_report_table = new PdfPTable(3);
                             
                       //create a cell object
                       PdfPCell table_cell;
                                          
                                         
                                       table_cell=new PdfPCell(new Phrase(" match_name"));
                                      table_cell.setBackgroundColor(BaseColor.PINK);
                                       my_report_table.addCell(table_cell);
                                       table_cell=new PdfPCell(new Phrase("match_date"));
                                       table_cell.setBackgroundColor(BaseColor.PINK);
                                       my_report_table.addCell(table_cell);
                                       table_cell=new PdfPCell(new Phrase("result"));
                                       table_cell.setBackgroundColor(BaseColor.PINK);
                                       my_report_table.addCell(table_cell);
                               
                                       
                                       
                                       
                                       
                                      while(rs.next()){
                                       
                                       String produits= rs.getString("match_name");
                                       table_cell=new PdfPCell(new Phrase(produits));
                                       my_report_table.addCell(table_cell);
                            
                                       java.util.Date date = Calendar.getInstance().getTime();
                                       DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                                       String strDate = dateFormat.format(date);
                                       table_cell=new PdfPCell(new Phrase(strDate));
                                       my_report_table.addCell(table_cell);
                                       String prix_tot=rs.getString("result");
                                       table_cell=new PdfPCell(new Phrase(prix_tot));
                                       my_report_table.addCell(table_cell);
                                   
                                       
                                       
                                               
                       }
                       /* Attach report table to PDF */
                       
                       my_pdf_report.add(my_report_table); 
                       
             System.out.println(my_pdf_report);
                       my_pdf_report.close();
                       JOptionPane.showMessageDialog(null, "Imprimer avec succès");

                       /* Close all DB related objects */
                       rs.close();
                       pt.close(); 
                       cnx.close();               


       } catch (FileNotFoundException e) {
       // TODO Auto-generated catch block
       e.printStackTrace();
       }
        
}}
