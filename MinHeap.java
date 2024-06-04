import java.util.Arrays;
import java.util.Date;
import java.text.SimpleDateFormat;

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

    }


}