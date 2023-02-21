package edu.ntnu.idatt2001;

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
    private List<String> inventory = new ArrayList<>();

    /**
     * Instantiates a new Player.
     *
     * @param name   the name
     * @param health the health
     * @param score  the score
     * @param gold   the gold
     */
    Player(String name,int health,int score,int gold){
        this.name = name;
        this.health = health;
        this.score = score;
        this.gold = gold;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
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
     * Add health.
     *
     * @param health the health
     */
    public void addHealth(int health) {
        this.health += health;
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
}
