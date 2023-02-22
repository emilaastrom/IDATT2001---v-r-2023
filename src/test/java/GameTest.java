import edu.ntnu.idatt2001.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    Passage testPassage = new Passage("First passage in test", "Content of first passage");
    Story testStory = new Story("Test story", testPassage);
    List<Goal> goalList = new ArrayList<>();

    Player player = new Player("Kåre", 100, 50, 25);
    Game myGame = new Game(player,testStory, goalList);

    @Test
    void getPlayerReturnsPlayer() {
        assertEquals(player, myGame.getPlayer());
    }

    @Test
    void getPlayerDoesNotReturnStory() {
        assertNotEquals(player, myGame.getStory());
    }


    @Test
    void getStoryReturnsCorrectStory() {
        assertEquals(testStory, myGame.getStory());
        assertNotEquals(testStory, myGame.getGoals());
        assertNotEquals(testStory, myGame.getClass());
        assertNotEquals(testStory, myGame.getPlayer());

    }

    @Test
    void getGoals() {
    }

    @Test
    void begin() {
    }

    @Test
    void go() {
    }
}