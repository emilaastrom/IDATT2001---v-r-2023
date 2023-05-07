package edu.ntnu.idatt2001.Model;

import edu.ntnu.idatt2001.Model.Action.Action;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The class Passage.
 */
public class Passage {
    private final String title;
    private final String content;
    private List<Link> links;

    /**
     * Instantiates a new Passage.
     *
     * @param title   the title
     * @param content the content
     */
    public Passage(String title, String content){
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

    public String getLinksFormatted(){
        StringBuilder outputString = new StringBuilder();
        for (Link link : links){
            outputString
                .append("[")
                .append(link.getText())
                .append("]").append("(")
                .append(link.getReference())
                .append(")");
                if(!link.getActions().isEmpty()){
                    outputString
                        .append("{");
                }
                for(Action action : link.getActions()){
                    outputString
                        .append(action.getType())
                        .append(" ")
                        .append(action.getAmount())
                        .append(";");
                }
                if(!link.getActions().isEmpty()){
                    outputString
                        .append("}");
                }
            outputString
                .append("\n");
        }

        return String.valueOf(outputString);
    }

    /**
     * Add link.
     *
     * @param link the link
     * @return the boolean
     */
    public boolean addLink (Link link){
        links.add(link);
        return true;
    }

    public boolean addLink (Link link, Action action){
        links.add(link);
        return true;
    }

    /**
     * Has links.
     *
     * @return the boolean
     */
    public boolean hasLinks(){
        if (links.isEmpty()){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passage passage = (Passage) o;
        return title.equals(passage.title) && content.equals(passage.content) && Objects.equals(links, passage.links);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, content, links);
    }

    @Override
    public String toString() {
        return "Passage{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", links=" + links +
                '}';
    }
}
