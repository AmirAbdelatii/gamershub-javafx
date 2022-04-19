/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Services;

import gamershub.Entities.Blog;
import gamershub.Entities.User;
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
 * @author amira
 */ 
public class BlogService {
    private Statement ste;
    private PreparedStatement pst;
    private Connection cnx;

    public BlogService() {
        cnx = MyDB.getInstance().getCon();
    }

    public void AddBlog(Blog a) {

        String req = "insert into blog (title,description,published_at,user_id,views) values (?,?,?,?,?)";
        try {
            pst = cnx.prepareStatement(req);
 
            pst.setString(1, a.getTitle());
            pst.setString(2, a.getDescription());
            pst.setDate(3, a.getPublishedAt());
            pst.setInt(4, a.getUser().getId());
            //pst.setString(5, a.getImage());
           // pst.setInt(5, a.getCat_id().getId());

            pst.setInt(5, 0);

            pst.executeUpdate();
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }

    }

    public void DeleteBlog(int p) {
        try {
            String req = "DELETE from blog  WHERE id =" + p + " ";

            ste = cnx.createStatement();
            ste.executeUpdate(req);
            System.out.println("Done");
        } catch (SQLException ex) {
            System.out.println("Error deleting");
           System.out.println(ex.getMessage());
        }
    }

    public void UpdateBlog(Blog a) {
        String req = "UPDATE blog set title=? , description=? , published_at=? , user_id=? , image=?  WHERE id =" + a.getId() + " ";
        try {
            pst = cnx.prepareStatement(req);
            pst.setString(1, a.getTitle());
            pst.setString(2, a.getDescription());
            pst.setDate(3, a.getPublishedAt());       
            pst.setInt(4, a.getUser().getId());
            pst.setString(5, a.getImage());
            pst.executeUpdate();
            System.out.println("Blog updated");

        } catch (SQLException ex) {
            System.out.println("Error updating");
            System.out.println(ex.getMessage());

        }
    }

    public List<Blog> ShowBlog() {
        List<Blog> blog = new ArrayList<>();
        String sql = "select * from blog";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
           
            while (rs.next()) {
                Blog p = new Blog();
                User c =new User (rs.getInt("id"));
                
                p.setId(rs.getInt(1));
                p.setTitle(rs.getString(2));
                p.setDescription(rs.getString(3));
                p.setPublishedAt(rs.getDate(4));
                p.setUser(c);
                p.setImage(rs.getString(6));
                
                //cat c = new cat(rs.getInt(6));
               
               //p.setCat_id(c);
                p.setViews(rs.getInt(7));
                  
                blog.add(p);

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return blog;
    }
}
