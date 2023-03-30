package edu.ntnu.idatt2001.Action;

import edu.ntnu.idatt2001.Player;

/**
 * The interface Action.
 */
public interface Action {

    /**
     * Execute abstract method, defined in classes that are implementing this interface.
     *
     * @param player the player
     */
    void Execute(Player player);

}
