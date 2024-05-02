import java.lang.reflect.Array;
import java.util.NoSuchElementException;
public class GeneralPurposeHeap<T extends Comparable<T>> {
    private T[] heap;
    private int currentElementsAmount = 0;

    public GeneralPurposeHeap() {
        heap = (T[]) new Comparable[10];
    }

    public GeneralPurposeHeap(int capacity) {
        heap = (T[]) new Comparable[capacity];
    }

    public GeneralPurposeHeap(T[] initialData) {
        heap = (T[]) new Comparable[initialData.length+1];
        System.arraycopy(initialData, 0, heap, 1, initialData.length);
        currentElementsAmount = initialData.length;
        for (int i = currentElementsAmount / 2; i >= 0; i--) {
            precDown(i);
        }
    }

    public void insert(T element) {
        if (currentElementsAmount == heap.length) {
            resize();
        }
        heap[currentElementsAmount] = element;
        precUp(currentElementsAmount);
        currentElementsAmount++;
    }
    
    public T findMin() {
        if (currentElementsAmount == 0) {
            throw new NoSuchElementException("Heap is empty.");
        }
        return heap[0];
    }

    public int getSize() {
        return currentElementsAmount;
    }

    public T deleteMin() {
        if (currentElementsAmount == 0) {
            throw new NoSuchElementException("Heap is empty.");
        }
        T min = heap[0];
        heap[0] = heap[--currentElementsAmount];  // Move the last element to the root.
        if (currentElementsAmount > 0) {
            precDown(0);  // Restore the heap property.
        }
        return min;
    }

    public void mergeHeap(GeneralPurposeHeap<T> otherHeap) {
        if (otherHeap.currentElementsAmount == 0) {
            return;
        }

        if (currentElementsAmount + otherHeap.currentElementsAmount >= heap.length) {
            resize();
        }

        System.arraycopy(otherHeap.heap, 1, heap, currentElementsAmount + 1, otherHeap.currentElementsAmount);
        currentElementsAmount += otherHeap.currentElementsAmount;

        for (int i = currentElementsAmount / 2; i >= 1; i--) {
            precDown(i);
        }
    }

    private void precDown(int index) {
        T x = heap[index];
        while (2 * index <= currentElementsAmount) {
            int j = 2 * index; // Left child
            if (j < currentElementsAmount && heap[j + 1].compareTo(heap[j]) < 0) {
                j++; // Right child is smaller
            }
            if (heap[j].compareTo(x) >= 0) {
                break;
            }
            heap[index] = heap[j];
            index = j;
        }
        heap[index] = x;
    }

    private void resize() {
        T[] newHeap = (T[]) new Comparable[heap.length*2];
        System.arraycopy(heap, 0, newHeap, 0, heap.length);
        heap = newHeap;
    }

    private void precUp(int index) {
        T x = heap[index];
        while (index > 1 && heap[index / 2].compareTo(x) > 0) {
            heap[index] = heap[index / 2];
            index /= 2;
        }
        heap[index] = x;
    }
}

