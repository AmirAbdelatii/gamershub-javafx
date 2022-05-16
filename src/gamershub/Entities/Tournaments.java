/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Entities;

import java.util.Date;

/**
 *
 * @author Ghofrane
 */
public class Tournaments {
    private int id;
    private String name;
    private String decription;
    private int teamSize;
    private Date startDate;
    private Date finishDate;
    private int maxT;
    private String images;

    public Tournaments() {
    }

    public Tournaments(int id, String name, String decription, int teamSize, Date startDate, Date finishDate, int maxT, String images) {
        this.id = id;
        this.name = name;
        this.decription = decription;
        this.teamSize = teamSize;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.maxT = maxT;
        this.images = images;
    }

    public Tournaments(String name, String decription, int teamSize, Date startDate, Date finishDate, int maxT, String images) {
        this.name = name;
        this.decription = decription;
        this.teamSize = teamSize;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.maxT = maxT;
        this.images = images;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public int getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(int teamSize) {
        this.teamSize = teamSize;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public int getMaxT() {
        return maxT;
    }

    public void setMaxT(int maxT) {
        this.maxT = maxT;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "Tournaments{" + "id=" + id + ", name=" + name + ", decription=" + decription + ", teamSize=" + teamSize + ", startDate=" + startDate + ", finishDate=" + finishDate + ", maxT=" + maxT + ", images=" + images + '}';
    }

    
}
