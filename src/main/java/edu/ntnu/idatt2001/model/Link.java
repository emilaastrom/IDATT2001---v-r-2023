package edu.ntnu.idatt2001.model;

import edu.ntnu.idatt2001.model.action.Action;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * The class Link.
 */
public class Link {
  private final String text;
  private final String reference;
  private final List<Action> actions;

  /**
   * Instantiates a new Link.
   *
   * @param text      the text
   * @param reference the reference
   */
  public Link(String text, String reference, Action[] actions) {
    this.text = text;
    this.reference = reference;
    this.actions = new ArrayList<>();
    this.actions.addAll(Arrays.asList(actions));
  }

  /**
   * Instantiates a new Link.
   *
   * @param text      the text
   * @param reference the reference
   */
  public Link(String text, String reference) {
    this.text = text;
    this.reference = reference;
    actions = new ArrayList<>();
  }

  /**
   * Gets text.
   *
   * @return the text
   */
  public String getText() {
    return text;
  }

  /**
   * Gets reference.
   *
   * @return the reference
   */
  public String getReference() {
    return reference;
  }

  /**
   * Gets actions.
   *
   * @return the actions
   */
  public List<Action> getActions() {
    return actions;
  }

  /**
   * Add action.
   *
   * @param action the action
   */
  public void addAction(Action action) {
    actions.add(action);
  }

  /**
   * equals method for Link.
   *
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Link link = (Link) o;
    return text.equals(link.text)
            && reference.equals(link.reference)
            && actions.equals(link.actions);
  }

  /**
   * hashCode method for Link.
   *
   */
  @Override
  public int hashCode() {
    return Objects.hash(text, reference, actions);
  }

  /**
   * toString method for Link.
   *
   */
  @Override
  public String toString() {
    return "Link{"
            + "text='"
            + text
            + '\''
            + ", reference='"
            + reference
            + '\''
            + ", actions="
            + actions
            + '}';
  }
}
