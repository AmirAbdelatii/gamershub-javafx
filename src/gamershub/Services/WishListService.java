/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Services;

import Entities.Products;
import gamershub.Utils.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author meriam
 */
public class WishListService {

    public WishListService() {
        con = MyDB.getInstance().getCon();
    }

    Connection con;
    Statement stm;

    public void add(int prodId, int userId) throws SQLException {
        String req = "INSERT INTO `wish_list`(`product_id`, `user_id`) VALUES ('" + prodId + "','" + userId + "')";

        PreparedStatement pstm = con.prepareStatement(req);

        pstm.executeUpdate();
    }
    
      public void remove(int prodId) throws SQLException {
        String req = "DELETE FROM `wish_list` WHERE `product_id`="+prodId;

        PreparedStatement pstm = con.prepareStatement(req);

        pstm.executeUpdate();
    }
    public List<Integer> afficher() throws SQLException {
        String req = "Select `product_id` from `wish_list`";
        stm = con.createStatement();
        ResultSet rst = stm.executeQuery(req);
        System.out.println(rst.toString());
        List<Integer> products = new ArrayList<Integer>();
        while (rst.next()) {
            
            int c=rst.getInt(1);      
            products.add(c);
            
        }
        return products;
    }

}
