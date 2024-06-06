import java.util.Arrays;

public class MinHeap{
    Ride[] rides; //array of rides
    int k; // size of the heap 
    int defaultSize = 20; // default size of the heap as there are 20 vehicles

    /*
     * initialise minHeap
     * 
     * @param the size of the array
     */
    public MinHeap(){
        rides = new Ride[defaultSize]; 
        k = 0;
    }

    /*
     * for all the rides in the array print them out to terminal 
     */
    public void dump(){
        for (Ride r : rides) {
            if (r != null) {
                System.out.println(r.toString());
            }
        }
    }
    
    /*
     * swaps two rides in the heap 
     * 
     * @param firstIndex is the index of the first ride
     * @param secondIndex is the index of the second rule
     */
    private void swap(int firstIndex, int secondIndex){
        Ride temp = rides[firstIndex];
        rides[firstIndex] = rides[secondIndex];
        rides[secondIndex] = temp;
    }

    /*
     * returns the root index for a specific value
     * 
     * @param the index of value we need the parent for 
     * @return the index of the parent
     */
    private int getParent(int i){
        return (i - 1) / 2;
    }

    /*
     * expands and copies the rides array.
     */
    private void expandHeap(){
        System.out.println("Not enough space in minHeap to insert another Ride"); //program is trying to expand the heap but only has maximum 20 rides
    }

    /*
     * insert a ride into the minHeap
     * 
     * @param the ride to be inserted
     */
    public void insert(Ride r){
        if (k == rides.length){
            expandHeap(); //expand the heap
        }
        rides[k] = r;
        upheap();
        k++;
    }

    /*
     * Swaps the minimum values up the tree to maintain the heap integrity 
     */
    private void upheap(){
        int i = k;
        int parent = getParent(i);
        while (i > 0 && rides[i].compareTo(rides[parent]) < 0) { //while not root and current ride is less than the parent 
            swap(i, parent);
            i = parent;
            parent = getParent(i);
        }
    }

    /*
     * remove a specific ride from the minHeap whilst maintaining the heap order integrity
     * 
     * @param the ride to be removed
     */
    public void remove(Ride r){
        if (k == 0) return; //if heap is empty
        int index = getIndexRide(r);
        if (index == -1) {
            return; //didn't find element to remove
        }
        swap(index, k - 1);
        k--;
        rides[k] = null;
        downheap(index);
    }

    /*
     * function that performs the downheap on a specified index.
     * 
     * @param the index in the heap to start downheap from
     */
    private void downheap(int i){
        int lChild, rChild, minChild;
        while ((lChild = getLeftIndex(i)) < k){ // while we have a left child
            rChild = getRightIndex(i);
            minChild = lChild;

            if (rChild < k && rides[rChild].compareTo(rides[lChild]) < 0){ // find the smaller child
                minChild = rChild;
            }
            if (rides[i].compareTo(rides[minChild]) <= 0){ //if the current ride is less than or equal to smaller child 
                break; //stop
            }
            swap(i, minChild); //swap the ride with smaller child
                i = minChild;
        }
    }


    /*
     * gets the left child of this ride
     * 
     * @param the index of the parent node
     * @return the index of the left child
     */
    private int getLeftIndex(int i) {
        return 2 * i + 1;
    }

    /*
     * gets the right child of this ride
     * 
     * @param the index of the parent node
     * @return the index of the right child
     */
    private int getRightIndex(int i) {
        return 2 * i + 2;
    }

    /*
     * Function to give the index of a specific ride in the minheap
     * 
     * @param the ride we need index for 
     * @return the index of the Ride in the heap 
     */
    private int getIndexRide(Ride r){
        for(int i = 0; i < k; i++){
            if(rides[i].equals(r)){
                return i;
            }
        }
        return -1;
    }

    /*
     * returns if there are no passengers waiting.
     * 
     * @return true if there are no passengers waiting, false otherwise
     */
    public boolean isEmpty() {
        return k == 0;
    }

    /*
     * Looks at first ride in the heap without removing
     *  
     * @return the next ride in the heap
     */
    public Ride peek() {
        if (k == 0) return null; // if empty, return null
        return rides[0];
    }

    /*
     * Takes a ride array with a specified number of rides and puts them into heap order
     * 
     * @param rides the array to be heapified 
     */
    public void heapify(Ride[] rides, int rideNum) {
        this.rides = rides; //assigns provided values and updates size
        this.k = rideNum;
        for (int i = k / 2; i >= 0; i--) {
            downheap(i);
        }
    }
    
    /*
     * implements the heap sort algorithm 
     * 
     * @return the sorted Ride array of all items in heap order
     */
    public Ride[] sort() {
        for (int i = k / 2 - 1; i >= 0; i--) { //builds the heap
            downheap(i);
        }
        //Extract from the heap one by one and place in sorted array
        for (int i = k - 1; i > 0; i--) {
            //swap the root with last element
            Ride temp = rides[0];
            rides[0] = rides[i];
            rides[i] = temp;
            //reduce the size of heap and restore heap
            k--;
            downheap(0);
        }
        return rides;
    }
}
