/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Entities;



/**
 *
 * @author Ghofrane
 */
public class Rewards {
    private int id;
    private int tournamentId;
    private String type;
    private int quantity;

    public Rewards() {
    }

    public Rewards(int id, int tournamentId, String type, int quantity) {
        this.id = id;
        this.tournamentId = tournamentId;
        this.type = type;
        this.quantity = quantity;
    }

    public Rewards(int tournamentId, String type, int quantity) {
        this.tournamentId = tournamentId;
        this.type = type;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(int tournamentId) {
        this.tournamentId = tournamentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Rewards{" + "id=" + id + ", tournamentId=" + tournamentId + ", type=" + type + ", quantity=" + quantity + '}';
    }
    
    

    
    
}
