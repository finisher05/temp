//Implement Stack and all its operations using Linked List.
/*Expression conversion and Evaluation
Graph traversal ( DFS)
Undo button, Back button of browser
Reversing a string */

class Node
{
    int data;
    Node next;

    public Node(int data)
    {
        this.data = data;
        this.next = null;
    }

}

class Stack
{
    private Node top;
    
    public Stack()
    {
        this.top = null;
    }

    public void push(int data)
    {
        Node newnode = new Node(data);
        newnode.next = top;
        top = newnode;
        System.out.println(data+" was pushed into the stack");
        print_stack();
    }

    public void peek()
    {
        System.out.println("The topmost element in the stack is "+top.data);
    }

    public void pop()
    {
        if(is_empty())
        {
            System.out.println("Stack is empty cant pop element");
            return;
        }

        int dta = top.data;
        top = top.next;
        System.out.println(dta+" was poped from the stack.");
        print_stack();
    }

    public boolean is_empty()
    {
        return(top == null);
    }

    public void print_stack()
    {   
        if(is_empty())
        {
            System.out.println("The stack is empty cant print");
            return;
        }

        Node current = top;
        System.out.println("The stack is :");

        while(current !=null)
        {
            System.out.print(current.data+" ");
            current = current.next;
        }
        System.out.println();

    }


    public static void main(String[] args)
    {
        Stack stk = new Stack();
        stk.push(10);
        stk.push(20);
        stk.push(30);
        stk.push(40);
        stk.push(50);

        stk.peek();
        stk.pop();
        stk.pop();
        stk.peek();
        
        stk.push(60);
        stk.peek();


    }
}

