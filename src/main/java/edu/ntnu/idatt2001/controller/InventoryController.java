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
    game.getPlayer().getInventory().stream()
            .map(item -> "- " + item.substring(0, 1).toUpperCase() + item.substring(1).toLowerCase() + "\r\n")
            .forEach(inventoryString::append);
    return inventoryString.toString();
  }
}
