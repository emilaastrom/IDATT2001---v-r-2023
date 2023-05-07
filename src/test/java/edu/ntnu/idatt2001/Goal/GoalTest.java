package edu.ntnu.idatt2001.Goal;

import edu.ntnu.idatt2001.Model.Goal.GoldGoal;
import edu.ntnu.idatt2001.Model.Goal.HealthGoal;
import edu.ntnu.idatt2001.Model.Goal.InventoryGoal;
import edu.ntnu.idatt2001.Model.Goal.ScoreGoal;
import edu.ntnu.idatt2001.Model.Player;
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
        player = new Player.PlayerBuilder("TestPlayer").health(0).score(0).gold(0).build();
        mandatoryItems = new ArrayList<>();
        mandatoryItems.add("item1");
        mandatoryItems.add("item2");
        healthGoal = new HealthGoal(100);
        scoreGoal = new ScoreGoal(100);
        goldGoal = new GoldGoal(100);
        inventoryGoal = new InventoryGoal(mandatoryItems);
    }

    @Test
    @DisplayName("Test Health isFulfilled()")
    void isHealthFulfilled(){
        assertFalse(healthGoal.isFulfilled(player));

        player.addHealth(100);
        assertTrue(healthGoal.isFulfilled(player));
    }

    @Test
    @DisplayName("Test Score isFulfilled()")
    void isScoreFulfilled(){
        assertFalse(scoreGoal.isFulfilled(player));

        player.addScore(100);
        assertTrue(scoreGoal.isFulfilled(player));
    }

    @Test
    @DisplayName("Test Gold isFulfilled()")
    void isGoldFulfilled(){
        assertFalse(goldGoal.isFulfilled(player));

        player.addGold(100);
        assertTrue(goldGoal.isFulfilled(player));
    }

    @Test
    @DisplayName("Test Inventory isFulfilled()")
    void isInventoryFulfilled(){
        assertFalse(inventoryGoal.isFulfilled(player));

        player.addToInventory("item1");
        player.addToInventory("item2");
        assertTrue(inventoryGoal.isFulfilled(player));
    }
}
