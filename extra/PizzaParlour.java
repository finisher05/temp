class CircularQueue {
    private int front, rear, size;
    private int capacity;
    private int[] queue;

    public CircularQueue(int capacity) {
        this.capacity = capacity;
        front = size = 0;
        rear = capacity - 1;
        queue = new int[this.capacity];
    }

    // Check if the queue is full
    boolean isFull() {
        return (size == capacity);
    }

    // Check if the queue is empty
    boolean isEmpty() {
        return (size == 0);
    }

    // Add an order to the queue
    void enqueue(int order) {
        if (isFull()) {
            System.out.println("Queue is full. Cannot place more orders.");
            return;
        }
        rear = (rear + 1) % capacity;
        queue[rear] = order;
        size++;
        System.out.println("Order " + order + " placed.");
    }

    // Serve an order from the queue
    int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty. No orders to serve.");
            return Integer.MIN_VALUE;
        }
        int order = queue[front];
        front = (front + 1) % capacity;
        size--;
        System.out.println("Order " + order + " served.");
        return order;
    }

    // Display the current orders in the queue
    void displayQueue() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return;
        }
        System.out.print("Orders in queue: ");
        for (int i = 0; i < size; i++) {
            System.out.print(queue[(front + i) % capacity] + " ");
        }
        System.out.println();
    }
}

public class PizzaParlour {
    public static void main(String[] args) {
        int maxOrders = 5;
        CircularQueue ordersQueue = new CircularQueue(maxOrders);

        ordersQueue.enqueue(101);
        ordersQueue.enqueue(102);
        ordersQueue.enqueue(103);
        ordersQueue.enqueue(104);
        ordersQueue.enqueue(105);

        ordersQueue.displayQueue();

        ordersQueue.dequeue();
        ordersQueue.dequeue();

        ordersQueue.displayQueue();

        ordersQueue.enqueue(106);
        ordersQueue.enqueue(107);

        ordersQueue.displayQueue();
    }
}
