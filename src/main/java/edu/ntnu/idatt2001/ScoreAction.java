package edu.ntnu.idatt2001;

public class ScoreAction implements Action {

    int points;

    ScoreAction(int points){
        this.points = points;
    };

    @Override
    public void Execute(Player player){
        //Player.addScore(points);
    }

}
