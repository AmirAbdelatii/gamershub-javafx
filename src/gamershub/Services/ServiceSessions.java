/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Services;

import gamershub.Entities.Sessioncoaching;
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
public class ServiceSessions implements IService <Sessioncoaching> {
    
    Connection con;
    Statement stm;
    
    public ServiceSessions() {
        con = MyDB.getInstance().getCon();
    }
    
    @Override
    public void ajouter(Sessioncoaching t) throws SQLException {
        String req = "INSERT INTO `sessioncoaching`(`coach_id`,`user_id`,`description`,`date_debut`, `date_fin`, `prix`) VALUES ('"+t.getCoach()+"',"
                + "'" + t.getUser()+ "','" + t.getDescription() + "','" + t.getDate_debut() + "','" + t.getDate_fin() + "','" + t.getPrix() + "')";
        stm = con.createStatement();
       stm.executeUpdate(req);

    }

    
    @Override
        public void ajouterr(Sessioncoaching t) throws SQLException {
        String req = "INSERT INTO `sessioncoaching`(`coach_id`,`user_id`,`description`,`date_debut`, `date_fin`, `prix`,`background_color`, `border_color`,`text_color`) VALUES (?,?,?,?,?,?,'0','0','0')";
        PreparedStatement pstm = con.prepareStatement(req);
        pstm.setInt(1, t.getCoach());
        pstm.setInt(2, t.getUser());
        pstm.setString(3, t.getDescription());
        pstm.setDate(4,t.getDate_debut());
        pstm.setDate(5,t.getDate_fin());
        pstm.setFloat(6, t.getPrix());
        pstm.executeUpdate();
           
    }

    @Override
    public List<Sessioncoaching> afficher() throws SQLException {
        String req = "Select * from `sessioncoaching`";
        stm = con.createStatement();
        ResultSet rst = stm.executeQuery(req);
        System.out.println(rst.toString());
        List<Sessioncoaching> sessioncoaching = new ArrayList<Sessioncoaching>();
        while(rst.next()){
            
            Sessioncoaching p = new Sessioncoaching(rst.getInt(1),rst.getInt(2),rst.getInt(3),rst.getString(9),rst.getFloat(6),rst.getDate(4),rst.getDate(5));
            sessioncoaching.add(p);
            
        
        }
        return sessioncoaching;
        
        
        
    }
    
    public List<Sessioncoaching> afficherr() throws SQLException {
        String req = "Select s.id, s.date_debut, s.date_fin, s.prix, s.description, u.username, u.id, s.coach_id from sessioncoaching s ,user u where s.user_id=u.id";
        stm = con.createStatement();
        ResultSet rst = stm.executeQuery(req);
        System.out.println(rst.toString());
        List<Sessioncoaching> sessioncoaching = new ArrayList<Sessioncoaching>();
        while(rst.next()){
            
            Sessioncoaching p = new Sessioncoaching(rst.getInt(1),rst.getDate(2),rst.getDate(3),rst.getFloat(4),rst.getString(5),rst.getString(6),rst.getInt(7),rst.getInt(8));
            sessioncoaching.add(p);
            
        
        }
        return sessioncoaching;
        
        
        
    }
    
     public void deletesession(int idsession ) throws SQLException{
     String req="DELETE FROM `Sessioncoaching` WHERE id =" +idsession ;
     stm = con.createStatement();
     stm.executeUpdate(req);
     
     }
     public void delete(Sessioncoaching session) throws SQLException{
     String req="DELETE FROM `Sessioncoaching` WHERE id =" +session.getId()+"";
     stm = con.createStatement();
     stm.executeUpdate(req);
     
     }
     
     public void update(Sessioncoaching session) throws SQLException{
      String req = "UPDATE `sessioncoaching` SET `user_id`= ? ,`description`= ?,`date_debut`= ?,`date_fin`= ?,`prix`= ? WHERE id = "+session.getId()+"";
        PreparedStatement pstm = con.prepareStatement(req);
        pstm.setInt(1, session.getUser());
        pstm.setString(2,session.getDescription());
        pstm.setDate(3, session.getDate_debut());
        pstm.setDate(4, session.getDate_fin());
        pstm.setFloat(5, session.getPrix());
        pstm.executeUpdate();   

      }
    
    
}
