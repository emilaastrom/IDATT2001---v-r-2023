package edu.ntnu.idatt2001.model.goal;

import edu.ntnu.idatt2001.model.Player;
import java.util.List;

/**
 * The goal of having certain items in the inventory.
 */
public class InventoryGoal implements Goal {
  private final List<String> mandatoryItems;

  /**
   * Instantiates a new InventoryGoal.
   *
   * @param mandatoryItems the mandatory items
   */
  public InventoryGoal(List<String> mandatoryItems) {
    this.mandatoryItems = mandatoryItems;
  }

  /**
   * Gets the mandatory items.
   *
   * @return the mandatory items
   */
  public List<String> getMandatoryItems() {
    return mandatoryItems;
  }

  /**
   * Is fulfilled boolean.
   *
   * @param player the player
   * @return the boolean
   */
  @Override
  public boolean isFulfilled(Player player) {
    boolean response = false;
    for (String mandatoryItem : mandatoryItems) {
      if (player.getInventory().contains(mandatoryItem)) {
        response = true;
      } else {
        response = false;
        break;
      }
    }
    return response;
  }
}


