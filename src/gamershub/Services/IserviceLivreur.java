/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Services;

import gamershub.Entities.Livreur;
import java.sql.SQLException;
import javafx.collections.ObservableList;

/**
 *
 * @author User
 */
public interface IserviceLivreur {

    public void AjouterLivreur(Livreur c);

    public ObservableList<Livreur> AfficherLivreur();

    public void supprimerlivreur(int id);

    public void ModifierLivreur(Livreur c);
}
