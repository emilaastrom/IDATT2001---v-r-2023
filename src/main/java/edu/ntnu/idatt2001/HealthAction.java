package edu.ntnu.idatt2001;

/**
 * An action that affects player health.
 */
public class HealthAction implements Action{

    private int health;

    /**
     * Instantiates a new HealthAction.
     *
     * @param health the health
     */
    HealthAction(int health){
        this.health = health;
    }

    @Override
    public void Execute(Player player){
        //Player.addScore(points);
    }

}
