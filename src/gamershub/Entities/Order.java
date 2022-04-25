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
public class Order {

    private int id, user_Id;
    private double totalPrice;
    private boolean is_canceled, is_paid;

    public Order() {
    }

    public Order(int id, int user_Id, double totalPrice, boolean is_canceled, boolean is_paid) {
        this.id = id;
        this.user_Id = user_Id;
        this.totalPrice = totalPrice;
        this.is_canceled = is_canceled;
        this.is_paid = is_paid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(int user_Id) {
        this.user_Id = user_Id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isIs_canceled() {
        return is_canceled;
    }

    public void setIs_canceled(boolean is_canceled) {
        this.is_canceled = is_canceled;
    }

    public boolean isIs_paid() {
        return is_paid;
    }

    public void setIs_paid(boolean is_paid) {
        this.is_paid = is_paid;
    }

}
