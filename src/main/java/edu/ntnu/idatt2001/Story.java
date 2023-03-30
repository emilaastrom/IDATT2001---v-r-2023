package edu.ntnu.idatt2001;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    public boolean removePassage(Link linkToBeRemoved){
        Passage passageToBeRemoved = passages.get(linkToBeRemoved);
        boolean foundMultipleLinksToPassage = false;
        boolean successfullyRemoved = false;

        for (int i=0; i < passages.size(); i++){

            for (Map.Entry<Link, Passage> entry : passages.entrySet()){
                Link currentLink = entry.getKey();
                Passage currentPassage = entry.getValue();

                List<Link> currentPassageLinks = currentPassage.getLinks();
                for (Link linkInList : currentPassageLinks){
                    if (linkInList.equals(currentLink) && currentPassage != passageToBeRemoved){
                        foundMultipleLinksToPassage = true;
                    }
                }
            }
        }

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
    public ArrayList<Link> getBrokenLinks(){
        ArrayList<Link> deadLinks = new ArrayList<>();

        for (Map.Entry<Link, Passage> entry : passages.entrySet()){
            Link currentLink = entry.getKey();
            Passage currentPassage = entry.getValue();

            List<Link> currentPassageLinks = currentPassage.getLinks();
            for (Link linkInList : currentPassageLinks){
                if (passages.containsKey(linkInList) && passages.containsValue(null)){
                    deadLinks.add(linkInList);
                }
            }
        }
        return deadLinks;
    }

}
