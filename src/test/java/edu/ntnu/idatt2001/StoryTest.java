package edu.ntnu.idatt2001;

import edu.ntnu.idatt2001.Model.Link;
import edu.ntnu.idatt2001.Model.Passage;
import edu.ntnu.idatt2001.Model.Story;
import org.junit.jupiter.api.*;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class StoryTest {

    private Story story;

    @BeforeEach
    public void setUp() {
        story = new Story("Test", new Passage("Title of testPassage", "Content of testPassage"));
        story.getOpeningPassage().addLink(new Link("Name of first link", "New link in opening passage"));
        story.getOpeningPassage().addLink(new Link("Name of second link", "Another link in opening passage"));
    }

    @Test
    @DisplayName("Test getTitle() method")
    public void getTitle() {
        //Ensuring getTitle() returns the same value as the locally given title.
        assertEquals("Test", story.getTitle());

        //Making local title different from testPassage and then ensuring they are not the same.
        assertNotEquals("Test 2", story.getTitle());
    }

    @Test
    @DisplayName("Test getPassages() method")
    public void getPassages() {
        //Making sure the size of the getPassages()
        assertEquals(0, story.getPassages().size());

        //Making local title different from testPassage and then ensuring they are not the same.
        assertNotEquals(1, story.getPassages().size());
    }

    @Test
    @DisplayName("Test getOpeningPassage() method")
    public void getOpeningPassage() {
        //Creating local title with same value as testPassage title, assertEquals
        assertEquals("Title of testPassage", story.getOpeningPassage().getTitle());

        //Making local title different from testPassage and then ensuring they are not the same.
        assertNotEquals("Test 2", story.getOpeningPassage().getTitle());
    }

    @Test
    @DisplayName("Test getPassage() method")
    public void getPassage() {
        //Creating local passage with same values as testPassage and ensuring they are the same.
        Passage passage = new Passage("Title of p 1", "content");
        Link passageLink = new Link("l text", "Title of p 2");
        story.addPassage(passage);
        assertNotEquals(passage, story.getPassage(passageLink));

        //Making local passage with a different title and then ensuring it is not the same as the one in testPassage.
        Passage passage2 = new Passage("Title of p 2", "content 2");
        Link passageLink2 = new Link("l text 2", "Test2");
        story.addPassage(passage2);
        assertEquals(passage2, story.getPassage(passageLink));
    }

    @Test
    @DisplayName("Test addPassage() method")
    public void addPassage() {
        Passage passage = new Passage("Test", "Test");
        story.addPassage(passage);
        assertEquals(passage, story.getPassage(new Link("Test", "Test")));
    }

    @Test
    @DisplayName("Test removePassage() method")
    public void removePassage(){
        //Creating an example passage and adding it to the story.
        Passage passageOne = new Passage("passageOneTitle","Test");
        story.addPassage(passageOne);

        //Creating a link to passageOne.
        Link linkToPassageOne = new Link("Link to passageOne","passageOneTitle");

        //Using the link to passageOne to remove the passage from the story. True = successfully removed.
        assertTrue(story.removePassage(linkToPassageOne));

        //Creating new passage and adding the link to the first passage (passageOne) to passageTwo.
        Passage passageTwo = new Passage("passageWithLink","passage");
        passageTwo.addLink(linkToPassageOne);

        //Adding both the passages to the story
        story.addPassage(passageOne);
        story.addPassage(passageTwo);

        //Ensuring that passageOne is NOT removed from the story since it is linked to from passageTwo. False = not removed.
        assertFalse(story.removePassage(linkToPassageOne));
    }

    @Test
    @DisplayName("Test getBrokenLinks() method")
    public void getBrokenLinks(){
        //Creating two example passages.
        Passage examplePassageOne = new Passage("ExamplePassageOneTitle", "Content of the examplePassage");
        Passage examplePassageTwo = new Passage("ExamplePassageTwoTitle", "Content of the examplePassage");

        //Creating different links, some of which are broken.
        Link functionalLink = new Link("Functional link", "ExamplePassageTwoTitle");
        Link brokenLinkOne = new Link("Broken link", "Broken reference");
        Link brokenLinkTwo = new Link("Another broken link", "Another broken reference ");

        //Adding all links to the example passages.
        examplePassageOne.addLink(functionalLink);
        examplePassageOne.addLink(brokenLinkOne);
        examplePassageTwo.addLink(brokenLinkTwo);

        //Adding the example passages to the story.
        story.addPassage(examplePassageOne);
        story.addPassage(examplePassageTwo);

        //Creating an ArrayList of the links we expect to be broken.
        ArrayList<Link> expectedBrokenLinks = new ArrayList<>();
        expectedBrokenLinks.add(brokenLinkOne);
        expectedBrokenLinks.add(brokenLinkTwo);
        //Ensuring that the expectedBrokenLinks ArrayList is the same as the ArrayList returned by the getBrokenLinks() method.
        assertEquals(expectedBrokenLinks, story.getBrokenLinks());

        //Creating an ArrayList of a combination of functional and broken links.
        ArrayList<Link> combinationOfFunctionalAndBrokenLinks = new ArrayList<>();
        combinationOfFunctionalAndBrokenLinks.add(functionalLink);
        combinationOfFunctionalAndBrokenLinks.add(brokenLinkOne);
        combinationOfFunctionalAndBrokenLinks.add(brokenLinkTwo);
        //Ensuring that the combinationOfFunctionalAndBrokenLinks ArrayList is NOT the same as the ArrayList returned by the getBrokenLinks() method.
        assertNotEquals(combinationOfFunctionalAndBrokenLinks, story.getBrokenLinks());
    }

}
