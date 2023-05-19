package edu.ntnu.idatt2001.Model.Action;

import edu.ntnu.idatt2001.Model.Player;

/**
 * An action that affects player gold.
 */
public class GoldAction implements Action {

    private int gold;

    /**
     * Instantiates a new GoldAction.
     *
     * @param gold the gold
     */
    public GoldAction(int gold){
        this.gold += gold;
    }

    public String getType(){
        return "Gold";
    }

    public String getAmount(){
        return Integer.toString(gold);
    }

    @Override
    public void execute(Player player){
        player.addGold(gold);
    }

    @Override
    public void undo(Player player){
        player.addGold(-gold);
    }


}
