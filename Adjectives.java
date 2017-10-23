import java.util.*;
import java.io.*;

public class Adjectives {
  private Random rnd;
  private ArrayList<String> adjectives;

  public Adjectives() {
    adjectives = new ArrayList<String> ();
    rnd        = new Random();
  }

  public void parseDict (String pth) {
    try {
      File file                     = new File(pth);
      FileReader fileReader         = new FileReader(file);
      BufferedReader bufferedReader = new BufferedReader(fileReader);

      String line;
      while ((line = bufferedReader.readLine()) != null) {
        int ix = line.indexOf(' ');
        String word = line.substring(0, ix);
        String type = line.substring(ix+1);

        if (!type.equals("adj")) continue;
        adjectives.add(word.toLowerCase());
      }

      fileReader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void parseList (String pth) {
    try {
      File file                     = new File(pth);
      FileReader fileReader         = new FileReader(file);
      BufferedReader bufferedReader = new BufferedReader(fileReader);

      String line;
      while ((line = bufferedReader.readLine()) != null) {
        adjectives.add(line.toLowerCase());
      }

      fileReader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String getRandomAdj () throws Exception {
    if (adjectives.size() == 0)
      throw new Exception("Out of words");

    int i = rnd.nextInt(adjectives.size());
    String w = adjectives.get(i);
    adjectives.remove(i);
    return w;
  }
}
