/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gamershub.Services;

import gamershub.Entities.Matchs;
import java.util.List;







/**
 *
 * @author LENOVO
 */
public interface InterfaceMatch {
//CRUD

    public void createMatch(Matchs p);

    public List<Matchs> readMatch();

    public void deleteMatch(Matchs p);

    public void modifyMatch(Matchs p);

}
