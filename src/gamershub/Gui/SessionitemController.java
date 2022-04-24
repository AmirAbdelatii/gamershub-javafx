/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Gui;

import gamershub.Entities.Sessioncoaching;
import gamershub.Services.ServiceCoachs;
import gamershub.Services.ServiceSessions;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.JFileChooser;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class SessionitemController implements Initializable {

    @FXML
    private ImageView coachimg;
    @FXML
    private Label idsession;
    @FXML
    private Label coachid;
    @FXML
    private Label username;
    @FXML
    private Label desc;
    @FXML
    private Label prix;
    @FXML
    private Label start;
    @FXML
    private Label end;
    @FXML
    private Label userid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setCoachImage(String url) {
        this.coachimg.setImage(new Image(url));
    }
    
    public void SetCoachid(String coachid){
       this.coachid.setText(coachid);
    }
    
    public void SetUsername(String username){
       this.username.setText(username);
    }
    
    public void Setdesc(String desc){
       this.desc.setText(desc);
    }
    
    public void Setidsession(String idsession){
       this.idsession.setText(idsession);
    }
    
    public void Setprix(String prix){
       this.prix.setText(prix);
    }
    public void Setdatedebut(String start){
       this.start.setText(start);
    }
    public void Setdatefin(String end){
       this.end.setText(end);
    }
    public void Setuserid(String userid){
       this.userid.setText(userid);
    }

    @FXML
    private void updateClick(ActionEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Editsession.fxml"));
            Parent root = loader.load();
            EditsessionController c =loader.getController();
            Sessioncoaching s = new Sessioncoaching(Integer.parseInt(idsession.getText()),Date.valueOf(start.getText()),Date.valueOf(end.getText()),Float.parseFloat(prix.getText()),desc.getText(),username.getText(),Integer.parseInt(userid.getText()),Integer.parseInt(coachid.getText())); 
            System.out.println(s);
            c.remplir(s);
            desc.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(LoginFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void deleteClick(ActionEvent event) {
        ServiceSessions cs = new ServiceSessions();
        int id=Integer. parseInt(idsession.getText());
        try {
            cs.deletesession(id);
        } catch (SQLException ex) {
            Logger.getLogger(SessionitemController.class.getName()).log(Level.SEVERE, null, ex);
        }
                try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Session.fxml"));
            Parent root = loader.load();
            desc.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(SessionitemController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @FXML
    private void exportClick(ActionEvent event) {
        
       int[] serial = new int [7];
       for (int i =0; i< serial.length;i++)
       {
           serial[i] = i+1;
       }
       String[] name =new String[7];
       name[0]="Session ID";
       name[1]="Coach ID";
       name[2]="User ID";
       name[3]="Start Date";
       name[4]="End Date";
       name[5]="Price";
       name[6]="description";
       
       String[] result= new String[7];
       result[0]=idsession.getText();
       result[1]=coachid.getText();
       result[2]=userid.getText();
       result[3]=start.getText();
       result[4]=end.getText();
       result[5]=prix.getText();
       result[6]=desc.getText();
        
       XSSFWorkbook workbook =new XSSFWorkbook();
       CellStyle style = workbook.createCellStyle();
       style.setAlignment(HorizontalAlignment.CENTER);
       style.setVerticalAlignment(VerticalAlignment.CENTER);
       
       
        XSSFSheet sheet = workbook.createSheet("results");
        
        XSSFRow row;
         row= sheet.createRow(0);
         Cell cell0= row.createCell(0);
         Cell cell1= row.createCell(1);
         Cell cell2= row.createCell(2);
         
         cell0.setCellStyle(style);
         cell1.setCellStyle(style);
         cell2.setCellStyle(style);
         
         cell0.setCellValue("Ordre");
         cell1.setCellValue("Donnees");
         cell2.setCellValue("Result");
         
         for (int i =0;i<serial.length;i++)
         {
             row=sheet.createRow(i+1);
             for (int j=0;j<3;j++)
             { 
                 Cell cell =row.createCell(j);
                 cell.setCellStyle(style);
                 
                 if(cell.getColumnIndex()==0)
                 {
                     cell.setCellValue(serial[i]);
                     
                 }
                 else if (cell.getColumnIndex()==1){
                  cell.setCellValue(name[i]);
                 }
                 else if (cell.getColumnIndex()==2){
                  cell.setCellValue(result[i]);
                 }
                 
             }
            
         }
         
         for(int i =0;i<5;i++){
                 sheet.autoSizeColumn(i);
             }
         
//         try{
//         FileOutputStream out =new FileOutputStream(new File("Result.xlsx"));
//         workbook.write(out);
//         System.out.println("Excel file is created");
//         out.close();
//         } catch (FileNotFoundException ex) {
//            Logger.getLogger(SessionitemController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(SessionitemController.class.getName()).log(Level.SEVERE, null, ex);
//        }
         
          JFileChooser savefile = new JFileChooser();
          savefile.setDialogTitle("save file");
          savefile.setSelectedFile(new File("Result.xlsx"));
          if(savefile.showSaveDialog(null)==JFileChooser.APPROVE_OPTION){
          File output = savefile.getSelectedFile();
          try(FileOutputStream out =new FileOutputStream(output)){
          workbook.write(out);
          out.close();
          } catch (FileNotFoundException ex) {
               Logger.getLogger(SessionitemController.class.getName()).log(Level.SEVERE, null, ex);
           } catch (IOException ex) {
               Logger.getLogger(SessionitemController.class.getName()).log(Level.SEVERE, null, ex);
           }
          }
         
//                try {
//
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("Session.fxml"));
//            Parent root = loader.load();
//            desc.getScene().setRoot(root);
//
//        } catch (IOException ex) {
//            Logger.getLogger(SessionitemController.class.getName()).log(Level.SEVERE, null, ex);
//        } 
         
         
         
        
    }
    
}
