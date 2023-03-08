package edu.ntnu.idatt2001;

import edu.ntnu.idatt2001.Actions.Action;
import edu.ntnu.idatt2001.Actions.GoldAction;
import edu.ntnu.idatt2001.Actions.HealthAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LinkTest {

    Link testLink;
    GoldAction myGoldAction;
    HealthAction myHealthAction;
    List<Action> actions;

    @BeforeEach
    void setUp() {
        //setting up default test conditions that are reset before each test
        testLink = new Link("String",  "Reference string");

        myGoldAction = new GoldAction(200);
        myHealthAction = new HealthAction(25);

        actions = new ArrayList<>();
    }

    @Test
    @DisplayName("getActions()")
    void testingGetActionMethodToCheckIfAddedActionsAreEqualToListInTestLink(){
        //adding similar actions to testLink and local list of actions then ensuring getActions returns the same values.
        actions.add(myGoldAction);
        actions.add(myHealthAction);

        testLink.addAction(myGoldAction);
        testLink.addAction(myHealthAction);

        assertEquals(testLink.getActions(), this.actions);
    }

    @Test
    @DisplayName("addActions()")
    void testAddActionsAddsCorrectObject(){
        //Adding action to both testLink and the local test list of actions to ensure they both receive the actions.
        testLink.addAction(myHealthAction);
        this.actions.add(myHealthAction);
        assertEquals(testLink.getActions(), actions);

        //Adding another action to testLink and NOT the local test list of actions, then ensuring they are not the same.
        testLink.addAction(myGoldAction);
        assertNotEquals(testLink.getActions(), actions);
    }

    @Test
    @DisplayName("getReference()")
    void getReferenceTest(){
        //Creating local reference with same value as testLink reference, assertEquals
        String localTestReference = "Reference string";
        assertEquals(testLink.getReference(), localTestReference);

        //Changing local reference to be different from testLink reference, assertNotEquals
        localTestReference = "Updated and different reference string";
        assertNotEquals(testLink.getReference(), localTestReference);

    }


}