//Read the marks obtained by students of second year in an online examination of a particular subject. 
//Find out the maximum and minimum marks obtained in that subject. Use Max Heap data structure and find the highest marks.

//A heap is a nearly complete binary tree - A[PARENT(i)] ≥ A[i]
//Complete binary tree is A binary tree T with n levels is Complete if all levels except possibly the last are completely full, and the last level has all its nodes to the left side
//Full binary tree = a binary tree in which each node is either a leaf or has degree exactly 2.
//Arrays are passed by reference

import java.util.Scanner;

class MaxHeap
{
    static void Max_heapify(int arr[], int i , int n)
    {
        int l = 2*i;
        int r = 2*i+1;
        int largest = i;

        if(l<n && arr[l]>arr[largest])
        {
            largest = l;
        }

        if(r<n && arr[r]>arr[largest])
        {
            largest = r;
        }

        if(largest != i)
        {
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;

            Max_heapify(arr, largest, n);
        }
    }
    
    static void build_maxHeap(int [] arr) // O(n Log(n))
    {
        int n = arr.length;
        for(int i = n/2 ; i>=1;i--)
        {
            Max_heapify(arr,i,n); //O(Log(n))
        }
    }


    void heap_sort(int arr[]) // O(n Log(n))
    {
        build_maxHeap(arr);
    
        for(int i = arr.length-1;i>=1;i--)
        {
            int temp = arr[i];
            arr[i] = arr[1];
            arr[1] = temp;
            System.out.print(arr[i]+" ");
            Max_heapify(arr,1,i-1);
        }
        System.out.println();

    }

    void printarray(int[] arr)
    {
        for(int i = 1;i<arr.length;i++)
        {
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }

    public static void main(String[] args) 
    {
        MaxHeap heap = new MaxHeap();

        Scanner sc = new Scanner(System.in);
        System.out.print("What is the class size:");
        int n = sc.nextInt();
        int[] arr = new int[n+1];
        for(int i = 1; i<n+1;i++)
        {
            System.out.println("Enter marks of Roll No."+i);
            arr[i] = sc.nextInt();
        }
        sc.close();
        heap.printarray(arr);

        System.out.println("Max heap of the given data is :");
        build_maxHeap(arr);
        heap.printarray(arr);

        System.out.println("The largest mark obtained is "+arr[1]);
        System.out.println("Marks in descending order is:");
        heap.heap_sort(arr);
        

    }
}