/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Entities;

import java.sql.Date;

/**
 *
 * @author User
 */
public class Livraison {
    private int id;
    private Date date;
    private float prix;
    private String etat;

    public Livraison() {
    }

    public Livraison(int id, Date date, float prix, String etat) {
        this.id = id;
        this.date = date;
        this.prix = prix;
        this.etat = etat;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", date=" + date + ", prix=" + prix + ", etat=" + etat + "}\n";
    }
    
    
    
}
