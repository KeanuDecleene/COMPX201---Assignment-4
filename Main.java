import java.util.Arrays;
public class Main {
    public static void main(String[] args) {
        MinHeap heapTest = new MinHeap();
        String[] passengers = {"johnny", "mitchell"};
        Ride ride = new Ride(1, "04:12:23",passengers, 1, 2);
        heapTest.insert(ride);
        Ride n = heapTest.peek();
        if (n.equals(ride)){
            System.out.println("TRUE");
        }

        try{
        String[] h =  {"alice", "James"};
        String[] b = {"kean", "jksj"," James"};
        String[] j = {"kehn", "jksj", "James"};
        Ride car = new Ride( 4, "09:12:23", h, 2, 4);
        Ride kean = new Ride( 2, "05:14:23", b, 2, 4);
        Ride keanu = new Ride( 3, "02:14:23", j, 2, 4);
        //System.out.println(car);
        //System.out.println(kean);
        MinHeap minHeap = new MinHeap();
        minHeap.insert(car);
        minHeap.insert(kean);
        minHeap.insert(keanu);
        minHeap.insert(car);
       // minHeap.dump();
        } catch (Exception e){
            System.out.println();
        }

        }
}

