package edu.ntnu.idatt2001;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Map;
import java.util.Scanner;

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

    /*Scanner fileScanner = new Scanner(text);
    //HUSK Å SJEKK FOR EXCEPTIONS (FileNotFoundException osv.)

    public void readFile(String fileReference){
      int lineNumber = 1;
      while (fileScanner.hasNextLine()){
        String line = fileScanner.nextLine();
        //System.out.println("line " + lineNumber + " :" + line);
        lineNumber++;

      }
    }*/

}
