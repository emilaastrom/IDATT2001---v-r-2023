package edu.ntnu.idatt2001.model;

import edu.ntnu.idatt2001.model.action.Action;
import edu.ntnu.idatt2001.model.action.GoldAction;
import edu.ntnu.idatt2001.model.action.HealthAction;
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
    @DisplayName("Testing getActions() method")
    void testingGetActionMethodToCheckIfAddedActionsAreEqualToListInTestLink(){
        //adding similar actions to testLink and local list of actions then ensuring getActions returns the same values.
        actions.add(myGoldAction);
        actions.add(myHealthAction);

        testLink.addAction(myGoldAction);
        testLink.addAction(myHealthAction);

        assertEquals(testLink.getActions(), this.actions);
    }

    @Test
    @DisplayName("Testing addActions() method")
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
    @DisplayName("Testing getReference() method")
    void getReferenceTest(){
        //Creating local reference with same value as testLink reference, assertEquals
        String localTestReference = "Reference string";
        assertEquals(testLink.getReference(), localTestReference);

        //Changing local reference to be different from testLink reference, assertNotEquals
        localTestReference = "Updated and different reference string";
        assertNotEquals(testLink.getReference(), localTestReference);
    }

    @Test
    @DisplayName("Testing getText() method")
    void getText(){
        //Creating local text with same value as testLink, assertEquals
        String localText = "String";
        assertEquals(testLink.getText(), localText);

        //Changing local text value only, assertNotEquals
        localText = "New string value";
        assertNotEquals(testLink.getText(), localText);
    }


}