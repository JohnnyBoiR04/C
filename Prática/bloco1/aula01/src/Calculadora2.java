import java.util.HashMap;
import java.util.Scanner;

public class Calculadora2 {
    private Scanner scanner = new Scanner(System.in);
    private HashMap<String, Double> variables = new HashMap<>();

    public static void main(String[] args) {
        Calculadora2 calculadora = new Calculadora2();
        calculadora.iniciar();
        calculadora.analisarInput();
        calculadora.fim();
    }

    public void iniciar() {
        System.out.println("Calculadora Iniciando... (Digite 'fim' para terminar)");
    }

    public void fim() {
        System.out.println("Calculadora Encerrando...");
        scanner.close();
    }

    public void analisarInput() {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equalsIgnoreCase("fim")) {
                break;
            }
            processInput(line);
        }
    }

    private void processInput(String input) {
        String[] parts = input.split(" ");
        if (parts.length == 5 && parts[1].equals("=")){
            // Assignment: Add new variable or update existing
            String variableName = parts[0];
            String operand1Str = parts[2];
            String operator = parts[3];
            String operand2Str = parts[4];
            
            // Convert operands to Double, checking if they are variables
            Double operand1 = variables.containsKey(operand1Str) ? variables.get(operand1Str) : Double.parseDouble(operand1Str);
            Double operand2 = variables.containsKey(operand2Str) ? variables.get(operand2Str) : Double.parseDouble(operand2Str);
            
            // Utilize performOperation to compute the result
            double result = performOperation(operator, operand1, operand2);
            
            // Assign the result to the variable
            variables.put(variableName, result);
            System.out.println("Variable " + variableName + " set to: " + result);

        } else if (parts.length == 3 && parts[1].equals("=")) {
            // Assignment: Add new variable or update existing
            try {
                double value = Double.parseDouble(parts[2]);
                variables.put(parts[0], value);
                System.out.println("Variable " + parts[0] + " set to: " + value);
            } catch (NumberFormatException e) {
                System.out.println("Invalid value for variable assignment.");
            }
        } else if (parts.length == 3) {
            // Operation: Check if operands are variables or numbers
            String part1 = parts[0];
            String part2 = parts[2];
            Double a = variables.containsKey(part1) ? variables.get(part1) : Double.parseDouble(part1);
            Double b = variables.containsKey(part2) ? variables.get(part2) : Double.parseDouble(part2);
            double result = performOperation(parts[1], a, b);
            System.out.println("Result: " + result);
        } else {
            System.out.println("Invalid input format.");
        }
    }
    
    private double performOperation(String operator, Double a, Double b) {
        double result = 0;
        switch (operator) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                if (b != 0) {
                    result = a / b;
                } else {
                    System.out.println("Division by zero.");
                }
                break;
            default:
                System.out.println("Invalid operator.");
        }

        return result;
    }
    
}
