/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Services;

import gamershub.Entities.Comments;
import gamershub.Entities.Blog;
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

    public void AddComments(Comments a) {

        String req = "insert into comments (comment,commented_at,comments_id) values (?,?,?,?,?)";
        try {
            pst = cnx.prepareStatement(req);

            pst.setString(1, a.getComment());
            pst.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
            pst.setInt(3, a.getBlog().getId());
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

    public void UpdateComments(Comments a, int pu) {
        String req = "UPDATE comments set title=? , description=? , image=?  WHERE id =" + pu + " ";
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

    public List<Comments> ShowComments() {
        List<Comments> comments = new ArrayList<>();
        String sql = "select * from comments";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
           
            while (rs.next()) {
                Comments p = new Comments();
                Blog c =new Blog (rs.getInt("id"));

                p.setId(rs.getInt(1));
                p.setComment(rs.getString(2));
                p.setCommentedAt(rs.getDate(3));
                p.setBlog(c);
                //cat c = new cat(rs.getInt(6));
               
               //p.setCat_id(c);
                  
                comments.add(p);

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return comments;
    }
}
