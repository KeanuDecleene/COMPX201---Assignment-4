
import java.io.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

/*
 * A minimum heap testing implementation
 * 
 * @author Keanu De Cleene
 */
public class MinHeapTest{
    private MinHeap heapTest;
    private final PrintStream output = System.out;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    // functions to help testing 

    /*
     * before each test resets the heap and outputstream
     */
    @BeforeEach
    public void setup(){
        heapTest = new MinHeap();
        System.setOut(new PrintStream(outputStream));
    }

    /*
     * builds a heap by inserting rides into the heap
     */
    private void buildHeap(){
        Ride ride1 = new Ride(1, "04:12:23",new String[] {"Keanux", "Jubilee"}, 1, 2);
        Ride ride2 = new Ride(2, "03:12:23", new String[]{"Gerald", "Comet"}, 2, 3);
        Ride ride3 = new Ride(3, "07:12:23", new String[] {"Max", "Jubilee"}, 3, 4);
        Ride ride4 = new Ride(4, "10:12:23", new String[]{"Cooper", "Fair"}, 4, 5);
        Ride ride5 = new Ride(5, "1:12:23", new String[]{"Keanux", "Ronald"}, 5, 6);

        heapTest.insert(ride1);
        heapTest.insert(ride2);
        heapTest.insert(ride3);
        heapTest.insert(ride4);
        heapTest.insert(ride5);
    }

    /*
     * Function for verifying the properties of the heap are correct
     */
    private boolean verifyHeapOrder(){
        for(int i = 0; i < heapTest.k; i++){
            int leftChildIndex = 2 * i + 1;
            int rightChildIndex = 2 * i + 2;
            if (leftChildIndex < heapTest.k && heapTest.rides[i].compareTo(heapTest.rides[leftChildIndex]) > 0) {
                return false;
            }
            if (rightChildIndex < heapTest.k && heapTest.rides[i].compareTo(heapTest.rides[rightChildIndex]) > 0) {
                return false;
            }
        }
        return true;
    }

    //Tests for insert 

    /*
     * Testing to see if ride is inserted correctly
     */
    @Test 
    @DisplayName("Inserting to empty Heap Test")
    public void testInsert(){
        String[] passengers = {"johnny", "mitchell"};
        Ride ride = new Ride(1, "04:12:23",passengers, 1, 2);
        heapTest.insert(ride);
        Assertions.assertEquals(ride, heapTest.peek());
    }

    /*
     * checks inserting with 3 rides that are within 10 minutes of each other, checks if there is only one ride in heap after,
     * checks to see if all passengers are included in the final array
     */
    @Test
    @DisplayName("Inserting with optimisation")
    public void testInsertWithOptimisation(){
        //all within 10 minutes of each other
        Ride ride1 = new Ride(1, "15:30:00", new String[]{"1"}, 324, 586);
        Ride ride2 = new Ride(2, "15:34:00", new String[]{"2"}, 324, 586);
        Ride ride3 = new Ride(3, "15:36:00", new String[]{"3"}, 324, 586);
        heapTest.insert(ride1);
        heapTest.insert(ride2);
        heapTest.insert(ride3);

        Assertions.assertEquals(1, heapTest.k);
        Assertions.assertEquals("1", heapTest.rides[0].passengerNames[0]);
        Assertions.assertEquals("2", heapTest.rides[0].passengerNames[1]);
        Assertions.assertEquals("3", heapTest.rides[0].passengerNames[2]);
        Assertions.assertEquals(1, heapTest.k);
    }

    /*
     * Testing to see if multiple rides are inserted correctly with the minimum time at the beginning of the minHeap and
     * that the minHeap array is maintained correctly whilst inserting with the normal time gaps
     */
    @Test 
    @DisplayName("Entire heap is inserted correctly and heap property is maintained")
    public void testInsertWithOrder(){
        buildHeap();
        Assertions.assertTrue(verifyHeapOrder());
    }

    /*
     * Testing error handling when trying to insert too many Rides
     */
    @Test
    @DisplayName("inserting into full Heap")
    public void testInsertFullHeap(){
        for (int i = 0; i < 21; i++) {
            Ride ride = new Ride(i, i +":12:23", new String[]{"Passenger" + i}, i,i+1);
            heapTest.insert(ride);
        }
        Assertions.assertEquals("All 20 vehicles are being used cannot insert another ride", outputStream.toString().trim());
        Assertions.assertEquals(heapTest.k, 20);
    }

    /*
     * Testing error handling when trying to insert an invalid ride with too many passengers
     */
    @Test
    @DisplayName("inserting an invalid ride")
    public void testInsertInvalidRide(){
        Ride ride5 = new Ride(5, "1:12:23", new String[]{"K", "R", "H", "3", "e", "5", "7"}, 5, 6);
        heapTest.insert(ride5);
        Assertions.assertEquals("Error creating Ride - Too many passengers", outputStream.toString().trim());
        Assertions.assertEquals(heapTest.k, 0);
    }
    //Tests for Remove

    /*
     * Testing to see if the correct ride was removed correctly and that the size of the minHeap is maintained when removing
     */
    @Test
    @DisplayName("Removing correct ride from Heap and size of heap is maintained")
    public void testRemove(){
        buildHeap();
        heapTest.remove(heapTest.rides[4]); // ride 1 from buildheap
        Assertions.assertEquals(4, heapTest.k);
        Assertions.assertNull(heapTest.rides[4]);
    }

