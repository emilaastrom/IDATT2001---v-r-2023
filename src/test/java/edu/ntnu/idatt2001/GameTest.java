package edu.ntnu.idatt2001;

import edu.ntnu.idatt2001.Goal.Goal;
import edu.ntnu.idatt2001.Goal.GoldGoal;
import edu.ntnu.idatt2001.Goal.HealthGoal;
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
        //Creating test data
        testPassage = new Passage("First passage in test", "Content of first passage");
        testLink = new Link("testText","testReference");
        testPassage.addLink(testLink);
        testStory = new Story("Test story", testOpeningPassage);
        testStory.addPassage(testPassage);
        testGoldGoal = new GoldGoal(1);
        testHealthGoal = new HealthGoal(6);
        goalList = new ArrayList<>();
        player = new Player.PlayerBuilder("Kåre").build();
        myGame = new Game(player,testStory, goalList);
    }

    @Test
    @DisplayName("Ensure that method getPlayer() returns correct variable")
    void getPlayerReturnsPlayer() {
        //Testing that the method getPlayer() returns the correct variable
        assertEquals(player, myGame.getPlayer());
    }

    @Test
    @DisplayName("Ensure that getStory() returns correct story")
    void getStoryReturnsCorrectStory() {
        //Testing that the method getStory() returns the same story as the one that was added in the constructor
        assertEquals(testStory, myGame.getStory());
    }

    @Test
    @DisplayName("Testing that getGoals method returns correct goals")
    void getGoals() {
        //Testing that the method getGoals() returns the same goals as the ones that were added in the constructor
        assertEquals(goalList, myGame.getGoals());
    }

    @Test
    @DisplayName("Testing begin method")
    void begin() {
        //Testing that the method begin() returns the same passage as the one that was added in the constructor
        assertEquals(testOpeningPassage, myGame.begin());
    }

    @Test
    @DisplayName("Testing go method")
    void go() {
        //Testing that the method go() returns the passage corresponding to the given link
        assertEquals(testStory.getPassage(testLink), myGame.go(testLink));
    }
}