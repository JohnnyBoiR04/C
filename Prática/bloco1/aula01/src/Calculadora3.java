import java.util.Scanner;
import java.util.Stack;

// the objective of this calculator is too read input 
// in post-fix notation, using a stack 
public class Calculadora3 {
    Stack<Double> stack = new Stack<Double>();

    public static void main(String[] args) {
        Calculadora3 calculadora = new Calculadora3();
        calculadora.input();
        calculadora.result();
    }

    private void input() {
        Scanner sc1 = new Scanner(System.in);
        while (sc1.hasNextLine()){
            Scanner sc2 = new Scanner(sc1.nextLine());
            while (sc2.hasNext()){
                calculate(sc2.next());
            }
            sc2.close();
            if (stack.size() == 1) {
                break;
            }
        }
        sc1.close();
    }

    public void calculate(String inputLine) {
        Scanner tokenScanner = new Scanner(inputLine);
        while (tokenScanner.hasNext()) {
            if (tokenScanner.hasNextDouble()) {
                stack.push(tokenScanner.nextDouble());
            } else {
                double operand2 = stack.pop();
                double operand1 = stack.pop();
                switch (tokenScanner.next()) {
                    case "+":
                        stack.push(operand1 + operand2);
                        break;
                    case "-":
                        stack.push(operand1 - operand2);
                        break;
                    case "*":
                        stack.push(operand1 * operand2);
                        break;
                    case "/":
                        stack.push(operand1 / operand2);
                        break;
                    default:
                        // Handle invalid operator
                        System.out.println("Invalid operator.");
                }
            }
        }
        tokenScanner.close();
    }
    
    public void result() {
        if (!stack.isEmpty()) {
            System.out.println("Result: " + stack.pop());
        } else {
            System.out.println("No result to display. Ensure the calculation is done first.");
        }
    }
    
    
}