package edu.ntnu.idatt2001;

import edu.ntnu.idatt2001.*;
import edu.ntnu.idatt2001.Goals.Goal;
import edu.ntnu.idatt2001.Goals.GoldGoal;
import edu.ntnu.idatt2001.Goals.HealthGoal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    Passage testOpeningPassage;
    Passage testPassage;
    Story testStory;
    HealthGoal testHealthGoal;
    GoldGoal testGoldGoal;
    List<Goal> goalList;
    Player player;
    Game myGame;
    Link testLink;



    @BeforeEach
    @DisplayName("Initialize variables with test data")
    void init(){
    testPassage = new Passage("First passage in test", "Content of first passage");
    testLink = new Link("testText","testReference");
    testPassage.addLink(testLink);
    testStory = new Story("Test story", testOpeningPassage);
    testStory.addPassage(testPassage);
    testGoldGoal = new GoldGoal(1);
    testHealthGoal = new HealthGoal(6);
    goalList = new ArrayList<>();
    player = new Player("Kåre", 100, 50, 25);
    myGame = new Game(player,testStory, goalList);
    }

    @Test
    @DisplayName("Ensure that method getPlayer() returns correct variable")
    void getPlayerReturnsPlayer() {
        assertEquals(player, myGame.getPlayer());
    }

    @Test
    @DisplayName("Ensure that getStory() returns correct story")
    void getStoryReturnsCorrectStory() {
        assertEquals(testStory, myGame.getStory());
    }

    @Test
    @DisplayName("Testing that getGoals method returns correct goals")
    void getGoals() {
        assertEquals(goalList, myGame.getGoals());
    }

    @Test
    @DisplayName("Testing begin method")
    void begin() {
        assertEquals(testOpeningPassage, myGame.begin());
    }

    @Test
    @DisplayName("Testing go method")
    void go() {
        assertEquals(testStory.getPassage(testLink), myGame.go(testLink));
    }
}