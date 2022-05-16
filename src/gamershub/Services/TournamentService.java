/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Services;

import static gamershub.APIs.sendSMS.sendSMS;
import gamershub.Entities.Tournaments;
import gamershub.Interfaces.ITournamentService;
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
public class TournamentService implements ITournamentService<Tournaments> {
    MyDB instance = MyDB.getInstance();
    Connection cnx = instance.getCon();
    
    @Override
    public void createTournament(Tournaments T) {
      
        try {
            String req = "INSERT INTO `tournaments`(`name`,`decription`,`team_size`, `start_date`,`finish_date`, `max_t`, `images` ) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement st = cnx.prepareStatement(req);
         
            st.setString(1, T.getName());
            st.setString(2, T.getDecription());
            st.setInt(3, T.getTeamSize());
            st.setDate(4, (Date) T.getStartDate());
            st.setDate(5, (Date) T.getFinishDate());
            st.setInt(6, T.getMaxT());
            st.setString(7, T.getImages());


            st.executeUpdate();
            System.out.println("Tournaments ajoutée avec succes.");
                  sendSMS(T);
      

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    @Override
    public void editTournament(Tournaments T) {
        try {
            String req = "update tournaments set name = '" + T.getName() + "', decription ='" + T.getDecription() + "', team_size = '" + T.getTeamSize() +"',start_date = '"+T.getStartDate()+"',finish_date = '"+T.getFinishDate()+ "', max_t ='" + T.getMaxT() + "', images='" + T.getImages()+"'   WHERE id='" + T.getId() + "'";
            PreparedStatement st = cnx.prepareStatement(req);
            
            

            st.executeUpdate(req);
            System.out.println("Tournament edited.");

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    
    @Override
     public boolean deleteTournaments(int id) {
        boolean isDeleted = false;
        try {
            String req = "delete from tournaments where id = ?";
            PreparedStatement prepare = cnx.prepareStatement(req);
            prepare.setInt(1, id);
            prepare.executeUpdate();
            isDeleted = true;
            System.out.println("Tournaments deleted");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error");
        }
        return isDeleted;
    }
     
     public List<Tournaments> readTournaments() {
        ArrayList<Tournaments> Tournaments = new ArrayList();

        try {
            String requete = "SELECT * FROM tournaments";
            Statement st = MyDB.getInstance().getCon()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete);                                                                                                                                            //eli bch y7ot feha les données executé par la requete
            while(rs.next()){                                                                                                                                                           //verifi ken mezel fama data
                Tournaments t = new Tournaments();
                t.setId(rs.getInt("id"));
                t.setName(rs.getString("name"));
                t.setDecription(rs.getString("decription"));
                t.setTeamSize(rs.getInt("team_size"));
                t.setStartDate(rs.getDate("start_date"));
                t.setFinishDate(rs.getDate("finish_date"));
                t.setMaxT(rs.getInt("max_t"));
                t.setImages(rs.getString("images"));
                Tournaments.add(t);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Tournaments;

        

    }

    @Override
    public List<Tournaments> searchTournaments(String search) {
        String requete="select * from tournaments where name like ? or decription like ? or team_size like ? or max_t like ?";
        ResultSet rs=null;
        List<Tournaments> list=new ArrayList();
        try{
            PreparedStatement ps = MyDB.getInstance().getCon()
                    .prepareStatement(requete);
            ps.setString(1, search);
            ps.setString(2, search);
            ps.setString(3, search);
            ps.setString(4, search);
            rs=ps.executeQuery();
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
         Tournaments t = new Tournaments();
        try{
             while(rs.next()){                                                                                                                                                           //verifi ken mezel fama data
               
                t.setId(rs.getInt("id"));
                t.setName(rs.getString("name"));
                t.setDecription(rs.getString("decription"));
                t.setTeamSize(rs.getInt("team_size"));
                t.setStartDate(rs.getDate("start_date"));
                t.setFinishDate(rs.getDate("finish_date"));
                t.setMaxT(rs.getInt("max_t"));
                t.setImages(rs.getString("images"));
                list.add(t);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
        
    }

    @Override
    public List<Tournaments> trierTournaments() {
    List<Tournaments> list=new ArrayList<>();
        try{
            String requete="select * from tournaments order by finish_date desc";
            PreparedStatement ps = MyDB.getInstance().getCon()
                    .prepareStatement(requete);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
 Tournaments t = new Tournaments();
                t.setId(rs.getInt("id"));
                t.setName(rs.getString("name"));
                t.setDecription(rs.getString("decription"));
                t.setTeamSize(rs.getInt("team_size"));
                t.setStartDate(rs.getDate("start_date"));
                t.setFinishDate(rs.getDate("finish_date"));
                t.setMaxT(rs.getInt("max_t"));
                t.setImages(rs.getString("images"));
                list.add(t);            }
        }
        catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        return list;    }
     
    

    }
    
    

