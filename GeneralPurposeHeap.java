import java.lang.reflect.Array;
import java.util.NoSuchElementException;

public class GeneralPurposeHeap<T extends Comparable<T>> {
    private T[] heap;
    private int currentElementsAmount = 0;

    public GeneralPurposeHeap(Class<T> type, int capacity) {
        heap = (T[]) Array.newInstance(type, capacity);
    }

    public GeneralPurposeHeap(T[] initialData) {
        heap = (T[]) Array.newInstance(initialData.getClass().getComponentType(), initialData.length * 2);
        System.arraycopy(initialData, 0, heap, 0, initialData.length);
        currentElementsAmount = initialData.length;
        for (int i = currentElementsAmount / 2 - 1; i >= 0; i--) {
            precDown(i);
        }
    }

    public void insert(T element) {
        if (currentElementsAmount == heap.length) {
            resize();
        }
        heap[currentElementsAmount] = element;
        precUp(currentElementsAmount, element);
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
        if (otherHeap == null) {
            throw new IllegalArgumentException("The other heap must not be null.");
        }
        for (int i = 0; i < otherHeap.currentElementsAmount; i++) {
            this.insert(otherHeap.heap[i]);
        }
    }

    private void precDown(int index) {
        while (index * 2 + 1 < currentElementsAmount) { // While there are children
            int leftChildIndex = index * 2 + 1;
            int rightChildIndex = leftChildIndex + 1;
            int smallestChildIndex = leftChildIndex; // Assume left child is smaller first

            // Check if right child exists and is smaller
            if (rightChildIndex < currentElementsAmount && heap[rightChildIndex].compareTo(heap[leftChildIndex]) < 0) {
                smallestChildIndex = rightChildIndex;
            }

            // If the current index is less than the smallest child, the heap property is violated
            if (heap[smallestChildIndex].compareTo(heap[index]) < 0) {
                T temp = heap[index];
                heap[index] = heap[smallestChildIndex];
                heap[smallestChildIndex] = temp;
                index = smallestChildIndex; // Move to the child's index and continue
            } else {
                // If we didn't swap, the heap property isn't violated and we can stop percolating down
                break;
            }
        }
    }

    private void resize() {
        T[] newHeap = (T[]) Array.newInstance(heap.getClass().getComponentType(), heap.length * 2);
        System.arraycopy(heap, 0, newHeap, 0, heap.length);
        heap = newHeap;
    }

    private void precUp(int index, T newValue) {
        int parentIndex = (index - 1) / 2;
        if (index > 0 && newValue.compareTo(heap[parentIndex]) < 0) { // For min-heap
            heap[index] = heap[parentIndex];
            precUp(parentIndex, newValue);  // Corrected recursive call
        } else {
            heap[index] = newValue;
        }
    }
}
