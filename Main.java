public class Main {
  public static void main(String[] args) {
    StoryCreator st = new StoryCreator ();
    st.createAdjectiveStoryFromDictionary("adjektivhistorie.txt", "NSF-ordlisten.txt", "out.txt");
  }
}
