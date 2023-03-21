package edu.ntnu.idatt2001;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class StoryTest {

    private Story story;

    @BeforeEach
    public void setUp() {
        story = new Story("Test", new Passage("Title of test passage", "Content of test passage"));
        story.getOpeningPassage().addLink(new Link("Name of first link", "New link in opening passage"));
        story.getOpeningPassage().addLink(new Link("Name of second link", "Another link in opening passage"));
    }

    @Test
    @DisplayName("Test getTitle() method")
    public void getTitle() {
        //Creating local title with same value as testPassage title, assertEquals
        assertEquals("Test", story.getTitle());

        //Making local title different from testPassage and then ensuring they are not the same.
        assertNotEquals("Test 2", story.getTitle());
    }

    @Test
    @DisplayName("Test getPassages() method")
    public void getPassages() {
        //Creating local title with same value as testPassage title, assertEquals
        assertEquals(0, story.getPassages().size());

        //Making local title different from testPassage and then ensuring they are not the same.
        assertNotEquals(1, story.getPassages().size());
    }

    @Test
    @DisplayName("Test getOpeningPassage() method")
    public void getOpeningPassage() {
        //Creating local title with same value as testPassage title, assertEquals
        assertEquals("Test", story.getOpeningPassage().getTitle());

        //Making local title different from testPassage and then ensuring they are not the same.
        assertNotEquals("Test 2", story.getOpeningPassage().getTitle());
    }

    @Test
    @DisplayName("Test getPassage() method")
    public void getPassage() {
        //Creating local title with same value as testPassage title, assertEquals
        Passage passage = new Passage("Test", "Test");
        Link passageLink = new Link("Test", "Test");
        story.getPassages().put(passageLink, passage);
        assertEquals(passage, story.getPassage(passageLink));

        //Making local title different from testPassage and then ensuring they are not the same.
        Passage passage2 = new Passage("Test2", "Test2");
        Link passageLink2 = new Link("Test2", "Test2");
        story.getPassages().put(passageLink2, passage);
        assertNotEquals(passage2, story.getPassage(passageLink));
    }

    @Test
    @DisplayName("Test addPassage() method")
    public void addPassage() {
        Passage passage = new Passage("Test", "Test");
        story.addPassage(passage);
        assertEquals(passage, story.getPassage(new Link("Test", "Test")));
    }
}
