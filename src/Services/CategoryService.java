/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Category;
import Utils.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import  java.sql.Date;
import java.sql.Time;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author meriam
 */
public class CategoryService implements InterfaceService<Category> {

    Connection con;
    Statement stm;

    public CategoryService() {
        con = MyDB.getInstance().getCon();
    }

    @Override
    public void add(Category t) throws SQLException {
        String req = "INSERT INTO `category`(`name_category`, `description`, `image`, `creation_date`, `is_enabled`) VALUES (?,?,?,NOW(),?)";
        PreparedStatement pstm = con.prepareStatement(req);
        pstm.setString(1, t.getCategoryName());
        pstm.setString(2, t.getDescription());
        pstm.setString(3, t.getImage());
        pstm.setBoolean(4, true);
        pstm.executeUpdate();

    }
      public void update(Category t) throws SQLException {
         
        String req = "UPDATE `category` SET name_category='"+t.getCategoryName()+
                "',description='"+t.getDescription()+"',image='"+t.getImage()+"',modification_date=NOW(),is_enabled="+t.isIsEnabled()+" WHERE id="+t.getId()+"";
        
        PreparedStatement pstm = con.prepareStatement(req);
        System.out.println(req);
        pstm.executeUpdate();

    }
     
    public void delete(int id) throws SQLException {
        String req = "DELETE FROM `category` WHERE `id`=?";
        PreparedStatement pstm = con.prepareStatement(req);
        pstm.setInt(1, id);
        pstm.executeUpdate();

    }

    @Override
    public List<Category> show() throws SQLException {
        String req = "Select * from `category`";
        stm = con.createStatement();
        ResultSet rst = stm.executeQuery(req);
        System.out.println(rst.toString());
        List<Category> categories = new ArrayList<Category>();
        while (rst.next()) {

            Category c = new Category(rst.getInt(1), rst.getString(2), rst.getString(3),rst.getString(4),rst.getBoolean(7));
            c.setCreationDate(rst.getDate(5));
            c.setModificationDate(rst.getDate(6));
            categories.add(c);

        }
        return categories;
    }

}
