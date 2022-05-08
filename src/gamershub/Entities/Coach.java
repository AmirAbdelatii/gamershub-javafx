/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Entities;

/**
 *
 * @author hp
 */
public class Coach {
     private int id,user_id,game_id;
     private String description,gamename,username;
     
     public Coach(){
     }
     
     public Coach(int user_id,int game_id, String description) {
        this.description = description;
        this.game_id = game_id;
        this.user_id = user_id;
    }
     
     public Coach(int id,String username, String gamename,String description,int game_id)
     {
         this.id=id;
         this.username=username;
         this.gamename=gamename;
         this.description = description;
         this.game_id = game_id;
     }
     
     public Coach(int id, int user_id, int game_id, String description) {
        this.id = id;
        this.description = description;
        this.user_id = user_id;
        this.game_id = game_id;
    }
     
     @Override
    public String toString() {
        return "Coach{" + "id=" + id + ", user_id=" + user_id + ", game_id=" + game_id + ", description=" + description + '}';
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getGameId() {
        return game_id;
    }

    public void setGameId(int game_id) {
        this.game_id = game_id;
    }
    
    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
     public String getusername() {
        return username;
    }

    public void setusername(String username) {
        this.username = username;
    }
    
    public String getgamename() {
        return gamename;
    }

    public void setgamename(String gamename) {
        this.gamename = gamename;
    }

    
    


     
}
