package edu.ntnu.idatt2001;

public class Game {
    private final Player player;
    private final Story story;
    private List<Goal> goals;

    Game(Player player, Story story, List<Goal> goals){
        this.player = player;
        this.story = story;
        this.goals = goals;
    }

    public Player getPlayer() {
        return player;
    }

    public Story getStory() {
        return story;
    }

    public List<Goal> getGoals() {
        return goals;
    }

    public void begin(){
        //return Story.getOpeningPassage();
    }

    public void go(Link link){
        //return Story.getPassage(link);
    }

}
