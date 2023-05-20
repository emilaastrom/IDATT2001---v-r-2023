package edu.ntnu.idatt2001.model.action;

import edu.ntnu.idatt2001.model.Player;

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
        }else{
        player.addToInventory(item);
    }
    }

    @Override
    public void undo(Player player){
        if(item.startsWith("-")){
            player.addToInventory(item.substring(1));
        }else{
        player.removeFromInventory(item);
    }
    }

}

