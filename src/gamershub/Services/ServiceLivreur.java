/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Services;

import gamershub.Entities.Livreur;
import  gamershub.Services.IserviceLivreur;
import  gamershub.Utils.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import java.util.Optional;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author User
 */
public class ServiceLivreur implements IserviceLivreur {

    Connection con;

    public ServiceLivreur() {
          con = MyDB.getInstance().getCon();
    }

    @Override
    public void AjouterLivreur(Livreur c) {
        try {
            Statement stm = con.createStatement();

            String query = "INSERT INTO livreur (nom,prenom,cin,salaire,zone_geographique) VALUES ('" + c.getNom() + "','" + c.getPrenom() + "','" + c.getCin() + "','" + c.getSalaire() + "','" + c.getZone_geographique() + "')";
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Confirmation ");
            alert.setContentText("Etes vous sur de vouloir ajouter ce livreur ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                stm.executeUpdate(query);
                Alert alert2 = new Alert(AlertType.INFORMATION);
                alert2.setTitle("Ajout");
                alert2.setHeaderText("Livreur ajoutée");
                alert2.setContentText("Le livreur a été ajouter avec success!");
                alert2.showAndWait();
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceLivreur.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public ObservableList<Livreur> AfficherLivreur() {
        ObservableList<Livreur> livreurs = FXCollections.observableArrayList();
        try {
            Statement stm;

            stm = con.createStatement();

            String query = "SELECT * from `livreur`";
            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {
                Livreur c = new Livreur();
                c.setId(rst.getInt("id"));
                c.setNom(rst.getString("nom"));
                c.setPrenom(rst.getString("prenom"));
                c.setCin(rst.getInt("cin"));
                c.setZone_geographique(rst.getString("zone_geographique"));
                c.setSalaire(rst.getDouble("salaire"));
                livreurs.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceLivreur.class.getName()).log(Level.SEVERE, null, ex);
        }

        return livreurs;
    }

    @Override
    public void supprimerlivreur(int id) {
        try {
            Statement stm = con.createStatement();

            String query = " Delete FROM livreur where id='" + id + "'";
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Confirmation ");
            alert.setContentText("Etes vous sur de vouloir supprimer ce livreur ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                stm.executeUpdate(query);
                Alert alert2 = new Alert(AlertType.INFORMATION);
                alert2.setTitle("Suppression");
                alert2.setHeaderText("Livreur Supprimé");
                alert2.setContentText("Le livreur a été supprimer avec success!");
                alert2.showAndWait();
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceLivreur.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void ModifierLivreur(Livreur c) {
        try {

            PreparedStatement ps;

            ps = con.prepareStatement("UPDATE  livreur set `nom`=?,`prenom`=?,`cin`=?,`salaire`=?,`zone_geographique`=? where id=" + c.getId());
            ps.setString(1, c.getNom());
            ps.setString(2, c.getPrenom());
            ps.setInt(3, c.getCin());
            ps.setDouble(4, c.getSalaire());
            ps.setString(5, c.getZone_geographique());
            ps.executeUpdate();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Update");
            alert.setHeaderText("Livreur Modifié");
            alert.setContentText("Le livreur a été modifié avec success!");
            alert.showAndWait();

            alert.showAndWait();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceLivreur.class.getName()).log(Level.SEVERE, null, ex);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Ajout");
            alert.setHeaderText("Livreur Ajouté");
            alert.setContentText("Le Livreur a été Ajouter avec success!");
            alert.showAndWait();
        }
    }

    public ObservableList<Livreur> search(String input) {
        ObservableList<Livreur> livreurs = FXCollections.observableArrayList();
        try {
            Statement stm;
            stm = con.createStatement();

            String query = "SELECT * from livreur where zone_geographique like '%" + input + "%'";
            ResultSet rst = stm.executeQuery(query);
            Livreur form;
            while (rst.next()) {
                Livreur c = new Livreur();
                c.setId(rst.getInt("id"));
                c.setCin(rst.getInt("cin"));
                c.setPrenom(rst.getString("prenom"));
                c.setNom(rst.getString("nom"));
                c.setSalaire(rst.getDouble("salaire"));
                c.setZone_geographique(rst.getString("zone_geographique"));
                form = new Livreur(rst.getInt("id"), rst.getInt("cin"), rst.getString("prenom"), rst.getString("nom"), rst.getDouble("salaire"), rst.getString("zone_geographique"));
                livreurs.add(form);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceLivreur.class.getName()).log(Level.SEVERE, null, ex);
        }

        return livreurs;
    }

    public ObservableList<Livreur> triasc() {
        ObservableList<Livreur> livreurs = FXCollections.observableArrayList();
        try {
            Statement stm = con.createStatement();
            String query = "SELECT * from livreur ORDER by zone_geographique ASC";
            ResultSet rst = stm.executeQuery(query);
            Livreur form;
            while (rst.next()) {
                Livreur c = new Livreur();
                c.setId(rst.getInt("id"));
                c.setCin(rst.getInt("cin"));
                c.setPrenom(rst.getString("prenom"));
                c.setNom(rst.getString("nom"));
                c.setSalaire(rst.getDouble("salaire"));
                c.setZone_geographique(rst.getString("zone_geographique"));
                form = new Livreur(rst.getInt("id"), rst.getInt("cin"), rst.getString("prenom"), rst.getString("nom"), rst.getDouble("salaire"), rst.getString("zone_geographique"));
                livreurs.add(form);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceLivreur.class.getName()).log(Level.SEVERE, null, ex);
        }

        return livreurs;
    }

    public ObservableList<Livreur> triadsc() {
        ObservableList<Livreur> livreurs = FXCollections.observableArrayList();
        try {
            Statement stm = con.createStatement();
            String query = "SELECT * from livreur ORDER by zone_geographique DESC";
            ResultSet rst = stm.executeQuery(query);
            Livreur form;
            while (rst.next()) {
                Livreur c = new Livreur();
                c.setId(rst.getInt("id"));
                c.setCin(rst.getInt("cin"));
                c.setPrenom(rst.getString("prenom"));
                c.setNom(rst.getString("nom"));
                c.setSalaire(rst.getDouble("salaire"));
                c.setZone_geographique(rst.getString("zone_geographique"));
                form = new Livreur(rst.getInt("id"), rst.getInt("cin"), rst.getString("prenom"), rst.getString("nom"), rst.getDouble("salaire"), rst.getString("zone_geographique"));
                livreurs.add(form);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceLivreur.class.getName()).log(Level.SEVERE, null, ex);
        }

        return livreurs;
    }
public void sendmail(String mail,String nom, String prenom) {
		
		String to = mail;
		String from = "azizbarguellil@gmail.com";
		final String username = "azizbarguellil@gmail.com";
		final String password = "Azem-2019mai";
		
		String host = "smtp.gmail.com";
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");  
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");
		
		javax.mail.Session session = javax.mail.Session.getInstance(props,
         new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(username, password);
     }
         });
		
		try {
    //create a MimeMessage object
    Message message = new MimeMessage(session);
 
    //set From email field 
    message.setFrom(new InternetAddress(from));
 
    //set To email field
    message.setRecipients(javax.mail.Message.RecipientType.TO,
               InternetAddress.parse(to));
 
    //set email subject field
    message.setSubject("GamersHub");
 
    //set the content of the email message
    message.setText("Votre livreur est : "+nom +prenom);

    //send the email message
    Transport.send(message);

    System.out.println("Votre livreur est : "+nom +prenom);

      } catch (MessagingException e) {
         throw new RuntimeException(e);
      }
    
    
}
 

}
