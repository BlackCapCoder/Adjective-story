import java.util.*;
import java.io.*;
import java.util.function.*;

public class Story {
  private ArrayList<String> words;
  private boolean           outOfWords;

  public Story (String pth) {
    InputReader ir = new InputReader();
    words          = ir.getWordsInFileWithScanner (pth);
    outOfWords     = false;
  }

  // A generic source of adjectives
  @FunctionalInterface
  public interface WordProvider<R> {
    R apply() throws Exception;
  }

  // Writes the story given a source of adjectives
  public void writeStory (WordProvider<String> f) {
    try {
      boolean doUpper = true;

      for (int i = 0; i < words.size(); i++) {
        String w = words.get(i);

        // Assumes that "ADJEKTIV" does not have trailing punctuation, which would be gramatically invalid.
        if (w.equals("ADJEKTIV")) {
          String a = f.apply();
          if (doUpper) a = a.substring(0, 1).toUpperCase() + a.substring(1);
          words.set(i, a);
        }

        doUpper = !w.equals(w.replace("!", "").replace(".", "").replace("?", ""));
      }
    } catch (Exception ex) {
      outOfWords = true;
    }
  }

  public ArrayList<String> getStory () {
    if (outOfWords)
      return new ArrayList<> (Arrays.asList(new String[]{"Ran out of words!"}));

    return words;
  }
}
