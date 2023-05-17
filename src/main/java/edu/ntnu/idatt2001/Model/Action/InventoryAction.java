package edu.ntnu.idatt2001.Model.Action;

import edu.ntnu.idatt2001.Model.Player;

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

    public String getType(){
        return "Inventory";
    }

    public String getAmount(){
        return item;
    }

    @Override
    public void execute(Player player){
        if(item.startsWith("-")){
            player.removeFromInventory(item.substring(1));
            return;
        }else{
        player.addToInventory(item);
    }
    }

}

