package edu.ntnu.idatt2001;

/**
 * An action that affects player inventory.
 */
public class InventoryAction implements Action{

    private final String item;

    /**
     * Instantiates a new InventoryAction.
     *
     * @param item the item
     */
    InventoryAction(String item){
        this.item = item;
    }

    @Override
    public void Execute(Player player){
        player.addToInventory(item);
    }

}

