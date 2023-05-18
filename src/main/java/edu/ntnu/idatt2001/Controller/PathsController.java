package edu.ntnu.idatt2001.Controller;

import edu.ntnu.idatt2001.Model.Action.Action;
import edu.ntnu.idatt2001.Model.Action.InventoryAction;
import edu.ntnu.idatt2001.Model.Game;
import edu.ntnu.idatt2001.Model.Link;
import edu.ntnu.idatt2001.Model.Passage;
import edu.ntnu.idatt2001.Model.Story;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Separator;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class PathsController {

  public PathsController() {
  }

  public void showInventory() {
  }

  public void showHelp() {
  }

  public void showSettings() {
  }
  public Story getStory(){
    return Game.getInstance().getStory();
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
