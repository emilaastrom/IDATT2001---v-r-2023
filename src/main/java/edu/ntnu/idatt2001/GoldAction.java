package edu.ntnu.idatt2001;

public class GoldAction implements Action{

    private int gold;

    GoldAction(int gold){
        this.gold = gold;
    }

    @Override
    public void Execute(Player player){
        //Player.addGold(gold);
    }


}
