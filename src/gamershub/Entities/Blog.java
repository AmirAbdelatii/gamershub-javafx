/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Entities;

import java.sql.Date;

/**
 *
 * @author amira
 */
public class Blog {
    private int id;
    private String title;
    private String description;
    private Date publishedAt;
    private String image;
    private int views;
    private User user;

    public Blog() {
    }

    public Blog(int id) {
        this.id = id;
    }

    public Blog(String title, String description, Date publishedAt, User user, String image, int views) {
        this.title = title;
        this.description = description;
        this.publishedAt = publishedAt;
        this.user = user;
        this.image = image;
        this.views = views;
    }

    public Blog(String title, String description, Date publishedAt, String image) {
        this.title = title;
        this.description = description;
        this.publishedAt = publishedAt;
        this.image = image;
        
    }
    

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }
    
    public User getUser() {
        return user;
    }

    public String getImage() {
        return image;
    }

    public int getViews() {
        return views;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setViews(int views) {
        this.views = views;
    }

    @Override
    public String toString() {
        return "Blog{" + "id=" + id + ", title=" + title + ", description=" + description + ", publishedAt=" + publishedAt + ", image=" + image + ", views=" + views + ", user=" + user + '}';
    }


    
}
