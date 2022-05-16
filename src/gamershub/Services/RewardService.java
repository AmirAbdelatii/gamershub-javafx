/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Services;

import gamershub.Entities.Rewards;
import gamershub.Entities.Tournaments;
import gamershub.Interfaces.IRewardService;
import gamershub.Utils.MyDB;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ghofrane
 */
public class RewardService implements IRewardService<Rewards> {
    MyDB instance = MyDB.getInstance();
    Connection cnx = instance.getCon();
    
    @Override
    public void createReward(Rewards R) {
      
        try {
            String req = "INSERT INTO `rewards`(`tournament_id_id`,`type`,`quantity`) VALUES (?,?,?)";
            PreparedStatement st = cnx.prepareStatement(req);
         
            st.setInt(1, R.getTournamentId());
            st.setString(2, R.getType());
            st.setInt(3, R.getQuantity());
            


            st.executeUpdate();
            System.out.println("Rewards ajoutée avec succes.");
      

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    @Override
    public void editReward(Rewards R) {
        try {
            String req = "update rewards set tournament_id_id = '" + R.getTournamentId() + "', type ='" + R.getType()+ "', quantity = '" + R.getQuantity() +"'   WHERE id='" + R.getId() + "'";
            PreparedStatement st = cnx.prepareStatement(req);
            
            

            st.executeUpdate(req);
            System.out.println("Reward edited.");

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    
    @Override
     public boolean deleteReward(int id) {
        boolean isDeleted = false;
        try {
            String req = "delete from rewards where id = ?";
            PreparedStatement prepare = cnx.prepareStatement(req);
            prepare.setInt(1, id);
            prepare.executeUpdate();
            isDeleted = true;
            System.out.println("Rewards deleted");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error");
        }
        return isDeleted;
    }
     
     public List<Rewards> readRewards() {
        ArrayList<Rewards> Rewards = new ArrayList();

        try {
            String requete = "SELECT * FROM rewards";
            Statement st = MyDB.getInstance().getCon()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete);                                                                                                                                           
            while(rs.next()){                                                                                                                                                     
                Rewards r = new Rewards();
                r.setId(rs.getInt("id"));
                r.setTournamentId(rs.getInt("tournament_id_id"));
                r.setType(rs.getString("type"));
                r.setQuantity(rs.getInt("quantity"));
                
                Rewards.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Rewards;

        

    }
    
}
