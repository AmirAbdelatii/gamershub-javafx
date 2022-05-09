/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Services;

import gamershub.Entities.Livraison;
import gamershub.Entities.Livraison;
import gamershub.Services.IServiceLivraison;
import gamershub.Utils.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.Date;
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
public class ServiceLivraison implements IServiceLivraison {

    Connection con;

    public ServiceLivraison() {
         con = MyDB.getInstance().getCon();
    }

    @Override
    public void AjouterLivraison(Livraison c) {
        try {
            Statement stm = con.createStatement();

            String query = "INSERT INTO livraison2(date,prix,etat) VALUES ('" + c.getDate() + "','" + c.getPrix() + "','" + c.getEtat() + "')";
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Confirmation ");
            alert.setContentText("Etes vous sur de vouloir ajouter cette Livraison?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                stm.executeUpdate(query);
                Alert alert2 = new Alert(AlertType.INFORMATION);
                alert2.setTitle("Ajout");
                alert2.setHeaderText("Livraison ajoutée");
                alert2.setContentText("La Livraison a été ajouter avec success!");
                alert2.showAndWait();
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceLivraison.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public ObservableList<Livraison> AfficherLivraison() {
        ObservableList<Livraison> livraisons = FXCollections.observableArrayList();
        try {
            Statement stm;

            stm = con.createStatement();

            String query = "SELECT * from `livraison2`";
            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {
                Livraison c = new Livraison();
                c.setId(rst.getInt("id"));
                c.setDate(rst.getDate("date"));
                c.setPrix(rst.getFloat("prix"));
                c.setEtat(rst.getString("etat"));
                livraisons.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceLivraison.class.getName()).log(Level.SEVERE, null, ex);
        }

        return livraisons;
    }

    @Override
    public void supprimerLivraison(int id) {
        try {
            Statement stm = con.createStatement();

            String query = " Delete FROM livraison2 where id='" + id + "'";
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Confirmation ");
            alert.setContentText("Etes vous sur de vouloir supprimer cette Livraison?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                stm.executeUpdate(query);
                Alert alert2 = new Alert(AlertType.INFORMATION);
                alert2.setTitle("Suppression");
                alert2.setHeaderText("Livraison Supprimé");
                alert2.setContentText("La Livraison a été supprimer avec success!");
                alert2.showAndWait();
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceLivraison.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void ModifierLivraison(Livraison c) {
        try {

            PreparedStatement ps;

            ps = con.prepareStatement("UPDATE  livraison2 set `date`=?,`prix`=?,`etat`=? where id=" + c.getId());
            ps.setDate(1, (java.sql.Date) c.getDate());
            ps.setFloat(2, c.getPrix());
            ps.setString(3, c.getEtat());
            ps.executeUpdate();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Update");
            alert.setHeaderText("Livraison Modifié");
            alert.setContentText("La Livraison a été modifier avec success!");
            alert.showAndWait();

            alert.showAndWait();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceLivraison.class.getName()).log(Level.SEVERE, null, ex);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Ajout");
            alert.setHeaderText("Livraison Ajouté");
            alert.setContentText("La Livraison a été Ajouter avec success!");
            alert.showAndWait();
        }
    }

    public ObservableList<Livraison> search(String input) {
        ObservableList<Livraison> livraisons = FXCollections.observableArrayList();
        try {
            Statement stm;
            stm = con.createStatement();

            String query = "SELECT * from livraison2 where etat like '%" + input + "%'";
            ResultSet rst = stm.executeQuery(query);
            Livraison form;
            while (rst.next()) {
                Livraison c = new Livraison();
                c.setId(rst.getInt("id"));
                c.setDate(rst.getDate("date"));
                c.setPrix(rst.getFloat("prix"));
                c.setEtat(rst.getString("etat"));
                form = new Livraison(rst.getInt("id"), rst.getDate("date"), rst.getFloat("prix"), rst.getString("etat"));
                livraisons.add(form);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceLivraison.class.getName()).log(Level.SEVERE, null, ex);
        }

        return livraisons;
    }

    public ObservableList<Livraison> triasc() {
        ObservableList<Livraison> livraisons = FXCollections.observableArrayList();
        try {
            Statement stm = con.createStatement();
            String query = "SELECT * from livraison2 ORDER by etat ASC";
            ResultSet rst = stm.executeQuery(query);
            Livraison form;
            while (rst.next()) {
                Livraison c = new Livraison();
                c.setId(rst.getInt("id"));
                c.setDate(rst.getDate("date"));
                c.setPrix(rst.getFloat("prix"));
                c.setEtat(rst.getString("etat"));
                form = new Livraison(rst.getInt("id"), rst.getDate("date"), rst.getFloat("prix"), rst.getString("etat"));
                livraisons.add(form);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceLivraison.class.getName()).log(Level.SEVERE, null, ex);
        }

        return livraisons;
    }

    public ObservableList<Livraison> triadsc() {
        ObservableList<Livraison> livraisons = FXCollections.observableArrayList();
        try {
            Statement stm = con.createStatement();
            String query = "SELECT * from livraison2 ORDER by etat DESC";
            ResultSet rst = stm.executeQuery(query);
            Livraison form;
            while (rst.next()) {
                Livraison c = new Livraison();
                c.setId(rst.getInt("id"));
                c.setDate(rst.getDate("date"));
                c.setPrix(rst.getFloat("prix"));
                c.setEtat(rst.getString("etat"));
                form = new Livraison(rst.getInt("id"), rst.getDate("date"), rst.getFloat("prix"), rst.getString("etat"));
                livraisons.add(form);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceLivraison.class.getName()).log(Level.SEVERE, null, ex);
        }

        return livraisons;
    }

    public void sendmail(String mail, float prix) {

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
            message.setSubject("Your Order from Gamershub ");

            //set the content of the email message
            message.setText("Merci pour votre achat , prix total est de :" + prix);

            //send the email message
            Transport.send(message);

            System.out.println("Merci pour votre achat , prix total est de :" + prix);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Livraison> getAllRec() {
        List<Livraison> livraisons = new ArrayList<>();

        try {
            Statement stm;
            stm = con.createStatement();
            String query = "Select * from `livraison2`";
            ResultSet rst = stm.executeQuery(query);
            while (rst.next()) {
                Livraison c = new Livraison();
                c.setId(rst.getInt("id"));
                c.setDate(rst.getDate("date"));
                c.setPrix(rst.getFloat("prix"));
                c.setEtat(rst.getString("etat"));
                System.out.println(rst.getInt("id"));
                livraisons.add(c);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceLivreur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return livraisons;
    }
}
