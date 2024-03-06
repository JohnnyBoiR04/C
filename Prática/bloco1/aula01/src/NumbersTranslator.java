import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class NumbersTranslator {
    HashMap<String, String> numbers = new HashMap<String, String>();
    public static void main(String[] args) throws FileNotFoundException {
        NumbersTranslator translator = new NumbersTranslator();
        translator.readFile("/home/laptop-johnny/Secretária/Uni/2ºAno/Segundo Semestre/C/Prática/bloco1/numbers.txt");
        translator.analyseInput();
    }


    private void readFile(String path) {
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().toLowerCase();
                String[] parts = line.split(" ");
                numbers.put(parts[2], parts[0]);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Please check the path and try again.");
            System.exit(1);
        }
    }

    private void analyseInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().toLowerCase();
        String[] parts = input.split("[,;:.!?\\s-]");
        for (String part : parts) {
            if (numbers.containsKey(part)) {
                System.out.print(numbers.get(part) + " ");
            } else {
                System.out.print(part + " ");
            }
            System.out.println('\n');
        }
        scanner.close();
    }
}
