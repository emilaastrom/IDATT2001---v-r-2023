package edu.ntnu.idatt2001.Goals;

import edu.ntnu.idatt2001.Goals.Goal;
import edu.ntnu.idatt2001.Player;

/**
 * The goal of reaching a certain amount of gold.
 */
public class GoldGoal implements Goal {
    private final int minimumGold;

    /**
     * Instantiates a new GoldGoal.
     *
     * @param minimumGold the minimum gold
     */
    public GoldGoal(int minimumGold){
        this.minimumGold = minimumGold;
    }

    @Override
    public boolean isFulfilled(Player player){
        return player.getGold() >= minimumGold;
    }
}
