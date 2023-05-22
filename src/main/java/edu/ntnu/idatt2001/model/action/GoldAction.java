package edu.ntnu.idatt2001.model.action;

import edu.ntnu.idatt2001.model.Player;

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
  public GoldAction(int gold) {
    this.gold += gold;
  }

  /**
   * Gets type.
   *
   * @return the type
   */
  public String getType() {
    return "Gold";
  }

  /**
   * Gets amount.
   *
   * @return the amount
   */
  public String getAmount() {
    return Integer.toString(gold);
  }

  /**
   * Execute the action.
   *
   * @param player the player
   */
  @Override
  public void execute(Player player) {
    player.addGold(gold);
  }

  /**
   * Undo the action.
   *
   * @param player the player
   */
  @Override
  public void undo(Player player) {
    player.addGold(-gold);
  }
}
