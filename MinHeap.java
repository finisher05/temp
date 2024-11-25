// Read the marks obtained by students of second year in an online examination of particular subject. 
// Find out the maximum and minimum marks obtained in that subject. Use Min Heap data structure and find the lowest marks.

//A heap is a nearly complete binary tree - A[PARENT(i)] ≤ A[i]
//Complete binary tree is A binary tree T with n levels is Complete if all levels except possibly the last are completely full, and the last level has all its nodes to the left side
//Full binary tree = a binary tree in which each node is either a leaf or has degree exactly 2.
//Arrays are passed by reference

import java.util.Scanner;

class MinHeap
{   
    static void MinHeapify(int[] arr, int i , int n)
    {
        int l = i*2;
        int r = i*2 + 1;
        int smallest = i;

        if(l<n && arr[l]<arr[smallest])
        {
            smallest = l;
        }

        if(r<n && arr[r]<arr[smallest])
        {
            smallest = r;
        }

        if(smallest != i)
        {
            int temp = arr[i];
            arr[i] = arr[smallest];
            arr[smallest] = temp;

            MinHeapify(arr, smallest, n);
        }
    }

    static void BuildHeap(int[] arr) // O(n Log(n))
    {
        int i;
        int n = arr.length;

        for(i=(arr.length/2);i>=1;i--)
        {
            MinHeapify(arr, i, n); //O(Log(n))
        }
    }
    
    void MinSort(int[] arr) // O(n Log(n))
    {
        BuildHeap(arr);

        for(int i = arr.length-1;i>1;i--)
        {
            int temp = arr[i];
            arr[i] = arr[1];
            arr[1] = temp;
            System.out.print(arr[i]+" ");

            MinHeapify(arr, 1, i-1);
        }
        System.out.print(arr[1]);
        System.out.println();
    }

    
    void printArray(int[] arr)
    {
        int i;
        for(i = 1 ; i<arr.length;i++)
        {
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }

    public static void main(String[] args)
    {
        Scanner obj = new Scanner(System.in);
        MinHeap heap = new MinHeap();

        System.out.print("What is the class size: ");
        int n = obj.nextInt();
        n = n+1;

        int[] arr = new int[n];
        for(int i = 1 ; i<n;i++)
        {
            System.out.println("Enter marks of Roll No."+i+" :");
            arr[i]= obj.nextInt();
        }
        obj.close();

        System.out.print("Given heap is: ");
        heap.printArray(arr);

        System.out.print("Min Heap of given heap is:");
        BuildHeap(arr);
        heap.printArray(arr);

        System.out.println("Lowest score of the class is: "+arr[1]);

        System.out.print("The heap in ascending order is:");
        heap.MinSort(arr);
        
    }
}