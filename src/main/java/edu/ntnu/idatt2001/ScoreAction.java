package edu.ntnu.idatt2001;

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
    ScoreAction(int points){
        this.points = points;
    };

    @Override
    public void Execute(Player player){
        player.addScore(points);
    }

}
