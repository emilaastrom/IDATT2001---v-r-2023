package edu.ntnu.idatt2001.controller;

import edu.ntnu.idatt2001.model.Game;

/**
 * Controller for InventoryView.
 */
public class InventoryController {
  public InventoryController() {
  }

  /**
   * Gets items in inventory as a string.
   *
   * @return the inventory string
   */
  public String getInventoryString() {
    StringBuilder inventoryString = new StringBuilder("Inventory:\r\n\n");
    Game game = Game.getInstance();
    for (String item : game.getPlayer().getInventory()) {
      inventoryString
          .append("- ")
          .append(item.substring(0, 1).toUpperCase())
          .append(item.substring(1).toLowerCase())
          .append("\r\n");
    }
    return inventoryString.toString();
  }
}
