/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Entities;

import java.sql.Date;

/**
 *
 * @author MAB
 */
public class Game {

    private int id;
    private String name, image, description;
    private Date createdAt,lastUpdated;

    public Game() {
    }

    public Game(int id, String name, String image, String description, Date createdAt, Date lastUpdated) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
        this.createdAt = createdAt;
        this.lastUpdated = lastUpdated;
    }

    public Game(String name, String image, String description) {
        this.name = name;
        this.image = image;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public String toString() {
        return "Game{" + "id=" + id + ", name=" + name + ", image=" + image + ", description=" + description + ", createdAt=" + createdAt + ", lastUpdated=" + lastUpdated + '}';
    }
    
    
}
