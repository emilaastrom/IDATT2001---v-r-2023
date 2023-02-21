package edu.ntnu.idatt2001;

/**
 * The goal of reaching a certain health.
 */
public class HealthGoal implements Goal{
    private final int minimumHealth;

    /**
     * Instantiates a new HealthGoal.
     *
     * @param minimumHealth the minimum health
     */
    public HealthGoal(int minimumHealth){
        this.minimumHealth = minimumHealth;
    }

    @Override
    public boolean isFulfilled(Player player){
        return player.getHealth() >= minimumHealth;
    }
}
