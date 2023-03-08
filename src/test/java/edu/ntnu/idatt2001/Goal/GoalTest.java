package edu.ntnu.idatt2001.Goal;

import edu.ntnu.idatt2001.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GoalTest {
    Player player;
    HealthGoal healthGoal;
    ScoreGoal scoreGoal;
    GoldGoal goldGoal;
    InventoryGoal inventoryGoal;
    List<String> mandatoryItems;
    @BeforeEach
    void setup(){
        player = new Player("TestPlayer", 0, 0, 0);
        mandatoryItems = new ArrayList<>();
        mandatoryItems.add("item1");
        mandatoryItems.add("item2");
        healthGoal = new HealthGoal(100);
        scoreGoal = new ScoreGoal(100);
        goldGoal = new GoldGoal(100);
        inventoryGoal = new InventoryGoal(mandatoryItems);
    }

    @Test
    @DisplayName("Test Health isFulfilled() false")
    void isHealthNotFulfilled(){
        assertFalse(healthGoal.isFulfilled(player));
    }

    @Test
    @DisplayName("Test Health isFulfilled() true")
    void isHealthFulfilled(){
        player.addHealth(100);
        assertTrue(healthGoal.isFulfilled(player));
    }

    @Test
    @DisplayName("Test Score isFulfilled() false")
    void isScoreNotFulfilled(){
        assertFalse(scoreGoal.isFulfilled(player));
    }

    @Test
    @DisplayName("Test Score isFulfilled() true")
    void isScoreFulfilled(){
        player.addScore(100);
        assertTrue(scoreGoal.isFulfilled(player));
    }

    @Test
    @DisplayName("Test Gold isFulfilled() false")
    void isGoldNotFulfilled(){
        assertFalse(goldGoal.isFulfilled(player));
    }

    @Test
    @DisplayName("Test Gold isFulfilled() true")
    void isGoldFulfilled(){
        player.addGold(100);
        assertTrue(goldGoal.isFulfilled(player));
    }

    @Test
    @DisplayName("Test Inventory isFulfilled() false")
    void isInventoryFulfilled(){
        assertFalse(inventoryGoal.isFulfilled(player));
    }

    @Test
    @DisplayName("Test Inventory isFulfilled() true")
    void isInventoryNotFulfilled(){
        player.addToInventory("item1");
        player.addToInventory("item2");
        assertTrue(inventoryGoal.isFulfilled(player));
    }
}
