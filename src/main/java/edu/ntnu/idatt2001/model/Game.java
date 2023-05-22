package edu.ntnu.idatt2001.model;

import edu.ntnu.idatt2001.handlers.UserInformerHandler;
import edu.ntnu.idatt2001.model.action.Action;
import edu.ntnu.idatt2001.model.goal.Goal;
import java.util.ArrayList;
import java.util.List;

/**
 * The class Game.
 */
public class Game {
  private static final Game instance = new Game();
  private Player player = new Player.PlayerBuilder("").build();
  private Story story = new Story("", new Passage("", ""));
  private List<Goal> goals = new ArrayList<>();
  private final ArrayList<Passage> passageHistory = new ArrayList<>();
  private final ArrayList<Link> linkHistory = new ArrayList<>();


  public Game() {
  }

  /**
   * Gets instance.
   *
   * @return the instance
   */
  public static Game getInstance() {
    return instance;
  }

  /**
   * Gets player.
   *
   * @return the player
   */
  public Player getPlayer() {
    return player;
  }

  /**
   * Sets player.
   *
   * @param player the player
   */
  public void setPlayer(Player player) {
    this.player = player;
  }

  /**
   * Sets story.
   *
   * @param story the story
   */
  public void setStory(Story story) {
    this.story = story;
  }

  /**
   * Sets goals.
   *
   * @param goals the goals
   */
  public void setGoals(List<Goal> goals) {
    this.goals = goals;
  }

  /**
   * add a goal to the game.
   */
  public void addGoal(Goal goal) {
    this.goals.add(goal);
  }

  /**
   * Gets story.
   *
   * @return the story
   */
  public Story getStory() {
    return story;
  }

  /**
   * Gets goals.
   *
   * @return the goals
   */
  public List<Goal> getGoals() {
    return goals;
  }

  /**
   * Begins the game, by referring to the opening passage.
   */
  public Passage begin() {
    return story.getOpeningPassage();
  }

  /**
   * Goes to a given passage, using a link, within the story.
   *
   * @param link the link
   */
  public Passage go(Link link) {
    passageHistory.add(story.getPassage(link));
    linkHistory.add(link);
    return story.getPassage(link);
  }

  /**
   * Goes to a given passage, without adding it to the history.
   *
   * @param link the link
   */
  public Passage goSilent(Link link) {
    return story.getPassage(link);
  }

  /**
   * Goes back to the previous passage.
   */
  public Link goBack() {
    try {
      //Checking if there is a passage to go back to
      if (linkHistory.size() > 1) {
        List<Action> actionsToUndo = linkHistory.get(linkHistory.size() - 1).getActions();
        //Executing undo action for each action in the list
        actionsToUndo.forEach(action -> action.undo(player));
        Link link = linkHistory.get(linkHistory.size() - 2);
        //Removing the last passage and link from the history
        linkHistory.remove(linkHistory.size() - 1);
        return link;
      } else {
        return null;
      }
    } catch (Exception e) {
      UserInformerHandler.errorWarning("Error trying to undo", "Try again!");
      return null;
    }
  }

  /**
   * Restarts the game.
   */
  public void restartGame() {
    //Setting the player to a new player with the same name
    Game.getInstance().setPlayer(
        new Player.PlayerBuilder(Game.getInstance().getPlayer().getName()).build());
    //Clearing the history
    passageHistory.clear();
    linkHistory.clear();
  }

  /**
   * Gets passage history.
   *
   * @return the passage history
   */
  public ArrayList<Passage> getPassageHistory() {
    return passageHistory;
  }

  /**
   * Gets link history.
   *
   * @return the link history
   */
  public ArrayList<Link> getLinkHistory() {
    return linkHistory;
  }
}
