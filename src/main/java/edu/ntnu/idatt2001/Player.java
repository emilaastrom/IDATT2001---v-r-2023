package edu.ntnu.idatt2001;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private int health;
    private int score;
    private int gold;
    private List<String> inventory = new ArrayList<>();

    Player(String name,int health,int score,int gold){
        this.name = name;
        this.health = health;
        this.score = score;
        this.gold = gold;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getScore() {
        return score;
    }

    public int getGold() {
        return gold;
    }

    public List<String> getInventory() {
        return inventory;
    }

    public void addHealth(int health) {
        this.health += health;
    }

    public void addScore(int points) {
        this.score += points;
    }

    public void addGold(int gold) {
        this.gold += gold;
    }

    public void addToInventory(String item) {
        this.inventory.add(item);
    }
}
