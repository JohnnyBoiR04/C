import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class NumbersTranslator2 {
    private HashMap<String, Integer> numbers = new HashMap<>();
    private HashMap<String, Integer> multipliers = new HashMap<>();

    public static void main(String[] args) {
        NumbersTranslator2 translator = new NumbersTranslator2();
        translator.readFile("/home/laptop-johnny/Secretária/Uni/2ºAno/Segundo_Semestre/C/Prática/bloco1/numbers.txt");
        translator.analyseInput();
    }

    private void readFile(String path) {
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().toLowerCase();
                String[] parts = line.split(" ");
                int value = Integer.parseInt(parts[0]);
                if (value % 10 == 0 && value != 0) { 
                    multipliers.put(parts[2], value);
                } else {
                    numbers.put(parts[2], value);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Please check the path and try again.");
            System.exit(1);
        }
    }

    private void analyseInput() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine().toLowerCase();
            String[] parts = input.split("[,;:.!?\\s-]+");
            long result = 0;
            long currentNumber = 0;
            boolean numberPresentBeforeMultiplier = false;
    
            for (String part : parts) {
                if (multipliers.containsKey(part)) {
                    long multiplier = multipliers.get(part);
                    if (currentNumber == 0 && !numberPresentBeforeMultiplier) {
                        currentNumber = 1;
                    }
                    currentNumber *= multiplier;
                    numberPresentBeforeMultiplier = false;
                } else if (numbers.containsKey(part)) {
                    currentNumber += numbers.get(part);
                    numberPresentBeforeMultiplier = true;
                } else {
                    System.out.print(part + " ");
                }
    
                if (!numberPresentBeforeMultiplier || parts[parts.length - 1].equals(part)) {
                    result += currentNumber;
                    currentNumber = 0;
                }
            }
    
            System.out.println(result);
        }
        scanner.close();
    }
}
    
