import java.util.Arrays;
public class Main {
    public static void main(String[] args) {
        MinHeap heapTest = new MinHeap();
        //Ride ride1 = new Ride(1, "04:12:23",new String[] {"Keanux", "Jubilee"}, 1, 2);
        //Ride ride2 = new Ride(2, "03:12:23", new String[]{"Gerald", "Comet"}, 2, 3);
        //Ride ride3 = new Ride(3, "07:12:23", new String[] {"Max", "Jubilee"}, 3, 4);
        //Ride ride4 = new Ride(4, "10:12:23", new String[]{"Cooper", "Fair"}, 4, 5);
        //Ride ride5 = new Ride(5, "1:12:23", new String[]{"Keanux", "Ronald", "H", "3", "e", "5", "7"}, 5, 6);
        Ride ride1 = new Ride(1, "12:00:00", new String[]{"Passenger1"}, 1, 2);
        Ride ride2 = new Ride(1, "12:00:00", new String[]{"Passenger1"}, 1, 2);
        Ride ride3 = new Ride(1, "12:00:00", new String[]{"Passenger1"}, 1, 2);
        Ride ride4 = new Ride(1, "12:00:00", new String[]{"Passenger1"}, 1, 2);
        Ride ride5 = new Ride(1, "12:00:00", new String[]{"Passenger1"}, 1, 2);
        Ride ride6 = new Ride(1, "12:00:00", new String[]{"Passenger1"}, 1, 2);
        Ride ride7 = new Ride(1, "12:00:00", new String[]{"Passenger1"}, 1, 2);
        Ride ride8 = new Ride(1, "12:00:00", new String[]{"Passenger1"}, 1, 2);
        heapTest.insert(ride1);
        heapTest.insert(ride2);
        heapTest.insert(ride3);
        heapTest.insert(ride4);
        heapTest.insert(ride5);
        heapTest.insert(ride6);
        heapTest.insert(ride7);
        heapTest.insert(ride8);
        heapTest.dump();
        System.out.println(heapTest.k);
        heapTest.sort();
        System.out.println(heapTest.k);
        //heapTest.insert(ride5);

        }
}

