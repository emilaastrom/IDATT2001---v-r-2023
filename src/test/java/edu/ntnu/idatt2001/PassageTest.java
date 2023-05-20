package edu.ntnu.idatt2001;

import edu.ntnu.idatt2001.model.Link;
import edu.ntnu.idatt2001.model.Passage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PassageTest {

    Passage testPassage;
    Link testLink;

    @BeforeEach
    @DisplayName("Initialize variables with test data")
    void setUp() {
        testPassage = new Passage("Test passage", "Test content");
        testLink = new Link("Test link", "Test reference");
    }



    @Test
    @DisplayName("Testing getTitle() method")
    void getTitle() {
        //Creating local title with same value as testPassage title, assertEquals
        String localTitle = "Test passage";
        assertEquals(localTitle, testPassage.getTitle());

        //Making local title different from testPassage and then ensuring they are not the same.
        localTitle = "Test passage 2";
        assertNotEquals(localTitle, testPassage.getTitle());

    }

    @Test
    @DisplayName("Testing getContent() method")
    void getContent() {
        //Creating local content with same value as testPassage content, assertEquals
        String localContent = "Test content";
        assertEquals(localContent, testPassage.getContent());

        //Making local content different from testPassage and then ensuring they are not the same.
        localContent = "Test content 2";
        assertNotEquals(localContent, testPassage.getContent());
    }

    @Test
    @DisplayName("Testing getLinks() method")
    void getLinks() {
        //Creating local list of links with same value as testPassage links, assertEquals
        Link localLink = new Link("Test link", "Test reference");
        List<Link> localLinks = new ArrayList<>();
        assertEquals(localLinks, testPassage.getLinks());

        localLinks.add(localLink);
        testPassage.addLink(localLink);
        assertEquals(localLinks, testPassage.getLinks());

        //Creating another local link and adding it to the local list of links, then ensuring they are not the same.
        Link localLink2 = new Link("Test link 2", "Test reference2");
        localLinks.add(localLink2);
        assertNotEquals(localLinks, testPassage.getLinks());
    }

    @Test
    @DisplayName("Testing addLink() method")
    void addLink() {
        //Adding link to both testPassage and the local test list of links to ensure they both receive the link.
        testPassage.addLink(testLink);
        List<Link> localLinks = new ArrayList<>();
        localLinks.add(testLink);
        assertEquals(testPassage.getLinks(), localLinks);

        //Adding another link to testPassage and NOT the local test list of links, then ensuring they are not the same.
        Link testLink2 = new Link("Test link 2", "Test reference2");
        testPassage.addLink(testLink2);
        assertNotEquals(testPassage.getLinks(), localLinks);
    }

    @Test
    @DisplayName("Testing hasLinks() method")
    void hasLinks() {
        //Testing if testPassage has links, assertFalse
        testPassage = new Passage("Test passage", "Test content");
        assertFalse(testPassage.hasLinks());

        //Testing if testPassage has links, assertTrue
        testPassage.addLink(testLink);
        assertTrue(testPassage.hasLinks());
    }
}