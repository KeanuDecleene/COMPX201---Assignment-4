import java.util.Arrays;
public class Main {
    public static void main(String[] args) {
        MinHeap heapTest = new MinHeap();
        Ride ride1 = new Ride(1, "04:12:23",new String[] {"Keanux", "Jubilee"}, 1, 2);
        Ride ride2 = new Ride(2, "03:12:23", new String[]{"Gerald", "Comet"}, 2, 3);
        Ride ride3 = new Ride(3, "07:12:23", new String[] {"Max", "Jubilee"}, 3, 4);
        Ride ride4 = new Ride(4, "10:12:23", new String[]{"Cooper", "Fair"}, 4, 5);
        Ride ride5 = new Ride(5, "1:12:23", new String[]{"Keanux", "Ronald", "H", "3", "e", "5", "7"}, 5, 6);

        //heapTest.insert(ride1);
        //heapTest.insert(ride2);
        //heapTest.insert(ride3);
        //heapTest.insert(ride4);
        heapTest.insert(ride5);

        Boolean pass;
        if (heapTest.k == 4 && heapTest.rides[4] == null){
            pass = true;
        }
        else {
            pass = false;
        }
        
        heapTest.dump();
        System.out.println(heapTest.k);
        //System.out.println(pass);
        heapTest.sort();
        //heapTest.dump();
        //heapTest.heapify(heapTest.rides, 5);
        //for (int i = 0; i < 21; i++) {
         //   Ride ride = new Ride(i, "02:12:23", new String[]{"Passenger" + i}, i,i+1);
        //    heapTest.insert(ride);
        //}
        heapTest.dump();
        System.out.println(heapTest.k);


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

