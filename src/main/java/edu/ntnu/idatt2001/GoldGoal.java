package edu.ntnu.idatt2001;

public class GoldGoal implements Goal{
    private final int minimumGold;

    public GoldGoal(int minimumGold){
        this.minimumGold = minimumGold;
    }

    @Override
    public boolean isFulfilled(Player player){
        return player.getGold() >= minimumGold;
    }
}
