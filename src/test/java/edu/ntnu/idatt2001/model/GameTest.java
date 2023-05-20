package edu.ntnu.idatt2001.model;

import edu.ntnu.idatt2001.model.goal.Goal;
import edu.ntnu.idatt2001.model.goal.GoldGoal;
import edu.ntnu.idatt2001.model.goal.HealthGoal;
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
    Game myStaticGame;
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
        myStaticGame = Game.getInstance();
        myStaticGame.setPlayer(player);
        myStaticGame.setStory(testStory);
        myStaticGame.setGoals(goalList);
    }

    @Test
    @DisplayName("Ensure that method getPlayer() returns correct variable")
    void getPlayerReturnsPlayer() {
        //Testing that the method getPlayer() returns the correct variable
        assertEquals(player, myStaticGame.getPlayer());
    }

    @Test
    @DisplayName("getInstance()")
    void getInstance() {
        //Testing that the method getInstance() returns the same instance as the one that was created initially
        assertEquals(myStaticGame, Game.getInstance());
        assertNotEquals(new Game(), Game.getInstance());
    }

    @Test
    @DisplayName("Testing goSilent() function, and ensuring it does not affect passageHistory/linkHistory")
    void goSilent() {
        //Testing that the method goSilent() returns the passage corresponding to the given link
        assertEquals(testStory.getPassage(testLink), myStaticGame.goSilent(testLink));
        //Checking if passageHistory is empty
        assertEquals(0, myStaticGame.getPassageHistory().size());
        //Checking if linkHistory is empty
        assertEquals(0, myStaticGame.getLinkHistory().size());

        //Using regular go() function and seeing if passageHistory/linkHistory is updated
        myStaticGame.go(testLink);
        assertEquals(1, myStaticGame.getPassageHistory().size());
        assertEquals(1, myStaticGame.getLinkHistory().size());

        //Using goSilent() function and making sure passageHistory/linkHistory is not updated
        myStaticGame.goSilent(testLink);
        assertEquals(1, myStaticGame.getPassageHistory().size());
        assertEquals(1, myStaticGame.getLinkHistory().size());
    }

    @Test
    @DisplayName("Testin restartGame() function to make sure a new player has been created and that the score, health and gold has been reset")
    void restartGame() {
        Player initialPlayer = myStaticGame.getPlayer();
        initialPlayer.addScore(5);
        initialPlayer.addGold(5);
        initialPlayer.addHealth(5);

        int initScore = initialPlayer.getScore();
        int initHealth = initialPlayer.getHealth();
        int initGold = initialPlayer.getGold();

        myStaticGame.restartGame();

        Player newPlayer = myStaticGame.getPlayer();
        assertNotEquals(initialPlayer, newPlayer);
        assertNotEquals(initScore, newPlayer.getScore());
        assertNotEquals(initHealth, newPlayer.getHealth());
        assertNotEquals(initGold, newPlayer.getGold());
    }

    @Test
    @DisplayName("Ensure that getStory() returns correct story")
    void getStoryReturnsCorrectStory() {
        //Testing that the method getStory() returns the same story as the one that was added in the constructor
        assertEquals(testStory, myStaticGame.getStory());
    }

    @Test
    @DisplayName("Testing that getGoals method returns correct goals")
    void getGoals() {
        //Testing that the method getGoals() returns the same goals as the ones that were added in the constructor
        assertEquals(goalList, myStaticGame.getGoals());
    }

    @Test
    @DisplayName("Testing begin method")
    void begin() {
        //Testing that the method begin() returns the same passage as the one that was added in the constructor
        assertEquals(testOpeningPassage, myStaticGame.begin());
    }

    @Test
    @DisplayName("Testing go method")
    void go() {
        //Testing that the method go() returns the passage corresponding to the given link
        assertEquals(testStory.getPassage(testLink), myStaticGame.go(testLink));
    }
}