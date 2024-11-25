// Pizza parlour accepting maximum N orders. Order once placed can’t be cancelled. 
// Orders are served in FCFS basis. 
// Write a program to simulate the system using circular QUEUE.

//Once the rear reaches size-1 we can enqueue more elements even if there are empty spaces so we introduced circular queue.
//In this circular queue implementation every where if a front++ or a rear++ occur replace it with a (front+1)%capacity or (rear+1)%capacity.

//Job Scheduling , Graph traversal (BFS),Ticketing system.

class Pizza_parlour
{
    int[] queue;
    int front;
    int rear;
    int capacity;

    public Pizza_parlour(int size)
    {
        queue = new int[size];
        front = -1;
        rear = -1;
        capacity = size;
    }

    
    boolean is_full()
    {
        return((rear+1)%capacity == front);
    }

    boolean is_empty()
    {
        return(front == -1);
    }

    void enqueue(int data)
    {
        if(is_full())
        {
            System.out.println("Queue is full cant enqueue order No."+data);
        }

        else
        {
            if(is_empty())
            {
                front = 0;
                rear = 0;
                queue[rear] = data;
            }
    
            else
            {
                rear = (rear+1)%capacity;
                queue[rear] = data;
            }

            System.out.println("Order No."+data+" has been queued");
       
        }
        
    }

    void dequeue()
    {
        if(is_empty())
        {
            System.out.println("No order's to serve");
            return;
        }

        int data = queue[front];
        
        if(front == rear)
        {
            front = -1;
            rear = -1;    
        }

        else
        {
            front = (front+1)%capacity;
        }
        System.out.println("Order No."+data+" has been served.");

    }

    void print_queue()
    {
        if(is_empty())
        {
            System.out.println("Queue is empty , nothing to print");
            return;
        }

        int current = front;

       System.out.print("Current queue is: ");
       do
       {
          System.out.print(queue[current]+" ");
          current = (current+1)%capacity;
       }

       while(current!= (rear+1)%capacity);
       System.out.println();
    }

    public static void main(String[] args) {
        Pizza_parlour queue = new Pizza_parlour(5);
        queue.print_queue();
        queue.dequeue();
        queue.enqueue(101);
        queue.enqueue(102);
        queue.enqueue(103);
        queue.enqueue(104);
        queue.print_queue();

        queue.enqueue(105);
        queue.enqueue(106);
        queue.dequeue();
        
        queue.print_queue();
        queue.enqueue(107);
        queue.print_queue();
    }
    
}