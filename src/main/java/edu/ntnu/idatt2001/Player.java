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
    public Player(String name,int health,int score,int gold){

        checkPositiveIntInput("Health", health);
        checkPositiveIntInput("Gold", gold);

        this.name = name;
        this.health = health;
        this.score = score;
        this.gold = gold;
    }


    /**
     * Check a variable to ensure it has a positive value.
     *
     * @param s the name of the given value
     * @param i the integer to be checked
     * @throws IllegalArgumentException if the value of health or gold is negative
     */
    public void checkPositiveIntInput( String s, int i) throws IllegalArgumentException{
        if (i<0){
            throw new IllegalArgumentException("Error value regarding: " + s + ". This must be greater than zero!");
        }
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
     * Add health, ensures result is a valid number (>0)
     *
     * @param health the health
     */
    public void addHealth(int health) {
        checkPositiveIntInput("health after addition", this.health += health);
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
