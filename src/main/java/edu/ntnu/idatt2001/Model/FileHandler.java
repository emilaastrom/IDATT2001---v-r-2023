package edu.ntnu.idatt2001.Model;

import edu.ntnu.idatt2001.Model.Action.*;
import edu.ntnu.idatt2001.Model.Goal.Goal;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileHandler {


    public static void writeFile(Story story) {

    File text = new File("exampleStory.paths");

      try {
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(text));
        fileWriter.write(story.getTitle() + "\n\n");
        fileWriter.write("::" +
            story.getOpeningPassage().getTitle() + "\n" +
            story.getOpeningPassage().getContent() + "\n" +
            story.getOpeningPassage().getLinksFormatted());


        for (Map.Entry<Link, Passage> entry : story.getPassages().entrySet()){
          Passage currentPassage = entry.getValue();

          //skriv ting her
          fileWriter.write("\n::" +
              currentPassage.getTitle() + "\n" +
              currentPassage.getContent() + "\n" +
              currentPassage.getLinksFormatted());

        }

        fileWriter.close();

      } catch (Exception fileNotFoundException){
        fileNotFoundException.printStackTrace();
      }
    }


    public static Story readFile(String fileReference){
        File text = new File(fileReference);
        Scanner fileScanner;
        try {
            fileScanner = new Scanner(text);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        String storyTitle;
        Passage openingPassage = new Passage("","");

        storyTitle = fileScanner.nextLine();
        Story story = new Story(storyTitle,openingPassage);
        fileScanner.nextLine();
        int i=0;
        while (fileScanner.hasNextLine()){
            String line = fileScanner.nextLine();

            //Detecting PASSAGES
            if (line.startsWith("::")){
                String title = line.substring(2);
                line = fileScanner.nextLine();
                String content = line;
                Passage passage = new Passage(title, content);
                if (fileScanner.hasNextLine()){
                    line = fileScanner.nextLine();
                }

                //Detecting LINKS
                while (line.startsWith("[")){
                    String linkString = line;
                    String linkTitle = "";
                    String linkContent = "";

                    ArrayList<GoldAction> goldActionList = new ArrayList<>();
                    ArrayList<HealthAction> healthActionList = new ArrayList<>();
                    ArrayList<InventoryAction> inventoryActionList = new ArrayList<>();
                    ArrayList<ScoreAction> scoreActionList = new ArrayList<>();
                    //Arraylist to keep track of which actions are being added. [0] = Gold, [1] = Health, [2] = Inventory, [3] = Score.
                    ArrayList<Boolean> activeActionList = new ArrayList<>(Arrays.asList(new Boolean[4]));
                    Collections.fill(activeActionList, false);


                    Pattern patternForTitle = Pattern.compile("\\[(.*?)\\]");
                    Pattern patternForContent = Pattern.compile("\\((.*?)\\)");
                    Pattern patternForAction = Pattern.compile("\\{(.*?)}");

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
                        String actionListString = matcherAction.group(1);
                        String[] actions = actionListString.split(";");
                        for (String action : actions) {
                            String currentType = action.split(" ")[0];
                            String currentValue = action.split(" ")[1];

                            if (Objects.equals(currentType, "Gold")){
                                goldActionList.add(new GoldAction(Integer.parseInt(currentValue)));
                                activeActionList.set(0, true);
                            } else if (Objects.equals(currentType, "Health")) {
                                healthActionList.add(new HealthAction(Integer.parseInt(currentValue)));
                                activeActionList.set(1, true);
                            } else if (Objects.equals(currentType, "Score")) {
                                scoreActionList.add(new ScoreAction(Integer.parseInt(currentValue)));
                                activeActionList.set(2, true);
                            } else if (Objects.equals(currentType, "Inventory")) {
                                inventoryActionList.add(new InventoryAction(currentValue));
                                activeActionList.set(3, true);
                            }
                        }
                    }
                    //Building link including title, content & actions
                    Link link = new Link(linkTitle, linkContent);
                        if (Objects.equals(activeActionList.get(0), true)){
                            for (GoldAction goldAction : goldActionList) {
                                link.addAction(goldAction);
                            }
                        } else if (Objects.equals(activeActionList.get(1), true)){
                            for (HealthAction healthAction : healthActionList) {
                                link.addAction(healthAction);
                            }
                        } else if (Objects.equals(activeActionList.get(2), true)){
                            for (ScoreAction scoreAction : scoreActionList) {
                                link.addAction(scoreAction);
                            }
                        } else if (Objects.equals(activeActionList.get(3), true)){
                            for (InventoryAction inventoryAction : inventoryActionList) {
                                link.addAction(inventoryAction);
                            }
                        }
                    passage.addLink(link);
                    line = fileScanner.nextLine();
                    }
                if(i != 0){
                    story.addPassage(passage);}
                else{
                    story.setOpeningPassage(passage);
                }
                }
            i++;
            }
            return story;
        }

    public static boolean openGame(Stage stage){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Paths Files", "*.paths"));
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            String path = selectedFile.getAbsolutePath();
            try{
                Story story = FileHandler.readFile(path);
                String inputValue = JOptionPane.showInputDialog("Please input a name");
                Player player = new Player.PlayerBuilder(inputValue).build();
                List<Goal> goals = new ArrayList<>();
                Game.getInstance().setPlayer(player);
                Game.getInstance().setStory(story);
                Game.getInstance().setGoals(goals);
                return true;

            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }
}
