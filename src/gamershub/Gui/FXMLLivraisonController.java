/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Gui;

import gamershub.Entities.Livraison;
import gamershub.Services.ServiceLivraison;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author User
 */
public class FXMLLivraisonController implements Initializable {

    private Label label;

    @FXML
    private DatePicker datec;
    @FXML
    private Button stat;
    @FXML
    private TextField tfprix;
    @FXML
    private TextField tfetat;
    @FXML
    private TableColumn<Livraison, Integer> idt;
    @FXML
    private TableColumn<Livraison, Date> datet;
    @FXML
    private TableColumn<Livraison, Float> prixt;
    @FXML
    private TableColumn<Livraison, String> etatt;
    @FXML
    private TableView<Livraison> tablec;
    @FXML
    private TextField idsup;
    @FXML
    private TextField tfsearch;
    private int vartri = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void statScene(ActionEvent event) throws IOException {
        Parent view3 = FXMLLoader.load(getClass().getResource("StatRec.fxml"));
        Scene scene3 = new Scene(view3);
        Stage window = new Stage();
        window.setScene(scene3);
        window.show();
    }

    @FXML
    private void AjouterLivraison(ActionEvent event) {
        ServiceLivraison sc = new ServiceLivraison();
        Livraison c = new Livraison();
        c.setDate(java.sql.Date.valueOf(datec.getValue()));
        c.setPrix(Float.parseFloat(tfprix.getText()));
        c.setEtat(tfetat.getText());

        sc.AjouterLivraison(c);
        this.AfficherLivraison(event);

        Task<Void> task = new Task<Void>() {
            @Override
            public Void call() throws Exception {
                Thread.sleep(0);
                return null;
            }
        };

        task.setOnSucceeded(event2 -> {

            //IL FAUT RECUBERER LE mail de utili
            sc.sendmail("aziz.barguellil@esprit.tn", c.getPrix());

        });

        new Thread(task).run();

        System.out.println("mail en cours");

    }

    @FXML
    private void AfficherLivraison(ActionEvent event) {
        ServiceLivraison sc = new ServiceLivraison();
        ObservableList<Livraison> livraisons = sc.AfficherLivraison();

        idt.setCellValueFactory(new PropertyValueFactory<Livraison, Integer>("id"));
        datet.setCellValueFactory(new PropertyValueFactory<Livraison, Date>("date"));
        prixt.setCellValueFactory(new PropertyValueFactory<Livraison, Float>("prix"));
        etatt.setCellValueFactory(new PropertyValueFactory<Livraison, String>("etat"));
        tablec.setItems(livraisons);

    }

    @FXML
    private void selectionner(MouseEvent event) {

        Livraison c = tablec.getSelectionModel().getSelectedItem();
        java.sql.Date dateget = convertUtilToSql(c.getDate());
        idsup.setText(String.valueOf(c.getId()));
        datec.setValue(dateget.toLocalDate());
        tfprix.setText(String.valueOf(c.getPrix()));
        tfetat.setText(c.getEtat());
    }

    @FXML
    private void SupprimerLivraison(ActionEvent event) {
        ServiceLivraison sc = new ServiceLivraison();
        sc.supprimerLivraison(Integer.parseInt(idsup.getText()));
        this.AfficherLivraison(event);

    }

    private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;

    }

    @FXML
    private void ModifierLivraison(ActionEvent event) {
        ServiceLivraison sc = new ServiceLivraison();
        Livraison c = new Livraison();
        c.setId(Integer.parseInt(idsup.getText()));
        c.setDate(java.sql.Date.valueOf(datec.getValue()));
        c.setPrix(Float.parseFloat(tfprix.getText()));
        c.setEtat(tfetat.getText());
        sc.ModifierLivraison(c);
    }

    @FXML
    private void searchkey(KeyEvent event) {
        ServiceLivraison sc = new ServiceLivraison();
        ObservableList<Livraison> livraisons = sc.search(tfsearch.getText());
        idt.setCellValueFactory(new PropertyValueFactory<Livraison, Integer>("id"));
        datet.setCellValueFactory(new PropertyValueFactory<Livraison, Date>("date"));
        prixt.setCellValueFactory(new PropertyValueFactory<Livraison, Float>("prix"));
        etatt.setCellValueFactory(new PropertyValueFactory<Livraison, String>("etat"));
        tablec.setItems(livraisons);
    }

    @FXML
    private void tributton(MouseEvent event) {
        ServiceLivraison sc = new ServiceLivraison();
        ObservableList<Livraison> livraisons;
        if (vartri == 1) {
            vartri = 0;
            livraisons = sc.triasc();
            idt.setCellValueFactory(new PropertyValueFactory<Livraison, Integer>("id"));
            datet.setCellValueFactory(new PropertyValueFactory<Livraison, Date>("date"));
            prixt.setCellValueFactory(new PropertyValueFactory<Livraison, Float>("prix"));
            etatt.setCellValueFactory(new PropertyValueFactory<Livraison, String>("etat"));
            tablec.setItems(livraisons);

        } else {
            vartri = 1;
            livraisons = sc.triadsc();
            idt.setCellValueFactory(new PropertyValueFactory<Livraison, Integer>("id"));
            datet.setCellValueFactory(new PropertyValueFactory<Livraison, Date>("date"));
            prixt.setCellValueFactory(new PropertyValueFactory<Livraison, Float>("prix"));
            etatt.setCellValueFactory(new PropertyValueFactory<Livraison, String>("etat"));
            tablec.setItems(livraisons);

        }

    }

}
