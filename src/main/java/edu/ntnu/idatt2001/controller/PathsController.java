package edu.ntnu.idatt2001.controller;

import edu.ntnu.idatt2001.Main;
import edu.ntnu.idatt2001.model.Action.Action;
import edu.ntnu.idatt2001.model.Action.GoldAction;
import edu.ntnu.idatt2001.model.Action.InventoryAction;
import edu.ntnu.idatt2001.model.Game;
import edu.ntnu.idatt2001.model.Link;
import edu.ntnu.idatt2001.model.Passage;
import edu.ntnu.idatt2001.model.Story;
import edu.ntnu.idatt2001.view.InventoryView;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;
import java.util.List;

public class PathsController {
  InventoryView inventoryView;

  public PathsController(InventoryView InventoryView) {
    this.inventoryView = InventoryView;
  }

  public void showInventory(double width, double height, BorderPane dimmer) {
    inventoryView.showInventory(width, height, dimmer);
  }

  public void hideInventory(BorderPane dimmer){
    inventoryView.hideInventory(dimmer);
  }

  public void showHelp() {
  }

  public void showSettings() {
    Main.showGameSettings();
  }

  public Story getStory(){
    return Game.getInstance().getStory();
  }

  public void restartGame() {
    Game.getInstance().restartGame();
  }

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
            if (Game.getInstance().getPlayer().getGold() < Integer.parseInt(goldAction.getAmount().substring(1))) {
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
