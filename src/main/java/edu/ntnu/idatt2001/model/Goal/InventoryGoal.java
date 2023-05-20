package edu.ntnu.idatt2001.model.Goal;

import edu.ntnu.idatt2001.model.Player;

import java.util.List;

/**
 * The goal of having certain items in the inventory.
 */
public class InventoryGoal implements Goal {
        private final List<String> mandatoryItems;

    /**
     * Instantiates a new InventoryGoal.
     *
     * @param mandatoryItems the mandatory items
     */
    public InventoryGoal(List<String> mandatoryItems){
            this.mandatoryItems = mandatoryItems;
        }

        public List<String> getMandatoryItems() {
            return mandatoryItems;
        }

        @Override
        public boolean isFulfilled(Player player){
            boolean response = true;
            for (String mandatoryItem : mandatoryItems) {
                if (player.getInventory().contains(mandatoryItem)) {
                    response = true;
                } else {
                    response = false;
                    break;
                }
            }
            return response;
        }
    }


