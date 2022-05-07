    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Services;
import Entities.Products;
import gamershub.Services.InterfaceTeam;

import Entities.Teams;
import gamershub.Utils.MyDB;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author user
 */
public class TeamService implements InterfaceTeam{
    Connection cnx = MyDB.getInstance().getCon();
    Statement stm;

    /**
     *
     * @param p
     */
   // @Override
    public void createTeam(Teams p) {
        
        //request
        String req = "INSERT INTO `teams`(`id`,`team_name`, `gamers_nb`, `rank`, `verified`, `image`) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, p.getId());
            st.setString(2, p.getTeamName());
            st.setInt(3, p.getGamersNb());
            st.setInt(4, p.getRank());
            st.setBoolean(5, p.getIsVerified());
            st.setString(6, p.getImage());
            st.executeUpdate();
            System.out.println("Team ajouté avec succes.");
            
        } catch (SQLException ex) {
            
            ex.printStackTrace();
        }
        
        
    }

    @Override
    public List<Teams> readTeam() {
        ArrayList<Teams> Teams = new ArrayList();
        
        try {
            Statement st = cnx.createStatement();
            String req = "SELECT * FROM teams";
            ResultSet rs = st.executeQuery(req);
            
            while (rs.next()) {                
                
                Teams.add(new Teams(rs.getInt(1),rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getBoolean(5), rs.getString(6)));
                
            }
            
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
        
        return Teams;
    }
    @Override
    public void deleteTeam(Teams p) {
        String query = "DELETE FROM teams WHERE id= " + p.getId() + "";
        try{
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("Team Deleted Successfully");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void modifyTeam(Teams p) {
         String query = "UPDATE teams SET team_name = '" + p.getTeamName() + "',gamers_nb = '" + p.getGamersNb() +
                 "',rank = '" + p.getRank() + "' WHERE id = " + p.getId() + "";       
        try{
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("Team Updated Successfully ");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        } 
    }
    public List<Teams> show() throws SQLException {
        String req = "Select * from `teams`";
        stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(req);
        System.out.println(rst.toString());
        List<Teams> Teams = new ArrayList<Teams>();
        while (rst.next()) {

            Teams c = new Teams(rst.getInt(1),rst.getString(2),rst.getInt(3), rst.getInt(4),rst.getBoolean(5),rst.getString(6));
            
            Teams.add(c);

        }
        return Teams;
    }
     public void delete(int id) throws SQLException {
        String req = "DELETE FROM `teams` WHERE `id`=?";
        PreparedStatement pstm = cnx.prepareStatement(req);
        pstm.setInt(1, id);
        pstm.executeUpdate();

    }
     
      
    public HashMap<Integer, String> CountPdtByCat()  {
          HashMap<Integer, String> cats = new HashMap<Integer, String>();
        
 
        try{
           
           String req = "SELECT COUNT(verified) as nbr FROM teams WHERE verified=1";
             String req1 = "SELECT COUNT(verified) as nom FROM teams WHERE verified=0";
           stm = cnx.createStatement();
           ResultSet rs = stm.executeQuery(req);
          //ResultSet rs1 = stm.executeQuery(req1);
           System.out.println(rs.toString());
           System.out.println(cats);
           while (rs.next()) {
              System.out.println(cats);
             cats.put( rs.getInt("nbr"),"verified");
              
            }
           
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
           
        return cats;}
    
    public void generateExcel() 
         {
           String sql = "select * from teams";
        Statement ste;
        try {
       
          ste=cnx.prepareStatement(sql);
               ResultSet rs = ste.executeQuery(sql);
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("Team details ");
            HSSFRow header = sheet.createRow(0);
            header.createCell(0).setCellValue("ID");
            header.createCell(1).setCellValue("Team Name");
            header.createCell(2).setCellValue("Gamers Number");
            header.createCell(3).setCellValue("Rank");
            header.createCell(4).setCellValue("Verified");
            
            int index = 1;
            while (rs.next()) {
                HSSFRow row = sheet.createRow(index);
                row.createCell(0).setCellValue(rs.getString("id"));
                row.createCell(1).setCellValue(rs.getString("team_name"));
                row.createCell(2).setCellValue(rs.getString("gamers_nb"));
                row.createCell(3).setCellValue(rs.getString("rank"));
                row.createCell(4).setCellValue(rs.getString("verified"));
                
                index++;
            }
            FileOutputStream fileOut = new FileOutputStream("C:\\Users\\LENOVO\\OneDrive\\Bureau\\TeamDetails.Xls");
            wb.write(fileOut);
            fileOut.close();
           ste.close();
           rs.close();

        } catch (SQLException e) {
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

       

    }
    
    public int countNotVerifid() {        
            
            int cu = 0;
            
            try {
            String requete = "SELECT COUNT(verified) as nbr FROM teams WHERE verified=0";
            
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            
            
            while (rs.next()) {
                cu = rs.getInt("nbr");
            }
           
           
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
            
        return cu;
    }
    public int countVerifid() {        
            
            int cu = 0;
            
            try {
            String requete = "SELECT COUNT(verified) as nbr FROM teams WHERE verified=1";
            
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            
            
            while (rs.next()) {
                cu = rs.getInt("nbr");
            }
           
           
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
            
        return cu;
    }
}
