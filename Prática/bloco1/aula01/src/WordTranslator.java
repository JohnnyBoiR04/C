import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class WordTranslator {
    private Map<String, String> dict = new HashMap<String, String>();

    public static void main(String[] args) {
        WordTranslator translator = new WordTranslator();
        if (args.length > 0) {
            translator.readDict(args[0]);
            for (int i = 1; i < args.length; i++) {
                translator.translate(args[i]);
            }
        }
    }

    private void readDict(String dictFile) {
        try {
            File file = new File(dictFile);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] words = line.split(" ", 2);
                dict.put(words[0], words[1]);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private void translate(String textFile) {
        try {
            File file = new File(textFile);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] words = line.split(" ");
                for (String word : words) {
                    if (dict.containsKey(word)) {
                        System.out.print(dict.get(word) + " ");
                    } else {
                        System.out.print(word + " ");
                    }
                }
                System.out.println('\n');
            } 
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}