package edu.ntnu.idatt2001;

import edu.ntnu.idatt2001.Actions.Action;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The class Link.
 */
public class Link {
    private final String text;
    private final String reference;
    private List<Action> actions;

    /**
     * Instantiates a new Link.
     *
     * @param text      the text
     * @param reference the reference
     */
    Link(String text, String reference){
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
    public void addAction(Action action){
        actions.add(action);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Link link = (Link) o;
        return text.equals(link.text) && reference.equals(link.reference) && actions.equals(link.actions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, reference, actions);
    }

    @Override
    public String toString() {
        return "Link{" +
                "text='" + text + '\'' +
                ", reference='" + reference + '\'' +
                ", actions=" + actions +
                '}';
    }
}
