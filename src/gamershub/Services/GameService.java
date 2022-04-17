/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Services;

import gamershub.Entities.Game;
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
 * @author MAB
 */
public class GameService implements IService<Game>{

    Connection con;
    Statement stm;
    
    public GameService() {
        con = MyDB.getInstance().getCon();
    }
    

    @Override
    public void ajouter(Game game) throws SQLException {
        String req = "INSERT INTO `game` (`name`, `image`, `description`, `created_at`, `updated_at`) VALUES (?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)";
        PreparedStatement pstm = con.prepareStatement(req);
        pstm.setString(1, game.getName());
        pstm.setString(2, game.getImage());
        pstm.setString(3, game.getDescription());
        pstm.executeUpdate();
    }

    @Override
    public void ajouterr(Game t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Game> afficher() throws SQLException {
        String req = "Select * from `game`";
        stm = con.createStatement();
        ResultSet result = stm.executeQuery(req);
        List<Game> games = new ArrayList<Game>();
        while (result.next()) {
            Game g = new Game(result.getInt("id"),result.getString("name"),result.getString("image"),result.getString("description"),result.getDate("created_at"),result.getDate("updated_at"));
            games.add(g);
        }
        return games;
    }

    @Override
    public void delete(Game game) throws SQLException {
        String req = "DELETE FROM `game` WHERE `id` = \""+game.getId()+"\"; ";
        stm = con.createStatement();
        stm.executeUpdate(req);
    }

    @Override
    public void update(Game game) throws SQLException {
        String req = "UPDATE `game` SET " +
            " `name` = '"+game.getName()+"', " +
            " `description` = '"+game.getDescription()+"', " +
            " `image` = '"+game.getImage()+"', " +
            " `updated_at` = CURRENT_TIMESTAMP " +
            "WHERE `id` = "+game.getId();
        stm = con.createStatement();
        stm.executeUpdate(req);
    }
    
    public Game getGame(String gameName) throws SQLException {
        String req = "Select * from `game` where name = '"+gameName+"'";
        stm = con.createStatement();
        ResultSet result = stm.executeQuery(req);
        Game g = new Game();
        while (result.next()) {
            g = new Game(result.getInt("id"),result.getString("name"),result.getString("image"),result.getString("description"),result.getDate("created_at"),result.getDate("updated_at"));
        }
        return g;
    }
    
    public void deleteByName(String name) throws SQLException {
        String req = "DELETE FROM `game` WHERE `name` = \""+name+"\"; ";
        stm = con.createStatement();
        stm.executeUpdate(req);
    }
    
}
