/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Entities;

/**
 *
 * @author DELL
 */
public class Livreur {

    private int id;
    private int cin;
    private String prenom;
    private String nom;
    private double salaire;
    private String zone_geographique;

    public Livreur() {
    }

    public Livreur(int id, int cin, String prenom, String nom, double salaire, String zone_geographique) {
        this.id = id;
        this.cin = cin;
        this.prenom = prenom;
        this.nom = nom;
        this.salaire = salaire;
        this.zone_geographique = zone_geographique;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getSalaire() {
        return salaire;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }

    public String getZone_geographique() {
        return zone_geographique;
    }

    public void setZone_geographique(String zone_geographique) {
        this.zone_geographique = zone_geographique;
    }

}
