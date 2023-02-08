package edu.ntnu.idatt2001;

import java.util.Map;

public class Story {

    final private String title;
    private Map<Link, Passage> passages;
    private Passage openingPassage;

    Story(String title, Passage openingPassage){
        this.title = title;
        this.openingPassage = openingPassage;
    }

    public String getTitle() {
        return title;
    }

    public Map<Link, Passage> getPassages() {
        return passages;
    }

    public Passage getOpeningPassage() {
        return openingPassage;
    }

    public Passage getPassage (Link link){
        return passages.get(link);
    }

    public void addPassage(Passage passage){
        Link newLink = new Link(passage.getTitle(), passage.getTitle());
        passages.put(newLink, passage);
    }

}
