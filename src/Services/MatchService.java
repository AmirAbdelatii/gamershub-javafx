/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Services.InterfaceMatch;

import Entities.Matchs;
import Utils.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class MatchService implements InterfaceMatch{
    Connection cnx = MyDB.getInstance().getCon();
    

    /**
     *
     * @param p
     */
    @Override
    public void createMatch(Matchs p) {
        
        //request
        String req = "INSERT INTO `matchs`(`id`,`match_date`,`result`,`match_name`) VALUES (?,?,?,?)";
        try {
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, p.getId());
            st.setString(4, p.getMatchName());
            st.setDate(2, p.getMatchDate());
            st.setInt(3, p.getResult());
            st.executeUpdate();
            System.out.println("Match ajouté avec succes.");
            
        } catch (SQLException ex) {
            
            ex.printStackTrace();
        }
        
        
    }

    @Override
    public List<Matchs> readMatch() {
        ArrayList<Matchs> Matchs = new ArrayList();
        
        try {
            Statement st = cnx.createStatement();
            String req = "SELECT * FROM matchs";
            ResultSet rs = st.executeQuery(req);
            
            while (rs.next()) {                
                
                Matchs.add(new Matchs(rs.getInt(1),rs.getString(4), rs.getDate(2), rs.getInt(3)));
                
            }
            
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
        
        return Matchs;
    }
    @Override
    public void deleteMatch(Matchs m) {
        String query = "DELETE FROM matchs WHERE id= " + m.getId() + "";
        try{
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("Match Deleted Successfully");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
     public void delete(int id) throws SQLException {
        String req = "DELETE FROM `matchs` WHERE `id`=?";
        PreparedStatement pstm = cnx.prepareStatement(req);
        pstm.setInt(1, id);
        pstm.executeUpdate();

    }
    @Override
    public void modifyMatch(Matchs m) {
         String query = "UPDATE matchs SET match_name = '" + m.getMatchName() +
                 "',result = '" + m.getResult() +"' WHERE id = " + m.getId() + "";       
        try{
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("Match Updated Successfully ");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        } 
    }
}
