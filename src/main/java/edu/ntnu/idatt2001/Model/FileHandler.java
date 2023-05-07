package edu.ntnu.idatt2001.Model;

import edu.ntnu.idatt2001.Model.Action.*;
import edu.ntnu.idatt2001.Model.Goal.Goal;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import javax.swing.Action;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
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
            //title
            if (line.startsWith("::")){
                String title = line.substring(2);
                line = fileScanner.nextLine();
                String content = line;
                Passage passage = new Passage(title, content);
                if (fileScanner.hasNextLine()){
                    line = fileScanner.nextLine();
                }
                //links
                while (line.startsWith("[")){
                    String linkString = line;
                    String linkTitle = "";
                    String linkContent = "";
                    ArrayList<Action> actionList = new ArrayList<>();


                    Pattern patternForTitle = Pattern.compile("\\[(.*?)\\]");
                    Pattern patternForContent = Pattern.compile("\\((.*?)\\)");
                    Pattern patternForAction = Pattern.compile("\\{(.*?)}");

                    Matcher matcher = patternForTitle.matcher(linkString);
                    Matcher matcherContent = patternForContent.matcher(linkString);
                    Matcher matcherAction = patternForAction.matcher(linkString);

                    if (matcher.find()) {
                        linkTitle = matcher.group(1);
                    }
                    if (matcherContent.find()) {
                        linkContent = matcherContent.group(1);
                    }
                    if (matcherAction.find()) {
                        String actionListString = matcherAction.group(1);
                        String[] actions = actionListString.split(";");
                        for (String action : actions) {
                            String currentType = action.split(" ")[0];
                            String currentValue = action.split(" ")[1];

                            switch (currentType) {
                                case "Gold" ->
                                    //TODO Try catch her? virker som om det kan bli problemer med exceptions
                                    actionList.add((Action) new GoldAction(Integer.parseInt(currentValue)));
                                case "Health" ->
                                    //TODO Try catch her?
                                    actionList.add((Action) new HealthAction(Integer.parseInt(currentValue)));
                                case "Score" ->
                                    //TODO Try catch?
                                    actionList.add((Action) new ScoreAction(Integer.parseInt(currentValue)));
                                case "Inventory" ->
                                    //TODO Try catch?
                                    actionList.add((Action) new InventoryAction(currentValue));
                            }
                        }
                        //
                    }
                    Link link = new Link(linkTitle, linkContent);
                    for (Action action : actionList) {
                        link.addAction((edu.ntnu.idatt2001.Model.Action.Action) action);
                    }
                    passage.addLink(link);
                    line = fileScanner.nextLine();
                }
                if(i != 0){
                    story.addPassage(passage);}
                else{
                    story.setOpeningPassage(passage);
                }
            i++;
            }
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
