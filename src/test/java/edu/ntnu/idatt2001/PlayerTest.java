package edu.ntnu.idatt2001;

import edu.ntnu.idatt2001.model.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player.PlayerBuilder("TestPlayer").build();
    }

    @Test
    @DisplayName("Test addHealth()")
    void addHealth() {
        // Test that health is added to player correctly
        int initialHealth = player.getHealth();
        player.addHealth(50);
        Assertions.assertEquals(initialHealth + 50, player.getHealth());
    }

    @Test
    @DisplayName("Test addScore()")
    void addScore() {
        // Test that score is added to player correctly
        int initialScore = player.getScore();
        player.addScore(100);
        Assertions.assertEquals(initialScore + 100, player.getScore());
    }

    @Test
    @DisplayName("Test addGold()")
    void addGold() {
        // Test that gold is added to player correctly
        int initialGold = player.getGold();
        player.addGold(200);
        Assertions.assertEquals(initialGold + 200, player.getGold());
    }

    @Test
    @DisplayName("Test addToInventory()")
    void addToInventory() {
        // Test that items are added to inventory
        int initialSize = player.getInventory().size();
        player.addToInventory("item1");
        player.addToInventory("item2");
        List<String> inventory = player.getInventory();
        Assertions.assertEquals(initialSize + 2, inventory.size());
        Assertions.assertTrue(inventory.contains("item1"));
        Assertions.assertTrue(inventory.contains("item2"));
    }

    @Test
    @DisplayName("Test checkPositiveIntInput()")
    void checkPositiveIntInput() {
        // Test that exception is thrown when input is negative
        Assertions.assertThrows(IllegalArgumentException.class, () -> player.checkPositiveIntInput("Health", -10));
        Assertions.assertThrows(IllegalArgumentException.class, () -> player.checkPositiveIntInput("Gold", -20));
    }

    @Test
    @DisplayName("Test getName()")
    void getName() {
        // Test that name is returned correctly
        Assertions.assertEquals("TestPlayer", player.getName());
    }
}
