package edu.ntnu.idatt2001.Model.Action;

import edu.ntnu.idatt2001.Model.Player;

/**
 * An action that affects player health.
 */
public class HealthAction implements Action {

    private final int health;

    /**
     * Instantiates a new HealthAction.
     *
     * @param health the health
     */
    public HealthAction(int health){
        this.health = health;
    }

    public String getType(){
        return "Health";
    }

    public String getAmount(){
        return Integer.toString(health);
    }

    @Override
    public void execute(Player player){
        player.addHealth(health);
    }

    @Override
    public void undo(Player player){
        player.addHealth(-health);
    }

}
