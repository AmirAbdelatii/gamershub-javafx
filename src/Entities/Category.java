/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;

/**
 *
 * @author meriam
 */
public class Category {
    private int id;
    private String categoryName,description,image;
    private boolean isEnabled;
    private Date creationDate,ModificationDate;



    public Category(int id, String categoryName, String description, String image, boolean isEnabled, Date creationDate, Date ModificationDate) {
        this.id = id;
        this.categoryName = categoryName;
        this.description = description;
        this.image = image;
        this.isEnabled = isEnabled;
        this.creationDate = creationDate;
        this.ModificationDate = ModificationDate;
    }

    public Category() {
    }

    public Category(String categoryName, String description, String image, boolean isEnabled) {
        this.categoryName = categoryName;
        this.description = description;
        this.image = image;
        this.isEnabled = isEnabled;
    }

    public Category(int id, String categoryName, String description, String image, boolean isEnabled) {
        this.id = id;
        this.categoryName = categoryName;
        this.description = description;
        this.image = image;
        this.isEnabled = isEnabled;
    }
    public Category(int id, String categoryName, String description, boolean isEnabled) {
        this.id = id;
        this.categoryName = categoryName;
        this.description = description;
        this.isEnabled = isEnabled;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    @Override
    public String toString() {
        return "Category{" + "id=" + id + ", categoryName=" + categoryName + ", description=" + description + ", image=" + image + ", isEnabled=" + isEnabled + ", creationDate=" + creationDate + ", ModificationDate=" + ModificationDate + '}';
    }

       public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getModificationDate() {
        return ModificationDate;
    }

    public void setModificationDate(Date ModificationDate) {
        this.ModificationDate = ModificationDate;
    }
    
}
