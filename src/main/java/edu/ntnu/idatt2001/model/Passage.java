package edu.ntnu.idatt2001.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The class Passage.
 */
public class Passage {
  private final String title;
  private final String content;
  private final List<Link> links;

  /**
   * Instantiates a new Passage.
   *
   * @param title   the title
   * @param content the content
   */
  public Passage(String title, String content) {
    this.title = title;
    this.content = content;
    links = new ArrayList<>();
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
   * Gets content.
   *
   * @return the content
   */
  public String getContent() {
    return content;
  }

  /**
   * Gets links.
   *
   * @return the links
   */
  public List<Link> getLinks() {
    return links;
  }

  /**
   * Get links in a formatted string for use in FileHandler.
   *
   * @return the string
   */
  public String getLinksFormatted() {
    StringBuilder outputString = new StringBuilder();
    // For each link, append the text and reference to the outputString
    links.stream().forEach(link -> {
      outputString.append("[")
              .append(link.getText())
              .append("](")
              .append(link.getReference())
              .append(")");

      // If the link has actions, append them to the outputString
      if (!link.getActions().isEmpty()) {
        outputString.append("{");
        link.getActions().stream()
                .map(action -> action.getType() + " " + action.getAmount() + ";")
                .forEach(outputString::append);
        outputString.append("}");
      }

      outputString.append("\n");
    });

    return String.valueOf(outputString);
  }

  /**
   * Add link.
   *
   * @param link the link
   */
  public void addLink(Link link) {
    links.add(link);
  }


  /**
   * Has links.
   *
   * @return the boolean
   */
  public boolean hasLinks() {
    return !links.isEmpty();
  }

  /**
   * Equals boolean for Passage.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Passage passage = (Passage) o;
    return title.equals(passage.title)
            && content.equals(passage.content)
            && Objects.equals(links, passage.links);
  }

  /**
   * Hash code int for Passage.
   */
  @Override
  public int hashCode() {
    return Objects.hash(title, content, links);
  }

  /**
   * To string for Passage.
   */
  @Override
  public String toString() {
    return "Passage{"
            + "title='"
            + title
            + '\''
            + ", content='"
            + content
            + '\''
            + ", links="
            + links
            + '}';
  }
}
