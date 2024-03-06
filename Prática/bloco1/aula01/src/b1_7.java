import java.util.Scanner;

public class b1_7 {
    private static int index = 0;

    public static void main(String[] args) {
        b1_7 m = new b1_7();
        m.ExpressionTree();
    }

    private void ExpressionTree() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the expression in prefix notation: ");
        String expression = scanner.nextLine();
        String[] tokens = expression.split(" ");
        
        if (!isOperator(tokens[0])) {
            System.out.println("Error: Invalid expression. Expression must start with an operator.");
            scanner.close();
            return;
        }
        
        try {
            Node root = createTree(tokens);
            index = 0;
            scanner.close();
            
            System.out.println("Full tree in infix order: ");
            printInfix(root);
            System.out.println();

            System.out.println("The evaluated result is: " + eval(root));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private Node createTree(String[] tokens) throws IllegalArgumentException {
        if (index >= tokens.length) {
            throw new IllegalArgumentException("Error: Not enough tokens provided for a valid prefix expression.");
        }
        
        String token = tokens[index++];
        if (!isOperator(token) && !isNumeric(token)) {
            throw new IllegalArgumentException("Error: Invalid token '" + token + "'");
        }
        
        Node newNode = new Node(token);

        if (isOperator(newNode.value)) {
            newNode.left = createTree(tokens);  
            newNode.right = createTree(tokens);
        }
        return newNode;
    }

    private boolean isOperator(String value) {
        return value.equals("+") || value.equals("-") || value.equals("*") || value.equals("/");
    }
    
    private boolean isNumeric(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void printInfix(Node node) {
        if (node == null) return;
        if (isOperator(node.value)) {
            System.out.print("(");
        }
        printInfix(node.left);
        System.out.print(node.value + " ");
        printInfix(node.right);
        if (isOperator(node.value)) {
            System.out.print(")");
        }
    }
    
    private double eval(Node node) {
        if (node == null) {
            throw new IllegalArgumentException("Error: Empty node encountered during evaluation.");
        }
        if (!isOperator(node.value)) {
            return Double.parseDouble(node.value);
        }
        double left = eval(node.left);
        double right = eval(node.right);
        switch (node.value) {
            case "+": return left + right;
            case "-": return left - right;
            case "*": return left * right;
            case "/": return left / right;
            default: throw new IllegalArgumentException("Error: Invalid operator '" + node.value + "'");
        }
    }
}

class Node {
    String value;
    Node left, right;

    Node(String item) {
        value = item;
        left = right = null;
    }
}
