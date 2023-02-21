package edu.ntnu.idatt2001;

/**
 * An action that affects player gold.
 */
public class GoldAction implements Action{

    private int gold;

    /**
     * Instantiates a new GoldAction.
     *
     * @param gold the gold
     */
    GoldAction(int gold){
        this.gold = gold;
    }

    @Override
    public void Execute(Player player){
        //Player.addGold(gold);
    }


}
