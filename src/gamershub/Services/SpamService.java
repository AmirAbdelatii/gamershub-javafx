/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Services;

import gamershub.Entities.Blog;
import gamershub.Entities.Spam;
import gamershub.Entities.User;
import gamershub.Utils.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author amira
 */
public class SpamService {
    private Statement ste;
    private PreparedStatement pst;
    private Connection cnx;
    public SpamService() {
        cnx = MyDB.getInstance().getCon();
    }
    
    public void AddSpam(Spam s,Blog b, User u) {

        String req = "insert into spam  ( post_id, user_id ) values (?,?)";
        try {
            pst = cnx.prepareStatement(req);
            pst.setInt(1, b.getId());            
            pst.setInt(2, u.getId());
           // pst.setInt(5, a.getCat_id().getId());

            pst.executeUpdate();
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }

    }
    
    public void DeleteSpam(int p) {
        try {
            String req = "DELETE from spam  WHERE id =" + p + " ";

            ste = cnx.createStatement();
            ste.executeUpdate(req);
            System.out.println("Done");
        } catch (SQLException ex) {
            System.out.println("Error deleting");
           System.out.println(ex.getMessage());
        }
    }

    public List<Spam> ShowSpamBack() {
        List<Spam> spam = new ArrayList<>();
        String sql = "select * from spam";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
           
            while (rs.next()) {
                Spam p = new Spam();
                Blog c =new Blog (rs.getInt("post_id"));
                User u = new User(rs.getInt("user_id"));
                p.setId(rs.getInt("id"));
                p.setBlog(c);
                p.setUser(u);
                //cat c = new cat(rs.getInt(6));
               
               //p.setCat_id(c);
                  
                spam.add(p);

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return spam;
    }
    
}
