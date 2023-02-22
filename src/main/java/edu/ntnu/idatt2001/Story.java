package edu.ntnu.idatt2001;

import java.util.Map;

/**
 * The type Story.
 */
public class Story {

    final private String title;
    private Map<Link, Passage> passages;
    private Passage openingPassage;

    /**
     * Instantiates a new Story.
     *
     * @param title          the title
     * @param openingPassage the opening passage
     */
    public Story(String title, Passage openingPassage){
        this.title = title;
        this.openingPassage = openingPassage;
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
    public Passage getPassage (Link link){
        return passages.get(link);
    }

    /**
     * Add passage.
     *
     * @param passage the passage
     */
    public void addPassage(Passage passage){
        Link newLink = new Link(passage.getTitle(), passage.getTitle());
        passages.put(newLink, passage);
    }

}
