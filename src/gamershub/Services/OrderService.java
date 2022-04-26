/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Services;

import gamershub.Entities.Cart;
import gamershub.Entities.Order;
import static gamershub.Gamershub.loggedUser;
import gamershub.Utils.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author meriam
 */
public class OrderService {

    public OrderService() {
        con = MyDB.getInstance().getCon();
    }

    Connection con;
    Statement stm;

    public void add(Order t) throws SQLException {
        String req = "INSERT INTO `order`( `user_id`, `totalprice`, `is_canceled`, `is_paid`) VALUES "
                + "('"+ t.getUser_Id()+"','"+t.getTotalPrice()+"','0','0')";
        
        PreparedStatement pstm = con.prepareStatement(req);
             
                pstm.executeUpdate();
    }
    
      public void addCart(Cart t) throws SQLException {
        String req = "INSERT INTO `cart`(`id`, `product_id`, `my_order_id`, `quantity`) VALUES "
                + "('?','?',NULL,'[value-4]')";
        
        PreparedStatement pstm = con.prepareStatement(req);
                pstm.setInt(1, t.getId());
                pstm.setInt(1, loggedUser.getId());
    }
      
}
