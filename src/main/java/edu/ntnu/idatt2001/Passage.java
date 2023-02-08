package edu.ntnu.idatt2001;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Passage {
    private final String title;
    private final String content;
    private List<Link> links;

    Passage(String title, String content){
        this.title = title;
        this.content = content;
        links = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public List<Link> getLinks() {
        return links;
    }

    public boolean addLink (Link link){
        links.add(link);
        return true;
    }

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
