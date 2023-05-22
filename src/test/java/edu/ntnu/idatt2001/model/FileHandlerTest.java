package edu.ntnu.idatt2001.model;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.idatt2001.handlers.FileHandler;
import edu.ntnu.idatt2001.model.action.GoldAction;
import edu.ntnu.idatt2001.model.action.HealthAction;
import edu.ntnu.idatt2001.model.action.ScoreAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FileHandlerTest {

  private Story exampleStory;

  @BeforeEach
  public void setUp(){
    exampleStory = new Story("Test", new Passage("Title of opening test passage", "Content of opening test passage"));
    exampleStory.getOpeningPassage().addLink(new Link("Link1", "A link in opening passage, no actions"));
    exampleStory.getOpeningPassage().addLink(new Link("Link2", "Another link in opening passage, no actions"));

    Passage passageTwo = new Passage("Title of test passage 2", "Content of test passage 2");
    Link link1 = new Link("Test link", "Test reference");
    link1.addAction(new GoldAction(10));
    link1.addAction(new HealthAction(20));
    link1.addAction(new ScoreAction(30));
    passageTwo.addLink(link1);
    passageTwo.addLink(new Link("Test link 2", "Test reference 2"));
    passageTwo.addLink(new Link("Test link 3", "Test reference 3"));
    exampleStory.addPassage(passageTwo);

    exampleStory.addPassage(new Passage("Title of test passage 3", "Content of test passage 3"));
    exampleStory.addPassage(new Passage("Title of test passage 4", "Content of test passage 4"));

  }

  @Test
  @DisplayName("Testing openGame() method")
  public void openGame(){
  }

  @Test
  @DisplayName("Testing openStaticGame() method")
  public void openStaticGame(){

  }

  @Test
  @DisplayName("Testing writeFile() method")
  public void writeFile(){

    //TODO Update these unit tests to be more comprehensive

    //Testing that the method writeFile() writes the correct story to the file
    Story story = FileHandler.readFile("exampleStory.paths");
    FileHandler.writeFile(exampleStory, "exampleStory");

    assertEquals(story.getOpeningPassage(), exampleStory.getOpeningPassage());
  }

 @Test
  @DisplayName("Testing readFile() method")
  public void readFile(){

    //TODO Update these unit tests to be more comprehensive

    Story story = FileHandler.readFile("exampleStory.paths");
    FileHandler.writeFile(story, "exampleStory");

    assertEquals(story.getOpeningPassage(), exampleStory.getOpeningPassage());
    assertEquals(story.getTitle(), exampleStory.getTitle());
    assertEquals(story.getPassages().size(), exampleStory.getPassages().size());
//    assertEquals(story.getPassages(), exampleStory.getPassages());
  }


}
