class Node {
    char data;
    Node next;

    public Node(char data) {
        this.data = data;
        this.next = null;
    }
}

class Stack {
    private Node top;

    public Stack() {
        this.top = null;
    }

    public void push(char data) {
        Node newNode = new Node(data);
        newNode.next = top;
        top = newNode;
    }

    public char pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return '\0';
        }
        char popped = top.data;
        top = top.next;
        return popped;
    }

    public char peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return '\0';
        }
        return top.data;
    }

    public boolean isEmpty() {
        return top == null;
    }
}

public class InfixToPostfix {
    public static int precedence(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }

    public static String infixToPostfix(String exp) {
        String result = "";
        Stack stack = new Stack();

        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);

            // If the character is an operand, add it to output
            if (Character.isLetterOrDigit(c)) {
                result += c;
            }
            // If the character is '(', push it to stack
            else if (c == '(') {
                stack.push(c);
            }
            // If the character is ')', pop and output from the stack 
            // until an '(' is encountered
            else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result += stack.pop();
                }
                stack.pop();
            } 
            else {
                // An operator is encountered
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    result += stack.pop();
                }
                stack.push(c);
            }
        }

        // Pop all the operators from the stack
        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        return result;
    }

    public static void main(String[] args) {
        // Stack operations demonstration
        Stack stackDemo = new Stack();
        stackDemo.push('A');
        stackDemo.push('B');
        stackDemo.push('C');
        System.out.println("Top element after pushes: " + stackDemo.peek());
        System.out.println("Popped element: " + stackDemo.pop());
        System.out.println("Top element after pop: " + stackDemo.peek());

        // Infix to Postfix conversion
        String exp = "a-b*c-d/e+f";
        System.out.println("Infix Expression: " + exp);
        System.out.println("Postfix Expression: " + infixToPostfix(exp));
    }
}
