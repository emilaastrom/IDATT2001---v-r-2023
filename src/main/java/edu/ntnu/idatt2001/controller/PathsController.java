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
    //filter out links that require items that the player does not have
    passage.getLinks().stream()
      .filter(somePassage -> {
        boolean itemNotFound = false;
        List<Action> actions = somePassage.getActions();
        boolean activeLink = actions.stream()
          .allMatch(action -> {
            //if the action is an inventory action
            if (action instanceof InventoryAction otherAction) {
              //if the amount is negative, check if the player has the item
              if (otherAction.getAmount().startsWith("-")) {
                return Game.getInstance().getPlayer().getInventory().stream()
                        .anyMatch(item -> item.equals(otherAction.getAmount().substring(1)));
              }
              //if the action is a gold action
            } else if (action instanceof GoldAction goldAction) {
              //if the amount is negative, check if the player has enough gold
              if (goldAction.getAmount().startsWith("-")) {
                return Game.getInstance().getPlayer().getGold() >= Integer.parseInt(goldAction.getAmount().substring(1));
              }
            }
            //if the action is not an inventory or gold action, return true
            return true;
           });
        if (!activeLink) {
            itemNotFound = true;
        }
        //if item not found, skip this link
        return !itemNotFound;
      })
      .forEach(links::add);
    return links;
  }
}
