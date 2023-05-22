package edu.ntnu.idatt2001.model.goal;

import edu.ntnu.idatt2001.model.Player;

/**
 * The interface Goal.
 */
public interface Goal {
  /**
   * Is fulfilled abstract method, defined in classes that are implementing this interface.
   *
   * @param player the player
   * @return the boolean
   */
  boolean isFulfilled(Player player);
}
