package edu.ntnu.idatt2001.Actions;

import edu.ntnu.idatt2001.Actions.Action;
import edu.ntnu.idatt2001.Player;

/**
 * An action that affects player inventory.
 */
public class InventoryAction implements Action {

    private final String item;

    /**
     * Instantiates a new InventoryAction.
     *
     * @param item the item
     */
    public InventoryAction(String item){
        this.item = item;
    }

    @Override
    public void Execute(Player player){
        player.addToInventory(item);
    }

}

