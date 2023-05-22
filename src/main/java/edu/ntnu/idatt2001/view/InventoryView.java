package edu.ntnu.idatt2001.view;

import edu.ntnu.idatt2001.controller.InventoryController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 * The InventoryView class responsible for visualizing the player inventory.
 */
public class InventoryView {

  private final Stage inventoryStage = new Stage(StageStyle.UNDECORATED);
  private final InventoryController controller;

  /**
   * Instantiates a new Inventory view and setting its controller.
   *
   * @param controller the controller
   */
  public InventoryView(InventoryController controller) {
    this.controller = controller;
  }

  /**
   * Display the inventory stage.
   *
   * @param width  the width of the background stage
   * @param height the height of the background stage
   * @param dimmer the dimmer for the background stage
   */
  public void showInventory(double width, double height, BorderPane dimmer) {
    BorderPane inventoryRoot = new BorderPane();
    inventoryRoot.setPadding(new Insets(30, 30, 30, 30));
    inventoryRoot.setId("inventoryRoot");
    Text tooltip = new Text();
    tooltip.setId("tooltip");
    String inventoryString = controller.getInventoryString();
    tooltip.setText(inventoryString);
    inventoryRoot.setTop(tooltip);
    Scene tooltipScene = new Scene(inventoryRoot, width, height);
    tooltipScene.getStylesheets().add("file:src/main/resources/maintheme.css");
    inventoryStage.setScene(tooltipScene);
    dimmer.setVisible(true);
    inventoryStage.show();
  }

  /**
   * Hide the inventory stage and disable background dimmer.
   *
   * @param dimmer the dimmer for the background stage
   */
  public void hideInventory(BorderPane dimmer) {
    inventoryStage.close();
    dimmer.setVisible(false);
  }
}
