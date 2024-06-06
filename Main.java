import java.util.Arrays;
public class Main {
    public static void main(String[] args) {
        MinHeap heapTest = new MinHeap();
        String[] ride1 = {"Keanux", "Jubilee"};
        String[] ride2 = {"Gerald", "Comet"};
        String[] ride3 = {"Jumbo", "Chris"};
    
        String[] ride4 = {"Max", "Jubilee"};
        String[] ride5 = {"Keanux", "Ronald"};

        heapTest.insert(new Ride(1, "04:12:23", ride1, 1, 2));
        heapTest.insert(new Ride(2, "03:12:23", ride2, 2, 3));
        heapTest.insert(new Ride(3, "07:12:23",ride3 , 3, 4));
        heapTest.insert(new Ride(4, "10:12:23", ride4, 4, 5));
        heapTest.insert(new Ride(5, "1:12:23", ride5, 5, 6));

        Boolean pass;
        if (heapTest.rides[0].rideID == 5 && heapTest.rides[1].rideID == 2 && 
        heapTest.rides[2].rideID == 3 && heapTest.rides[3].rideID == 4 && 
        heapTest.rides[4].rideID == 1){
            pass = true;
        }
        else {
            pass = false;
        }
        
        heapTest.dump();
        System.out.println(pass);
        System.out.println(heapTest.rides[0].rideID);
        System.out.println(heapTest.rides[1].rideID);

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

