package edu.ntnu.idatt2001.model.goal;

import edu.ntnu.idatt2001.model.Player;

/**
 * The goal of reaching a certain score.
 */
public class ScoreGoal implements Goal {
  private final int minimumPoints;

  /**
   * Instantiates a new ScoreGoal.
   *
   * @param minimumPoints the minimum points for having achieved the goal
   */
  public ScoreGoal(int minimumPoints) {
    this.minimumPoints = minimumPoints;
  }

  /**
   * Gets the minimum points.
   *
   * @return the minimum points
   */
  public int getScoreGoal() {
    return minimumPoints;
  }

  /**
   * Is fulfilled boolean.
   *
   * @param player the player
   * @return the boolean
   */
  @Override
  public boolean isFulfilled(Player player) {
    return player.getScore() >= minimumPoints;
  }
}
