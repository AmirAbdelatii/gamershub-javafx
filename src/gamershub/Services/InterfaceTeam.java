/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Services;
import java.util.List;
import Entities.Teams;
/**
 *
 * @author user
 */
public interface InterfaceTeam {
    //CRUD
    public void createTeam(Teams p);
    public List<Teams> readTeam();
    public void deleteTeam(Teams p);
    public void modifyTeam(Teams p);
}