    /*
     * checking non existing ride removal doesnt effect the size of the minheap 
     */
    @Test
    @DisplayName("Removing nonexistent ride")
    public void testRemoveNonExistent(){
        buildHeap();
        heapTest.remove(new Ride(88, "04:12:23",new String[] {"Keanux", "Jubilee"}, 1, 2));
        Assertions.assertEquals(5, heapTest.k); //size stays the same
    }

    /*
     * testing removing from an empty heap
     */
    @Test
    @DisplayName("Removing from empty heap")
    public void testRemoveEmpty(){
        heapTest.remove(new Ride(88, "04:12:23",new String[] {"Keanux", "Jubilee"}, 1, 2));
        Assertions.assertEquals(0, heapTest.k); //size stays the same
        
    }

    /*
     * Removing the minimum element from the heap multiple times and verifying the order
     */
    @Test
    @DisplayName("Remove min ride multiple times & verifying heap order properties")
    public void testRemoveMin(){
        buildHeap();
        heapTest.remove(heapTest.peek()); // removes the minimum element from the heap
        Assertions.assertTrue(verifyHeapOrder());
        heapTest.remove(heapTest.peek()); 
        Assertions.assertTrue(verifyHeapOrder());
        heapTest.remove(heapTest.peek()); 
        Assertions.assertTrue(verifyHeapOrder());
        heapTest.remove(heapTest.peek()); 
        Assertions.assertTrue(verifyHeapOrder());
        heapTest.remove(heapTest.peek()); 
        Assertions.assertTrue(verifyHeapOrder());
        Assertions.assertTrue(heapTest.isEmpty()); //empty heap now
    }

    //isEmpty tests

    /*
     * Testing to see if heap isempty is working on empty heap
     */
    @Test
    @DisplayName("Empty Heap")
    public void testIsEmpty(){
        Assertions.assertTrue(heapTest.isEmpty());
    }

    /*
     * Testing to see if heap isempty is working on non-empty heap
     */
    @Test
    @DisplayName("Non Empty Heap")
    public void testIsNotEmpty(){
        buildHeap();
        Assertions.assertFalse(heapTest.isEmpty());
    }


    //Peek tests

    /*
     * Testing to see if the correct ride is returned when peek is called
     */
    @Test
    @DisplayName("Peek returns correct ride when heap is not empty")
    public void testPeek(){
        buildHeap();
        Assertions.assertEquals(heapTest.rides[0], heapTest.peek());
    }

    /*
     * Testing to see if peek returns null when heap is empty
     */
    @Test
    @DisplayName("Peek returns null when heap is empty")
    public void testPeekEmpty(){
        Assertions.assertNull(heapTest.peek());
    }


    //Heapify tests

    /*
     * Testing to see if heapify given rides array returns a valid heapified version
     */
    @Test
    @DisplayName("Heapify function returns a valid MinHeap")
    public void testHeapify(){
        Ride[] giveRides = {new Ride(1, "04:12:23",new String[] {"Keanux", "Jubilee"}, 1, 2),
        new Ride(2, "03:12:23", new String[]{"Gerald", "Comet"}, 2, 3),
        new Ride(3, "07:12:23", new String[] {"Max", "Jubilee"}, 3, 4),
        new Ride(4, "10:12:23", new String[]{"Cooper", "Fair"}, 4, 5),
        new Ride(5, "1:12:23", new String[]{"Keanux", "Ronald"}, 5, 6)};

        heapTest.heapify(giveRides, 5);

        Assertions.assertTrue(verifyHeapOrder());
    }

    /*
     * Testing to see if heapify given identical elements works
     */
    @Test
    @DisplayName("Heapify with identical elements")
    public void testHeapifyIdenticalElements(){
        Ride[] identicalRides = {
            new Ride(1, "04:12:23", new String[]{"Passenger1"}, 1, 2),
            new Ride(2, "04:12:23", new String[]{"Passenger2"}, 2, 3),
            new Ride(3, "04:12:23", new String[]{"Passenger3"}, 3, 4)
        };
        heapTest.heapify(identicalRides, 3);
    
        Assertions.assertTrue(verifyHeapOrder());
    }

    /*
     * Testing heapify with an empty array
     */
    @Test
    @DisplayName("Heapify on empty array")
    public void testHeapifyEmptyArray(){
        Ride[] emptyRides = {};
        heapTest.heapify(emptyRides, 0);
        Assertions.assertTrue(heapTest.isEmpty());
    }

    //Sort tests

    /*
     * Testing to see if sort produces a valid sorted array
     */
    @Test
    @DisplayName("Sort returns a valid sorted array")
    public void testSort(){
        buildHeap();
        heapTest.sort();
        for(int i = 0; i < heapTest.k; i++){
            if(heapTest.rides[i] != null){
                if (heapTest.rides[i+1] !=null){
                    Assertions.assertTrue(heapTest.rides[i].compareTo(heapTest.rides[i+1]) <= 0);
                }
            }
        }
    }

    /*
     * Testing to see if sort works correctly with identical timestamps
     */
    @Test 
    @DisplayName("Sort with identicalTimeStamps")
    public void testSortEmpty(){
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
        heapTest.sort();
        Assertions.assertEquals(heapTest.k, 2);
        for(int i = 0; i < heapTest.k; i++){
            if(heapTest.rides[i] != null){
                if (heapTest.rides[i+1] != null){
                    Assertions.assertTrue(heapTest.rides[i].compareTo(heapTest.rides[i+1]) <= 0);
                }
            }
        }
    }
}


