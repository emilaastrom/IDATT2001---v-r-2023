package edu.ntnu.idatt2001.Action;

import edu.ntnu.idatt2001.Link;
import edu.ntnu.idatt2001.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ActionTest {
    GoldAction myGoldAction;
    HealthAction myHealthAction;
    InventoryAction myInventoryAction;
    ScoreAction myScoreAction;
    Link myTestLink;
    Player myTestPlayer;

    @BeforeEach
    void setUp(){
        myTestLink = new Link("Text string", "Reference string");
        myTestPlayer = new Player.PlayerBuilder("Test player").health(100).gold(100).score(100).build();
        myGoldAction = new GoldAction(50);
        myHealthAction = new HealthAction(50);
        myInventoryAction = new InventoryAction("Dragon dagger(p++)");
        myScoreAction = new ScoreAction(50);
    }


    @Test
    @DisplayName("Testing getActions() using all types of actions")
    void testingGetActionsMethodWithDifferentActionsAddedToTheList(){
        ArrayList<Action> emptyActionList = new ArrayList<>();
        assertEquals(myTestLink.getActions(),emptyActionList );

        myTestLink.addAction(myGoldAction);
        assertNotNull(myTestLink.getActions());

        assertEquals(myGoldAction, myTestLink.getActions().get(0));

        myTestLink.addAction(myHealthAction);
        assertEquals(myHealthAction, myTestLink.getActions().get(1));

        myTestLink.addAction(myInventoryAction);
        assertEquals(myInventoryAction, myTestLink.getActions().get(2));

        myTestLink.addAction(myScoreAction);
        assertEquals(myScoreAction, myTestLink.getActions().get(3));
    }


    @Test
    @DisplayName("Testing execute()")
    void testingExecuteMethodUsingDifferentActions(){
        //Adding actions to the link in an ordered list, which can be used to ensure the correct action is executed.
        myTestLink.addAction(myGoldAction);
        myTestLink.addAction(myHealthAction);
        myTestLink.addAction(myInventoryAction);
        myTestLink.addAction(myScoreAction);

        //ensuring myTestPlayer has the initial gold value of 100
        assertEquals(100, myTestPlayer.getGold());
        assertNotEquals(150, myTestPlayer.getGold());

        //Using the first action (adding 50 gold) then ensuring the value is correct
        myTestLink.getActions().get(0).Execute(myTestPlayer);
        assertNotEquals(100, myTestPlayer.getGold());
        assertEquals(150, myTestPlayer.getGold());

        //ensuring myTestPlayer has the initial health value of 100
        assertEquals(100, myTestPlayer.getHealth());
        assertNotEquals(150, myTestPlayer.getHealth());

        //Using the second action (adding 50 health) then ensuring the value is correct
        myTestLink.getActions().get(1).Execute(myTestPlayer);
        assertNotEquals(100, myTestPlayer.getHealth());
        assertEquals(150, myTestPlayer.getHealth());

        //ensuring myTestPlayer has an empty inventory
        assertEquals(0, myTestPlayer.getInventory().size());
        assertNotEquals(1, myTestPlayer.getInventory().size());

        //Using the third action (adding a dragon dagger(p++) to inventory), then making sure it is added correctly
        myTestLink.getActions().get(2).Execute(myTestPlayer);
        assertEquals(1, myTestPlayer.getInventory().size());
        assertNotEquals(0, myTestPlayer.getInventory().size());
        assertEquals("Dragon dagger(p++)", myTestPlayer.getInventory().get(0));

        //ensuring myTestPlayer has the initial score value of 100
        assertEquals(100, myTestPlayer.getScore());

        //Using the fourth action (adding 50 score) then ensuring the value is correct
        myTestLink.getActions().get(3).Execute(myTestPlayer);
        assertEquals(150, myTestPlayer.getScore());
        assertNotEquals(100, myTestPlayer.getScore());
    }
}
