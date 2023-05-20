package edu.ntnu.idatt2001.controller;

import edu.ntnu.idatt2001.Main;
import edu.ntnu.idatt2001.model.Game;
import edu.ntnu.idatt2001.model.Link;
import edu.ntnu.idatt2001.model.Passage;
import edu.ntnu.idatt2001.model.Story;
import edu.ntnu.idatt2001.model.action.Action;
import edu.ntnu.idatt2001.model.action.GoldAction;
import edu.ntnu.idatt2001.model.action.InventoryAction;
import edu.ntnu.idatt2001.view.InventoryView;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.BorderPane;

/**
 * Controller class for the PathsView.
 */
public class PathsController {
  InventoryView inventoryView;

  /**
   * Constructor for the PathsController.

   * @param inventoryView the InventoryView
   */
  public PathsController(InventoryView inventoryView) {
    this.inventoryView = inventoryView;
  }

  /**
   * Shows the inventory.

   * @param width the width of the inventory
   * @param height the height of the inventory
   * @param dimmer the dimmer
   */
  public void showInventory(double width, double height, BorderPane dimmer) {
    inventoryView.showInventory(width, height, dimmer);
  }

  /**
   * Hides the inventory.

   * @param dimmer the dimmer
   */
  public void hideInventory(BorderPane dimmer) {
    inventoryView.hideInventory(dimmer);
  }

  /**
   * Shows the settings.
   */
  public void showSettings() {
    Main.showGameSettings();
  }

  /**
   * Restarts the game.
   */
  public void restartGame() {
    Game.getInstance().restartGame();
  }

  /**
   * Gets the story.

   * @return the story
   */
  public Story getStory() {
    return Game.getInstance().getStory();
  }

  /**
   * Gets the current passage links.

   * @param passage the passage
   * @return the current passage links
   */
  public ArrayList<Link> getCurrentPassageLinks(Passage passage) {
    ArrayList<Link> links = new ArrayList<>();
    for (Link link : passage.getLinks()) {
      //check if link is active
      List<Action> actions = link.getActions();
      boolean activeLink = true;
      boolean itemNotFound = false;
      //TODO consider replacing with streams if possible
      for (Object action : actions) {
        //check if action is inventory action
        if (action instanceof InventoryAction inventoryAction) {
          //check if action is subtract item-action
          if (inventoryAction.getAmount().startsWith("-")) {
            boolean found = false;
            for (String item : Game.getInstance().getPlayer().getInventory()) {
              if (item.equals(inventoryAction.getAmount().substring(1))) {
                found = true;
                break;
              }
            }
            if (!found) {
              activeLink = false;
              break;
            }
          }
        } else if (action instanceof GoldAction goldAction) {
          if (goldAction.getAmount().startsWith("-")) {
            if (Game.getInstance().getPlayer().getGold()
                < Integer.parseInt(goldAction.getAmount().substring(1))) {
              activeLink = false;
              break;
            }
          }
        }
      }
      if (!activeLink) {
        itemNotFound = true;
      }
      //if item not found, skip this link
      if (itemNotFound) {
        continue;
      }
      links.add(link);
    }
    return links;
  }
}
