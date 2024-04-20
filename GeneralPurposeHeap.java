public class GeneralPurposeHeap<T>{
    private final int jeneralSize = 1000000;
    private T[] arr;
    private int cuurentElementsAmount = 0;

    public GeneralPurposeHeap(Class<T> type, int capacity) {
        this.arr = (T[]) Array.newInstance(type, capacity);
    }

    public GeneralPurposeHeap(int initialCapacity) {
        this.arr = (T[]) new Object[initialCapacity];
    }

    public GeneralPurposeHeap(T[] initialData) {
        arr = (T[]) new Comparable[initialData.length];
        System.arraycopy(initialData, 0, arr, 0, initialData.length);
        currentElementsAmount = initialData.length;
        for (int i = currentElementsAmount / 2 - 1; i >= 0; i--) {
            precDown(i); // Ensuring heap property
        }
        //counter++;
        //this.cuurentElementsAmount = counter;
        //this.arr = initialData;
    }
        //public GeneralPurposeHeap(T[] initialData) {
           // int counter = 0;
           // while (initialData[counter] != null) {
                //counter++;
            //}
            //this.cuurentElementsAmount = counter;
            //this.arr = initialData;
        //}

    public void insert(T element) {
        if (currentElementsAmount == arr.length) {
            resize();
        }
        arr[currentElementsAmount] = element;
        precUp(0, element, this.cuurentElementsAmount, this.arr);
        currentElementsAmount++;
    }

    public T findMin() {
        if (currentElementsAmount == 0) throw new NoSuchElementException("Heap is empty.");
        return arr[0];
    }
    
    public int gitSize () {
        return currentElementsAmount;
    }

    //public T deleteMin() {
        //if (cuurentElementsAmount == 0) {
            //throw new NoSuchElementException("Heap is empty.");
        //}
        //T min = arr[0];
        //arr[0] = arr[--cuurentElementsAmount];
        //heapify(0);
        //return min;
    //}
    public T deleteMin() {
        if (currentElementsAmount == 0) {
            throw new NoSuchElementException("Heap is empty.");
        }
        T min = arr[0];
        arr[0] = arr[--currentElementsAmount];  // Move the last element to the root.
        if (currentElementsAmount > 0) {
            precDown(0);  // Restore the heap property.
        }
        return min;
    }


    public void mergeHeap (GeneralPurposeHeap <T > otherHeap ) {
        int counter = 0;
        while (otherHeap.arr[counter] != null) {
            counter++;
        }
        this.cuurentElementsAmount += counter;
        
    }

    //private void precDown(int index, T newValue, int cuurentElementsAmount, T[] array) {
    private void precDown(int index) {
        int leftChildIndex = 2 * index + 1; // The left child index
        int rightChildIndex = 2 * index + 2; // The right child index
        int smallest = index;  // Start by considering the current index as the smallest

        if (leftChildIndex < currentElementsAmount && arr[leftChildIndex].compareTo(arr[smallest]) < 0) {
            smallest = leftChildIndex;
        }
        if (rightChildIndex < currentElementsAmount && arr[rightChildIndex].compareTo(arr[smallest]) < 0) {
            smallest = rightChildIndex;
        }

        if (smallest != index) {  // If the smallest is not the current index, swap and continue
            T temp = arr[index];
            arr[index] = arr[smallest];
            arr[smallest] = temp;
            precDown(smallest);  // Recursively adjust from the new position
        }
    }

    private void precUp(int index, T newValue, T[] arr) {
        boolean hasCompareToMethod = false;
        if (arr[0] != null){
            var a = arr[0].getClass().getMethods();
            for(int i = 0; i < a.length; i++){
                if(a[i].getName().equals("compareTo")) {
                    hasCompareToMethod = true;
                }
            }
        }

        int indexParrent = index/2;
        if(index == 1){
            arr[1] = newValue;
        } else if(arr) {

        } else {
            arr[index] = arr[indexParrent];
            precUp(indexParrent, newValue, arr);
        }

    }
    //private void resize() {//add
       // T[] newArr = (T[]) new Comparable[arr.length * 2];
       // System.arraycopy(arr, 0, newArr, 0, arr.length);
       // arr = newArr;
    //}

    //private void swap(int i, int j) {//add
     //   T temp = arr[i];
     //   arr[i] = arr[j];
        arr[j] = temp;
    //}

//}
