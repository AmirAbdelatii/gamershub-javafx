/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Gui;

import gamershub.Entities.Livreur;
import gamershub.Services.ServiceLivreur;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfcin;
    @FXML
    private TableView<Livreur> tablec;
    @FXML
    private TableColumn<Livreur, Integer> idt;
    @FXML
    private TableColumn<Livreur, String> nomt;
    @FXML
    private TableColumn<Livreur, String> prenomt;
    @FXML
    private TableColumn<Livreur, Integer> cint;
    @FXML
    private TableColumn<Livreur, Double> salairet;
    @FXML
    private TableColumn<Livreur, String> zonet;
    @FXML
    private TextField idsup;
    @FXML
    private TextField tfsearch;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfzone;
    @FXML
    private TextField tfsalaire;

    private int vartri = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    
    @FXML
    private void AjouterLivreur(ActionEvent event) {
        if (tfnom.getText().equals("") || tfprenom.getText().equals("") || tfcin.getText().equals("") || tfsalaire.getText().equals("") || tfzone.getText().equals("")) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("non.");
            alert.setHeaderText(null);
            alert.setContentText("Verifiez les champs vides ");
            alert.showAndWait();
        } else {
            ServiceLivreur sc = new ServiceLivreur();
            Livreur c = new Livreur();
            c.setNom(tfnom.getText());
            c.setPrenom(tfprenom.getText());
            c.setCin(Integer.parseInt(tfcin.getText()));
            c.setSalaire(Double.parseDouble(tfsalaire.getText()));
            c.setZone_geographique(tfzone.getText());

            sc.AjouterLivreur(c);
            this.AfficherLivreur(event);

            Task<Void> task = new Task<Void>() {
                @Override
                public Void call() throws Exception {
                    Thread.sleep(0);
                    return null;
                }
            };
            task.setOnSucceeded(event2 -> {

                //IL FAUT RECUBERER LE mail de utili
                sc.sendmail("aziz.barguellil@esprit.tn", c.getNom(), c.getPrenom());

            });

            new Thread(task).run();

            System.out.println("mail en cours");
        }
    }

    @FXML
    private void AfficherLivreur(ActionEvent event) {
        ServiceLivreur sc = new ServiceLivreur();
        ObservableList<Livreur> livreurs = sc.AfficherLivreur();

        idt.setCellValueFactory(new PropertyValueFactory<Livreur, Integer>("id"));
        nomt.setCellValueFactory(new PropertyValueFactory<Livreur, String>("nom"));
        prenomt.setCellValueFactory(new PropertyValueFactory<Livreur, String>("prenom"));
        cint.setCellValueFactory(new PropertyValueFactory<Livreur, Integer>("cin"));
        salairet.setCellValueFactory(new PropertyValueFactory<Livreur, Double>("salaire"));
        zonet.setCellValueFactory(new PropertyValueFactory<Livreur, String>("zone_geographique"));
        tablec.setItems(livreurs);
    }

    @FXML
    private void selectionner(MouseEvent event) {
        Livreur c = tablec.getSelectionModel().getSelectedItem();
        idsup.setText(String.valueOf(c.getId()));
        tfnom.setText(c.getNom());
        tfprenom.setText(c.getPrenom());
        tfcin.setText(String.valueOf(c.getCin()));
        tfsalaire.setText(String.valueOf(c.getSalaire()));
        tfzone.setText(c.getZone_geographique());
    }

    @FXML
    private void SupprimerLivreur(ActionEvent event) {
        ServiceLivreur sc = new ServiceLivreur();
        sc.supprimerlivreur(Integer.parseInt(idsup.getText()));
        this.AfficherLivreur(event);
    }

    @FXML
    private void ModifierLivreur(ActionEvent event) {
        ServiceLivreur sc = new ServiceLivreur();
        Livreur c = new Livreur();
        c.setNom(tfnom.getText());
        c.setPrenom(tfprenom.getText());
        c.setCin(Integer.parseInt(tfcin.getText()));
        c.setSalaire(Double.parseDouble(tfsalaire.getText()));
        c.setZone_geographique(tfzone.getText());
        sc.ModifierLivreur(c);
    }

    @FXML
    private void searchkey(KeyEvent event) {
        ServiceLivreur sc = new ServiceLivreur();
        ObservableList<Livreur> livreurs = sc.search(tfsearch.getText());

        idt.setCellValueFactory(new PropertyValueFactory<Livreur, Integer>("id"));
        nomt.setCellValueFactory(new PropertyValueFactory<Livreur, String>("nom"));
        prenomt.setCellValueFactory(new PropertyValueFactory<Livreur, String>("prenom"));
        cint.setCellValueFactory(new PropertyValueFactory<Livreur, Integer>("cin"));
        salairet.setCellValueFactory(new PropertyValueFactory<Livreur, Double>("salaire"));
        zonet.setCellValueFactory(new PropertyValueFactory<Livreur, String>("zone_geographique"));
        tablec.setItems(livreurs);
    }

    @FXML
    private void tributton(MouseEvent event) {
        ServiceLivreur sc = new ServiceLivreur();
        ObservableList<Livreur> livreurs;
        if (vartri == 1) {
            vartri = 0;
            livreurs = sc.triasc();
            idt.setCellValueFactory(new PropertyValueFactory<Livreur, Integer>("id"));
            nomt.setCellValueFactory(new PropertyValueFactory<Livreur, String>("nom"));
            prenomt.setCellValueFactory(new PropertyValueFactory<Livreur, String>("prenom"));
            cint.setCellValueFactory(new PropertyValueFactory<Livreur, Integer>("cin"));
            salairet.setCellValueFactory(new PropertyValueFactory<Livreur, Double>("salaire"));
            zonet.setCellValueFactory(new PropertyValueFactory<Livreur, String>("zone_geographique"));
            tablec.setItems(livreurs);

        } else {
            vartri = 1;
            livreurs = sc.triadsc();
            idt.setCellValueFactory(new PropertyValueFactory<Livreur, Integer>("id"));
            nomt.setCellValueFactory(new PropertyValueFactory<Livreur, String>("nom"));
            prenomt.setCellValueFactory(new PropertyValueFactory<Livreur, String>("prenom"));
            cint.setCellValueFactory(new PropertyValueFactory<Livreur, Integer>("cin"));
            salairet.setCellValueFactory(new PropertyValueFactory<Livreur, Double>("salaire"));
            zonet.setCellValueFactory(new PropertyValueFactory<Livreur, String>("zone_geographique"));
            tablec.setItems(livreurs);

        }
    }

}
