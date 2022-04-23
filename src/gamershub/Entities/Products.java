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
public class Products {
  private int id,catId,quantityStocked;
  private String productName,image,description;
  private Float price;
  private Boolean isEnabled;
  private Date creationDate,ModificationDate;

    public Products() {
    }

    public Products(int id,int catId, String productName, Float price,int quantityStocked, String image, String description, Boolean isEnabled) {
        this.id = id;
        this.catId = catId;
        this.quantityStocked = quantityStocked;
        this.productName = productName;
        this.image = image;
        this.description = description;
        this.price = price;
        this.isEnabled = isEnabled;
    }
      public Products(int id,int catId, String productName, Float price,int quantityStocked, String description, Boolean isEnabled) {
        this.id = id;
        this.catId = catId;
        this.quantityStocked = quantityStocked;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.isEnabled = isEnabled;
    }
      public Products(int catId, int quantityStocked, String productName, String description, Float price, Boolean isEnabled) {
     
        this.catId = catId;
        this.quantityStocked = quantityStocked;
        this.productName = productName;
        this.image = image;
        this.description = description;
        this.price = price;
        this.isEnabled = isEnabled;
    }

    public Products(int id, int catId, int quantityStocked, String productName, String image, String description, Float price, Date creationDate, Date ModificationDate, Boolean isEnabled) {
        this.id = id;
        this.catId = catId;
        this.quantityStocked = quantityStocked;
        this.productName = productName;
        this.image = image;
        this.description = description;
        this.price = price;
        this.isEnabled = isEnabled;
        this.creationDate = creationDate;
        this.ModificationDate = ModificationDate;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public int getQuantityStocked() {
        return quantityStocked;
    }

    public void setQuantityStocked(int quantityStocked) {
        this.quantityStocked = quantityStocked;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Boolean getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    @Override
    public String toString() {
        return "Products{" + "id=" + id + ", catId=" + catId + ", quantityStocked=" + quantityStocked + ", productName=" + productName + ", image=" + image + ", description=" + description + ", price=" + price + ", isEnabled=" + isEnabled + ", creationDate=" + creationDate + ", ModificationDate=" + ModificationDate + '}';
    }

   
    
}
