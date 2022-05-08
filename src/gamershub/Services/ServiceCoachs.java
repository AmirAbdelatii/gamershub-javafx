/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Services;

import gamershub.Entities.Coach;
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
 * @author hp
 */
public class ServiceCoachs implements IService<Coach> {
    Connection con;
    Statement stm;
    
    public ServiceCoachs() {
        con = MyDB.getInstance().getCon();
    }
    
    @Override
    public void ajouter(Coach t) throws SQLException {
        String req = "INSERT INTO `coach` (`user_id`, `game_id`,`description`) VALUES ( '"
                + t.getUserId() + "', '" + t.getGameId() + "','" + t.getDescription() + "') ";
        stm = con.createStatement();
        stm.executeUpdate(req);

    }

    
    @Override
        public void ajouterr(Coach t) throws SQLException {
        String req = "INSERT INTO `coach`(`user_id`,`game_id`,`description`) VALUES (?,?,?)";
        PreparedStatement pstm = con.prepareStatement(req);
        pstm.setInt(1, t.getUserId());
        pstm.setInt(2, t.getGameId());
        pstm.setString(3, t.getDescription());
        
        pstm.executeUpdate();
           
    }

    @Override
    public List<Coach> afficher() throws SQLException {
        String req = "Select * from `coach`";
        stm = con.createStatement();
        ResultSet rst = stm.executeQuery(req);
        System.out.println(rst.toString());
        List<Coach> coach = new ArrayList<Coach>();
        while(rst.next()){
            
            Coach p = new Coach(rst.getInt(1),rst.getInt(2),rst.getInt(3),rst.getString(4));
            coach.add(p);
            
        
        }
        return coach;
       
        
        
    }
     public List<Coach> afficherr() throws SQLException {
        String req = "Select c.description, u.username, g.name, c.id ,g.id from coach c, user u,game g WHERE c.user_id= u.id AND c.game_id=g.id";
        stm = con.createStatement();
        ResultSet rst = stm.executeQuery(req);
        System.out.println(rst.toString());
        List<Coach> coach = new ArrayList<Coach>();
        while(rst.next()){
            
           Coach p = new Coach(rst.getInt(4),rst.getString(2),rst.getString(3),rst.getString(1),rst.getInt(5));
           coach.add(p);
            
        
        }
       return coach;
    }
    public String getCoachname()throws SQLException{
        String coachname="";
    String req = "SELECT `username` from `user` INNER JOIN `coach` ON (user.id=coach.user_id)";
        stm = con.createStatement();
        ResultSet rst = stm.executeQuery(req);
        System.out.println(rst.toString());
         while(rst.next()){
          coachname= rst.getString("username");
         }
         return coachname;
    }
    
    
     public void deletecoach(int idcoach) throws SQLException{
     String req="DELETE FROM `coach` WHERE id = "+idcoach ;
     stm = con.createStatement();
     stm.executeUpdate(req);
     
     }
     public void delete(Coach coach) throws SQLException{
     String req="DELETE FROM `coach` WHERE id = "+coach.getId()+"" ;
     stm = con.createStatement();
     stm.executeUpdate(req);
     
     }
     
     public void update(Coach coach) throws SQLException{
      String req = "UPDATE `coach` SET `game_id`= ? ,`description`= ? WHERE id = "+coach.getId()+"";
        PreparedStatement pstm = con.prepareStatement(req);
        pstm.setInt(1, coach.getGameId());
        pstm.setString(2,coach.getDescription());
        pstm.executeUpdate();   

      }
     
     public int getIdUser(int id)throws SQLException
     {   int iduser=0;
         String req = "SELECT id from `coach` WHERE user_id="+id+"";
         stm = con.createStatement();
        ResultSet rst = stm.executeQuery(req);
        while(rst.next()){
          iduser= rst.getInt("id");
         }
         return iduser;
        
     }
     
     public List<Coach> recherche(String nom) throws SQLException {
        String req = "Select c.description, u.username, g.name, c.id,g.id from coach c, user u,game g WHERE c.user_id= u.id AND c.game_id=g.id AND u.username LIKE '%"+nom+"%'";
        stm = con.createStatement();
        ResultSet rst = stm.executeQuery(req);
        System.out.println(rst.toString());
        List<Coach> coach = new ArrayList<Coach>();
        while(rst.next()){
            
           Coach p = new Coach(rst.getInt(4),rst.getString(2),rst.getString(3),rst.getString(1),rst.getInt(5));
           coach.add(p);
            
        
        }
       return coach;
    }
    
    
}
