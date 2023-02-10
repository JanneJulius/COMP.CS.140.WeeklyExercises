import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class WordGame {

  private ArrayList<String> words;
  private boolean gameActive;
  private String coveredWord;
  private String originalWord;
  private int mistakes;
  private int mistakeLimit;
  private ArrayList<Character> charList;

  public static class WordGameState {
    private String word;
    private int mistakes;
    private int mistakeLimit;
    private int missingChars;

    private WordGameState(String word, int mistakes, int mistakeLimit, int missingChars) {
      this.word = word;
      this.mistakes = mistakes;
      this.mistakeLimit = mistakeLimit;
      this.missingChars = missingChars;
    }

    public String getWord() {
      return word;
    }

    public int getMistakes() {
      return mistakes;
    }

    public int getMistakeLimit() {
      return mistakeLimit;
    }

    public int getMissingChars() {
      return missingChars;
    }
  }

  private String createCover(String s ){
    String finalWord = "";
      for (int i = 0; i < s.length(); i++) {
        finalWord = finalWord + "_";
      }
    return finalWord;
  }

  public WordGame(String wordFilename) {
    words = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(wordFilename))) {
      String line;
      while ((line = br.readLine()) != null) {
        words.add(line);
      }
    } catch (IOException e) {
      System.out.println("Error reading word file: " + e.getMessage());
    }
  }

  public void initGame(int wordIndex, int mistakeLimit) {
    this.originalWord = words.get(wordIndex % words.size()).toLowerCase();
    this.coveredWord = createCover(words.get(wordIndex % words.size()));
    this.mistakeLimit = mistakeLimit;
    this.mistakes = 0;
    this.gameActive = true;

    charList = new ArrayList<>();
    for (char c : originalWord.toCharArray()) {
      charList.add(Character.toLowerCase(c));
    }
  }

  public boolean isGameActive() {
    return gameActive;
  }

  public WordGameState getGameState() throws GameStateException {
    if (!gameActive) {
      throw new GameStateException("There is currently no active word game!");
    }
    return new WordGameState(originalWord, mistakes, mistakeLimit, charList.size());
  }

  public WordGameState guess(char c) throws GameStateException {
    c = Character.toLowerCase(c);
    if (!gameActive) {
      throw new GameStateException("There is currently no active word game!");
    }

    boolean containsChar = charList.contains(c);

    if(containsChar){

      for (int i = charList.size() - 1; i >= 0; i--) {
        if (charList.get(i) == c) {
          charList.remove(i);
        }
      }

      int index = originalWord.indexOf(c);
      ArrayList<Integer> indexes = new ArrayList<>();
      while (index >= 0) {
        indexes.add(index);
        index = originalWord.indexOf(c, index + 1);
      }

      for(int i: indexes) {
        StringBuilder sb = new StringBuilder(coveredWord);
        sb.setCharAt(i, c);
        coveredWord = sb.toString();
      }

      if (charList.size() == 0) {
        gameActive = false;
      }
    }
    else{
      mistakes++;
      if (mistakes > mistakeLimit){
        gameActive = false;
        return new WordGameState(originalWord, mistakes, mistakeLimit, charList.size());
      }
    }
   
    return new WordGameState(coveredWord, mistakes, mistakeLimit, charList.size());
  }

  public WordGameState guess(String w) throws GameStateException {
    if (!gameActive) {
      throw new GameStateException("There is currently no active word game!");
    }
    if (w.equals(originalWord)) {
      gameActive = false;
      return new WordGameState(originalWord, mistakes, mistakeLimit, 0);
    } 
    else {
      mistakes++;
      if (mistakes > mistakeLimit) {
        gameActive = false;
        return new WordGameState(originalWord, mistakes, mistakeLimit, charList.size());
      }
      return new WordGameState(coveredWord, mistakes, mistakeLimit, charList.size());
    }
  }
}