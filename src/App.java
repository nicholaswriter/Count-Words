import java.io.*;
import java.util.*;

public class App {
    public static void main(String[] args) {
        String input = "input.txt";
        String output = "output.txt";

       String[] words = new String[100];
       int[] counts = new int[100];
       int wordCount = 0;

       try (Scanner scanner = new Scanner(new File(input))) {
        while (scanner.hasNextLine()) {
            String word = scanner.nextLine().toLowerCase();
            int index = findWord(words, word, wordCount);
            if (index == -1) {
                words[wordCount] = word;
                counts[wordCount] = 1;
                wordCount++;
            } else {
                counts[index]++;
            }
        }
       } catch (Exception e) {
        System.out.println("Error. Please try again. Ensure you are inputting a proper file.");
        return; 
       }
       sortWords(words, counts, wordCount);

       try (PrintWriter writer = new PrintWriter(new File(output))) {
        for (int i = 0; i < wordCount; i++) {
            writer.println(words[i] + " " + counts[i]);
        }
       } catch (IOException e) {
        System.out.println("There was an error writing the new file.");
       }
    }

    public static int findWord(String[] words, String word, int wordCount) {
        for (int i = 0; i < wordCount; i++) {
            if (words[i].equals(word)) {
                return i;
            }
        }
        return -1;
    }

    public static void sortWords(String[] words, int[] counts, int wordCount) {
        for (int i = 0; i < wordCount - 1; i++) {
            for (int j = i + 1; j < wordCount; j++) {
                if (words[i].compareTo(words[j]) > 0) {
                    String word = words[i];
                    words[i] = words[j];
                    words[j] = word;

                    int count = counts[i];
                    counts[i] = counts[j];
                    counts[j] = count;
                }
            }
        }
    }

}