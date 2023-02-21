package edu.ntnu.idatt2001;

import java.util.List;

/**
 * The class Game.
 */
public class Game {
    private final Player player;
    private final Story story;
    private List<Goal> goals;

    /**
     * Instantiates a new Game.
     *
     * @param player the player
     * @param story  the story
     * @param goals  the goals
     */
    Game(Player player, Story story, List<Goal> goals){
        this.player = player;
        this.story = story;
        this.goals = goals;
    }

    /**
     * Gets player.
     *
     * @return the player
     */
    public Player getPlayer() {
        return player;
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
