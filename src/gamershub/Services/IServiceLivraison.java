/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Services;

import gamershub.Entities.Livraison;
import java.sql.SQLException;
import javafx.collections.ObservableList;

/**
 *
 * @author User
 */
public interface IServiceLivraison {
    public void AjouterLivraison(Livraison c);
    public ObservableList<Livraison>AfficherLivraison();
    public void supprimerLivraison(int id);
    public void ModifierLivraison(Livraison c);
}
