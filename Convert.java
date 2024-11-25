//For given infix expression eg. a-b*c-d/e+f, convert it into postfix expression using stack and show the operations step by step.
//Implicit stack i.e. build in stack is used.


import java.util.Stack;

class Conversion
{
    int prescedence(char c)
    {
        if(c == '/' || c == '*')
            return 2;
        else if(c == '+' || c == '-')
            return 1;
        else
            return -1;
    }

    void convert(String exp)
    {
        Stack<Character> stack = new Stack<>();
        String result = "";

        int i;
        for(i=0;i<exp.length();i++)
        {
            char c = exp.charAt(i);

            if(Character.isLetterOrDigit(c))
            {
                result = result + c;
            }

            else if(c == '(')
            {
                stack.push(c);
            }

            else if(c == ')')
            {
                while(stack.peek() != '(')
                {
                    result = result + stack.pop();
                }
            }

            else
            {
                while(!stack.isEmpty() && (prescedence(c)<= prescedence(stack.peek())))
                {
                    result = result+ stack.pop();
                }
                
                stack.push(c);
                
            }

        System.out.println(result);

        }

        while(!stack.isEmpty())
        {
            result = result + stack.pop();
        }

        System.out.println(exp +" converted to postfix format is : "+ result);
    }

    public static void main(String[] args)
    {
        Conversion newconv = new Conversion();
        String exp = "a-b*c-d/e+f";
        System.out.println("Postfix of "+exp+" is :- ");
        newconv.convert(exp);
    }

}