import edu.ntnu.idatt2001.*;
import edu.ntnu.idatt2001.Goals.Goal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    Passage testPassage;
    Story testStory;
    List<Goal> goalList;
    Player player;
    Game myGame;


    @BeforeEach
    @DisplayName("Initialize variables with test data")
    void init(){
    testPassage = new Passage("First passage in test", "Content of first passage");
    testStory = new Story("Test story", testPassage);
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
    }

    @Test
    @DisplayName("")
    void begin() {
    }

    @Test
    @DisplayName("")
    void go() {
    }
}