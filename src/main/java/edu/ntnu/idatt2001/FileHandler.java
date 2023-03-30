package edu.ntnu.idatt2001;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
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
            if (line.startsWith("::")){
                String title = line.substring(2);
                line = fileScanner.nextLine();
                String content = line;
                Passage passage = new Passage(title, content);
                if (fileScanner.hasNextLine()){
                    line = fileScanner.nextLine();
                }
                while (line.startsWith("[")){
                    String linkString = line;
                    String linkTitle = "";
                    String linkContent = "";


                    Pattern patternForTitle = Pattern.compile("\\[(.*?)\\]");
                    Pattern patternForContent = Pattern.compile("\\((.*?)\\)");

                    Matcher matcher = patternForTitle.matcher(linkString);
                    Matcher matcherContent = patternForContent.matcher(linkString);

                    if (matcher.find()) {
                        linkTitle = matcher.group(1);
                    }
                    if (matcherContent.find()) {
                        linkContent = matcherContent.group(1);
                    }
                    passage.addLink(new Link(linkTitle, linkContent));
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
}
