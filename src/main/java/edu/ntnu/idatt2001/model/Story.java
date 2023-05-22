package edu.ntnu.idatt2001.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The type Story.
 */
public class Story {

  private final String title;
  private final Map<Link, Passage> passages;
  private Passage openingPassage;

  /**
   * Instantiates a new Story.
   *
   * @param title          the title
   * @param openingPassage the opening passage
   */
  public Story(String title, Passage openingPassage) {
    this.title = title;
    this.openingPassage = openingPassage;
    this.passages = new HashMap<>();
  }

  /**
   * Gets title.
   *
   * @return the title
   */
  public String getTitle() {
    return title;
  }

  /**
   * Gets passages.
   *
   * @return the passages
   */
  public Map<Link, Passage> getPassages() {
    return passages;
  }

  /**
   * Gets opening passage.
   *
   * @return the opening passage
   */
  public Passage getOpeningPassage() {
    return openingPassage;
  }

  /**
   * Get passage passage.
   *
   * @param link the link
   * @return the passage
   */
  public Passage getPassage(Link link) {
    String reference = link.getReference();
    //find passage with title equal to link reference
    return passages.values().stream()
            .filter(passage -> passage.getTitle().equals(reference))
            .findFirst()
            .orElse(null);
  }

  /**
   * Add passage.
   *
   * @param passage the passage
   */
  public void addPassage(Passage passage) {
    //check if passage already exists
    if (passages.containsValue(passage)) {
      throw new IllegalArgumentException("Passage already exists in story");
    }
    //make new link with title equal to passage title
    Link newLink = new Link(passage.getTitle(), passage.getTitle());
    //adds passage to passages map
    passages.put(newLink, passage);
  }

  /**
   * Sets opening passage.
   *
   * @param openingPassage the opening passage
   */
  public void setOpeningPassage(Passage openingPassage) {
    this.openingPassage = openingPassage;
  }

  /**
   * Removes a given passage, if it is not referenced from another passage in the story.
   *
   * @param linkToBeRemoved the link, referencing the passage to be removed
   */
  public boolean removePassage(Link linkToBeRemoved) {
    Passage passageToBeRemoved = passages.get(linkToBeRemoved);
    boolean foundMultipleLinksToPassage = false;
    boolean successfullyRemoved = false;

    //check if there are multiple links to the passage to be removed
    for (int i = 0; i < passages.size(); i++) {
      //iterate through all passages
      for (Map.Entry<Link, Passage> entry : passages.entrySet()) {
        Passage currentPassage = entry.getValue();

        List<Link> currentPassageLinks = currentPassage.getLinks();
        //check if the current passage has a link to the passage to be removed
        for (Link linkInCurrentPassageList : currentPassageLinks) {
          if ((linkInCurrentPassageList.equals(linkToBeRemoved))
                  && (currentPassage != passageToBeRemoved)) {
            foundMultipleLinksToPassage = true;
            break;
          }
        }
      }
    }

    //if there are no links to the passage, remove it
    if (!foundMultipleLinksToPassage) {
      passages.remove(linkToBeRemoved);
      successfullyRemoved = true;
    }

    return successfullyRemoved;
  }

  /**
   * gets a list of broken links.
   *
   * @return the array list containing the broken links
   */
  public ArrayList<Link> getBrokenLinks() {
    //checks if the link reference is equal to any of the passages titles
    return passages.values().stream()
      //gets all the passages
      .flatMap(passage -> passage.getLinks().stream())
      //gets all the links
      .filter(linkToCompare -> passages.keySet().stream()
      //if none of the passages titles are equal to the link reference, add it to the list
      .noneMatch(inputLink -> linkToCompare.getReference().equals(inputLink.getReference())))
      .collect(Collectors.toCollection(ArrayList::new));
  }
}
