/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Entities;

/**
 *
 * @author amira
 */
public class Player {
    private int id;
    private String rank;
    private User user;

    public Player() {
    }

    public Player(int id) {
        this.id = id;
    }

    public Player(String rank, User user) {
        this.rank = rank;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public String getRank() {
        return rank;
    }

    public User getUser() {
        return user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Player{" + "id=" + id + ", rank=" + rank + ", user=" + user + '}';
    }
    
}
