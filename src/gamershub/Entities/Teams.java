/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gamershub.Entities;

/**
 *
 * @author LENOVO
 */
public class Teams {

    private int id;
    private String TeamName;
    private int gamersNb;
    private int rank;
    private boolean isVerified;
    private String image;

    
    
    public Teams(String TeamName, int gamersNb, int rank, boolean isVerified, String image) {
        this.TeamName = TeamName;
        this.gamersNb = gamersNb;
        this.rank = rank;
        this.isVerified = isVerified;
        this.image = image;
    }

    public Teams() {
       
    }

    public Teams(int id, String TeamName, int gamersNb, int rank) {
        this.id = id;
        this.TeamName = TeamName;
        this.gamersNb = gamersNb;
        this.rank = rank;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeamName() {
        return TeamName;
    }

    public void setTeamName(String TeamName) {
        this.TeamName = TeamName;
    }

    public int getGamersNb() {
        return gamersNb;
    }

    public void setGamersNb(int gamersNb) {
        this.gamersNb = gamersNb;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public boolean isIsVerified() {
        return isVerified;
    }

    public void setIsVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }
   public Boolean getIsVerified() {
        return isVerified;
    }
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Teams(int id, String TeamName, int gamersNb, int rank, boolean isVerified, String image) {
        this.id = id;
        this.TeamName = TeamName;
        this.gamersNb = gamersNb;
        this.rank = rank;
        this.isVerified = isVerified;
        this.image = image;
    }

    public Teams(int id, String TeamName, int gamersNb, int rank, boolean isVerified) {
        this.id = id;
        this.TeamName = TeamName;
        this.gamersNb = gamersNb;
        this.rank = rank;
        this.isVerified = isVerified;
    }
    
    
  @Override
    public String toString() {
        return "Teams{" + " id=" + id + "\n, team_name=" + TeamName + "\n, gamers_nb=" + gamersNb + "\n, rank=" + rank + "\n, verified=" + isVerified + "\n, image=" + image + '}';
    }

}
