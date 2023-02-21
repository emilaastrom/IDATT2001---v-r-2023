package edu.ntnu.idatt2001;

public class HealthAction implements Action{

    int health;

    HealthAction(int health){
        this.health = health;
    }

    @Override
    public void Execute(Player player){
        //Player.addScore(points);
    }

}
