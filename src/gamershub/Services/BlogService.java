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
import java.io.*;
import okhttp3.*;
import org.json.JSONObject;
import static org.json.XMLTokener.entity;


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
public static String BadWords(Blog b) throws IOException{
        OkHttpClient client = new OkHttpClient().newBuilder().build();

    MediaType mediaType = MediaType.parse("text/plain");
    RequestBody body = RequestBody.create(mediaType, b.getDescription());

    Request request = new Request.Builder()         
      .url("https://api.apilayer.com/bad_words?censor_character=fuck")
      .addHeader("apikey", "q3NysslktPhIwdnnYbfexEbnyo0XM3uT")
      .method("POST", body)
      .build();
    Response response = client.newCall(request).execute();
        String responseData = response.body().string();
    
    JSONObject json = new JSONObject(responseData);
        return(json.getString("censored_content"));
    }

    public void AddBlog(Blog a) throws IOException {
        String req = "insert into blog (title,description,published_at,user_id) values (?,?,?,?)";
        try {
            pst = cnx.prepareStatement(req);
            
            pst.setString(1, a.getTitle());
            pst.setString(2, BadWords(a));
            pst.setDate(3, a.getPublishedAt());
            pst.setInt(4, a.getUser().getId());
            //pst.setString(5, a.getImage());
           // pst.setInt(5, a.getCat_id().getId());


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
        
        String req = "UPDATE blog set title=? , description=? , published_at=?   WHERE id =" + a.getId() + " ";
        try {
            pst = cnx.prepareStatement(req);
            pst.setString(1, a.getTitle());
            pst.setString(2, a.getDescription());
            pst.setDate(3, a.getPublishedAt());       
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
                User u =new User (rs.getInt("user_id"));
                //c.setUsername(p.getUser().getUsername());
                p.setId(rs.getInt(1));
                p.setTitle(rs.getString(2));
                p.setDescription(rs.getString(3));
                p.setPublishedAt(rs.getDate(4));
                p.setUser(u);
                p.setImage(rs.getString(6));
                
                //cat c = new cat(rs.getInt(6));
               
               //p.setCat_id(c);
                p.setViews(rs.getInt("views"));
                  
                blog.add(p);

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return blog;
    }
    
    public String getUsername()throws SQLException{
        String username="";
    String req = "SELECT username from user INNER JOIN blog ON (user.id=blog.user_id)";
        ste = cnx.createStatement();
        ResultSet rst = ste.executeQuery(req);
        System.out.println(rst.toString());
         while(rst.next()){
          username= rst.getString("username");
         }
         return username;
    }
    
    public void increment(Blog p) {

      String req1 = "UPDATE blog set views=? WHERE id =" +p.getId()+ "";
        try {

          
           pst = cnx.prepareStatement(req1);
            pst.setInt(1, p.getViews()+1);
           
            pst.executeUpdate();
            System.out.println("+1 Views");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
