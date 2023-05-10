package edu.ntnu.idatt2001.Model;

import edu.ntnu.idatt2001.Model.Goal.Goal;

import java.util.ArrayList;
import java.util.List;

/**
 * The class Game.
 */
public class Game {
    private static final Game instance = new Game();
    private Player player = new Player.PlayerBuilder("").build();
    private Story story = new Story("", new Passage("", ""));
    private List<Goal> goals = new ArrayList<>();


    public Game(){
    }

    public static Game getInstance() {return instance;
    }

    /**
     * Gets player.
     *
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setStory(Story story) {
        this.story = story;
    }

    public void setGoals(List<Goal> goals) {
        this.goals = goals;
    }

    public void addGoal(Goal goal) {
        this.goals.add(goal);
    }

    /**
     * Gets story.
     *
     * @return the story
     */
    public Story getStory() {
        return story;
    }

    /**
     * Gets goals.
     *
     * @return the goals
     */
    public List<Goal> getGoals() {
        return goals;
    }

    /**
     * Begins the game, by referring to the opening passage.
     */
    public Passage begin(){
        return story.getOpeningPassage();
    }

    /**
     * Goes to a given passage, using a link, within the story.
     *
     * @param link the link
     */
    public Passage go(Link link){
        return story.getPassage(link);
    }

}