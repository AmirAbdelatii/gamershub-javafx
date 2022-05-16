/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Interfaces;

import gamershub.Entities.Tournaments;
import java.util.List;


/**
 *
 * @author Ghofrane
 */
public interface ITournamentService<Tournaments>{
    public void createTournament(Tournaments T);
    public void editTournament(Tournaments T);
    public boolean deleteTournaments(int id);
    public List<Tournaments> readTournaments();
    public List<Tournaments> searchTournaments(String search);
    public List<Tournaments> trierTournaments();
    
    
    
}
