package edu.ntnu.idatt2001.Goals;

import edu.ntnu.idatt2001.Player;

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
    public boolean isFulfilled(Player player);
}
