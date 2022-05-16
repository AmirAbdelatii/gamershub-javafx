/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Services;

import gamershub.Entities.Comments;
import gamershub.Entities.Blog;
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
public class CommentsService {
    private Statement ste;
    private PreparedStatement pst;
    private Connection cnx;

    public CommentsService() {
        cnx = MyDB.getInstance().getCon();
    }

    public void AddComments(Comments a, Blog b, User u) {

        String req = "insert into comments  ( user_id, comment,commented_at, blog_id) values (?,?,?,?)";
        try {
            pst = cnx.prepareStatement(req);
            
            pst.setInt(1, u.getId());
            pst.setString(2, a.getComment());
            pst.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
            pst.setInt(4, b.getId());
           // pst.setInt(5, a.getCat_id().getId());

            pst.executeUpdate();
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }

    }

    public void DeleteComments(int p) {
        try {
            String req = "DELETE from comments  WHERE id =" + p + " ";

            ste = cnx.createStatement();
            ste.executeUpdate(req);
            System.out.println("Done");
        } catch (SQLException ex) {
            System.out.println("Error deleting");
           System.out.println(ex.getMessage());
        }
    }

    public void UpdateComments(Comments a) {
        String req = "UPDATE comments set comment=? , commented_at=?  WHERE id =" + a.getId() + " ";
        try {
            pst = cnx.prepareStatement(req);
            pst.setString(1, a.getComment());
            pst.setDate(2, java.sql.Date.valueOf(LocalDate.now()));

            pst.executeUpdate();
            System.out.println("Comments updated");

        } catch (SQLException ex) {
            System.out.println("Error updating comment");
            System.out.println(ex.getMessage());

        }
    }

    public List<Comments> ShowComments(Blog b) {
        List<Comments> comments = new ArrayList<>();
        String sql = "select * from comments where blog_id = "+b.getId()+"";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
           
            while (rs.next()) {
                Comments p = new Comments();
                Blog c =new Blog (rs.getInt("id"));
                System.out.println(c.getId());
               // p.setId(rs.getInt(c.getId()));
                p.setBlog(c);
                p.setComment(rs.getString("comment"));
              //  p.setCommentedAt(rs.getDate(4));
                //cat c = new cat(rs.getInt(6));
               
               //p.setCat_id(c);
                  
                comments.add(p);

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return comments;
    }
    
    public List<Comments> ShowCommentsBack() {
        List<Comments> comments = new ArrayList<>();
        String sql = "select * from comments";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
           
            while (rs.next()) {
                Comments p = new Comments();
                Blog c =new Blog (rs.getInt("blog_id"));
                User u = new User(rs.getInt("user_id"));
                p.setId(rs.getInt("id"));
                p.setUser(u);
                p.setBlog(c);
                p.setComment(rs.getString("comment"));
                p.setCommentedAt(rs.getDate(4));
                //cat c = new cat(rs.getInt(6));
               
               //p.setCat_id(c);
                  
                comments.add(p);

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return comments;
    }
    
    public void UpdateCommentsBack(Comments a) {
        String req = "UPDATE comments set title=? , description=? , image=?  WHERE id =" + a.getId() + " ";
        try {
            pst = cnx.prepareStatement(req);
            pst.setString(1, a.getComment());
            pst.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
            pst.setInt(3, a.getBlog().getId());

            pst.executeUpdate();
            System.out.println("Comments updated");

        } catch (SQLException ex) {
            System.out.println("Error updating comment");
            System.out.println(ex.getMessage());

        }
    }
}
