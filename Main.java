import java.util.Arrays;
public class Main {
    public static void main(String[] args) {
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
        minHeap.dump();
        } catch (Exception e){
            System.out.println();
        }

        }
}

