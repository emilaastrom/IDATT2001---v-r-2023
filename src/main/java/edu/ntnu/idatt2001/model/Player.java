package edu.ntnu.idatt2001.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The Player class.
 */
public class Player {
  private final String name;
  private int health;
  private int score;
  private int gold;
  private final List<String> inventory;

  /**
   * Instantiates a new Player.
   *
   * @param builder the builder
   */
  public Player(PlayerBuilder builder) {
    name = builder.name;
    health = builder.health;
    score = builder.score;
    gold = builder.gold;
    inventory = builder.inventory;
    checkPositiveIntInput("Health", health);
    checkPositiveIntInput("Gold", gold);
  }


  /**
   * Check a variable to ensure it has a positive value.
   *
   * @param valueType the name of the given value
   * @param valueAmount the integer to be checked
   * @throws IllegalArgumentException if the value of health or gold is negative
   */
  public void checkPositiveIntInput(
          String valueType,
          int valueAmount)
          throws IllegalArgumentException {
    // If the value is negative, throw an exception
    if (valueAmount < 0) {
      throw new IllegalArgumentException("Error value regarding: "
              + valueType
              + ". This must be greater than zero!");
    }
  }

  /**
   * Gets name.
   *
   * @return the name
   */
  public String getName() {
    // If name is null or empty, change it to "You"
    if (name == null) {
      return "You";
    }
    if (name.equals("")) {
      return "You";
    }
    return name;
  }

  /**
   * Gets health.
   *
   * @return the health
   */
  public int getHealth() {
    return health;
  }

  /**
   * Gets score.
   *
   * @return the score
   */
  public int getScore() {
    return score;
  }

  /**
   * Gets gold.
   *
   * @return the gold
   */
  public int getGold() {
    return gold;
  }

  /**
   * Gets inventory.
   *
   * @return the inventory
   */
  public List<String> getInventory() {
    return inventory;
  }

  /**
   * Add health, ensures result is a valid number (>0).
   *
   * @param health the health
   */
  public void addHealth(int health) {
    //add health
    try {
      checkPositiveIntInput("health after addition", this.health += health);
    } catch (IllegalArgumentException e) {
      // If the health is negative, set it to 0
      this.health = 0;
    }
  }

  /**
   * Add score.
   *
   * @param points the points
   */
  public void addScore(int points) {
    this.score += points;
  }

  /**
   * Add gold.
   *
   * @param gold the gold
   */
  public void addGold(int gold) {
    this.gold += gold;
  }

  /**
   * Add to inventory.
   *
   * @param item the item
   */
  public void addToInventory(String item) {
    this.inventory.add(item);
  }

  /**
   * Remove an item from inventory.
   *
   * @param item the item to be removed
   */
  public void removeFromInventory(String item) {
    if (this.inventory.contains(item)) {
      this.inventory.remove(item);
      // If the item is not in the inventory, throw an exception
    } else {
      throw new IllegalArgumentException("Item not in inventory");
    }
  }

  /**
   * Builder class for Player.
   */
  public static class PlayerBuilder {
    //required
    private final String name;

    //optional
    private int health = 100;
    private int score = 0;
    private int gold = 0;
    private List<String> inventory = new ArrayList<>();

    /**
     * Instantiates a new Player builder.
     *
     * @param name the name
     */
    public PlayerBuilder(String name) {
      this.name = name;
    }

    /**
     * Health player builder.
     *
     * @param health the health
     * @return the player builder
     */
    public PlayerBuilder health(int health) {
      this.health = health;
      return this;
    }

    /**
     * Score player builder.
     *
     * @param score the score
     * @return the player builder
     */
    public PlayerBuilder score(int score) {
      this.score = score;
      return this;
    }

    /**
     * Gold player builder.
     *
     * @param gold the gold
     * @return the player builder
     */
    public PlayerBuilder gold(int gold) {
      this.gold = gold;
      return this;
    }

    /**
     * Inventory player builder.
     *
     * @param inventory the inventory
     * @return the player builder
     */
    public PlayerBuilder inventory(List<String> inventory) {
      this.inventory = inventory;
      return this;
    }

    /**
     * Build player.
     *
     * @return the player
     */
    public Player build() {
      return new Player(this);
    }
  }
}
