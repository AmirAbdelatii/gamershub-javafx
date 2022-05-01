/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Entities;

/**
 *
 * @author meriam
 */
public class Cart {
    private int id,product_id,my_order_id,quantity;
   

    public Cart(int id, int product_id, int my_order_id, int quantity) {
        this.id = id;
        this.product_id = product_id;
        this.my_order_id = my_order_id;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public int getMy_order_id() {
        return my_order_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public void setMy_order_id(int my_order_id) {
        this.my_order_id = my_order_id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}
