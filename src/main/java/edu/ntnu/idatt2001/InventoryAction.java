package edu.ntnu.idatt2001;

public class InventoryAction implements Action{

    String item;

    InventoryAction(String item){
        this.item = item;
    }

    @Override
    public void Execute(Player player){
        //Player.addToInventory(item);
    }

}

