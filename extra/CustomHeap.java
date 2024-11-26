public class CustomHeap {
    private int[] heap;
    private int size;
    private int maxSize;
    private boolean isMaxHeap;

    public CustomHeap(int maxSize, boolean isMaxHeap) {
        this.maxSize = maxSize;
        this.size = 0;
        this.heap = new int[this.maxSize + 1];
        this.isMaxHeap = isMaxHeap;
    }

    private int parent(int pos) {
        return pos / 2;
    }

    private int leftChild(int pos) {
        return (2 * pos);
    }

    private int rightChild(int pos) {
        return (2 * pos) + 1;
    }

    private boolean isLeaf(int pos) {
        return pos > (size / 2) && pos <= size;
    }

    private void swap(int fpos, int spos) {
        int tmp;
        tmp = heap[fpos];
        heap[fpos] = heap[spos];
        heap[spos] = tmp;
    }

    private void maxHeapify(int pos) {
        if (isLeaf(pos))
            return;

        if (heap[pos] < heap[leftChild(pos)] || heap[pos] < heap[rightChild(pos)]) {
            if (heap[leftChild(pos)] > heap[rightChild(pos)]) {
                swap(pos, leftChild(pos));
                maxHeapify(leftChild(pos));
            } else {
                swap(pos, rightChild(pos));
                maxHeapify(rightChild(pos));
            }
        }
    }

    private void minHeapify(int pos) {
        if (isLeaf(pos))
            return;

        if (heap[pos] > heap[leftChild(pos)] || heap[pos] > heap[rightChild(pos)]) {
            if (heap[leftChild(pos)] < heap[rightChild(pos)]) {
                swap(pos, leftChild(pos));
                minHeapify(leftChild(pos));
            } else {
                swap(pos, rightChild(pos));
                minHeapify(rightChild(pos));
            }
        }
    }

    public void insert(int element) {
        heap[++size] = element;
        int current = size;

        if (isMaxHeap) {
            while (current > 1 && heap[current] > heap[parent(current)]) {
                swap(current, parent(current));
                current = parent(current);
            }
        } else {
            while (current > 1 && heap[current] < heap[parent(current)]) {
                swap(current, parent(current));
                current = parent(current);
            }
        }
    }

    public int remove() {
        int popped = heap[1];
        heap[1] = heap[size--];
        if (isMaxHeap) {
            maxHeapify(1);
        } else {
            minHeapify(1);
        }
        return popped;
    }

    public static void main(String[] args) {
        int[] marks = {85, 92, 78, 65, 99, 88, 70, 95, 60, 80};

        CustomHeap maxHeap = new CustomHeap(marks.length, true);
        CustomHeap minHeap = new CustomHeap(marks.length, false);

        for (int mark : marks) {
            maxHeap.insert(mark);
            minHeap.insert(mark);
        }

        int maxMarks = maxHeap.remove();
        int minMarks = minHeap.remove();

        System.out.println("Maximum marks obtained: " + maxMarks);
        System.out.println("Minimum marks obtained: " + minMarks);
    }
}
