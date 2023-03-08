package edu.ntnu.idatt2001.Actions;

import edu.ntnu.idatt2001.Actions.Action;
import edu.ntnu.idatt2001.Player;

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

    @Override
    public void Execute(Player player){
        player.addHealth(health);
    }

}
