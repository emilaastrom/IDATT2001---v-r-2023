package edu.ntnu.idatt2001.model.action;

import edu.ntnu.idatt2001.model.Player;

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
  public ScoreAction(int points) {
    this.points = points;
  }

  /**
   * Gets type.
   *
   * @return the type
   */
  public String getType() {
    return "Score";
  }

  /**
   * Gets amount.
   *
   * @return the amount
   */
  public String getAmount() {
    return Integer.toString(points);
  }

  /**
   * Execute the action.
   *
   * @param player the player
   */
  @Override
  public void execute(Player player) {
    player.addScore(points);
  }

  /**
   * Undo the action.
   *
   * @param player the player
   */
  @Override
  public void undo(Player player) {
    player.addScore(-points);
  }

}
