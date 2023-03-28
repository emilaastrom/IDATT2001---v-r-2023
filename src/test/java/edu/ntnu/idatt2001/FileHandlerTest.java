package edu.ntnu.idatt2001;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FileHandlerTest {

  private Story exampleStory;

  @BeforeEach
  public void setUp(){
    exampleStory = new Story("Test", new Passage("Title of test passage", "Content of test passage"));
    exampleStory.getOpeningPassage().addLink(new Link("Link1", "New link in opening passage"));
    exampleStory.getOpeningPassage().addLink(new Link("Link2", "Another link in opening passage"));
    Passage passage = new Passage("Title of test passage 2", "Content of test passage 2");
    passage.addLink(new Link("Test", "Test"));
    exampleStory.addPassage(passage);
    exampleStory.addPassage(new Passage("Title of test passage 3", "Content of test passage 3"));
    exampleStory.addPassage(new Passage("Title of test passage 4", "Content of test passage 4"));

  }

  @Test
  @DisplayName("Testing writeFile() method")
  public void writeFile(){
    //Testing that the method writeFile() writes the correct story to the file
    FileHandler.writeFile(exampleStory);
  }


}
