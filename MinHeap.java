import java.util.Arrays;

public class MinHeap{
    Ride[] rides; //array of rides
    int k; // size of the heap 

    /*
     * initialise minHeap
     * 
     * @param the size of the array
     */
    public MinHeap(int size){
        rides = new Ride[size]; 
        k = 0;
    }

    /*
     * for all the rides in the array print them out to terminal 
     */
    public void dump(){
        for(Ride r : rides){
            if(!r.equals(null)){
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
        return (i-1)/2;
    }

    /*
     * expands and copies the rides array.
     */
    private void expandHeap(){
        rides = Arrays.copyOf(rides, rides.length + 15); //just adds 15 new spaces to the heap space
    }

    /*
     * insert a ride into the minHeap
     * 
     * @param the ride to be inserted
     */
    public void insert(Ride r){
        if (k == rides.length){
            expandHeap();
        }
        rides[k] = r;
        upheap();
        k++;
    }

    /*
     * Swaps the minimum values up the tree to maintain the heap integrity 
     */
    private void upheap(){
        int i = k - 1;
        int parent = getParent(i);
        while (i > 0 && rides[i].compareTo(rides[parent]) < 0){ //while not root and current rides is less than the parent 
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
        if(k==0) return; //if heap is empty
        int index = getIndexRide(r);
        if(index == -1){
            return; //didnt find element of thing to return
        }
        swap(index, k-1);
        k--;
        rides[k] = null;
        downheap(index);

    }

    /*
     * function that performs the downhead on a specified index.
     * 
     * @param the index in the heap to start downheap from
     */
    private void downheap(int i){
        int lChild, rChild, minChild;
        while((lChild = getLeftIndex(i)) < k){ // while we have a left child
            rChild = getRightIndex(i);
            minChild = lChild;

            if(rChild < k && rides[rChild].compareTo(rides[lChild]) < 0){ // find the smaller child
                minChild = rChild;
            }
            if(rides[i].compareTo(rides[minChild]) <= 0){ //if the current ride is less than or equal to smaller child 
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
    private int getLeftIndex(int i){
        return 2 * i+1;
    }

    /*
     * gets the right child of this ride
     * 
     * @param the index of the parent node
     * @return the index of the right child
     */
    private int getRightIndex(int i){
        return 2 * i + 2;

    }

    /*
     * Function to give the index of a specific ride of in the minheap
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
    public boolean isEmpty(){
        if(k == 0) return true; 
        else return false;
        

    }
}