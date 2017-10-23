import java.util.Scanner;
import java.util.function.*;
import java.util.*;

public class StoryCreator {
  public StoryCreator() {}

  // Write a story using a generic adjective provider
  private void createStory (String storyPth, Story.WordProvider<String> wp, String outPth) {
    Story        s = new Story (storyPth);
    OutputWriter o = new OutputWriter ();

    s.writeStory(wp);
    o.write(s.getStory(), outPth);
  }

  // Write a story using a plain list of adjectives
  public void createAdjectiveStory(String storyFilename, String adjectivesFilename, String outputFilename) {
    Adjectives adj = new Adjectives ();
    adj.parseList(adjectivesFilename);
    createStory(storyFilename, () -> adj.getRandomAdj(), outputFilename);
  }

  // Write a story using a dictionary of words
  public void createAdjectiveStoryFromDictionary(String storyFilename, String dictionaryFilename, String outputFilename) {
    Adjectives adj = new Adjectives ();
    adj.parseDict(dictionaryFilename);
    createStory(storyFilename, () -> adj.getRandomAdj(), outputFilename);
  }

  // Write a story using adjectives from STDIN
  public void createAdjectiveStory(String storyFilename, String outputFilename) {
    Scanner           inp  = new Scanner(System.in);
    ArrayList<String> used = new ArrayList<String> ();

    createStory(storyFilename, () -> {
      while (true) {
        System.out.print("Enter an adjective: ");
        String q = inp.nextLine().toLowerCase();

        if (used.contains(q)) {
          System.out.print("You've already used that word!");
          continue;
        }

        used.add(q);
        return q;
      }
    }, outputFilename);
  }
}
