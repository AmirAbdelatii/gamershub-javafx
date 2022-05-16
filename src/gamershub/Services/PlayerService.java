/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Services;

import gamershub.Entities.Player;
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
public class PlayerService {
     private Statement ste;
    private PreparedStatement pst;
    private Connection cnx;

    public PlayerService() {
        cnx = MyDB.getInstance().getCon();
    }

    public void AddPlayer(Player a) {

        String req = "insert into player (rank,user_id) values (?,?)";
        try {
            pst = cnx.prepareStatement(req);
 
            pst.setString(1, a.getRank());
            pst.setInt(2, a.getUser().getId());


            pst.executeUpdate();
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }

    }

    public void DeletePlayer(int p) {
        try {
            String req = "DELETE from player  WHERE id =" + p + " ";

            ste = cnx.createStatement();
            ste.executeUpdate(req);
            System.out.println("Done");
        } catch (SQLException ex) {
            System.out.println("Error deleting");
           System.out.println(ex.getMessage());
        }
    }

    public void UpdatePlayer(Player a) {
        String req = "UPDATE player set rank=?  WHERE id =" + a.getId() + " ";
        try {
            pst = cnx.prepareStatement(req);
            pst.setString(1, a.getRank());     
            pst.executeUpdate();
            System.out.println("Player updated");

        } catch (SQLException ex) {
            System.out.println("Error updating");
            System.out.println(ex.getMessage());

        }
    }

    public List<Player> ShowPlayer() {
        List<Player> player = new ArrayList<>();
        String sql = "select * from player";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
           
            while (rs.next()) {
                Player p = new Player();
                User c =new User (rs.getInt("id"));
                
                p.setId(rs.getInt(1));
                p.setRank(rs.getString("rank"));
                p.setUser(c);     

                  
                player.add(p);

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return player;
    }
}
