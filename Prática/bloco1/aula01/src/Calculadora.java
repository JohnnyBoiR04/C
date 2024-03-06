import java.util.Scanner;

public class Calculadora {

    Scanner scanner = new Scanner(System.in);
    double a;
    String operando;
    double b;

    public static void main(String[] args){
        Calculadora calculadora = new Calculadora();
        calculadora.iniciar();
        calculadora.analisarInput();
    }

    public void iniciar(){
        System.out.println("Calculadora a Iniciar... (Enter 'fim' para terminar) (Use ',' for decimal separator)");
        System.out.println("Insira a operação a realizar: ");
    }

    public void fim(){
        System.out.println("Calculadora a Terminar... ");
        scanner.close();
        System.exit(0);
    }

    public void analisarInput() {
        while (scanner.hasNextLine()){
            if (scanner.hasNext("fim")) {
                fim();
                break;
            }
            
            if (scanner.hasNextDouble()) {
                a = scanner.nextDouble(); // Assign to instance variable 'a'
            } else {
                System.out.println("Operando inválido. Por favor, insira um operando inteiro válido.");
                scanner.next(); // Consume invalid input
                continue;
            }

            if (scanner.hasNext("[-+*/]")) {
                operando = scanner.next("[-+*/]"); // Assign to instance variable 'operando'
            } else {
                System.out.println("Operador inválido. Por favor, use um dos operadores '+', '-', '*', '/'.");
                scanner.next(); // Consume invalid input
                continue;
            }

            if (scanner.hasNextDouble()) {
                b = scanner.nextDouble(); // Assign to instance variable 'b'
            } else {
                System.out.println("Operando inválido. Por favor, insira um operando inteiro válido.");
                scanner.next(); // Consume invalid input
                continue;
            }

            double result = calculate(a, operando, b);
            System.out.println("Resultado: " + result);
        }
    }

    public double calculate(double a, String operando, double b) {
        double result = 0;
        switch (operando) {
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
                    result = (double) a / b;
                } else {
                    System.out.println("Não é possível dividir por zero.");
                }
                break;
            default:
                System.out.println("Operador inválido.");
        }
        return result;
    }
}
