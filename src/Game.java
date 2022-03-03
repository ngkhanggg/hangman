import java.io.*;
import java.util.*;

public class Game {
    private ArrayList <String> words = new ArrayList<String>();
    private String fileName = "assets/Words.txt";
    private String wordToGuess;

    // constructor
    public Game() {
        insertWordsIntoList();
    }
    
    // count total lines in text file
    public int countLines() {
        int lines = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while (reader.readLine() != null) lines++;
        }
        catch (Exception e) {
            System.out.println("Go to Game.java line 6 and modify fie\'s name please");
        }
        return lines;
    }

    // insert words from text file into an array
    public void insertWordsIntoList() {
        try (Scanner sc = new Scanner(new FileReader(fileName))) {
            while (sc.hasNext()) {
                words.add(sc.nextLine());
            }
        }
        catch (Exception e) {
            System.out.println("Go to Game.java line 6 and modify fie\'s name please");
        }
    }

    // choose a random word
    // change word to guess accordingly
    public String getWord() {
        Random rd = new Random();
        int line = rd.nextInt(countLines());
        String word = words.get(line).toUpperCase();
        this.wordToGuess = word;
        return word;
    }
}
