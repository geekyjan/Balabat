import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class PEMDASCalculator {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        System.out.println("PEMDAS Calculator (W/O Exponent)");
        
        while (true) {
            System.out.print("\nEnter an expression: ");
            String expression = scan.nextLine();
            double result = calculate(expression);
            System.out.println("Result: " + result);
        }
    }
    
    public static double calculate(String expression) {
        expression = expression.replaceAll("\\s+", ""); // Remove spaces
        Deque<Double> numbers = new ArrayDeque<>();
        Deque<Character> operators = new ArrayDeque<>();
        int index = 0;

        while (index < expression.length()) {
            char c = expression.charAt(index);

            if (Character.isDigit(c) || (c == '-' && (index == 0 || isOperator(expression.charAt(index - 1)) || expression.charAt(index - 1) == '('))) {
                StringBuilder numBuilder = new StringBuilder();
                while (index < expression.length() && (Character.isDigit(c) || c == '.' || (c == '-' && numBuilder.length() == 0))) {
                    numBuilder.append(c);
                    index++;
                    if (index < expression.length()) {
                        c = expression.charAt(index);
                    }
                }
                numbers.push(Double.parseDouble(numBuilder.toString()));
            } 
            else if (isOperator(c)) {
                while (!operators.isEmpty() && (operators.peek() == '*' || operators.peek() == '/')) {
                    performOperation(numbers, operators);
                }
                operators.push(c);
                index++;
            } 
            else if (c == '(') {
                operators.push(c);
                index++;
            } 
            else if (c == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    performOperation(numbers, operators);
                }
                if (!operators.isEmpty() && operators.peek() == '(') {
                    operators.pop(); // Pop the '('
                }
                index++;
            } 
            else {
                throw new IllegalArgumentException("Invalid character in expression: " + c);
            }
        }

        while (!operators.isEmpty()) {
            performOperation(numbers, operators);
        }

        if (numbers.size() != 1 || !operators.isEmpty()) {
            throw new IllegalArgumentException("Invalid expression");
        }

        return numbers.pop();
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    private static void performOperation(Deque<Double> numbers, Deque<Character> operators) {
        char operator = operators.pop();
        double operand2 = numbers.pop();
        double operand1 = numbers.pop();
        double result = performSingleOperation(operand1, operand2, operator);
        numbers.push(result);
    }

    private static double performSingleOperation(double operand1, double operand2, char operator) {
        switch (operator) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;           
            case '/':
                if (operand2 == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return operand1 / operand2;
            case '*':
                return operand1 * operand2;
            case '^':
                return Math.pow(operand1, operand2);
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
}
