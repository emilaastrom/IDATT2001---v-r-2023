package edu.ntnu.idatt2001.model.goal;

import edu.ntnu.idatt2001.model.Player;

/**
 * The goal of reaching a certain health.
 */
public class HealthGoal implements Goal {
  private final int minimumHealth;

  /**
   * Instantiates a new HealthGoal.
   *
   * @param minimumHealth the minimum health
   */
  public HealthGoal(int minimumHealth) {
    this.minimumHealth = minimumHealth;
  }

  /**
   * Gets the minimum health.
   *
   * @return the minimum health
   */
  public int getHealthGoal() {
    return minimumHealth;
  }

  /**
   * Is fulfilled boolean.
   *
   * @param player the player
   * @return the boolean
   */
  @Override
  public boolean isFulfilled(Player player) {
    return player.getHealth() >= minimumHealth;
  }
}
