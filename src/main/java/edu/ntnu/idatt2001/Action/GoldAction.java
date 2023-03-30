package edu.ntnu.idatt2001.Action;

import edu.ntnu.idatt2001.Player;

/**
 * An action that affects player gold.
 */
public class GoldAction implements Action {

    private final int gold;

    /**
     * Instantiates a new GoldAction.
     *
     * @param gold the gold
     */
    public GoldAction(int gold){
        this.gold = gold;
    }

    @Override
    public void Execute(Player player){
        player.addGold(gold);
    }


}
