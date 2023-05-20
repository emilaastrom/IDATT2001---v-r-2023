package edu.ntnu.idatt2001.Model.Action;

import edu.ntnu.idatt2001.Model.Player;

/**
 * The interface Action.
 */
public interface Action {

    String getType();

    String getAmount();

    /**
     * Execute abstract method, defined in classes that are implementing this interface.
     *
     * @param player the player
     */
    void execute(Player player);

    /**
     * Undo abstract method, defined in classes that are implementing this interface.
     *
     * @param player the player
     */
    void undo(Player player);

}
