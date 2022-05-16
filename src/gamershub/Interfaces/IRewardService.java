/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Interfaces;

import gamershub.Entities.Rewards;
import java.util.List;

/**
 *
 * @author Ghofrane
 */
public interface IRewardService<Rewards> {
    public void createReward(Rewards R);
    public void editReward(Rewards R);
    public boolean deleteReward(int id);
    public List<Rewards> readRewards();
    
}
