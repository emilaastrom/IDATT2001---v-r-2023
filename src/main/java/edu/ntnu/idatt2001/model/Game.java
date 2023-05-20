package edu.ntnu.idatt2001.model;

import edu.ntnu.idatt2001.controller.UserInformer;
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

  public void setPlayer(Player player) {
    this.player = player;
  }

  public void setStory(Story story) {
    this.story = story;
  }

  public void setGoals(List<Goal> goals) {
    this.goals = goals;
  }

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

  public Passage goSilent(Link link) {
    return story.getPassage(link);
  }

  /**
   * Goes back to the previous passage.
   */
  public Link goBack() {
    if (linkHistory.size() > 1) {
      List<Action> actionsToUndo = linkHistory.get(linkHistory.size() - 1).getActions();
      for (Action action : actionsToUndo) {
        action.undo(player);
      }
      Link link = linkHistory.get(linkHistory.size() - 2);
      Passage passage = passageHistory.get(passageHistory.size() - 1);
      linkHistory.remove(linkHistory.size() - 1);
      return link;
    } else {
      UserInformer.errorWarning("Error", "No passages to go back to");
      return null;
    }
  }

  /**
   * Restarts the game.
   */
  public void restartGame() {
    //TODO restartGame() function
    Game.getInstance().setPlayer(
        new Player.PlayerBuilder(Game.getInstance().getPlayer().getName()).build());
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
