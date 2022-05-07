/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gamershub.Entities;

import java.sql.Date;

/**
 *
 * @author LENOVO
 */
public class Matchs {
    private int id;
    private String MatchName;
    private Date MatchDate;
    private int Result;

    public Matchs(int id, String MatchName, Date MatchDate, int Result) {
        this.id = id;
        this.MatchName = MatchName;
        this.MatchDate = MatchDate;
        this.Result = Result;
    }

    public Matchs(String MatchName, Date MatchDate, int Result) {
        this.MatchName = MatchName;
        this.MatchDate = MatchDate;
        this.Result = Result;
    }

    public Matchs() {
       
    }

    public Matchs(String text, int parseInt) {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatchName() {
        return MatchName;
    }

    public void setMatchName(String MatchName) {
        this.MatchName = MatchName;
    }

        public Date getMatchDate() {
        return MatchDate;
    }

    public void setMatchDate(Date MatchDate) {
        this.MatchDate = MatchDate;
    }

    public int getResult() {
        return Result;
    }

    public void setResult(int Result) {
        this.Result = Result;
    }


   @Override
    public String toString() {
        return "Matchs :" + "\n id=" + id + "\n match_name =" + MatchName + "\n  match_date=" + MatchDate + "\n  result=" + Result + '"';
    }

    public void setMatchDate(String myFormattedDate) {
       
    }

    public Matchs(int id, String MatchName, int Result) {
        this.id = id;
        this.MatchName = MatchName;
        this.Result = Result;
    }

}
