package fi.tuni.prog3;
import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SevenZipSearch {

  public static void main(String[] args) throws IOException {
    String fileName = args[0];
    String searchWord = args[1];

    try (SevenZFile sevenZFile = new SevenZFile(new File(fileName))) {
      SevenZArchiveEntry entry;
      while ((entry = sevenZFile.getNextEntry()) != null) {
        if (!entry.isDirectory() && entry.getName().endsWith(".txt")) {
          System.out.println(entry.getName());
          searchInTextFile(sevenZFile.getInputStream(entry), searchWord);
        }
      }
    }
  }
  private static void searchInTextFile(InputStream inputStream, String searchWord) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
    String line;
    int lineNumber = 0;
    while ((line = reader.readLine()) != null) {
      lineNumber++;
      if (line.toLowerCase().contains(searchWord.toLowerCase())) {
        String formattedLine = line.replaceAll("(?i)" + searchWord, searchWord.toUpperCase());
        System.out.println(lineNumber + ": " + formattedLine);
      }
    }
    System.out.println();
  }
}
