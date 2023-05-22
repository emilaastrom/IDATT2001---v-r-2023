package edu.ntnu.idatt2001.model.action;

import edu.ntnu.idatt2001.model.Player;

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
  public HealthAction(int health) {
    this.health = health;
  }

  /**
   * Gets type.
   *
   * @return the type
   */
  public String getType() {
    return "Health";
  }

  /**
   * Gets amount.
   *
   * @return the amount
   */
  public String getAmount() {
    return Integer.toString(health);
  }

  /**
   * Execute the action.
   *
   * @param player the player
   */
  @Override
  public void execute(Player player) {
    player.addHealth(health);
  }

  /**
   * Undo the action.
   *
   * @param player the player
   */
  @Override
  public void undo(Player player) {
    player.addHealth(-health);
  }
}
