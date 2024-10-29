package random.misc;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * @author souas62002
 */
public class ReadFile {
    private static void printFileMetadata(String name) {
        int lines = 0;
        int words = 0;
        int chars = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(name))) {
            String line = reader.readLine();
            while (line != null) {
                lines++;
                String[] wordsArray = line.split("\\s+");
                words += wordsArray.length;
                chars += line.length();
                line = reader.readLine();
            }

            System.out.println("Lines - " + lines);
            System.out.println("Words - " + words);
            System.out.println("Chars - " + chars);
        } catch (Exception ex) {
            System.err.println("Error - " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        printFileMetadata("C:\\Users\\Potla\\IdeaProjects\\java-practice\\resources\\Test.txt");
    }
}
