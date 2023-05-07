package edu.ntnu.idatt2001.Model.Action;

import edu.ntnu.idatt2001.Model.Player;

/**
 * An action that affects player score.
 */
public class ScoreAction implements Action {

    private final int points;

    /**
     * Instantiates a new ScoreAction.
     *
     * @param points the points
     */
    public ScoreAction(int points){
        this.points = points;
    };

    public String getType(){
        return "Score";
    }

    public String getAmount(){
        return Integer.toString(points);
    }

    @Override
    public void Execute(Player player){
        player.addScore(points);
    }

}
