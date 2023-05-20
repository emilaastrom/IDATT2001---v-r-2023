package edu.ntnu.idatt2001.model;

import edu.ntnu.idatt2001.model.action.GoldAction;
import edu.ntnu.idatt2001.model.action.HealthAction;
import edu.ntnu.idatt2001.model.action.InventoryAction;
import edu.ntnu.idatt2001.model.action.ScoreAction;
import edu.ntnu.idatt2001.model.goal.Goal;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * The type File handler.
 */
public class FileHandler {

  /**
   * Write file.
   *
   * @param story the story
   */
  public static void writeFile(Story story) {

    File text = new File("exampleStory.paths");

    try {
      BufferedWriter fileWriter = new BufferedWriter(new FileWriter(text));
      fileWriter.write(story.getTitle() + "\n\n");
      fileWriter.write("::"
              + story.getOpeningPassage().getTitle() + "\n"
              + story.getOpeningPassage().getContent() + "\n"
              + story.getOpeningPassage().getLinksFormatted());


      for (Map.Entry<Link, Passage> entry : story.getPassages().entrySet()) {
        Passage currentPassage = entry.getValue();

        //skriv ting her
        fileWriter.write("\n::"
                + currentPassage.getTitle() + "\n"
                + currentPassage.getContent() + "\n"
                + currentPassage.getLinksFormatted());

      }

      fileWriter.close();

    } catch (Exception fileNotFoundException) {
      fileNotFoundException.printStackTrace();
    }
  }


  /**
   * Reading the given file.
   *
   * @param fileReference the file reference
   * @return the story
   */
  public static Story readFile(String fileReference) {
    File text = new File(fileReference);
    Scanner fileScanner;
    try {
      fileScanner = new Scanner(text);
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }

    String storyTitle;
    Passage openingPassage = new Passage("", "");

    storyTitle = fileScanner.nextLine();
    Story story = new Story(storyTitle, openingPassage);
    fileScanner.nextLine();
    int i = 0;
    while (fileScanner.hasNextLine()) {
      String line = fileScanner.nextLine();

      //Detecting PASSAGES
      if (line.startsWith("::")) {
        String title = line.substring(2);
        line = fileScanner.nextLine();
        String content = line;
        Passage passage = new Passage(title, content);
        if (fileScanner.hasNextLine()) {
          line = fileScanner.nextLine();
        }

        //Detecting LINKS
        while (line.startsWith("[")) {
          String linkString = line;
          String linkTitle = "";
          String linkContent = "";

          //Lists for storing the different types of actions
          ArrayList<GoldAction> goldActionList = new ArrayList<>();
          ArrayList<HealthAction> healthActionList = new ArrayList<>();
          ArrayList<InventoryAction> inventoryActionList = new ArrayList<>();
          ArrayList<ScoreAction> scoreActionList = new ArrayList<>();

          // Keeping track of which actions are being added: gold, health, inventory, score.
          ArrayList<Boolean> activeActionList = new ArrayList<>(Arrays.asList(new Boolean[4]));

          //Regex patterns for detecting TITLE, CONTENT and ACTIONS in link.
          Pattern patternForTitle = Pattern.compile("\\[(.*?)\\]");
          Pattern patternForContent = Pattern.compile("\\((.*?)\\)");
          Pattern patternForAction = Pattern.compile("\\{(.*?)}");

          //Matcher for utilizing the regex patterns.
          Matcher matcherTitle = patternForTitle.matcher(linkString);
          Matcher matcherContent = patternForContent.matcher(linkString);
          Matcher matcherAction = patternForAction.matcher(linkString);

          //Detecting TITLE of link
          if (matcherTitle.find()) {
            linkTitle = matcherTitle.group(1);
          }
          //Detecting CONTENT/DESCRIPTION of link
          if (matcherContent.find()) {
            linkContent = matcherContent.group(1);
          }
          //Detecting ACTIONS in link
          if (matcherAction.find()) {
            //Identifying actions and splitting them into an array.
            String actionListString = matcherAction.group(1);
            String[] actions = actionListString.split(";");
            //Looping through actions and tracking which types are being added.
            for (String action : actions) {
              //Splitting the action type/value into: type (INDEX 0) and value (INDEX 1).
              String[] currentTypeAndValue = action.split(" ");

              //Keeping track of which types of actions are being added.
              if (Objects.equals(currentTypeAndValue[0], "Gold")) {
                goldActionList.add(new GoldAction(Integer.parseInt(currentTypeAndValue[1])));
                activeActionList.set(0, true);
              } else if (Objects.equals(currentTypeAndValue[0], "Health")) {
                healthActionList.add(new HealthAction(Integer.parseInt(currentTypeAndValue[1])));
                activeActionList.set(1, true);
              } else if (Objects.equals(currentTypeAndValue[0], "Score")) {
                scoreActionList.add(new ScoreAction(Integer.parseInt(currentTypeAndValue[1])));
                activeActionList.set(2, true);
              } else if (Objects.equals(currentTypeAndValue[0], "Inventory")) {
                inventoryActionList.add(new InventoryAction(currentTypeAndValue[1]));
                activeActionList.set(3, true);
              }
            }
          }
          Link link = new Link(linkTitle, linkContent);
          //Checking to see which types of actions have been activated and adding them to the link.
          if (Objects.equals(activeActionList.get(0), true)) {
            for (GoldAction goldAction : goldActionList) {
              link.addAction(goldAction);
            }
          }
          if (Objects.equals(activeActionList.get(1), true)) {
            for (HealthAction healthAction : healthActionList) {
              link.addAction(healthAction);
            }
          }
          if (Objects.equals(activeActionList.get(2), true)) {
            for (ScoreAction scoreAction : scoreActionList) {
              link.addAction(scoreAction);
            }
          }
          if (Objects.equals(activeActionList.get(3), true)) {
            for (InventoryAction inventoryAction : inventoryActionList) {
              link.addAction(inventoryAction);
            }
          }
          passage.addLink(link);

          //Looping if there is another line in the .paths file.
          if (fileScanner.hasNextLine()) {
            line = fileScanner.nextLine();
          } else {
            //Breaks the loop if there are no lines following the current one.
            break;
          }
        }
        if (i != 0) {
          story.addPassage(passage);
        } else {
          story.setOpeningPassage(passage);
        }
      }
      i++;
    }
    return story;
  }

  /**
   * Open static game.
   *
   * @param stage       the stage
   * @param path        the path
   * @param playerName  the player name
   * @param playerGoals the player goals
   */
  public static void openStaticGame(
          Stage stage,
          String path,
          String playerName,
          List<Goal> playerGoals) {
    File selectedFile = new File(path);
    String absolutePath = selectedFile.getAbsolutePath();
    try {
      Story story = FileHandler.readFile(absolutePath);
      try {
        Game.getInstance().setPlayer(new Player.PlayerBuilder(playerName).build());
        Game.getInstance().setStory(story);
        Game.getInstance().setGoals(playerGoals);
      } catch (Exception e) {
        e.printStackTrace();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Open game boolean.
   *
   * @param stage       the stage
   * @param playerName  the player name
   * @param playerGoals the player goals
   * @return the boolean
   */
  public static boolean openGame(
      Stage stage, String path, String playerName, List<Goal> playerGoals) {
    try {
      Story story = FileHandler.readFile(path);
      try {
        Player player = new Player.PlayerBuilder(playerName).build();
        Game.getInstance().setPlayer(player);
        Game.getInstance().setStory(story);
        Game.getInstance().setGoals(playerGoals);
        return true;
      } catch (Exception e) {
        return false;
      }
    } catch (Exception e) {
      return false;
    }
  }
}
