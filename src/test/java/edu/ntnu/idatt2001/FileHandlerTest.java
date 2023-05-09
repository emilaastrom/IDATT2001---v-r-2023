package edu.ntnu.idatt2001;

import edu.ntnu.idatt2001.Model.Action.GoldAction;
import edu.ntnu.idatt2001.Model.Action.HealthAction;
import edu.ntnu.idatt2001.Model.FileHandler;
import edu.ntnu.idatt2001.Model.Link;
import edu.ntnu.idatt2001.Model.Passage;
import edu.ntnu.idatt2001.Model.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FileHandlerTest {

  private Story exampleStory;

  @BeforeEach
  public void setUp(){
    exampleStory = new Story("Test", new Passage("Title of test passage", "Content of test passage"));
    exampleStory.getOpeningPassage().addLink(new Link("Link1", "New link in opening passage"));
    exampleStory.getOpeningPassage().addLink(new Link("Link2", "Another link in opening passage"));
    Passage passage = new Passage("Title of test passage 2", "Content of test passage 2");
    Link link1 = new Link("Test link", "Test reference");
    link1.addAction(new GoldAction(10));
    link1.addAction(new HealthAction(20));
    passage.addLink(link1);
    passage.addLink(new Link("Test link 2", "Test reference 2"));
    passage.addLink(new Link("Test link 3", "Test reference 3"));
    exampleStory.addPassage(passage);
    exampleStory.addPassage(new Passage("Title of test passage 3", "Content of test passage 3"));
    exampleStory.addPassage(new Passage("Title of test passage 4", "Content of test passage 4"));

  }

  @Test
  @DisplayName("Testing writeFile() method")
  public void writeFile(){

    //TODO Update these unit tests to be more comprehensive

    //Testing that the method writeFile() writes the correct story to the file
    Story story = FileHandler.readFile("exampleStory.paths");
    FileHandler.writeFile(exampleStory);

    assertEquals(story.getOpeningPassage(), exampleStory.getOpeningPassage());
  }

 @Test
  @DisplayName("Testing readFile() method")
  public void readFile(){

    //TODO Update these unit tests to be more comprehensive

    Story story = FileHandler.readFile("exampleStory.paths");
    FileHandler.writeFile(story);

    assertEquals(story.getOpeningPassage(), exampleStory.getOpeningPassage());
    assertEquals(story.getTitle(), exampleStory.getTitle());
    assertEquals(story.getPassages().size(), exampleStory.getPassages().size());
    //assertEquals(story.getPassages(), exampleStory.getPassages());
  }


}
