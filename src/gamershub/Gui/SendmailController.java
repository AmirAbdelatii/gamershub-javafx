/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Gui;

import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class SendmailController implements Initializable {

    @FXML
    private TextField fromfield;
    @FXML
    private TextField subjectfield;
    @FXML
    private TextArea messagefield;
    @FXML
    private TextField sendfield;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void sendclick(ActionEvent event) {
    }

    @FXML
    private void cancelclick(ActionEvent event) {
    }
    
    public void sendmail(){
       
        String to = sendfield.getText();
        String host = "smtp.gmail.com";
        String username = fromfield.getText();
        
        Properties props = System.getProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        
         String myAccountEmail = "ghub2441@gmail.com";
        String password = "curvanordallez";
        
        Session session = Session.getDefaultInstance(props, new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password); //To change body of generated methods, choose Tools | Templates.
            }
        
        });
         
    }
    
}
