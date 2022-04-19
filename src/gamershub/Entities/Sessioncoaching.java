/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Entities;

import java.sql.Date;

/**
 *
 * @author hp
 */
public class Sessioncoaching {
    private int id,user,coach;
    private String description,client;
    private float prix;
    private Date date_debut,date_fin;

    public Sessioncoaching() {
    }

    public Sessioncoaching(int id,int coach, int user, String description, float prix, Date date_debut, Date date_fin) {
        this.id = id;
        this.user = user;
        this.description = description;
        this.coach = coach;
        this.prix = prix;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }
    public Sessioncoaching(int coach, int user, String description, float prix, Date date_debut, Date date_fin) {
       this.user = user;
        this.description = description;
        this.coach = coach;
        this.prix = prix;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }
    
    public Sessioncoaching(int id, Date date_debut, Date date_fin,float prix, String description,String client ,int coach) {
       this.id = id;
        this.description = description;
        this.coach = coach;
        this.prix = prix;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.client=client;
    }

    @Override
    public String toString() {
        return "Sessioncoaching{" + "id=" + id + ", usere=" + user + ", description=" + description + ", coach=" + coach + ", prix=" + prix + ", date_debut=" + date_debut + ", date_fin=" + date_fin + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public int getCoach() {
        return coach;
    }

    public void setCoach(int coach) {
        this.coach = coach;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }
    
}
