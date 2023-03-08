package edu.ntnu.idatt2001;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StoryTest {

    private Story story;

    @BeforeEach
    public void setUp() {
        story = new Story("Test", new Passage("Test", "Test"));
    }

    @Test
    @DisplayName("Test getTitle() method")
    public void getTitle() {
        assertEquals("Test", story.getTitle());
    }

    @Test
    @DisplayName("Test getPassages() method")
    public void getPassages() {
        assertEquals(0, story.getPassages().size());
    }

    @Test
    @DisplayName("Test getOpeningPassage() method")
    public void getOpeningPassage() {
        assertEquals("Test", story.getOpeningPassage().getTitle());
    }

    @Test
    @DisplayName("Test getPassage() method")
    public void getPassage() {
        Passage passage = new Passage("Test", "Test");
        story.getPassages().put(new Link("Test", "Test"), passage);
        assertEquals(passage, story.getPassage(new Link("Test", "Test")));
    }

    @Test
    @DisplayName("Test addPassage() method")
    public void addPassage() {
        Passage passage = new Passage("Test", "Test");
        story.addPassage(passage);
        assertEquals(passage, story.getPassage(new Link("Test", "Test")));
    }
}
